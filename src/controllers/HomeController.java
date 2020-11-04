/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;



import DBConnection.DBHundler;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import utils.ConnectionUtil;

/**
 * FXML Controller class
 *
 * @author oXCToo
 */
public class HomeController implements Initializable {

    @FXML
    private TextField txtFirstname;
    @FXML
    private TextField txtLastname;
    @FXML
    private TextField txtEmail;
    @FXML
    private DatePicker txtDOB;
    @FXML
    private Button btnSave;
    @FXML
    private ComboBox<String> txtGender;
    @FXML
    Label lblStatus;

    @FXML
    TableView tblData;

    /**
     * Initializes the controller class.
     */
    private Connection connection2;
    private DBHundler handler2;
    private PreparedStatement pst2;

    public HomeController() {
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	handler2=new DBHundler();
        txtGender.getItems().addAll("Male", "Female", "Other");
        txtGender.getSelectionModel().select("Male");
        fetColumnList();
        fetRowList();

    }

   

    private void clearFields() {
        txtFirstname.clear();
        txtLastname.clear();
        txtEmail.clear();
    }

    private void  addclientss(ActionEvent evccc) {
    	String insertt= "INSERT INTO clients(nom,permes,datedebut,voitures)"+" VALUES(?,?,?,?)";
		connection2 = handler2.getConnection();

        try {
        	pst2=connection2.prepareStatement(insertt);
            
            pst2.setString(1, txtFirstname.getText());
            pst2.setString(2, txtLastname.getText());
            pst2.setString(3, txtEmail.getText());
            pst2.setString(4, txtGender.getValue().toString());
            //pst2.setString(5, txtDOB.getValue().toString());

            pst2.executeUpdate();
            lblStatus.setTextFill(Color.GREEN);
            lblStatus.setText("Added Successfully");

            fetRowList();
            //clear fields
            clearFields();
            

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText(ex.getMessage());
            
        }
    }

    private ObservableList<ObservableList> data;
    String SQL="SELECT * from clients";

    //only fetch columns
    private void fetColumnList() {

        try {
            ResultSet rs = connection2.createStatement().executeQuery(SQL);

            //SQL FOR SELECTING ALL OF CUSTOMER
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1).toUpperCase());
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tblData.getColumns().removeAll(col);
                tblData.getColumns().addAll(col);

                System.out.println("Column [" + i + "] ");

            }

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());

        }
    }

    //fetches rows and data from the list
    private void fetRowList() {
        data = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            rs = connection2.createStatement().executeQuery(SQL);

            while (rs.next()) {
                //Iterate Row
                ObservableList row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }

            tblData.setItems(data);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
