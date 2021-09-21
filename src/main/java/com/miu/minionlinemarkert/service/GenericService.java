package com.miu.minionlinemarkert.service;

import java.util.List;

public interface GenericService<T> {
    void save(T t);
    T getById(Long id);
    List<T> findAll();
}
