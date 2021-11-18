package ca.ucalgary.vetapp.controller;

import ca.ucalgary.vetapp.model.Animal;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.hateoas.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/animals")
public class AnimalController {
    private final AnimalRepository animalRepository;
    private final AnimalModelAssembler animalModelAssembler;

    public AnimalController(AnimalRepository ar, AnimalModelAssembler ama) {
        this.animalRepository = ar;
        this.animalModelAssembler = ama;
    }

    /**
     * Gets all animals
     *
     * @return
     */
    @GetMapping
    public CollectionModel<EntityModel<Animal>> getAllAnimals() {
        List<EntityModel<Animal>> allAnimals = this.animalRepository.findAll().stream()
                .map(this.animalModelAssembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(allAnimals, WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(AnimalController.class).getAllAnimals()).withSelfRel());
    }

    /**
     * Get one animal
     *
     * @param id
     * @return
     */
    @GetMapping(path = "{animalId}")
    public EntityModel<Animal> getOneAnimal(@PathVariable("animalId") Long id) {
        Optional<Animal> animalOptional = this.animalRepository.findById(id);

        if (animalOptional.isPresent()) {
            Animal oneAnimal = animalOptional.get();

            return this.animalModelAssembler.toModel(oneAnimal);
        }

        else {
            throw new NotFoundException(id);
        }
    }

    /**
     * Saves one animal
     *
     * @param a
     * @return
     */
    @PostMapping
    public ResponseEntity<?> addAnimal(@RequestBody Animal a) {
        EntityModel<Animal> entityModel = this.animalModelAssembler.toModel(this.animalRepository.save(a));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    /**
     * Updates or creates animal
     *
     * @param a
     * @param id
     * @return
     */
    @PutMapping(path = "{animalId}")
    public ResponseEntity<?> updateAnimal(@RequestBody Animal a, @PathVariable("animalId") Long id) {
        Optional<Animal> animalOptional = this.animalRepository.findById(id);

        Animal updatedAnimal;

        if (animalOptional.isPresent()) {
            Animal animal = animalOptional.get();

            animal.setName(a.getName());
            animal.setType(a.getType());
            animal.setBreed(a.getBreed());
            animal.setBirthDate(a.getBirthDate());
            animal.setSex(a.getSex());
            animal.setStatus(a.getStatus());
            animal.setTheOwner(a.getTheOwner());
            animal.setTattooNum(a.getTattooNum());
            animal.setRfidNumber(a.getRfidNumber());
            animal.setMicroChipNumber(a.getMicroChipNumber());
            animal.setWeight(a.getWeight());
            animal.setCoatColor(a.getCoatColor());
            animal.setContinuousMedication(a.getContinuousMedication());
            animal.setAnimalPhotoList(a.getAnimalPhotoList());
            animal.setAnimalTreatmentList(a.getAnimalTreatmentList());
            animal.setAnimalIssueList(a.getAnimalIssueList());

            updatedAnimal = this.animalRepository.save(animal);
        }

        else {
            a.setAnimalId(id);
            updatedAnimal = this.animalRepository.save(a);
        }

        EntityModel<Animal> entityModel = this.animalModelAssembler.toModel(updatedAnimal);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    /**
     * Deletes one animal
     *
     * @param id
     */
    @DeleteMapping(path = "{animalId}")
    public ResponseEntity<?> deleteAnimal(@PathVariable("animalId") Long id) {
        if (!this.animalRepository.existsById(id)) {
            throw new NotFoundException(id);
        }

        this.animalRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
