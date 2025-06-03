package com.meli.animals.services;

import com.meli.animals.entities.Animal;

import java.util.List;
import java.util.Optional;

public interface AnimalService {

    Animal save(Animal animal);

    List<Animal> encontrarTodosAnimais();

    Optional<Animal> findById(Long id);

    List<Animal> findByCor(String cor);

    List<Animal> findByRaca(String raca);

    void deletarAnimal(Long id);

    Optional<Animal> findByNome(String nome);

    boolean existsByNome(String nome);
}