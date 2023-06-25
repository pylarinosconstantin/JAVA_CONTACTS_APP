package com.example.contact;

import org.springframework.data.repository.CrudRepository;

public interface NoteRepository  extends CrudRepository<Note,Integer>{
	Iterable<Note> findById(User u);
    Iterable<Note> findAllByUser(User user);
    Iterable<Note> findAllByContact(Contact contact);
    
}



