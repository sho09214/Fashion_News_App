package com.example.Fashion_News_App.repository;

import com.example.Fashion_News_App.entity.NewsRawEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRawRepository extends JpaRepository<NewsRawEntity, Long> {

    boolean existsByHash(String hash);

}
