package com.sookpeech.restapi.domain.feedbacks;

import com.sookpeech.restapi.domain.practices.Practices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbacksRepository extends JpaRepository<Feedbacks, Long> {
    List<Feedbacks> findByInitiatorAndPractices(Initiator initiator, Practices practices);
}
