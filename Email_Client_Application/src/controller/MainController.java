package controller;

import info.ReceiveInfo;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
// Derya Cezik  
public class MainController implements Initializable {
    ArrayList<ReceiveInfo> inboxList = new ArrayList<>();

    @FXML
    private TableColumn<ReceiveInfo, Boolean> attachment;
  
    @FXML
    private TableColumn<ReceiveInfo, Date> mailDate;

    @FXML
    private TableColumn<ReceiveInfo, String> subject;

    @FXML
    private TableColumn<ReceiveInfo, String> userName;
    
    @FXML
    private TableView<ReceiveInfo> emailTable;
    
    @FXML
    private TableColumn<ReceiveInfo,String> content;

    @FXML
    private TextArea mailDisplayArea;

    @FXML
    void replyHandler(ActionEvent event) {
         try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Reply.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Email Client App");
            stage.setScene(scene);
            stage.show();
            ReplyController controller = fxmlLoader.getController();
            controller.showInformation(userName.getCellData(0),subject.getCellData(1));
        }catch (Exception ex){
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", ex);
        } 
         
    }
    
    @FXML
    void composeNewHandler(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ComposeNew.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Email Client App");
            stage.setScene(scene);
            stage.show();
        }catch (Exception ex){
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", ex);
        }
        
    }

    @FXML
    void newHostHandler(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Host.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Add New Host");
            stage.setScene(scene);
            stage.show();
        }catch (Exception ex){
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", ex);
        }

    }

    @FXML
    void refreshHandler(ActionEvent event) {
        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");
        Session session = Session.getInstance(props, null);
        
        try{
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", "java.project.advanced@gmail.com", "wjmoamficfepcpkf");
            
            Folder inbox = store.getFolder("Inbox");
            inbox.open(Folder.READ_ONLY);
            
         
           Message[] messages = inbox.getMessages();

            
            for (Message message : messages) {
               
                String sub = message.getSubject();
                String sender = message.getFrom()[0].toString();
                Date date = message.getSentDate();
                String cont = message.getContent().toString();
                ReceiveInfo item = new ReceiveInfo(sender,sub,date,cont);
                inboxList.add(item);
            }
             emailTable.setItems(FXCollections.observableList(inboxList));
             userName.setCellValueFactory(new PropertyValueFactory<>("sender"));
             subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
             mailDate.setCellValueFactory(new PropertyValueFactory<>("date"));
             content.setCellValueFactory(new PropertyValueFactory<>("content") );
             
        }catch (Exception mex){
            mex.printStackTrace();
            
        }
   
    }
    
    @FXML
    public void handle(MouseEvent event) {
        
        int ind = emailTable.getSelectionModel().getSelectedIndex();
        if (ind <= -1)
        {
            return;
        }
        mailDisplayArea.setText(content.getCellData(ind));
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }   
     
}
