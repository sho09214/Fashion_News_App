package com.example.Fashion_News_App.service;

import com.example.Fashion_News_App.dto.MytagCreateDto;
import com.example.Fashion_News_App.dto.MytagDisplayOrderUpdateDto;
import com.example.Fashion_News_App.dto.MytagUpdateDto;
import com.example.Fashion_News_App.dto.web.MytagListResponseDto;
import com.example.Fashion_News_App.entity.MytagEntity;
import com.example.Fashion_News_App.mapper.MytagMapper;
import com.example.Fashion_News_App.repository.MytagRepository;
import com.example.Fashion_News_App.repository.NewsMytagRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MytagServiceImpl implements MytagServiceIF{

    private final MytagRepository mytagRepository;
    private final NewsMytagRepository newsMytagRepository;

    //マイタグ一覧取得
    @Override
    public List<MytagListResponseDto> getAllMytags(Long userId) {
        List<MytagListResponseDto> mytagListResponseDto = mytagRepository.findMytagsByUserId(userId);
        if (mytagListResponseDto == null) {
            throw new RuntimeException("マイタグが見つかりません");
        }
        return mytagListResponseDto;
    }

    //マイタグ一覧変更(並び順)
    @Transactional
    @Override
    public void updateDisplayOrder(Long userId, List<MytagDisplayOrderUpdateDto> mytagDisplayOrderUpdateDto) {
        //ログインユーザのマイタグを全件取得
        List<MytagEntity> mytagEntities = mytagRepository.findByUserId(userId);

        //リクエストをMapに変換
        Map<Long, Integer> orderMap =
                mytagDisplayOrderUpdateDto.stream()
                        .collect(Collectors.toMap(
                                MytagDisplayOrderUpdateDto::getId,
                                MytagDisplayOrderUpdateDto::getDisplayOrder
                        ));

        //DisplayOrderを更新
        for (MytagEntity mytagEntity : mytagEntities) {
            Integer newDisplayOrder = orderMap.get(mytagEntity.getId());
            if (newDisplayOrder != null) {
                mytagEntity.setDisplayOrder(newDisplayOrder);
            }
        }
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

        // ★ 強制的にフラッシュ
        mytagRepository.flush();

    }

    //マイタグ削除
    @Override
    public void delete(Long userId, Long mytagId) {
        MytagEntity mytagEntity = mytagRepository
                .findByIdAndUserId(mytagId, userId)
                .orElseThrow(() -> new RuntimeException("マイタグが存在しません"));

        // 関連するマッピングを先に削除
        newsMytagRepository.deleteByMytagIdAndUserId(mytagId, userId);

        mytagRepository.delete(mytagEntity);
    }
}
