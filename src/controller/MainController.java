package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.DatabaseModel;
import model.Movie;

import java.io.Serializable;
import java.net.URL;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    @FXML
    private TableColumn<?, ?> ratingColumn;

    @FXML
    private Button searchButton;

    @FXML
    private ComboBox<?> searchComboBox;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TextField searchField;

    @FXML
    private Text titleText;

    @FXML
    private TableColumn<?, ?> dateColumn;


    @FXML
    private TableColumn<?, ?> genreColumn;

    @FXML
    private TableColumn<?, ?> buttonColumn;

    @FXML
    private TableView<Movie> movieTable;

    DatabaseModel databaseModel = new DatabaseModel();
    /*
        Initalize Combo Box options
     */
    @FXML
    void addToTable(ActionEvent event) throws SQLException {
        String name = searchField.getText();
        String searchBy = (String) searchComboBox.getValue();
        if(searchBy == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a search criteria");
            alert.setHeaderText("Error");
            alert.showAndWait();
        }else {
            List movies = databaseModel.getMovies(name,searchBy);
            databaseModel.restartConnect();
            ObservableList data = FXCollections.observableList(movies);
            movieTable.setItems(data);
            nameColumn.setCellValueFactory(new PropertyValueFactory("movieName"));
            genreColumn.setCellValueFactory(new PropertyValueFactory("genre"));
            dateColumn.setCellValueFactory(new PropertyValueFactory("releaseDate"));
            ratingColumn.setCellValueFactory(new PropertyValueFactory("rating"));
            buttonColumn.setCellValueFactory(new PropertyValueFactory("button"));
        }

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List list = new ArrayList<String>();
        list.add("Name");
        list.add("Genre");
        list.add("Release Date");
        list.add("Rating");
        list.add("Actor");
        list.add("Director");
        searchComboBox.setItems(FXCollections.observableList(list));
    }
}

