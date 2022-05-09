package com.sookpeech.restapi.web;

import com.sookpeech.restapi.domain.practices.Practices;
import com.sookpeech.restapi.domain.practices.Scope;
import com.sookpeech.restapi.service.practices.PracticesService;
import com.sookpeech.restapi.web.dto.analysis.AnalysisUpdateRequestDto;
import com.sookpeech.restapi.web.dto.practices.PracticesResponseDto;
import com.sookpeech.restapi.web.dto.practices.PracticesSaveRequestDto;
import com.sookpeech.restapi.web.dto.practices.PracticesUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PracticesApiController {
    private final PracticesService practicesService;

    // Create
    @PostMapping("/api/practices")
    public Long save(@RequestBody PracticesSaveRequestDto requestDto){
        return practicesService.save(requestDto);
    }

    // Update
    @PostMapping("/api/practices/{id}")
    public Long update(@PathVariable Long id, @RequestBody PracticesUpdateRequestDto requestDto){
        return practicesService.update(id, requestDto);
    }

    @PostMapping("/api/practices/analysis_complete/{id}")
    public Long update(@PathVariable Long id, @RequestBody AnalysisUpdateRequestDto requestDto){
        return practicesService.changeState(id, requestDto);
    }

    // Read
    @GetMapping("/api/practices/{id}")
    public PracticesResponseDto findById(@PathVariable Long id){
        return practicesService.findById(id);
    }
}
