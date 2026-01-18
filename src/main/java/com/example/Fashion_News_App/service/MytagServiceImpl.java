package com.example.Fashion_News_App.service;

import com.example.Fashion_News_App.dto.MytagCreateDto;
import com.example.Fashion_News_App.dto.MytagUpdateDto;
import com.example.Fashion_News_App.dto.business.MytagBusinessDto;
import com.example.Fashion_News_App.dto.web.MytagResponseDto;
import com.example.Fashion_News_App.entity.MytagEntity;
import com.example.Fashion_News_App.mapper.MytagMapper;
import com.example.Fashion_News_App.repository.MytagRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MytagServiceImpl implements MytagServiceIF{

    private final MytagRepository mytagRepository;
    private final MytagMapper mytagMapper;

    //マイタグ一覧取得
    @Override
    public List<MytagResponseDto> getAllMytags() {
        //Entity → Business Dto → Response Dto
        return mytagRepository.findAll().stream()
                .map(mytagMapper::toBusinessDto)
                .map(mytagMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    //マイタグ追加
    @Override
    public void create(Long userId, MytagCreateDto mytagCreateDto) {
        MytagEntity mytagEntity = new MytagEntity();
        mytagEntity.setUserId(userId);
        mytagEntity.setTagName(mytagCreateDto.getTagName());
        mytagEntity.setColor(mytagCreateDto.getColor());
        mytagEntity.setDisplayOrder(mytagCreateDto.getDisplayOrder());
        mytagEntity.setCreatedAt(LocalDateTime.now());
        mytagEntity.setUpdatedAt(LocalDateTime.now());

        mytagRepository.save(mytagEntity);
    }

    //マイタグ変更
    @Transactional
    @Override
    public void update(Long userId, MytagUpdateDto mytagUpdateDto) {
        MytagEntity mytagEntity = mytagRepository
                .findByIdAndUserId(mytagUpdateDto.getId(), userId)
                .orElseThrow(() -> new RuntimeException("マイタグが見つかりません"));

        mytagEntity.setTagName(mytagUpdateDto.getTagName());
        mytagEntity.setColor(mytagUpdateDto.getColor());
        mytagEntity.setUpdatedAt(LocalDateTime.now());

    }

    //マイタグ削除
    @Override
    public void delete(Long userId, Long mytagId) {
        MytagEntity mytagEntity = mytagRepository
                .findByIdAndUserId(mytagId, userId)
                .orElseThrow(() -> new RuntimeException("マイタグが存在しません"));

        mytagRepository.delete(mytagEntity);
    }
}
