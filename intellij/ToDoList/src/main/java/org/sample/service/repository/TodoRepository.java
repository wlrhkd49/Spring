package org.sample.service.repository;

import org.sample.model.TodoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 데이터베이스와 연결할 인터페이스 정의
public interface TodoRepository extends JpaRepository<TodoModel, Long> {

}
