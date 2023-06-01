package book.jakartapro.javamvc.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import book.jakartapro.javamvc.model.Pet;
import book.jakartapro.javamvc.repository.PetRepository;

@Stateless
public class PetService {

    @Inject
    private PetRepository petRepository;

    public List<Pet> getAllPets() {
        return petRepository.getAll();
    }

    public Optional<Pet> findPetForPetNumber(
          final UUID petNumber) {
        return petRepository.getByPetNumber(petNumber);
    }

    public Pet createNewPet(final String name) {
        final Pet pet = new Pet(name);
        petRepository.add(pet);

        return pet;
    }

    public void reservePet(final UUID petNumber) {
        final Optional<Pet> optPet = petRepository.
              getByPetNumber(petNumber);
        if (optPet.isPresent()) {
            final Pet pet = optPet.get();
            pet.reserve();
            petRepository.update(pet);
        }
    }

    public void deletePet(UUID petNumber) {
        final Optional<Pet> optPet = petRepository.
              getByPetNumber(petNumber);
        if (optPet.isPresent()) {
            final Pet pet = optPet.get();
            petRepository.delete(pet);
        }
    }

    public void setPetRepository(
          final PetRepository petRepository) {
        this.petRepository = petRepository;
    }

}
