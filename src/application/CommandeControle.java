package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import DBConnection.DBHundler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CommandeControle implements Initializable{
	  @FXML
	    private AnchorPane anchorpaneCommande;

	    @FXML
	    private JFXTextField nomC;

	    @FXML
	    private JFXTextField permesC;

	    @FXML
	    private JFXTextField datedebC;

	    @FXML
	    private JFXTextField voiture;

	    @FXML
	    private JFXButton validerC;
	    
	    private Connection connectionC;
	    private DBHundler handlerC;
	   private PreparedStatement pstC;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		handlerC=new DBHundler();
		
	}
	public void addclients(ActionEvent evc) {
		//String nomdb=nomsingup.getText();
		//String passdb=passwordsingup.getText();
		String insertt= "INSERT INTO clients(nom,permes,datedebut,voitures)"+" VALUES(?,?,?,?)";
		connectionC = handlerC.getConnection();
		
		try {
			pstC=connectionC.prepareStatement(insertt);
			pstC.setString(1,nomC.getText());
			pstC.setString(2,permesC.getText());
			pstC.setString(3,datedebC.getText());
			pstC.setString(4,voiture.getText());
			
			System.out.println("ok");
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			pstC.executeUpdate();
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		System.out.println("okkkk");
		
		
	}
	

}
