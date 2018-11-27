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

    public ArrayList getMovies(String name, String searchBy) throws SQLException {
        String searchCriteria = null;
        ArrayList movieIds = new ArrayList();
        int actorFlag = 0;
        int directorFlag = 0;
        //Setup switch case to convert searchBy to SQL variable name
        switch(searchBy){
            case "Name": searchCriteria = "movieName";
                break;
            case "Genre": searchCriteria = "genre";
                break;
            case "Release Date": searchCriteria = "releaseDate";
                break;
            case "Rating": searchCriteria = "rating";
                break;
            case "Actor": actorFlag = 1;
                break;
            case "Director": directorFlag = 1;
                break;
        }
        ArrayList<Movie> movies = new ArrayList<>();
        int id;
        String movieName;
        String genre;
        String releaseDate;
        double rating;
        int duration;
        PreparedStatement statement;
        ResultSet set;
        String query = null;
        if(actorFlag == 1){
            //First get actor ID, then go through castee table to get all the movie ID's, then search by movie ID
            String[] nameArray = name.split(" ");
            String firstName = nameArray[0];
            String lastName = nameArray[1];

            query = "SELECT ID from person where firstName = ? and lastName = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1,firstName);
            statement.setString(2,lastName);
            set = statement.executeQuery();
            //Should only be 1 person since ID is unique.
            int personId = -1;
            while(set.next()) {
                personId = set.getInt(1);
            }
            query = "SELECT movieID from casted where casteeID = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1,personId);
            set = statement.executeQuery();
            while(set.next()) {
                movieIds.add(set.getInt(1));
            }
            searchCriteria = "movieID";


        }else if(directorFlag == 1){

        }

        query = "SELECT * from Movie where " + searchCriteria + " = ?";
        statement = connection.prepareStatement(query);
        if(actorFlag == 1){
            if (movieIds.size() == 0){
                return movies; //Return empty list
            }
            query = "SELECT * from Movie where movieID = ?";
            for(int i = 1; i < movieIds.size(); i++){
                query = query.concat(" OR movieID = ?");
            }
            statement = connection.prepareStatement(query);
            for(int i = 0; i < movieIds.size(); i++) {
                statement.setInt(i + 1, (Integer) movieIds.get(i));
            }
        }else {
            statement.setString(1, name);
        }
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
            System.out.println("Connection not successful");
            System.exit(1);
        }
    }

}
