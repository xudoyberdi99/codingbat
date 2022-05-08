package com.example.service;

import com.example.entity.Answer;
import com.example.entity.Example;
import com.example.entity.Task;
import com.example.entity.User;
import com.example.payload.AnswerDto;
import com.example.payload.ApiResponse;
import com.example.payload.ExampleDto;
import com.example.repository.AnswerRepository;
import com.example.repository.TaskRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;

    public ApiResponse addAnswer(AnswerDto answerDto){

        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (!optionalTask.isPresent()){
            return new ApiResponse("bunday task mavjud emas", false);
        }
        Optional<User> byEmail = userRepository.findByEmail(answerDto.getEmail());
        if (!byEmail.isPresent()){
            return new ApiResponse("bunday User mavjud emas", false);
        }
        Answer answer=new Answer();
        answer.setText(answerDto.getText());
        answer.setTask(optionalTask.get());
        answer.setUser(byEmail.get());
        answer.setCorrect(answerDto.isCorrect());
        answerRepository.save(answer);
        return new ApiResponse("add answer example",true);
    }

    public List<Answer> getAnswerList(){
        List<Answer> all = answerRepository.findAll();
        return all;
    }

    public Answer getAnswer(Integer id){
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        return optionalAnswer.orElseGet(Answer::new);
    }

    public ApiResponse deleteAnswer(Integer id){
        try {
            answerRepository.deleteById(id);
            return new ApiResponse("example delete", true);
        }catch (Exception e){
            return new ApiResponse("Error!!!", false);
        }
    }

    public ApiResponse editAnswer(Integer id, AnswerDto answerDto){
        Optional<Answer>  optionalAnswer = answerRepository.findById(id);
        if (!optionalAnswer.isPresent()){
            return new ApiResponse("bunday answer mavjud emas",false);
        }
        Optional<User> byEmail = userRepository.findByEmail(answerDto.getEmail());
        if (!byEmail.isPresent()){
            return new ApiResponse("bunday email mavjud emas", false);
        }
        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (!optionalTask.isPresent()){
            return new ApiResponse("bunday task mavjud emas",false);
        }

        Answer answer = optionalAnswer.get();
        answer.setText(answerDto.getText());
        answer.setUser(byEmail.get());
        answer.setTask(optionalTask.get());
        answer.setCorrect(answerDto.isCorrect());

        answerRepository.save(answer);
        return new ApiResponse("edit answer success", true);
    }
}
