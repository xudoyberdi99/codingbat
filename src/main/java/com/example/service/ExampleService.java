package com.example.service;

import com.example.entity.Example;
import com.example.entity.Language;
import com.example.entity.Task;
import com.example.payload.ApiResponse;
import com.example.payload.ExampleDto;
import com.example.payload.LanguageDto;
import com.example.repository.ExampleRepository;
import com.example.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExampleService {
    @Autowired
    ExampleRepository exampleRepository;
    @Autowired
    TaskRepository taskRepository;

    public ApiResponse addExample(ExampleDto exampleDto){

        boolean exists = exampleRepository.existsByText(exampleDto.getText());
        if (exists){
            return new ApiResponse("bunday example mavjud",false);
        }

        Optional<Task> optionalTask = taskRepository.findById(exampleDto.getTaskId());
        if (!optionalTask.isPresent()){
            return new ApiResponse("bunday task mavjud emas", false);
        }
        Example example=new Example();
        example.setText(exampleDto.getText());
        example.setTask(optionalTask.get());
        exampleRepository.save(example);
        return new ApiResponse("add new example",true);
    }

    public List<Example> getExampleList(){
        List<Example> all = exampleRepository.findAll();
        return all;
    }

    public Example getExample(Integer id){
        Optional<Example> optionalExample = exampleRepository.findById(id);
        return optionalExample.orElseGet(Example::new);
    }

    public ApiResponse deleteExample(Integer id){
        try {
            exampleRepository.deleteById(id);
            return new ApiResponse("example delete", true);
        }catch (Exception e){
            return new ApiResponse("Error!!!", false);
        }
    }

    public ApiResponse editExample(Integer id, ExampleDto exampleDto){
        boolean exists = exampleRepository.existsByTextAndIdNot(exampleDto.getText(),id);
        if (exists){
            return new ApiResponse("bunday example mavjud",false);
        }
        Optional<Task> optionalTask = taskRepository.findById(exampleDto.getTaskId());
        if (!optionalTask.isPresent()){
            return new ApiResponse("bunday Task mavjud emas",false);
        }
        Optional<Example> exampleOptional = exampleRepository.findById(id);
        if (!exampleOptional.isPresent()){
            return new ApiResponse("bunday example mavjud emas",false);
        }
        Example example = exampleOptional.get();
        example.setText(exampleDto.getText());
        example.setTask(optionalTask.get());
        exampleRepository.save(example);
        return new ApiResponse("edit example success", true);
    }

}
