package com.example.Fashion_News_App.repository;

import com.example.Fashion_News_App.entity.FavoriteNewsEntity;
import com.example.Fashion_News_App.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoriteNewsRepository extends JpaRepository<FavoriteNewsEntity, Long> {

    //すでにお気に入り登録か判定
    boolean existsByUserIdAndNewsId(Long userId, Long newsId);

    //お気に入り登録解除
    void deleteByUserIdAndNewsId(Long userId, Long newsId);

    //ユーザーのお気に入りニュース取得
    @Query("""
            SELECT n
            FROM NewsEntity n
            JOIN FavoriteNewsEntity f
                ON n.id = f.newsId
            WHERE f.userId = :userId
            ORDER BY f.createdAt DESC
            """)
    List<NewsEntity> findFavoriteNewsByUserId(@Param("userId") Long userId);

    //ログインユーザーのお気に入りニュースのnewsIdを取得
    @Query("""
            SELECT f.newsId
            FROM FavoriteNewsEntity f
            WHERE f.userId = :userId
            """)
    List<Long> findFavoritreNewsIds(@Param("userId") Long userId);
}
