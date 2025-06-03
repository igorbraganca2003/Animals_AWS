package com.meli.animals.services;

import com.meli.animals.entities.Animal;
import com.meli.animals.repositories.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository repository;

    @Override
    public Animal save(Animal animal) {
        return repository.save(animal);
    }

    @Override
    public List<Animal> encontrarTodosAnimais() {
        return repository.findAll();
    }

    @Override
    public Optional<Animal> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Animal> findByCor(String cor) {
        return repository.findByCor(cor);
    }

    @Override
    public List<Animal> findByRaca(String raca) {
        return repository.findByRaca(raca);
    }

    @Override
    public void deletarAnimal(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Animal> findByNome(String nome) {
        return Optional.empty();
    }

    @Override
    public boolean existsByNome(String nome) {
        return repository.findByNome(nome).isPresent();
    }
}