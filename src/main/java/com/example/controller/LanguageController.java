package com.example.controller;

import com.example.entity.Language;
import com.example.payload.ApiResponse;
import com.example.payload.LanguageDto;
import com.example.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/languages")
public class LanguageController {
    @Autowired
    LanguageService languageService;

    @PostMapping
    public ResponseEntity<?> addLaguage(@Valid @RequestBody LanguageDto languageDto){
        ApiResponse apiResponse = languageService.addLanguage(languageDto);
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping
    public ResponseEntity<?> getLanguagesList(){
        List<Language> languList = languageService.getLanguList();
        return ResponseEntity.ok(languList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getLanguage(@PathVariable Integer id){
        Language languag = languageService.getLanguage(id);
        return ResponseEntity.ok(languag);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLanguage(@PathVariable Integer id){
        ApiResponse apiResponse = languageService.deleteLanguage(id);
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editLanguage(@Valid @PathVariable Integer id,@RequestBody LanguageDto languag){
        ApiResponse apiResponse = languageService.editLanguage(id, languag);
        return ResponseEntity.ok(apiResponse);
    }

}
