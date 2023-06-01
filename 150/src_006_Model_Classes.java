package book.jakartapro.javamvc.model;

import java.util.Objects;
import java.util.UUID;

public final class Pet {
    private UUID petNumber;
    private String name;
    private Status status;

    public Pet() {
        //necessary to fulfill Java Bean Standard
    }

    public Pet(final String name, Status status) {
    	this(UUID.randomUUID(), name, status);
    }

    public Pet(final String name) {
    	this(name, Status.ACTIVE);
    }

    public Pet(final UUID petNumber, 
          final String name, final Status status) {
        this.petNumber = petNumber;
        this.name = name;
        this.status = status;
    }

    public UUID getPetNumber() {
        return petNumber;
    }

    public void setPetNumber(final UUID petNumber) {
        this.petNumber = petNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public void reserve() {
        this.status = Status.RESERVED;
    }

    public boolean isReserved() {
        return this.status == Status.RESERVED;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Pet pet = (Pet) o;
        return Objects.equals(petNumber, pet.petNumber) &&
                Objects.equals(name, pet.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(petNumber, name);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "petNumber=" + petNumber +
                ", name='" + name + '\'' +
                '}';
    }

    public enum Status {
        ACTIVE,
        RESERVED
    }
}
