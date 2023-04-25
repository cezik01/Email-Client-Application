package info;

import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
//Derya Cezik
public class ReceiveInfo {
    
    private SimpleStringProperty sender;
    private SimpleStringProperty subject; 
    private SimpleStringProperty content;
    private Date date;
    private Boolean attach;
    

    private final static ReceiveInfo INSTANCE = new ReceiveInfo();
    
    public ReceiveInfo() {
    }

    public ReceiveInfo(String sender, String subject, Date date, String content) {
        this.sender = new SimpleStringProperty(sender) ;
        this.subject = new SimpleStringProperty(subject) ;
        this.content = new SimpleStringProperty(content) ;
        this.date = date;
        
    }
    
    
    public static ReceiveInfo getInstance() {

        return INSTANCE;
    }

    public String getSender() {
        return sender.get();
    }

    public void setSender(SimpleStringProperty sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject.get();
    }

    public void setSubject(SimpleStringProperty subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content.get();
    }

    public void setContent(SimpleStringProperty content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getAttach() {
        return attach;
    }

    public void setAttach(Boolean attach) {
        this.attach = attach;
    }


  
}
