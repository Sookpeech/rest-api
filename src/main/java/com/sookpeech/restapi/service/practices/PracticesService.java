package com.sookpeech.restapi.service.practices;

import com.sookpeech.restapi.domain.analysis.Analysis;
import com.sookpeech.restapi.domain.analysis.State;
import com.sookpeech.restapi.domain.analysisContents.AnalysisContents;
import com.sookpeech.restapi.domain.posts.Posts;
import com.sookpeech.restapi.domain.posts.PostsRepository;
import com.sookpeech.restapi.domain.practices.Practices;
import com.sookpeech.restapi.domain.practices.PracticesRepository;
import com.sookpeech.restapi.domain.users.Users;
import com.sookpeech.restapi.domain.users.UsersRepository;
import com.sookpeech.restapi.web.dto.analysisContents.AnalysisContentsUpdateRequestDto;
import com.sookpeech.restapi.web.dto.practices.PracticesFindRequestDto;
import com.sookpeech.restapi.web.dto.practices.PracticesResponseDto;
import com.sookpeech.restapi.web.dto.practices.PracticesSaveRequestDto;
import com.sookpeech.restapi.web.dto.practices.PracticesUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PracticesService {
    private final PracticesRepository practicesRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PostsRepository postsRepository;

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

    public List<PracticesResponseDto> findByTitleContaining(Long id, PracticesFindRequestDto requestDto){
        Users user = usersRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        List<Practices> practices = user.getPractices();
        List<PracticesResponseDto> responseDtos = new ArrayList<>();

        for (Practices p: practices){
            if (!p.getTitle().contains(requestDto.getTitle())) continue;
            responseDtos.add(new PracticesResponseDto(p));
        }

        return responseDtos;
    }

    @Transactional
    public Long deleteById(Long id){
        // 해당 practice의 post가 있는지 확인 후 삭제
        Practices practices = practicesRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 연습이 없습니다. id="+id));

        Optional<Posts> post = postsRepository.findByPractices(practices);
        post.ifPresent(posts -> postsRepository.deleteById(posts.getId()));
        practicesRepository.deleteById(id);

        return id;
    }
}
