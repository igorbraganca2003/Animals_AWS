package com.meli.animals.services;

import com.meli.animals.entities.Animal;
import com.meli.animals.repositories.AnimalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AnimalServiceTest {

    @Mock
    private AnimalRepository repository;

    @InjectMocks
    private AnimalServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testeSalvarAnimal() {
        Animal animal = new Animal();
        when(repository.save(animal)).thenReturn(animal);

        Animal savedAnimal = service.save(animal);
        assertEquals(animal, savedAnimal);
        verify(repository, times(1)).save(animal);
    }

    @Test
    void testeEncontrarTodosAnimais() {
        List<Animal> listaAnimais = Collections.singletonList(new Animal());
        when(repository.findAll()).thenReturn(listaAnimais);

        List<Animal> resultado = service.encontrarTodosAnimais();
        assertEquals(listaAnimais, resultado);
        verify(repository, times(1)).findAll();
    }

    @Test
    void testeEncontrarAnimalPorId() {
        Long id = 1L;
        Optional<Animal> optionalAnimal = Optional.of(new Animal());
        when(repository.findById(id)).thenReturn(optionalAnimal);

        Optional<Animal> resultado = service.findById(id);
        assertEquals(optionalAnimal, resultado);
        verify(repository, times(1)).findById(id);
    }

    @Test
    void testeEncontrarAnimalPorCor() {
        String cor = "preto";
        List<Animal> listaAnimais = Collections.singletonList(new Animal());
        when(repository.findByCor(cor)).thenReturn(listaAnimais);

        List<Animal> resultado = service.findByCor(cor);
        assertEquals(listaAnimais, resultado);
        verify(repository, times(1)).findByCor(cor);
    }

    @Test
    void testeEncontrarAnimalPorRaca() {
        String raca = "bulldog";
        List<Animal> listaAnimais = Collections.singletonList(new Animal());
        when(repository.findByRaca(raca)).thenReturn(listaAnimais);

        List<Animal> resultado = service.findByRaca(raca);
        assertEquals(listaAnimais, resultado);
        verify(repository, times(1)).findByRaca(raca);
    }

    @Test
    void testeDeletarAnimalPorId() {
        Long id = 1L;
        doNothing().when(repository).deleteById(id);

        service.deletarAnimal(id);
        verify(repository, times(1)).deleteById(id);
    }

    @Test
    void testeRetornarVazioQuandoProcurarAnimalPorNome() {
        String nome = "Rex";
        Optional<Animal> resultado = service.findByNome(nome);
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }
}