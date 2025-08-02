package com.example.n2eServer.repository;

import com.example.n2eServer.entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository extends JpaRepository<EmailEntity,Long> {
    List<EmailEntity> findByTo(String to);
}
