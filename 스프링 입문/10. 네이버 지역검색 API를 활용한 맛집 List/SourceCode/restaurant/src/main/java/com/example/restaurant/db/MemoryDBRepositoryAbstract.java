package com.example.restaurant.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract public class MemoryDBRepositoryAbstract<T extends MemoryDBEntity> implements MemoryDBRepositoryIfs<T>{

    private final List<T> db = new ArrayList<>();
    private int index = 0;

    @Override
    public Optional<T> findById(int index) {
        return db.stream().filter(it -> it.getIndex() == index).findFirst();
    }

    @Override
    public T save(T entity) {
        var optionalEntity = db.stream().filter(it -> it.getIndex() == entity.getIndex()).findFirst();

        // db 에 데이터가 없는 경우
        if (optionalEntity.isEmpty()) {
            index++;
            entity.setIndex(index);
            db.add(entity);
            return entity;
        }
        // db 에 이미 데이터가 있는 경우
        else {
            var preIndex = optionalEntity.get().getIndex(); // 기존을 가져와서
            entity.setIndex(preIndex);

            deleteById(preIndex); // 기존 삭제하고
            db.add(entity); // 새로 추가
            return entity;
        }
    }

    @Override
    public void deleteById(int index) {
        var optionalEntity = db.stream().filter(it -> it.getIndex() == index).findFirst();
        if(optionalEntity.isPresent()) {
            db.remove(optionalEntity.get());
        }
    }

    @Override
    public List<T> listAll() {
        return db;
    }
}
