package com.example.controller;

import com.example.entity.Answer;
import com.example.entity.Example;
import com.example.payload.AnswerDto;
import com.example.payload.ApiResponse;
import com.example.payload.ExampleDto;
import com.example.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {
    @Autowired
    AnswerService answerService;

    @PostMapping
    public ResponseEntity<?> addAnswer(@Valid @RequestBody AnswerDto answerDto){
        ApiResponse apiResponse = answerService.addAnswer(answerDto);
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping
    public ResponseEntity<?> getAnswerList(){
        List<Answer> answerList = answerService.getAnswerList();
        return ResponseEntity.ok(answerList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getAnswer(@PathVariable Integer id){
        Answer answer = answerService.getAnswer(id);
        return ResponseEntity.ok(answer);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable Integer id){
        ApiResponse apiResponse = answerService.deleteAnswer(id);
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editAnswer(@Valid @PathVariable Integer id,@RequestBody AnswerDto answerDto){
        ApiResponse apiResponse = answerService.editAnswer(id,answerDto);
        return ResponseEntity.ok(apiResponse);
    }
}
