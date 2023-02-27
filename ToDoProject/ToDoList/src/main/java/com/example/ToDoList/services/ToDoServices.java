package com.example.ToDoList.services;

import com.example.ToDoList.models.ToDo;
import com.example.ToDoList.repositories.ToDoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoServices {

    private ToDoRepository toDoRepository;

    public ToDoServices(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public List<ToDo> getAllToDo() {
        if(toDoRepository.findAll().size()!=0)
        {
            return toDoRepository.findAll();
        }
        else{
               return null;
        }
    }
    public ToDo getByIdToDo(Integer id){
        Optional<ToDo> optionalTodo = toDoRepository.findById(id);

        if (optionalTodo.isPresent()){
            return optionalTodo.get();
        }
        return null;
    }
    public Optional<ToDo> deleteToDo(Integer id) {
        Optional<ToDo> optional = toDoRepository.findById(id);
        if (optional.isPresent()) {
            ToDo existingToDo = optional.get();
            toDoRepository.delete(existingToDo);
            return optional;
        }
        else
        {
            return null;
        }

    }
    public ToDo updateToDo(ToDo toDo) {
        Optional<ToDo> optional = toDoRepository.findById(toDo.getId());
        if (optional.isPresent()) {
            return toDoRepository.save(toDo);
        }
        else
        {
            return null;
        }
    }
    public ToDo saveToDo(ToDo toDo) {
        if(toDo!=null)
        {
            return toDoRepository.save(toDo);
        }
        else {
            return null;
        }

    }
}
