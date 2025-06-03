package com.meli.animals.repositories;

import com.meli.animals.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByCor(String cor);
    List<Animal> findByRaca(String raca);
    Optional<Animal> findByNome(String nome);
}
