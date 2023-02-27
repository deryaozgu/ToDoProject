package com.example.ToDoList.controllers;

import com.example.ToDoList.models.ToDo;
import com.example.ToDoList.services.ToDoServices;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/todo")
public class ToDoControllers {
    private ToDoServices toDoServices;

    public ToDoControllers(ToDoServices toDoServices) {
        this.toDoServices = toDoServices;
    }
    @GetMapping(path = "getById/{id}")
    public ResponseEntity<ToDo> getByIdToDo(@PathVariable Integer id){
        ToDo todoResult = toDoServices.getByIdToDo(id);
            return ResponseEntity.ok(todoResult);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ToDo>> getAllTodo() {
        return ResponseEntity.ok(toDoServices.getAllToDo());
    }

    @PostMapping("/save")
    public ResponseEntity<ToDo> createTask(@RequestBody ToDo toDo) {
        return ResponseEntity.ok(toDoServices.saveToDo(toDo));
    }

    @PutMapping(path = "update/{id}")
    public ResponseEntity<ToDo> updateToDo(@PathVariable Integer id, @RequestBody ToDo toDo) {
        toDo.setId(id);
        return ResponseEntity.ok(toDoServices.updateToDo(toDo));
    }
    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<Optional<ToDo>> getAllToDo(@PathVariable Integer id) {
        Optional<ToDo> result= toDoServices.deleteToDo(id);
        return ResponseEntity.ok(result);
    }
}
