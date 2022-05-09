package com.sookpeech.restapi.service.practices;

import com.sookpeech.restapi.domain.analysis.Analysis;
import com.sookpeech.restapi.domain.practices.Practices;
import com.sookpeech.restapi.domain.practices.PracticesRepository;
import com.sookpeech.restapi.web.dto.analysis.AnalysisUpdateRequestDto;
import com.sookpeech.restapi.web.dto.practices.PracticesResponseDto;
import com.sookpeech.restapi.web.dto.practices.PracticesSaveRequestDto;
import com.sookpeech.restapi.web.dto.practices.PracticesUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PracticesService {
    private final PracticesRepository practicesRepository;

    @Transactional
    public Long save(PracticesSaveRequestDto requestDto){
        return practicesRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PracticesUpdateRequestDto requestDto){
        Practices practices = practicesRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 연습이 없습니다. id="+id));

        practices.update(requestDto.getTitle(), requestDto.getScope());

        return id;
    }

    @Transactional
    public Long changeState(Long id, AnalysisUpdateRequestDto requestDto){
        Practices practices = practicesRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 연습이 없습니다. id="+id));

        Analysis analysis = practices.getAnalysis();
        analysis.update(requestDto.getState());

        return id;
    }

    public PracticesResponseDto findById(Long id){
        Practices entity = practicesRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 연습이 없습니다. id="+id));

        return new PracticesResponseDto(entity);
    }
}
