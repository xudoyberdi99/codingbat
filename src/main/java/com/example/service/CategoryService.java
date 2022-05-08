package com.example.service;

import com.example.entity.Category;
import com.example.entity.Language;
import com.example.payload.ApiResponse;
import com.example.payload.CategoryDto;
import com.example.repository.CategoryRepository;
import com.example.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    LanguageRepository languageRepository;

    public ApiResponse addCategory(CategoryDto categoryDto){
        boolean exists = categoryRepository.existsByName(categoryDto.getName());
        if (exists){
            return new ApiResponse("bunday kategoriya mavjud qushaolmaysiz", false);
        }
        Optional<Language> languageRepositoryById = languageRepository.findById(categoryDto.getLanguageId());
        if (!languageRepositoryById.isPresent()){
            return new ApiResponse("bunday til mavjud emas",false);
        }
        Category category=new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setLanguages(languageRepositoryById.get());

        categoryRepository.save(category);
        return new ApiResponse("add category",true);
    }

    public List<Category> getCategoryList(){
        List<Category> all = categoryRepository.findAll();
        return all;
    }

    public Category getCategory(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElseGet(Category::new);
    }

    public ApiResponse deleteCategory(Integer id){
        try{
            categoryRepository.deleteById(id);
            return new ApiResponse("delete category",true);
        }catch (Exception e){
            return new ApiResponse("categoriya uchirilmadi",false);
        }

    }

    public ApiResponse editCategory(Integer id, CategoryDto categoryDto){
        boolean exists = categoryRepository.existsByNameAndIdNot(categoryDto.getName(), id);
        if (exists){
            return new ApiResponse("bunday categoriya mavjud ", false);
        }

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()){
            return new ApiResponse("bunday categoriya topilmadi", false);
        }

        Optional<Language> optionalLanguage = languageRepository.findById(categoryDto.getLanguageId());
        if (!optionalLanguage.isPresent()){
            return new ApiResponse("bunday dasturlash tili yuq", false);
        }
        Language language = optionalLanguage.get();

        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setLanguages(language);

        categoryRepository.save(category);
        return new ApiResponse("edit category",true);
    }
}
