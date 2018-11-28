package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.Movie;

public class MoreInfoController {

    @FXML
    private Text directorsText;

    @FXML
    private Text durationText;

    @FXML
    private Text producersText;

    @FXML
    private Text castText;

    @FXML
    private Text releasedText;

    @FXML
    private Text ratingText;

    @FXML
    private Text nameText;

    @FXML
    private Text genreText;

    @FXML
    private Text distributorsText;

    void setAllText(Movie movie) {
        nameText.setText(movie.getMovieName());
        releasedText.setText(releasedText.getText().concat(" " + movie.getReleaseDate()));
        genreText.setText(genreText.getText().concat(" " + movie.getGenre()));
        ratingText.setText(ratingText.getText().concat(" " + movie.getRating()));
        int hours;
        int minutes;
        hours = movie.getDuration() / 60;
        minutes = movie.getDuration() % 60;
        String hour;
        if(hours == 1){
            hour = "hour";
        }else{
            hour = "hours";
        }
        durationText.setText(durationText.getText().concat(" " +hours + " "+ hour + " " + minutes + " minutes"));

    }

}
