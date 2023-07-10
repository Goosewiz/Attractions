package com.example.attractions.Repo;

import com.example.attractions.Models.Attractions;
import org.springframework.data.repository.CrudRepository;

public interface AttractionsRepo extends CrudRepository<Attractions, Long> {
}
