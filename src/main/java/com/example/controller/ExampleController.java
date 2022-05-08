package com.example.controller;

import com.example.entity.Example;
import com.example.entity.Language;
import com.example.payload.ApiResponse;
import com.example.payload.ExampleDto;
import com.example.payload.LanguageDto;
import com.example.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/example")
public class ExampleController {
    @Autowired
    ExampleService exampleService;

    @PostMapping
    public ResponseEntity<?> addExample(@Valid @RequestBody ExampleDto exampleDto){
        ApiResponse apiResponse = exampleService.addExample(exampleDto);
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping
    public ResponseEntity<?> getExampleList(){
        List<Example> exampleList = exampleService.getExampleList();
        return ResponseEntity.ok(exampleList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getExample(@PathVariable Integer id){
        Example example = exampleService.getExample(id);
        return ResponseEntity.ok(example);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExample(@PathVariable Integer id){
        ApiResponse apiResponse = exampleService.deleteExample(id);
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editExample(@Valid @PathVariable Integer id,@RequestBody ExampleDto exampleDto){
        ApiResponse apiResponse = exampleService.editExample(id, exampleDto);
        return ResponseEntity.ok(apiResponse);
    }

}
