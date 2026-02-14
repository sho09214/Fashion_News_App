package com.example.Fashion_News_App.repository;

import com.example.Fashion_News_App.entity.NewsViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<NewsViewEntity, Long> {

    Optional<NewsViewEntity> findByHash(String hash);
}
