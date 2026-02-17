package com.example.Fashion_News_App.service;

import com.example.Fashion_News_App.dto.business.NewsBusinessDto;
import com.example.Fashion_News_App.dto.web.NewsResponseDto;
import com.example.Fashion_News_App.entity.NewsViewEntity;
import com.example.Fashion_News_App.entity.NewsMytagMappingEntity;
import com.example.Fashion_News_App.mapper.MytagMapper;
import com.example.Fashion_News_App.mapper.NewsMapper;
import com.example.Fashion_News_App.repository.FavoriteNewsRepository;
import com.example.Fashion_News_App.repository.NewsMytagRepository;
import com.example.Fashion_News_App.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsServiceIF {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    private final MytagMapper mytagMapper;
    private final FavoriteNewsRepository favoriteNewsRepository;
    private final NewsMytagRepository newsMytagRepository;

    //ニュース全件取得
    @Override
    public List<NewsResponseDto> getAllNews(Long userId) {

        List<NewsViewEntity> newsViewEntityList = newsRepository.findAll();

        Set<Long> favoriteNewsIds;

        //ログインユーザーのお気に入りニュースのnewsIdを取得
        if (userId != null) {
            favoriteNewsIds = new HashSet<>(favoriteNewsRepository.findFavoritreNewsIds(userId));
        } else {
            favoriteNewsIds = Set.of();
        }

        //Entity → Response DTO
        return newsViewEntityList.stream()
                .map(news -> {
                    NewsBusinessDto businessDto = newsMapper.toBusinessDto(news);
                    NewsResponseDto responseDto = newsMapper.toResponseDto(businessDto);
                    responseDto.setFavorite(
                            userId != null && favoriteNewsIds.contains(news.getId())
                    );
                    return responseDto;
                })
                .toList();

    }

    //ログインユーザーのマイタグに紐づくニュース一覧
    @Override
    public List<NewsResponseDto> getNewsByUserMytags(Long userId, String tagName) {

        //ユーザーが持っているマイタグのニュース取得
        List<Object[]> rows = newsMytagRepository.findNewsWithTagsByUserIdAndTagName(userId, tagName);

        Map<Long, NewsResponseDto> newsMap = new LinkedHashMap<>();

        for (Object[] row : rows) {

            Long newsId = (Long) row[0];

            NewsResponseDto dto = newsMap.get(newsId);

            if (dto == null) {

                dto = new NewsResponseDto();

                dto.setId(newsId);
                dto.setTitle((String) row[1]);
                dto.setDescription((String) row[2]);
                dto.setUrl((String) row[3]);
                dto.setImageUrl((String) row[4]);
                dto.setSourceName((String) row[5]);
                dto.setCategory((String) row[6]);

                if (row[7] != null) {

                    dto.setPublishedAt(
                            ((LocalDateTime) row[7])
                                    .format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"))
                    );

                }

                dto.setMyTags(new ArrayList<>());

                newsMap.put(newsId, dto);

            }

            dto.getMyTags().add((String) row[8]);

        }

        return new ArrayList<>(newsMap.values());
    }
}
