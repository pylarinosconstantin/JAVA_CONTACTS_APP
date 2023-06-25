package com.example.contact;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository  extends CrudRepository<User,Integer>{

    //The UserRepository interface extends the CrudRepository interface and specifies the entity class (User) it operates on and the type of the entity's primary key (Integer).
	
  User findByEmailAndPassword(String email, String password);//synartish dimiourgw gia na pairnei 2 parameters username kai password 
}


//The UserRepository interface provides basic CRUD operations inherited from CrudRepository and adds a custom query method to retrieve a user by their email and password. It acts as an abstraction layer that allows you to interact with the User entity and perform database operations using Spring Data's convenient features.
