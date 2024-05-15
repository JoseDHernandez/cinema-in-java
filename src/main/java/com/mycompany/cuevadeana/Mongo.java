package com.mycompany.cuevadeana;

import Classes.Bill;
import Classes.Movie;
import Classes.Showtime;
import Classes.Theater;
import Classes.User;
import Classes.Window;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.UpdateResult;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class Mongo {

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private String URI = "";
    private String DATABASE_NAME = "";
    //===============Collections=================
    private final String C_BILLS = "bills";
    private final String C_SHOWTIMES = "showtimes";
    private final String C_MOVIES = "movies";
    private final String C_USERS = "users";

    //===========================================
    /**
     * Constructor de la clase Mongo que acepta URI y nombre de la base de datos
     * como parámetros.
     *
     * @param uri URI de conexión a la base de datos.
     * @param name Nombre de la base de datos.
     */
    public Mongo(String uri, String name) {
        URI = uri;
        DATABASE_NAME = name;
        try {
            MongoClientURI clientURI = new MongoClientURI(URI);
            mongoClient = new MongoClient(clientURI);
            mongoDatabase = mongoClient.getDatabase(DATABASE_NAME);
        } catch (MongoException e) {
            Window.Message("danger", "Error to connect to database (" + DATABASE_NAME + "):\n" + e.toString(), "ERROR TO CONNECT");
        }
    }

    /**
     * Obtener cliente de mongo
     *
     * @return cliente
     */
    public MongoClient getClient() {
        if (mongoClient != null) {
            return mongoClient;
        }
        return null;
    }

    /**
     * Cierra la conexión con la base de datos MongoDB.
     */
    public void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

    /**
     * Obtiene una colección de la base de datos.
     *
     * @param collectionName Nombre de la colección.
     * @return La colección especificada.
     */
    private MongoCollection<Document> getCollection(String collectionName) {
        return mongoDatabase.getCollection(collectionName.toLowerCase());
    }

    /**
     * Inserta un usuario en la base de datos.
     *
     * @param user El usuario a insertar.
     */
    public void insert(User user) {
        insertDocument(C_USERS, user.convert());
    }

    /**
     * Inserta una película en la base de datos.
     *
     * @param movie La película a insertar.
     */
    public void insert(Movie movie) {
        insertDocument(C_MOVIES, movie.converter());
    }

    /**
     * Inserta un función con los horarios en la base de datos.
     *
     * @param theater Función/ presentación a insertar.
     */
    public void insert(Theater theater) {
        insertDocument(C_SHOWTIMES, theater.converter());
    }

    /**
     * Registrar una nueva factura en la base de datos y actualizar las sillas
     * disponibles en el showtime
     *
     * @param bill Factura de la funcion
     * @return Estado de la actualizacion
     */
    public boolean insert(Bill bill) {
        //Actualizar sillas
        List<String> seatsSold = bill.getSeats();
        try {
            //Variables de showtime y Id del documento 
            Showtime showtime = bill.getShowtime();
            MongoCursor<Document> shows = getCollection(C_SHOWTIMES).find(new Document("Name", showtime.getTheater()).append("Date", bill.getDateShow())).iterator();
            while (shows.hasNext()) {
                Document doc = shows.next();
                //Convertir los showtimes del documento actual a lista
                List<Document> showtimes = doc.getList("Showtimes", Document.class);
                //Iterar los showtimes de la funcion
                for (int i = 0; i < showtimes.size(); i++) {
                    Document s = showtimes.get(i);
                    //filtro
                    if (s.getString("Title").equals(showtime.getMovie())
                            && s.getString("StartHour").equals(showtime.getStartHour().toString())
                            && s.getString("EndHour").equals(showtime.getEndHour().toString())) {
                        // Encontrar el documento de showtime y actualizar SeatsSold
                        UpdateResult resultOfSeatsSold = getCollection(C_SHOWTIMES).updateOne(
                                new Document("_id", doc.getObjectId("_id")).append("Showtimes", new Document("$elemMatch", s)),
                                new Document("$addToSet", new Document("Showtimes." + i + ".SeatsSold", new Document("$each", seatsSold)))
                        );
                        if (resultOfSeatsSold.wasAcknowledged() && resultOfSeatsSold.getModifiedCount() > 0) {
                            //Registrar factura 
                            insertDocument(C_BILLS, bill.converter());
                            return true;
                        }
                    }
                }
            }
        } catch (MongoException e) {
            Window.Message("warning", "No se registraron las siguientes sillas: " + seatsSold.toString() + "\n"
                    + e.toString(), "Error al actualizar las sillas vendidas");
        }
        return false;
    }

    /**
     * Inserta un documento en la colección especificada.
     *
     * @param collectionName Nombre de la colección.
     * @param document El documento a insertar.
     */
    private void insertDocument(String collectionName, Document document) {
        try {
            MongoCollection<Document> collection = getCollection(collectionName);
            collection.insertOne(document);
            Window.Message("info", "Registered " + collectionName.substring(0, collectionName.length() - 1), "Registered " + collectionName.substring(0, collectionName.length() - 1));
        } catch (MongoException e) {
            Window.Message("danger", "Error while inserting into collection '" + collectionName + "': " + e.toString(), "ERROR WHILE INSERTING");
        }
    }

    /**
     * Busca un usuario en la base de datos.
     *
     * @param pass Contraseña del usuario.
     * @param username Nombre de usuario.
     * @return El documento del usuario encontrado.
     */
    public Document findUser(String pass, String username) {
        Document query = new Document("UserName", username).append("Password", pass);
        return getCollection(C_USERS).find(query).first();
    }

    /**
     * Obtiene un usuario de la base de datos.
     *
     * @param username Nombre de usuario.
     * @return El documento del usuario encontrado.
     */
    public Document getUser(String username) {
        Document query = new Document("UserName", username);
        return getCollection(C_USERS).find(query).first();
    }

    /**
     * Obtiene una lista de usuarios registrados en la base de datos.
     *
     * @return Lista de usuarios.
     */
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try {
            MongoCursor<Document> cursor = getCollection(C_USERS).find().iterator();
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                User user = new User(doc);
                users.add(user);
            }
            cursor.close();
        } catch (Exception e) {
            // Manejar cualquier excepción
        }
        return users;

    }

    /**
     * Funcion para eliminar una pelicula de la coleccion movies
     *
     * @param title Tiulo de la pelicula
     * @param date Fecha de estreno
     * @param duration Minutos de duracion de la pelicula
     * @return Retorna el estado de la operación: {@code True} si elimino de
     * manera exitosa, {@code False} si hubo un error
     */
    public boolean deleteMovie(String title, String date, int duration) {
        try {
            // Eliminar la película que coincida con el título, la fecha y la duración
            getCollection(C_MOVIES).deleteOne(new Document("Title", title)
                    .append("Date", date)
                    .append("Duration", duration));
            Window.Message("info", "La película '" + title + "' fue eliminada.", "Película eliminada");
            return true;
        } catch (MongoException e) {
            Window.Message("danger", "Error al eliminar la película '" + title + "': \n" + e.toString(), "Error al eliminar la pelicula");
            return false;
        }
    }

    /**
     * Busca una factura / boleto registrado
     *
     * @param date Fecha de la venta
     * @param identification Identificacion del cliente
     * @param movieName Nombre de la pelicula
     * @return Devuelve el documento de la factura
     */
    public Document findBill(String date, String identification, String movieName) {
        Document query = new Document();
        query.append("Date", date);
        query.append("Identification", identification);
        query.append("Movie", movieName);

        return getCollection(C_BILLS).find(query).first();
    }

    /**
     * Busca un horario de función en la base de datos.
     *
     * @param name Nombre del horario de función.
     * @param date Fecha del horario de función.
     * @return El documento del horario de función encontrado.
     */
    public Document findTheater(String name, String date) {
        Document query = new Document("Name", name).append("Date", date);
        return getCollection(C_SHOWTIMES).find(query).first();
    }

    /**
     * Obtener una pelicula almacenada en la base de datos
     *
     * @param title Titulo de la pelicula
     * @return Clase movie
     */
    public Movie getMovie(String title) {
        Document doc = getCollection(C_MOVIES).find(new Document("Title", title)).first();
        if (doc != null) {
            return new Movie(doc);
        } else {
            //Error
            throw new RuntimeException("No existe la pelicula con el titulo: " + title);
        }
    }

    /**
     * Metodo para eliminar una pelicula de la base datos
     *
     * @param user Usuario de tipo ADMINISTRADOR
     * @param updatedMovie Objeto {@code Movie} con los nuevos datos
     */
    public void updateMovie(User user, Movie updatedMovie) {
        // Verificar si el usuario tiene permisos para actualizar películas
        if (!user.getRol().equalsIgnoreCase("Administrador")) {
            System.out.println("El usuario no tiene permisos para actualizar películas.");
            return;
        }

        // Crear el filtro para identificar la película a actualizar
        Document filter = new Document("Name", updatedMovie.getTitle())
                .append("Date", updatedMovie.getDate())
                .append("Duration", updatedMovie.getDuration());

        // Convertir la película actualizada a un documento BSON
        Document updateDoc = updatedMovie.converter();

        // Crear la operación de actualización
        Document updateOperation = new Document("$set", updateDoc);

        // Ejecutar la actualización
        try {
            getCollection(C_MOVIES).updateOne(filter, updateOperation);
            Window.Message("info", "Película actualizada con éxito", "Película actualizada");
        } catch (Exception e) {
            System.out.println("Error al actualizar la película: " + e.getMessage());
            Window.Message("error", "Error al actualizar la película: \n" + e.getMessage(), "Error en actualizar pelicula");
        }
    }

    /**
     * Busca películas en la base de datos según diferentes criterios de
     * búsqueda.
     *
     * @param title Título de la película.
     * @param director Director de la película.
     * @param actors Lista de actores de la película.
     * @param classification Clasificación de la película.
     * @param duration Duración de la película.
     * @param date Fecha de la película.
     * @param genres Lista de géneros de la película.
     * @return Lista de películas que coinciden con los criterios de búsqueda.
     */
    public List<Movie> findMovies(String title, String director, List<String> actors,
            String classification, int duration, String date,
            List<String> genres) {
        List<Movie> movies = new ArrayList<>();

        try {
            // Crear filtro para la consulta
            Document filters = new Document();
            if (title != null && !title.isEmpty()) {
                System.out.println("Title: " + title);
                filters.append("Title", title);
            }
            if (director != null && !director.isEmpty()) {
                System.out.println("Director: " + director);
                filters.append("Director", director);
            }
            if (actors != null && !actors.isEmpty()) {
                System.out.println("Actors: " + actors);
                filters.append("Actors", new Document("$in", actors));
            }
            if (classification != null && !classification.isEmpty()) {
                System.out.println("Classification: " + classification);
                filters.append("Classification", classification);
            }
            if (duration >= 30) {
                System.out.println("Duration: " + duration);
                filters.append("Duration", duration);
            }
            if (date != null && !date.isEmpty()) {
                System.out.println("Date: " + date);
                filters.append("Date", date);
            }
            if (genres != null && !genres.isEmpty()) {
                System.out.println("Genres: " + genres);
                filters.append("Genres", new Document("$in", genres));
            }

            // Ejecutar la consulta
            MongoCursor<Document> cursor = getCollection(C_MOVIES).find(filters).iterator();
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Movie movie = new Movie(doc);
                movies.add(movie);
            }
            cursor.close();
        } catch (Exception e) {
            // Manejar cualquier excepción y enviar un mensaje de advertencia
            Window.Message("warning", "Error al buscar películas: " + e.toString(), "Error en buscar películas");
        }
        return movies;
    }

    /**
     * Obtiene los horarios de función de una película en una fecha específica.
     *
     * @param title Título de la película.
     * @param date Fecha de la función.
     * @return Lista de horarios de función.
     */
    public List<Theater> getTheater(String title, String date) {
        List<Theater> theaters = new ArrayList<>();
        try {
            MongoCursor<Document> cursor = getCollection(C_SHOWTIMES).find(and(eq("Date", date), eq("Showtimes.Title", title))).iterator();
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Theater theater = new Theater();
                theater.setName(doc.getString("Name"));
                theater.setDate(doc.getString("Date"));
                // Obtener la lista de showtimes del documento
                List<Document> showtimesDocs = doc.getList("Showtimes", Document.class);
                //Iterar sobre los horarios
                for (Document showtimeDoc : showtimesDocs) {
                    if (showtimeDoc.getString("Title").equals(title)) {
                        Showtime showtime = new Showtime();
                        showtime.setStartHour(LocalTime.parse(showtimeDoc.getString("StartHour")));
                        showtime.setEndHour(LocalTime.parse(showtimeDoc.getString("EndHour")));
                        showtime.setTheater(theater.getName());
                        showtime.setMovie(showtimeDoc.getString("Title"));
                        showtime.setSeatsSold((List<String>) showtimeDoc.get("SeatsSold"));
                        theater.addShowtime(showtime);
                    }
                }
                theaters.add(theater);
            }
            cursor.close();
            return theaters;
        } catch (Exception e) {
            System.out.println(e);
        }
        return theaters;
    }

    /**
     * Obtiene una lista de películas de la base de datos.
     *
     * @param min Índice mínimo de la lista.
     * @param max Índice máximo de la lista.
     * @return Lista de películas.
     */
    public List<Movie> getMovies(int min, int max) {
        //Validar cantidad maxima de documentos
        long totalDocuments = getCollection(C_MOVIES).countDocuments();
        max = (max == 0 || max > totalDocuments) ? (int) totalDocuments : max;
        List<Movie> movies = new ArrayList<>();
        //List of movies
        try (MongoCursor<Document> cursor = getCollection(C_MOVIES).find().skip(min).limit(max).iterator()) {
            //List of movies
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Movie movie = new Movie(doc);
                movies.add(movie);
            }
            cursor.close();
        }
        return movies;
    }

    /**
     * Eliminacion de la base de datos
     *
     * ELIMINA TODA LA BASE DE DATOS
     *
     */
    public void dropDatabase() {
        try {
            mongoClient.dropDatabase(DATABASE_NAME);
        } catch (MongoException e) {
            Window.Message("info", "No se pudo eliminar la base de datos: " + DATABASE_NAME, "Error en la eliminacion de la base de datos");
        }
    }

    /**
     * Crea las colecciones de: Bills,Movies, Showtimes y Users
     *
     * @return Retorna un valor boleano indicando si se crearon los datos o no
     */
    public boolean createDefaultCollections() {
        try {
            mongoDatabase.createCollection(C_BILLS);
            mongoDatabase.createCollection(C_MOVIES);
            mongoDatabase.createCollection(C_SHOWTIMES);
            mongoDatabase.createCollection(C_USERS);
            //Insercion de datos
            User admin = new User();
            admin.setName("Administrador de prueba");
            admin.setRol("Administrador");
            admin.setUserName("Admin");
            admin.setPassword("123");
            admin.setIdentification("111111111111");
            User casher = new User();
            casher.setName("Cajero de prueba");
            casher.setRol("Cajero");
            casher.setUserName("CajeroTest");
            casher.setPassword("123");
            casher.setCashRegister("Caja 1");
            casher.setIdentification("111111111112");
            insert(admin);
            insert(casher);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
