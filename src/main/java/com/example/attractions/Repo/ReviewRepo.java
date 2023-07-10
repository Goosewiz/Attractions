package com.example.attractions.Repo;

import com.example.attractions.Models.Reviews;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepo extends CrudRepository<Reviews, Long> {
}
