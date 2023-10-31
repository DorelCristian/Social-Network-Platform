package org.example.service;

import org.example.domain.Prietenie;
import org.example.domain.Tuple;
import org.example.domain.Utilizator;
import org.example.repository.Repository;

import java.util.Optional;

public class PrietenieService implements Service<Tuple<Long,Long>, Prietenie> {

    private Repository<Tuple<Long,Long>,Prietenie>prietenieRepository;
    private Repository<Long, Utilizator>utilizatorRepository;

    public PrietenieService(Repository<Tuple<Long,Long>,Prietenie>prietenieRepository,Repository<Long,Utilizator>utilizatorRepository)
    {
        this.prietenieRepository=prietenieRepository;
        this.utilizatorRepository=utilizatorRepository;
    }
    @Override
    public Optional<Prietenie> addUser(Prietenie prietenie) {
        Optional<Utilizator> utilizator1= utilizatorRepository.findOne(prietenie.getId().getLeft());
        Optional<Utilizator> utilizator2=utilizatorRepository.findOne(prietenie.getId().getRight());
        if(utilizator1.isPresent() && utilizator2.isPresent())
            return prietenieRepository.save(prietenie);
        else return Optional.empty();

    }

    @Override
    public Optional<Prietenie> removeUser(Tuple<Long, Long> longLongTuple) {
        return prietenieRepository.delete(longLongTuple);//id
    }

    @Override
    public Optional<Prietenie> findEntityByID(Tuple<Long, Long> longLongTuple) {
        return prietenieRepository.findOne(longLongTuple);//id
    }

    @Override
    public Iterable<Prietenie> findAllEntities() {
        return prietenieRepository.findAll();
    }

    @Override
    public Optional<Prietenie> saveEntity(Prietenie entity) {
        return prietenieRepository.save(entity);
    }

    @Override
    public Optional<Prietenie> deleteEntity(Tuple<Long, Long> longLongTuple) {
        return prietenieRepository.delete(longLongTuple);
    }

    @Override
    public Optional<Prietenie> updateEntity(Prietenie entity) {
        return prietenieRepository.update(entity);
    }
}
