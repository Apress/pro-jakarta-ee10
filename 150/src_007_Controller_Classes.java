package book.jakartapro.javamvc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.binding.BindingResult;
import jakarta.mvc.binding.MvcBinding;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import book.jakartapro.javamvc.model.Pet;
import book.jakartapro.javamvc.service.PetService;

// http://localhost:8080/JavaMvc2/mvc/pets
@Path("/pets")
@Controller
public class PetshopController {

    @Inject
    private Models models;

    @Inject
    private BindingResult bindingResult;

    @Inject
    private PetService petService;

    @GET
    public String showIndex() {
        final List<Pet> pets = petService.getAllPets();
        models.put("pets", pets);

        return "index.jsp";
    }

    @GET
    @Path("/{petNumber}")
    public Response showDetailsOfPet(
          @PathParam("petNumber") final UUID petNumber) {
        final Optional<Pet> pet = petService.
              findPetForPetNumber(petNumber);

        if (pet.isPresent()) {
            models.put("pet", pet.get());
            return Response.ok("details.jsp")
                    .build();
        } else {
            models.put("petNumber", petNumber);
            return Response.status(Status.NOT_FOUND)
                    .entity("404.jsp")
                    .build();
        }
    }

    @GET
    @Path("/new")
    public String showNewPetForm() {
        return "form.jsp";
    }

    @POST
    public String createNewPet(
          @FormParam("name") @MvcBinding @NotBlank 
          @Size(min = 1, max = 255) final String name) {
        if (bindingResult.isFailed()) {
            models.put("errors", new ArrayList<>(
                  bindingResult.getAllErrors()));
            return "form.jsp";
        }

        final Pet pet = petService.createNewPet(name);

        return "redirect:/pets/" + pet.getPetNumber();
    }

    @DELETE
    @Path("/{petNumber}")
    public String deletePet(
          @PathParam("petNumber") final UUID petNumber) {
    	petService.deletePet(petNumber);
    	return "redirect:/pets";
    }

    @PUT
    @Path("/{petNumber}")
    public String changePet(
          @PathParam("petNumber") final UUID petNumber, 
          @FormParam("reserve") @MvcBinding 
                final String reserve) {
    	if("reserve".equals(reserve)) {
            petService.reservePet(petNumber);
            return "redirect:/pets";
    	}
    	return "redirect:/pets";
    }
}
