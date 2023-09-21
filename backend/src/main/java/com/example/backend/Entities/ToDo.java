package com.example.backend.Entities;

public record ToDo(
    String id,
    String description,
    Status status
) {
}
