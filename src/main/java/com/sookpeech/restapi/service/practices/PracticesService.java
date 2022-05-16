package com.sookpeech.restapi.service.practices;

import com.sookpeech.restapi.domain.analysis.Analysis;
import com.sookpeech.restapi.domain.analysis.State;
import com.sookpeech.restapi.domain.analysisContents.AnalysisContents;
import com.sookpeech.restapi.domain.practices.Practices;
import com.sookpeech.restapi.domain.practices.PracticesRepository;
import com.sookpeech.restapi.domain.users.Users;
import com.sookpeech.restapi.domain.users.UsersRepository;
import com.sookpeech.restapi.web.dto.analysisContents.AnalysisContentsUpdateRequestDto;
import com.sookpeech.restapi.web.dto.practices.PracticesResponseDto;
import com.sookpeech.restapi.web.dto.practices.PracticesSaveRequestDto;
import com.sookpeech.restapi.web.dto.practices.PracticesUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PracticesService {
    private final PracticesRepository practicesRepository;

    @Autowired
    UsersRepository usersRepository;

    @Transactional
    public Long save(PracticesSaveRequestDto requestDto){
        Users users = usersRepository.findById(requestDto.getUser_id())
                .orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id="+requestDto.getUser_id()));
        return practicesRepository.save(requestDto.toEntity(users)).getId();
    }

    @Transactional
    public Long update(Long id, PracticesUpdateRequestDto requestDto){
        Practices practices = practicesRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 연습이 없습니다. id="+id));

        practices.update(requestDto.getTitle(), requestDto.getScope());

        return id;
    }

    @Transactional
    public Long changeState(Long id, AnalysisContentsUpdateRequestDto requestDto){
        Practices practices = practicesRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 연습이 없습니다. id="+id));

        // analysisContent update
        Analysis analysis = practices.getAnalysis();
        AnalysisContents analysisContents = analysis.getAnalysisContents();
        analysisContents.update(
                requestDto.getIntegration(),
                requestDto.getMovement(),
                requestDto.getPosture(),
                requestDto.getSpeed(),
                requestDto.getVolume(),
                requestDto.getTone(),
                requestDto.getClosing()
        );

        // analysis state update
        analysis.update(State.COMPLETE);

        return id;
    }

    public PracticesResponseDto findById(Long id){
        Practices entity = practicesRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 연습이 없습니다. id="+id));

        return new PracticesResponseDto(entity);
    }
}
