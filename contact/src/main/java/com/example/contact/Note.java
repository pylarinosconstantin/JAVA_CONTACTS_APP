package com.example.contact;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    
    private String lat;
    private String lng;
    private String title;
    private String content;
    private String date;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content=content;
    }

    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date=date;
    }

    public String getLat(){
        return lat;
    }
    public void setLat(String lat){
        this.lat=lat;
    }
    public String getLng(){
        return lng;
    }
    public void setLng(String lng){
        this.lng=lng;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public Integer getPid(){
        return id;
    }
    public void setId(Integer id){
        this.id=id;
    }
    public void setUser(User u) {
        this.contact = u.getContacts().get(0);
    }
    public void setContact(Contact contact) {
        this.contact = contact;
    }
    

    
}


