package com.example.Fashion_News_App.repository;

import com.example.Fashion_News_App.entity.NewsViewEntity;
import com.example.Fashion_News_App.entity.NewsMytagMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsMytagRepository extends JpaRepository<NewsMytagMappingEntity, Long> {

    // ログインユーザーのマイタグに紐づくニュース一覧
    @Query("""
        SELECT m.newsViewEntity
        FROM NewsMytagMappingEntity m
        WHERE m.myTagEntity.userId = :userId
        GROUP BY m.newsViewEntity
        ORDER BY MIN(m.myTagEntity.displayOrder) ASC
    """)
    List<NewsViewEntity> findNewsByUserIdOrderByMinTagOrder(@Param("userId") Long userId);

    //特定のニュース × ログインユーザーのマイタグ
    @Query("""
        SELECT m
        FROM NewsMytagMappingEntity m
        WHERE m.newsViewEntity.id = :newsId
          AND m.myTagEntity.userId = :userId
    """)
    List<NewsMytagMappingEntity> findByNewsIdAndUserId(
            @Param("newsId") Long newsId,
            @Param("userId") Long userId
    );
}
