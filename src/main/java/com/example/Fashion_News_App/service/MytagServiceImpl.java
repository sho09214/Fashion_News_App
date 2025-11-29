package com.example.Fashion_News_App.service;

import com.example.Fashion_News_App.dto.web.MytagResponseDto;
import com.example.Fashion_News_App.mapper.MytagMapper;
import com.example.Fashion_News_App.repository.MytagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MytagServiceImpl implements MytagServiceIF{

    @Autowired
    private MytagRepository mytagRepository;

    @Autowired
    private MytagMapper mytagMapper;

    @Override
    public List<MytagResponseDto> getAllMytags() {
        //Entity → Business Dto → Response Dto
        return mytagRepository.findAll().stream()
                .map(mytagMapper::toBusinessDto)
                .map(mytagMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
