package org.example.service;

import org.example.domain.Entity;
import org.example.repository.Repository;

import java.util.Optional;

public interface Service<ID, E extends Entity<ID>> {

    /**
     * Adaugă un utilizator nou în repository.
     *
     * @param user Utilizatorul de adăugat
     * @return Utilizatorul adăugat sau null dacă adăugarea nu a reușit
     */
    Optional<E> addUser(E user);

    /**
     * Șterge un utilizator după ID.
     *
     * @param id ID-ul utilizatorului de șters
     * @return Utilizatorul șters sau null dacă nu există un utilizator cu ID-ul respectiv
     */
    Optional<E> removeUser(ID id);

    /**
     * Găsește o entitate după ID.
     *
     * @param id ID-ul entității
     * @return Entitatea găsită sau null dacă nu există o entitate cu ID-ul respectiv
     */
    Optional<E> findEntityByID(ID id);

    /**
     * Returnează toate entitățile.
     *
     * @return Toate entitățile din repository
     */
    Iterable<E> findAllEntities();

    /**
     * Salvează o entitate în repository.
     *
     * @param entity Entitatea de salvat
     * @return Entitatea salvată sau null dacă salvarea nu a reușit
     */
    Optional<E> saveEntity(E entity);

    /**
     * Șterge o entitate după ID.
     *
     * @param id ID-ul entității de șters
     * @return Entitatea ștearsă sau null dacă nu există o entitate cu ID-ul respectiv
     */
    Optional<E> deleteEntity(ID id);

    /**
     * Actualizează o entitate existentă.
     *
     * @param entity Entitatea de actualizat
     * @return Entitatea actualizată sau null dacă actualizarea nu a reușit
     */
    Optional<E> updateEntity(E entity);
}
