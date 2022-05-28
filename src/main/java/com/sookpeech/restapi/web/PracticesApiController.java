package com.sookpeech.restapi.web;

import com.sookpeech.restapi.service.practices.PracticesService;
import com.sookpeech.restapi.web.dto.analysisContents.AnalysisContentsUpdateRequestDto;
import com.sookpeech.restapi.web.dto.practices.PracticesFindRequestDto;
import com.sookpeech.restapi.web.dto.practices.PracticesResponseDto;
import com.sookpeech.restapi.web.dto.practices.PracticesSaveRequestDto;
import com.sookpeech.restapi.web.dto.practices.PracticesUpdateRequestDto;
import lombok.RequiredArgsConstructor;
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
    public Long changeState(@PathVariable Long id, @RequestBody AnalysisContentsUpdateRequestDto requestDto){
        return practicesService.changeState(id, requestDto);
    }

    // Read
    @GetMapping("/api/practices/{id}")
    public PracticesResponseDto findById(@PathVariable Long id){
        return practicesService.findById(id);
    }

    // title 검색 기능
    @GetMapping("/api/practices/search/{id}")
    public List<PracticesResponseDto> findByTitleContaining(@PathVariable Long id, @RequestBody PracticesFindRequestDto requestDto){
        return practicesService.findByTitleContaining(id, requestDto);
    }

    @DeleteMapping("/api/practices/{id}")
    public Long delete(@PathVariable Long id){
        return practicesService.deleteById(id);
    }
}
