package com.example.boot.service;

import java.util.List;
import java.util.Optional;

/**
 * Đây là 1 interface hỗ trợ việc khởi tạo service interface
 * Những Interface Service sẽ extend Interface này và được sử dụng thông qua 1 class service implement nó
 * @param <T> : Kiểu đối tượng tương tác
 * @param <ID> : Kiểu dữ liệu id của đối tượng sẽ tương tác
 */
public interface CrudService<T, ID>{
    List<T> findAll();
    Optional<T> findById(ID id);
    T save(T t);
    void deleteById(ID id);
}
