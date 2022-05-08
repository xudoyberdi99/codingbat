package com.example.service;

import com.example.entity.Language;
import com.example.payload.ApiResponse;
import com.example.payload.LanguageDto;
import com.example.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    @Autowired
    LanguageRepository languageRepository;

    public ApiResponse addLanguage(LanguageDto lang){
        boolean exists = languageRepository.existsByName(lang.getName());
        if (exists){
            return new ApiResponse("bunday til mavjud",false);
        }
        Language languag=new Language();
        languag.setName(lang.getName());
        languageRepository.save(languag);
        return new ApiResponse("add new Language",true);
    }

    public List<Language> getLanguList(){
        List<Language> all = languageRepository.findAll();
        return all;
    }

    public Language getLanguage(Integer id){
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        return optionalLanguage.orElseGet(Language::new);
    }

    public ApiResponse deleteLanguage(Integer id){
        try {
        languageRepository.deleteById(id);
        return new ApiResponse("Language delete", true);
        }catch (Exception e){
            return new ApiResponse("bu dasturlash tili uchirilmadi", false);
        }
    }

    public ApiResponse editLanguage(Integer id, LanguageDto lang){
        boolean exists = languageRepository.existsByNameAndIdNot(lang.getName(),id);
        if (exists){
            return new ApiResponse("bunday til mavjud",false);
        }
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent()){
            return new ApiResponse("bunday til mavjud emas",false);
        }
        Language langua = optionalLanguage.get();
        langua.setName(lang.getName());
        languageRepository.save(langua);
        return new ApiResponse("edit Language success", true);
    }
}
