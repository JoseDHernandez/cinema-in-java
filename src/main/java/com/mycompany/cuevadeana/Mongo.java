package com.mycompany.cuevadeana;

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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.Binary;

public class Mongo {

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private final String URI = OptionsData.getInstance().getURI();
    private final String DATABASE_NAME = OptionsData.getInstance().getDBName();
    private DebugWindow window = new DebugWindow();

    public Mongo() {
        try {
            MongoClientURI clientURI = new MongoClientURI(URI);
            mongoClient = new MongoClient(clientURI);
            mongoDatabase = mongoClient.getDatabase(DATABASE_NAME);
        } catch (MongoException e) {
            window.newWindow("danger", "Error to connect to database (" + DATABASE_NAME + "):\n" + e.toString(), "ERROR TO CONNECT");
        }
    }

    public void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

    private MongoCollection<Document> getCollection(String collectionName) {
        return mongoDatabase.getCollection(collectionName);
    }

    // Insertar usuario
    public void insert(User user) {
        insertDocument("users", user.convert());
    }

    // Insertar película
    public void insert(Movie movie) {
        insertDocument("movies", movie.converter());
    }

    //Insertar showtime
    public void insert(Theater theater) {
        insertDocument("showtimes", theater.converter());
    }

    private void insertDocument(String collectionName, Document document) {
        try {
            MongoCollection<Document> collection = getCollection(collectionName);
            collection.insertOne(document);
            window.newWindow("info", "Registered " + collectionName.substring(0, collectionName.length() - 1), "Registered " + collectionName.substring(0, collectionName.length() - 1));
        } catch (MongoException e) {
            window.newWindow("danger", "Error while inserting into collection '" + collectionName + "': " + e.toString(), "ERROR WHILE INSERTING");
        }
    }

    public Document findUser(String pass, String username) {
        Document query = new Document("UserName", username).append("Password", pass);
        return getCollection("users").find(query).first();
    }

    public Document getUser(String username) {
        Document query = new Document("UserName", username);
        return getCollection("users").find(query).first();
    }

    public Document findTheater(String name, String date) {
        Document query = new Document("Name", name).append("Date", date);
        return getCollection("showtimes").find(query).first();
    }

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
                        showtime.setEndHour(LocalTime.parse(showtimeDoc.getString("End Hour")));
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

    // Obtener películas
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
