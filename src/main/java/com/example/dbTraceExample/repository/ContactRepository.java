package com.example.dbTraceExample.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.dbTraceExample.model.Contact;

public interface ContactRepository extends CrudRepository<Contact, Integer>{
    
}
