package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.ContactsEntity;

@org.springframework.stereotype.Repository
public interface ContactsRepository extends JpaRepository<ContactsEntity, Long>{

}
