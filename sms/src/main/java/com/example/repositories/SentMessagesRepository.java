package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.SentMessagesEntity;

@Repository
public interface SentMessagesRepository extends JpaRepository<SentMessagesEntity, Long> {

}
