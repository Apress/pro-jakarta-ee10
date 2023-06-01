package book.jakartapro.javamvc.repository;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;

import book.jakartapro.javamvc.model.Pet;

@Stateless
public class PetRepository {
    private final Map<UUID, Pet> petStore = 
          new HashMap<>();

    @PostConstruct
    public void init() {
        add(new Pet("Cat"));
        add(new Pet("Dog"));
        add(new Pet("Crocodile", Pet.Status.RESERVED));
        add(new Pet("Snake", Pet.Status.RESERVED));
    }

    public List<Pet> getAll() {
        final List<Pet> pets = 
              new ArrayList<>(petStore.values());
        pets.sort(Comparator.comparing(Pet::getName));

        return pets;
    }

    public Optional<Pet> getByPetNumber(
          final UUID petNumber) {
        return Optional.ofNullable(
              petStore.get(petNumber));
    }

    public void add(final Pet pet) {
        petStore.put(pet.getPetNumber(), pet);
    }

    public boolean update(final Pet pet) {
        return petStore.replace(pet.getPetNumber(), pet)
              != null;
    }

    public void delete(final Pet pet) {
        petStore.remove(pet.getPetNumber());
    }
}
