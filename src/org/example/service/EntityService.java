package org.example.service;

import org.example.repository.Repository;
import org.example.repository.InMemoryRepository;
import org.example.domain.Entity;

import java.util.Optional;

public class EntityService <ID, E extends Entity<ID>> implements Service<ID,E> {

    private Repository<ID,E>repository;

    public EntityService(Repository<ID,E> repository) {
    this.repository=repository;
    }
    @Override
    public Optional<E> addUser(E user) {
        return repository.save(user);
    }

    @Override
    public Optional<E> removeUser(ID id) {
        return repository.delete(id);
    }

    @Override
    public Optional<E> findEntityByID(ID id) {
        return repository.findOne(id);
    }

    @Override
    public Iterable<E> findAllEntities() {
        return repository.findAll();
    }

    @Override
    public Optional<E> saveEntity(E entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<E> deleteEntity(ID id) {
        return repository.delete(id);
    }

    @Override
    public Optional<E> updateEntity(E entity) {
        return repository.update(entity);
    }
}
