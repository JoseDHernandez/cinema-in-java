package com.mycompany.cuevadeana;

import Classes.Bill;
import Classes.Movie;
import Classes.Showtime;
import Classes.Theater;
import Classes.User;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import Templates.DebugWindow;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.UpdateResult;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.Binary;
import org.bson.types.ObjectId;

public class Mongo {

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private String URI = "";
    private String DATABASE_NAME = "";

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
            DebugWindow.Message("danger", "Error to connect to database (" + DATABASE_NAME + "):\n" + e.toString(), "ERROR TO CONNECT");
        }
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
        return mongoDatabase.getCollection(collectionName);
    }

    /**
     * Inserta un usuario en la base de datos.
     *
     * @param user El usuario a insertar.
     */
    public void insert(User user) {
        insertDocument("users", user.convert());
    }

    /**
     * Inserta una película en la base de datos.
     *
     * @param movie La película a insertar.
     */
    public void insert(Movie movie) {
        insertDocument("movies", movie.converter());
    }

    /**
     * Inserta un función con los horarios en la base de datos.
     *
     * @param theater Función/ presentación a insertar.
     */
    public void insert(Theater theater) {
        insertDocument("showtimes", theater.converter());
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
            MongoCursor<Document> shows = getCollection("showtimes").find(new Document("Name", showtime.getTheater()).append("Date", bill.getDateShow())).iterator();
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
                        UpdateResult resultOfSeatsSold = getCollection("showtimes").updateOne(
                                new Document("_id", doc.getObjectId("_id")).append("Showtimes", new Document("$elemMatch", s)),
                                new Document("$addToSet", new Document("Showtimes." + i + ".SeatsSold", new Document("$each", seatsSold)))
                        );
                        if (resultOfSeatsSold.wasAcknowledged() && resultOfSeatsSold.getModifiedCount() > 0) {
                            //Registrar factura 
                            insertDocument("bills", bill.converter());
                            return true;
                        }
                    }
                }
            }
        } catch (MongoException e) {
            DebugWindow.Message("warning", "No se registraron las siguientes sillas: " + seatsSold.toString() + "\n"
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
            DebugWindow.Message("info", "Registered " + collectionName.substring(0, collectionName.length() - 1), "Registered " + collectionName.substring(0, collectionName.length() - 1));
        } catch (MongoException e) {
            DebugWindow.Message("danger", "Error while inserting into collection '" + collectionName + "': " + e.toString(), "ERROR WHILE INSERTING");
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
        return getCollection("users").find(query).first();
    }

    /**
     * Obtiene un usuario de la base de datos.
     *
     * @param username Nombre de usuario.
     * @return El documento del usuario encontrado.
     */
    public Document getUser(String username) {
        Document query = new Document("UserName", username);
        return getCollection("users").find(query).first();
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
        return getCollection("showtimes").find(query).first();
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
            MongoCursor<Document> cursor = getCollection("showtimes").find(and(eq("Date", date), eq("Showtimes.Title", title))).iterator();
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
        long totalDocuments = getCollection("movies").countDocuments();
        max = (max == 0 || max > totalDocuments) ? (int) totalDocuments : max;
        List<Movie> movies = new ArrayList<>();
        //List of movies
        try (MongoCursor<Document> cursor = getCollection("movies").find().skip(min).limit(max).iterator()) {
            //List of movies
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Movie movie = new Movie();
                movie.setTitle(doc.getString("Title"));
                movie.setClassification(doc.getString("Classification"));
                movie.setDate(doc.getString("Date"));
                movie.setDescription(doc.getString("Description"));
                movie.setDirector(doc.getString("Director"));
                movie.setDuration(doc.getInteger("Duration"));
                movie.setPoster((Binary) doc.get("Poster"));
                if (doc.containsKey("Actors")) {
                    movie.setActors((List<String>) doc.get("Actors"));
                }
                if (doc.containsKey("Genres")) {
                    movie.setGenres((List<String>) doc.get("Genres"));
                }
                movies.add(movie);
            }
            cursor.close();
        }
        return movies;
    }

}
