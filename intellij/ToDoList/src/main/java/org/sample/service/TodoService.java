package org.sample.service;

import lombok.AllArgsConstructor;
import org.sample.model.TodoModel;
import org.sample.model.TodoRequest;
import org.sample.service.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service // 구체적인 기능을 구현
@AllArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoModel add(TodoRequest request) {
        TodoModel todoModel = new TodoModel();
        todoModel.setTitle(request.getTitle());
        todoModel.setOrder(request.getOrder());
        todoModel.setCompleted(request.getCompleted());
        TodoModel entity = this.todoRepository.save(todoModel);
        return entity;
    }

    public TodoModel searchById(Long id) {
        return this.todoRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        // 없는 경우 not found exception 날림
    }

    public List<TodoModel> searchAll() {
        return this.todoRepository.findAll();
    }

    public TodoModel updateById(Long id, TodoRequest request) {
        TodoModel todoModel = this.searchById(id);
        if(request.getTitle() != null) {
            todoModel.setTitle(request.getTitle());
        }
        if(request.getOrder() != null) {
            todoModel.setOrder(request.getOrder());
        }
        if(request.getCompleted() != null) {
            todoModel.setCompleted(request.getCompleted());
        }
        return this.todoRepository.save(todoModel);
    }

    public void deleteById(Long id) {
        this.todoRepository.deleteById(id);
    }

    public void deleteAll() {
        this.todoRepository.deleteAll();
    }

}
