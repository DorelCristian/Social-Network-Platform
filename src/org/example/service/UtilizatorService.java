package org.example.service;

import org.example.domain.Utilizator;
import org.example.repository.Repository;

import java.util.Optional;

public class UtilizatorService implements Service<Long, Utilizator> {
    private Repository<Long,Utilizator>repository;

    public UtilizatorService(Repository<Long,Utilizator>repository)
    {
        this.repository=repository;
    }
    @Override
    public Optional<Utilizator> addUser(Utilizator user) {
        return repository.save(user);
    }

    @Override
    public Optional<Utilizator> removeUser(Long aLong) {
        return repository.delete(aLong);
    }
    @Override
    public Optional<Utilizator> findEntityByID(Long aLong) {
        return repository.findOne(aLong);
    }

    @Override
    public Iterable<Utilizator> findAllEntities() {
        return repository.findAll();
    }

    @Override
    public Optional<Utilizator> saveEntity(Utilizator entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<Utilizator> deleteEntity(Long aLong) {
        return repository.delete(aLong);
    }

    @Override
    public Optional<Utilizator> updateEntity(Utilizator entity) {
        return repository.update(entity);
    }
}
