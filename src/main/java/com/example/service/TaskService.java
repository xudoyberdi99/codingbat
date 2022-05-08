package com.example.service;

import com.example.entity.Language;
import com.example.entity.Task;
import com.example.payload.ApiResponse;
import com.example.payload.TaskDto;
import com.example.repository.LanguageRepository;
import com.example.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    LanguageRepository languageRepository;

    public ApiResponse addTask(TaskDto taskDto){
        boolean exists = taskRepository.existsByName(taskDto.getName());
        if (exists){
            return new ApiResponse("bunday task mavjud qusha olmaysiz", false);
        }
        Optional<Language> optionalLanguage = languageRepository.findById(taskDto.getLaguageId());
        if (!optionalLanguage.isPresent()){
            return new ApiResponse("bunday dasturlash tili yuq",false);
        }
        Language language = optionalLanguage.get();
        Task task=new Task();

        task.setName(taskDto.getName());
        task.setText(taskDto.getText());
        task.setHint(taskDto.getHint());
        task.setHasStar(true);
        task.setMethod(taskDto.getMethod());
        task.setLanguages(language);
        task.setSolution(taskDto.getSolution());

        taskRepository.save(task);
        return new ApiResponse("add task success", true);
    }

    public List<Task> getAllTasks(){
        List<Task> all = taskRepository.findAll();
        return all;
    }

    public Task getTask(Integer id){
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElseGet(Task::new);
    }

    public ApiResponse deleteTask(Integer id){
        try {
            taskRepository.deleteById(id);
            return new ApiResponse("delete Task",true);
        }catch (Exception e){
            return new ApiResponse("Error!!!", false);
        }
    }

    public ApiResponse editTask(Integer id, TaskDto taskDto){
        boolean exists = taskRepository.existsByNameAndIdNot(taskDto.getName(), id);
        if (exists){
            return new ApiResponse("bunday task mavjud qusha olmaysiz",false);
        }
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()){
            return new ApiResponse("bunday task mavjud emas",false);
        }
        Optional<Language> optionalLanguage = languageRepository.findById(taskDto.getLaguageId());
        if (!optionalLanguage.isPresent()){
            return new ApiResponse("bunday universitet yuq",false);
        }
        Task task = optionalTask.get();

        task.setName(taskDto.getName());
        task.setMethod(taskDto.getMethod());
        task.setSolution(taskDto.getSolution());
        task.setHint(taskDto.getHint());
        task.setHasStar(taskDto.isHasStar());
        task.setText(taskDto.getText());
        task.setLanguages(optionalLanguage.get());

        taskRepository.save(task);
        return new ApiResponse("edit task success", true);
    }
}
