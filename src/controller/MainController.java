package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.DatabaseModel;
import model.Movie;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class MainController {


    @FXML
    private TableColumn<?, ?> ratingColumn;

    @FXML
    private Button genreButton;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TextField searchField;

    @FXML
    private Button nameButton;

    @FXML
    private Text titleText;

    @FXML
    private Button actorButton;

    @FXML
    private Button ratingButton;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private Button releaseButton;

    @FXML
    private TableColumn<?, ?> genreColumn;

    @FXML
    private TableColumn<?, ?> buttonColumn;

    @FXML
    private TableView<Movie> movieTable;

    DatabaseModel databaseModel = new DatabaseModel();
    @FXML
    void addToTable(ActionEvent event) throws SQLException {
        String name = searchField.getText();
        List movies = databaseModel.getMovieByName(name);
        databaseModel.restartConnect();
        ObservableList data = FXCollections.observableList(movies);
        movieTable.setItems(data);
        nameColumn.setCellValueFactory(new PropertyValueFactory("movieName"));
        genreColumn.setCellValueFactory(new PropertyValueFactory("genre"));
        dateColumn.setCellValueFactory(new PropertyValueFactory("releaseDate"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory("rating"));
        buttonColumn.setCellValueFactory(new PropertyValueFactory("button"));

    }
    ObservableList getTableData(){
        List list = new ArrayList<>();
        list.add(new Movie(0,"Jaws","Horror","2018",10,10));
        ObservableList data = FXCollections.observableList(list);
        return data;
    }

}

