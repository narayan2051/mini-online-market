package com.miu.minionlinemarkert.service;

import java.util.List;

public interface GenericService<T> {
    void save(T t);
    T getById(String id);
    List<T> findAll();
}
