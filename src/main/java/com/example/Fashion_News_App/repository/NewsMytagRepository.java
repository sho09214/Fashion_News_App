package com.example.Fashion_News_App.repository;

import com.example.Fashion_News_App.entity.NewsEntity;
import com.example.Fashion_News_App.entity.NewsMytagMappingEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsMytagRepository extends JpaRepository<NewsMytagMappingEntity, Long> {

    // ログインユーザーのマイタグに紐づくニュース一覧
    @Query("""
        SELECT m.newsEntity
        FROM NewsMytagMappingEntity m
        WHERE m.myTagEntity.userId = :userId
        GROUP BY m.newsEntity
        ORDER BY MIN(m.myTagEntity.displayOrder) ASC
    """)
    List<NewsEntity> findNewsByUserIdOrderByMinTagOrder(@Param("userId") Long userId);

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

    //ログインユーザが持っているマイタグに紐づくニュース一覧
    @Query("""
    SELECT
        n.id,
        n.title,
        n.description,
        n.url,
        n.urlToImage,
        n.sourceName,
        n.summary,
        n.publishedAt,
        t.tagName,
        t.displayOrder
    FROM NewsMytagMappingEntity m
    JOIN m.newsEntity n
    JOIN m.myTagEntity t
    WHERE t.userId = :userId
      AND t.tagName = :tagName
    ORDER BY n.publishedAt DESC
    """)
    List<Object[]> findNewsWithTagsByUserIdAndTagName(
            @Param("userId") Long userId,
            @Param("tagName") String tagName
    );

    @Query("""
        SELECT COUNT(m) > 0
        FROM NewsMytagMappingEntity m
        WHERE m.newsEntity.id = :newsId
          AND m.myTagEntity.id = :mytagId
          AND m.userId = :userId
    """)
    boolean existsByNewsEntityIdAndMyTagEntityIdAndUserId(
            @Param("newsId") Long newsId,
            @Param("mytagId") Long mytagId,
            @Param("userId") Long userId
    );

    @Modifying
    @Transactional
    @Query("""
        DELETE FROM NewsMytagMappingEntity m
        WHERE m.newsEntity.id = :newsId
          AND m.myTagEntity.id = :mytagId
          AND m.userId = :userId
    """)
    void deleteByNewsEntityIdAndMyTagEntityIdAndUserId(
            @Param("newsId") Long newsId,
            @Param("mytagId") Long mytagId,
            @Param("userId") Long userId
    );

    @Modifying
    @Transactional
    @Query("DELETE FROM NewsMytagMappingEntity m WHERE m.myTagEntity.id = :mytagId AND m.userId = :userId")
    void deleteByMytagIdAndUserId(
            @Param("mytagId") Long mytagId,
            @Param("userId") Long userId
    );
}
