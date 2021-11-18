package ca.ucalgary.vetapp.controller;

import ca.ucalgary.vetapp.model.Animal;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class AnimalModelAssembler implements RepresentationModelAssembler<Animal, EntityModel<Animal>> {
    @Override
    public EntityModel<Animal> toModel(Animal a) {
        return EntityModel.of(a, WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(AnimalController.class).getOneAnimal(a.getAnimalId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AnimalController.class).getAllAnimals())
                        .withRel("all"));
    }
}