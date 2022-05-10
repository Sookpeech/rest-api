package com.sookpeech.restapi.web;

import com.sookpeech.restapi.service.feedbacks.FeedbacksService;
import com.sookpeech.restapi.web.dto.feedbacks.FeedbacksResponseDto;
import com.sookpeech.restapi.web.dto.feedbacks.FeedbacksSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FeedbacksApiController {
    private final FeedbacksService feedbacksService;

    // Create
    @PostMapping("/api/feedbacks")
    public Long save(@RequestBody FeedbacksSaveRequestDto requestDto){
        return feedbacksService.save(requestDto);
    }

    // Read: 특정 피드백 가져오기(id == feedback_id)
    @GetMapping("/api/feedbacks/{id}")
    public FeedbacksResponseDto findById(@PathVariable Long id){
        return feedbacksService.findById(id);
    }

    // Read: 특정 연습에 대한 친구의 피드백 모두 가져오기(id == practice_id)
    @GetMapping("/api/feedbacks/friends/{id}")
    public List<FeedbacksResponseDto> findFriendFeedbacksByPracticeId(@PathVariable Long id){
        return feedbacksService.findFriendFeedbacksByPracticeId(id);
    }

    // Read: 특정 연습에 대한 제3자의 피드백 모두 가져오기(id == practice_id)
    @GetMapping("/api/feedbacks/users/{id}")
    public List<FeedbacksResponseDto> findUserFeedbacksByPracticeId(@PathVariable Long id){
        return feedbacksService.findUserFeedbacksByPracticeId(id);
    }

}
