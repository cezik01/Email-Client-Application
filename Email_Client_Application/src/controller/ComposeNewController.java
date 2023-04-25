package controller;

import info.Information;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
// Elif Su Tuncel
public class ComposeNewController implements Initializable {
    ObservableList<String> hostList= FXCollections.observableArrayList();
    
    @FXML
    private TextField subjectText;

    @FXML
    private TextField toText;

    @FXML
    private TextArea writeText;

    @FXML
    private ComboBox hostBox;
    
    @FXML
    private Button attachBtn;
  
    @FXML
    private Button send;
    
    @FXML
    void sendMailHandler(ActionEvent event) {
        Information info = Information.getInstance();
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp."+info.getHostAddress());
        props.put("mail.smtp.port", Integer.toString(info.getSendPort()));
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        
        Authenticator a = new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(info.getToSend(), info.getPassword());
            }
        };
        
        Session session = Session.getDefaultInstance(props, a);

        try {
            Address toAddress = new InternetAddress(toText.getText());
            MimeMessage msg = new MimeMessage(session);
            msg.setSubject(subjectText.getText());
            msg.setText(writeText.getText());
            msg.setSentDate(new Date());
            msg.setRecipient(Message.RecipientType.TO, toAddress);
            Transport.send(msg);

        } catch (MessagingException mex) {
            System.out.println("Send failed, exception: " + mex);
        }
        
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Information info = Information.getInstance();
        hostList.add(info.getHostAddress());
        hostBox.setItems(hostList);  
    }    
    
}
