package com.example.pokeapitesttask.Exception;

public final class EmptyBodyException extends IllegalStateException {
    public EmptyBodyException() {super("Body is empty!");}
}
