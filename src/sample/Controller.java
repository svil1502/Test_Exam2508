package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public LoginModel LoginModel = new LoginModel();

    @FXML
    private Label isConnected;

    @FXML
    private TableView<UserDetails> tableUser;
    @FXML
    private TableColumn<UserDetails, String> column_name;

    @FXML
    private Button btnLoad;

    private ObservableList<UserDetails> data;
    private FirebirdConnection dc;


    @Override

    public void initialize(URL location, ResourceBundle resources) {

        if (LoginModel.isDbConnected()) {
            isConnected.setText("Connected");
        } else {
            isConnected.setText("Not Connected");


        }
    }


    @FXML
    private void loadDataFromDatabase(ActionEvent event) {
        try {
            Connection conn = dc.Connector();
            data = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM subject");
            while (rs.next()) {
                //get string from db,whichever way
                data.add(new UserDetails(rs.getString(2)));
            }

        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }

        //Set cell value factory to tableview.
        //NB.PropertyValue Factory must be the same with the one set in model class.

        column_name.setCellValueFactory(new PropertyValueFactory<>("name"));

        tableUser.setItems(null);
        tableUser.setItems(data);

    }



}
