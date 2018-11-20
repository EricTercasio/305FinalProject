package model;

import javafx.scene.control.Button;

import java.time.Duration;

public class Movie {
    private int id;
    private String movieName;
    private String genre;
    private String releaseDate;
    private double rating;
    private Duration duration;
    private Button button = new Button("More info");

    public Movie(int id, String movieName, String genre, String releaseDate, double rating, Duration duration) {
        this.id = id;
        this.movieName = movieName;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.duration = duration;
    }
    public Button getButton(){
        return button;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}
