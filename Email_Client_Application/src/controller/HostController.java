package controller;

import info.Information;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.RadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
// Elif Su Tuncel
public class HostController implements Initializable {
    
    @FXML
    private TextField hostAddress;

    @FXML
    private TextField password;

    @FXML
    private TextField receivePort;

    @FXML
    private TextField sendPort;

    @FXML
    private TextField toReceive;
    
    @FXML
    private RadioButton pop3Btn;

    @FXML
    private TextField toSend;

    @FXML
    private TextField username;
    
    @FXML
    private RadioButton imapBtn;
    
    @FXML
    private ToggleGroup protocol;
    
    @FXML
    void saveHandler(ActionEvent event) {
        Information info = Information.getInstance();
        protocol = new ToggleGroup();
        pop3Btn = new RadioButton();
        pop3Btn.setToggleGroup(protocol);
        imapBtn = new RadioButton();
        imapBtn.setToggleGroup(protocol);
        
        
        if(pop3Btn.isSelected()){
            info.setReceivePort((Integer.parseInt(receivePort.getText())));
            info.setToReceive(toReceive.getText());
            info.setName("pop3s");
            
                   
           
        } else if(imapBtn.isSelected()){
            info.setReceivePort((Integer.parseInt(receivePort.getText())));
            info.setToReceive(toReceive.getText());
            info.setName("imaps");
            
        }
        info.setHostAddress(hostAddress.getText());
        info.setToSend(toSend.getText());
        info.setSendPort((Integer.parseInt(sendPort.getText())));
        info.setPassword(password.getText());
        
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Main.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Home Page");
            stage.setScene(scene);
            stage.show();
        }catch (Exception ex){
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", ex);
        }
         
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
