package model;

import controller.SQLiteConnection;

import java.sql.*;
import java.time.Duration;
import java.util.ArrayList;

public class DatabaseModel {
    Connection connection = null;

    public DatabaseModel() {
        connection = SQLiteConnection.Connector();
        if( connection == null){
            System.out.println("Connection not successful...");
            System.exit(1);
        }
    }

    public boolean isDatabaseConnected(){
        try{
            return !connection.isClosed();
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList getMovieByName(String name) throws SQLException {
        ArrayList<Movie> movies = new ArrayList<>();
        int id;
        String movieName;
        String genre;
        String releaseDate;
        double rating;
        int duration;
        PreparedStatement statement;
        ResultSet set;
        String query = "SELECT * from Movie where movieName = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, name);
        set = statement.executeQuery();

        while(set.next()){
            id = set.getInt(1);
            genre = set.getString(2);
            movieName = set.getString(3);
            releaseDate = set.getString(4);
            rating = set.getDouble(5);
            duration = set.getInt(6);
            Movie movie = new Movie(id,movieName,genre,releaseDate,rating,duration);
            movies.add(movie);
        }
        statement.close();
        set.close();
        connection.close();
        return movies;
    }
    /*
        Used to restart connection
        Must close and restart connection for each transaction.
     */
    public void restartConnect(){
        connection = SQLiteConnection.Connector();
        if (connection == null){
            System.out.println("Connection not successfull");
            System.exit(1);
        }
    }

}
