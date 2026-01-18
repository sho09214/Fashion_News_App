package com.example.Fashion_News_App.repository;

import com.example.Fashion_News_App.dto.web.MytagListResponseDto;
import com.example.Fashion_News_App.entity.MytagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MytagRepository extends JpaRepository<MytagEntity, Long> {

    List<MytagEntity> findByUserId(Long userId);

    Optional<MytagEntity> findByIdAndUserId(Long id, Long userId);

    @Query("""
            SELECT m.id, m.tagName, m.displayOrder
            FROM MytagEntity m
            WHERE m.userId = :userId
            ORDER BY m.displayOrder ASC
            """)
    List<MytagListResponseDto> findMytagsByUserId(@Param("userId") Long userId);
}
