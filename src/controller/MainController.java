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
import model.Movie;

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

    @FXML
    void addToTable(ActionEvent event) {
        movieTable.setItems(getTableData());
        nameColumn.setCellValueFactory(new PropertyValueFactory("movieName"));
        buttonColumn.setCellValueFactory(new PropertyValueFactory("button"));
    }
    ObservableList getTableData(){
        List list = new ArrayList<>();
        list.add(new Movie(0,"Jaws","Horror","2018",10,null));
        ObservableList data = FXCollections.observableList(list);
        return data;
    }

}

