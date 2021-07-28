/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package populatecombobox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author nickb
 */
public class PopulateComboBox extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       GridPane gridPane=new GridPane();
        Text txt=new Text("Name");   
        TextField tf=new TextField();
        Text txt1=new Text();
        
        Button btn= new Button("Save");
        ComboBox names = new ComboBox();
        ObservableList<String> names_list= FXCollections.observableArrayList();
        try
        {
            //Step One
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //Step Two
            Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/exprobe_networks?autoReconnect=true&useSSL=false","root","");
            
            //Step Three
            Statement st=con.createStatement();
            
            //Step Four
                String query = "SELECT name FROM exprobe_networks.customers";
            ResultSet rs = st.executeQuery(query);            
            
            while(rs.next())
            {                
                 System.out.println(rs.getString("name"));
                 names_list.add(rs.getString("name")); 
            }           
            
            names.setItems(names_list);
            
            //Step five
            con.close();
          }
        
        catch(Exception ee){System.out.println(ee);System.out.println("Connection error");} 
        names.getItems().addAll("");
      
        gridPane.setMinSize(600,400);
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);


        gridPane.add(txt1, 0, 0);
        gridPane.add(txt, 0, 1);
        
        gridPane.add(names,1,1);
        gridPane.add(tf, 2, 1);
        gridPane.add(btn, 0, 4);
        primaryStage.setTitle("Group B _ Save Employee Data Form");
         Scene scene=new Scene (gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
   
