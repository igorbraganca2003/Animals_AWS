package com.meli.animals.controllers;

import com.meli.animals.entities.Animal;
import com.meli.animals.services.AnimalService;
import com.meli.animals.Exceptions.AnimalNomeDuplicadoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AnimalControllerTest {

    @Mock
    private AnimalService service;

    @InjectMocks
    private AnimalController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testeEncontrarTodosOsAnimais() {
        List<Animal> animais = Collections.singletonList(new Animal());
        when(service.encontrarTodosAnimais()).thenReturn(animais);

        ResponseEntity<List<Animal>> response = controller.encontrarTodos();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(animais, response.getBody());
    }

    @Test
    void testeRetornarNotFoundQuandoNaoEncontrarAnimais() {
        when(service.encontrarTodosAnimais()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Animal>> response = controller.encontrarTodos();
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testeRetornarAnimalPorId() {
        Animal animal = new Animal();
        when(service.findById(1L)).thenReturn(Optional.of(animal));

        ResponseEntity<Animal> response = controller.findById(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(animal, response.getBody());
    }

    @Test
    void testeRetornarNotFoundParaAnimalNaoExistentePorId() {
        when(service.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Animal> response = controller.findById(99L);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testeSalvarAnimal() {
        Animal animal = new Animal();
        animal.setNome("Cachorro");

        when(service.existsByNome("Cachorro")).thenReturn(false);
        when(service.save(any(Animal.class))).thenReturn(animal);

        ResponseEntity<Animal> response = controller.salvarAnimal(animal);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(animal, response.getBody());
    }

    @Test
    void testeGerarExcecaoQuandoNomeDoAnimalForDuplicado() {
        Animal animal = new Animal();
        animal.setNome("Cachorro");

        when(service.existsByNome("Cachorro")).thenReturn(true);

        AnimalNomeDuplicadoException exception = assertThrows(
                AnimalNomeDuplicadoException.class,
                () -> controller.salvarAnimal(animal)
        );

        assertEquals("Já existe um animal com o nome: Cachorro", exception.getMessage());
    }

    @Test
    void testeDeletarAnimal() {
        doNothing().when(service).deletarAnimal(1L);

        ResponseEntity<Void> response = controller.deletarAnimal(1L);
        assertEquals(204, response.getStatusCodeValue());
    }
}