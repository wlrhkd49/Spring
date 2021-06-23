package org.sample.service;

import lombok.AllArgsConstructor;
import org.sample.model.TodoEntity;
import org.sample.model.TodoRequest;
import org.sample.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service // 구체적인 기능을 구현
@AllArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoEntity add(TodoRequest request) {
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setTitle(request.getTitle());
        todoEntity.setOrder(request.getOrder());
        todoEntity.setCompleted(request.getCompleted());
        TodoEntity entity = this.todoRepository.save(todoEntity);
        return entity;
    }

    public TodoEntity searchById(Long id) {
        return this.todoRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        // 없는 경우 not found exception 날림
    }

    public List<TodoEntity> searchAll() {
        return this.todoRepository.findAll();
    }

    public TodoEntity updateById(Long id, TodoRequest request) {
        TodoEntity todoEntity = this.searchById(id);
        if(request.getTitle() != null) {
            todoEntity.setTitle(request.getTitle());
        }
        if(request.getOrder() != null) {
            todoEntity.setOrder(request.getOrder());
        }
        if(request.getCompleted() != null) {
            todoEntity.setCompleted(request.getCompleted());
        }
        return this.todoRepository.save(todoEntity);
    }

    public void deleteById(Long id) {
        this.todoRepository.deleteById(id);
    }

    public void deleteAll() {
        this.todoRepository.deleteAll();
    }

}
