package com.sookpeech.restapi.service.feedbacks;

import com.sookpeech.restapi.domain.feedbacks.Feedbacks;
import com.sookpeech.restapi.domain.feedbacks.FeedbacksRepository;
import com.sookpeech.restapi.domain.feedbacks.Initiator;
import com.sookpeech.restapi.domain.practices.Practices;
import com.sookpeech.restapi.domain.practices.PracticesRepository;
import com.sookpeech.restapi.web.dto.feedbacks.FeedbacksResponseDto;
import com.sookpeech.restapi.web.dto.feedbacks.FeedbacksSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FeedbacksService {
    private final FeedbacksRepository feedbacksRepository;

    @Autowired
    PracticesRepository practicesRepository;

    @Transactional
    public Long save(FeedbacksSaveRequestDto requestDto){
        Practices practices = practicesRepository.findById(requestDto.getPractice_id())
                .orElseThrow(()->new IllegalArgumentException("해당 연습이 없습니다. id="+requestDto.getPractice_id()));
        return feedbacksRepository.save(requestDto.toEntity(practices)).getId();
    }

    public FeedbacksResponseDto findById(Long id){
        Feedbacks entity = feedbacksRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 피드백이 없습니다. id="+id));

        return new FeedbacksResponseDto(entity);
    }

    public List<FeedbacksResponseDto> findFriendFeedbacksByPracticeId(Long practice_id){
        Practices practices = practicesRepository.findById(practice_id)
                .orElseThrow(()->new IllegalArgumentException("해당 연습이 없습니다. id="+practice_id));
        List<Feedbacks> feedbacksList = feedbacksRepository.findByInitiatorAndPractices(Initiator.FRIEND, practices);

        return toResponseDto(feedbacksList);
    }

    public List<FeedbacksResponseDto> findUserFeedbacksByPracticeId(Long practice_id){
        Practices practices = practicesRepository.findById(practice_id)
                .orElseThrow(()->new IllegalArgumentException("해당 연습이 없습니다. id="+practice_id));
        List<Feedbacks> feedbacksList = feedbacksRepository.findByInitiatorAndPractices(Initiator.USER, practices);

        return toResponseDto(feedbacksList);
    }

    private List<FeedbacksResponseDto> toResponseDto(List<Feedbacks> list){
        List<FeedbacksResponseDto> responseDtoList = new ArrayList<>();
        for (Feedbacks feedbacks: list){
            responseDtoList.add(new FeedbacksResponseDto(feedbacks));
        }
        return responseDtoList;
    }
}
