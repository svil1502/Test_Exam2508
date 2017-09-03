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
import javafx.scene.input.MouseEvent;

public class Controller implements Initializable {
    public LoginModel LoginModel = new LoginModel();


    @FXML
    private Label isConnected;

    @FXML
    private TableView<UserDetails> tableUser;

    @FXML
    private TableColumn<UserDetails, String> column_id;

    @FXML
    private TableColumn<UserDetails, String> column_name;


    private ObservableList<Otvet> id_v = FXCollections.observableArrayList();//внешний ключ
    private ObservableList<Otvet> id_o = FXCollections.observableArrayList();
    private ObservableList<Otvet> text = FXCollections.observableArrayList();


    @FXML
    private Button btnLoad;

    private ObservableList<UserDetails> data;
    private FirebirdConnection dc;

    // 2 таблица

    @FXML
    private TableView<Otvet> tableOUser;

    @FXML
    private TableColumn<Otvet, String> column_oid;


    @FXML
    private TableColumn<Otvet, String> column_oidname;

    @FXML
    private TableColumn<Otvet, String> column_oname;

    private ObservableList<Otvet> datao;




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
            ResultSet rs = conn.createStatement().executeQuery("SELECT idv, textv  FROM vopros");
            while (rs.next())
            {
                //get string from db,whichever way
                data.add(new UserDetails(rs.getString(1), rs.getString(2)));

            }
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
/*
        try {
            Connection conn = dc.Connector();
            datao = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT id_v, id_o, text FROM otvet");
            while (rs.next())
            {
                //get string from db,whichever way
                datao.add(new Otvet(rs.getString(1), rs.getString(2), rs.getString(3)));

            }
            //conn.close();
        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
*/
        //Set cell value factory to tableview.
        //NB.PropertyValue Factory must be the same with the one set in model class.

        column_id.setCellValueFactory(new PropertyValueFactory<>("textv"));
        column_name.setCellValueFactory(new PropertyValueFactory<>("text"));

        tableUser.setItems(null);
        tableUser.setItems(data);
/*
      column_oid.setCellValueFactory(new PropertyValueFactory<>("id_v")); // id_v
        column_oidname.setCellValueFactory(new PropertyValueFactory<>("id_o")); //id_o
        column_oname.setCellValueFactory(new PropertyValueFactory<>("text"));  //text

        tableOUser.setItems(null);
        tableOUser.setItems(datao);

*/
    }


    // начало


    @FXML
    private void clickCatalogList(MouseEvent event) {

        try {
            System.out.println("id_v"+id_v); // проверка
            UserDetails selGroups = (UserDetails)  tableUser.getSelectionModel().getSelectedItem();
            showListChildrenCatalog(selGroups.gettextv()); //это вызов нужной функции по заполнению и передача параметров в нее


        } catch (Exception e) {

        }

        tableOUser.setItems(id_v); // дб внешний ключ
        System.out.println("id_v"+id_v);//проверка
    }



    private void showListChildrenCatalog(String thCatalog) {

            try {
                System.out.println(thCatalog); // проверка
        Connection conn = FirebirdConnection.Connector();
        datao = FXCollections.observableArrayList();

         ResultSet rs = conn.createStatement().executeQuery("SELECT ID_V, ID_O, TEXT FROM otvet where otvet.ID_V='" + thCatalog + "'");


        //ResultSet rs = conn.createStatement().executeQuery("SELECT ID_V, ID_O, TEXT FROM otvet");

        while (rs.next()) {

            datao.add(new Otvet(rs.getString(1), rs.getString(2), rs.getString(3)));
        }
        conn.close();
    } catch (SQLException ex) {
        System.err.println("Error" + ex);
    }

        column_oid.setCellValueFactory(new PropertyValueFactory<>("id_v")); // id_v
        column_oidname.setCellValueFactory(new PropertyValueFactory<>("id_o")); //id_o
        column_oname.setCellValueFactory(new PropertyValueFactory<>("text"));  //text

        tableOUser.setItems(null);
        tableOUser.setItems(datao);

}


//


}
