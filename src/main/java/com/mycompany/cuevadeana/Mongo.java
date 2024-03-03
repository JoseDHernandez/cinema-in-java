package com.mycompany.cuevadeana;

import Classes.Movie;
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

    public Document getTheater(String title, String date) {
        Document query = (Document) and(eq("Date", date), eq("Showtimes.Title", title));
        return getCollection("showtimes").find(query).first();
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
            movies = new ArrayList<>();
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
        }
        return movies;
    }

}
