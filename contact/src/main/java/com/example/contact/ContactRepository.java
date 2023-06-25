package com.example.contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository  extends CrudRepository<Contact,Integer>{
	
	Iterable<Contact> findAllByUsr(User u);
    Iterable<Contact> findById(User u);
   // User findByEmailAndPassword(String email, String password);
    //synartish dimiourgw gia na pairnei 2 parameters username kai password 
}



