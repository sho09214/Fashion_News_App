package com.example.Fashion_News_App.repository;

import com.example.Fashion_News_App.entity.MytagEntity;
import com.example.Fashion_News_App.entity.NewsEntity;
import com.example.Fashion_News_App.entity.NewsMytagMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsMytagRepository extends JpaRepository<NewsMytagMappingEntity, Long> {

    // ログインユーザーのマイタグに紐づくニュース一覧
    @Query("""
        SELECT DISTINCT m.newsEntity
        FROM NewsMytagMappingEntity m
        WHERE m.userId = :userId
        ORDER BY m.newsEntity.publishedAt DESC
    """)
    List<NewsEntity> findNewsByUserId(@Param("userId") Long userId);

    //特定のニュース × ログインユーザーのマイタグ
    @Query("""
        SELECT m
        FROM NewsMytagMappingEntity m
        WHERE m.newsEntity.id = :newsId
          AND m.myTagEntity.userId = :userId
    """)
    List<NewsMytagMappingEntity> findByNewsIdAndUserId(
            @Param("newsId") Long newsId,
            @Param("userId") Long userId
    );
}
