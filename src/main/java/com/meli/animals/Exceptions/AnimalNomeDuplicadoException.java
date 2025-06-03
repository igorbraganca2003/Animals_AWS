package com.meli.animals.Exceptions;

public class AnimalNomeDuplicadoException extends RuntimeException {
    public AnimalNomeDuplicadoException(String nome) {
        super("Já existe um animal com o nome: " + nome);
    }
}