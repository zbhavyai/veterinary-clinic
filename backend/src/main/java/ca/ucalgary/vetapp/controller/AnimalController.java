package ca.ucalgary.vetapp.controller;

import ca.ucalgary.vetapp.model.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.nio.file.*;
import java.io.*;
import java.net.MalformedURLException;
import java.util.Random;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/animals")
public class AnimalController {
    private final AnimalRepository animalRepository;
    private final OwnerRepository ownerRepository;
    private final UserRepository userRepository;
    private final WeightsRepository weightsRepository;
    private final PhotosRepository photosRepository;
    private final CommentsRepository commentsRepository;
    private final IssuesRepository issuesRepository;
    private final TreatmentsRepository treatmentsRepository;

    /**
     * Constructor
     *
     * @param ar jpa animal repository for db operations
     * @param or jpa owner repository for db operations
     * @param ur jpa user repository for db operations
     * @param wr jpa weights repository for db operations
     * @param pr jpa photos repository for db operations
     * @param cr jpa comments repository for db operations
     * @param ir jpa issues repository for db operations
     * @param tr jpa treatments repository for db operations
     */
    public AnimalController(AnimalRepository ar, OwnerRepository or, UserRepository ur, WeightsRepository wr,
            PhotosRepository pr, CommentsRepository cr,
            IssuesRepository ir, TreatmentsRepository tr) {
        this.animalRepository = ar;
        this.ownerRepository = or;
        this.userRepository = ur;
        this.weightsRepository = wr;
        this.photosRepository = pr;
        this.commentsRepository = cr;
        this.issuesRepository = ir;
        this.treatmentsRepository = tr;
    }

    /**
     * Search the animals by id
     *
     * @param id animal id
     * @return list of animals with id; the list would have atmost one element
     */
    private List<Animal> searchAnimalById(Long id) {
        Optional<Animal> animalOptional = this.animalRepository.findById(id);

        if (animalOptional.isPresent()) {
            List<Animal> searchResults = new ArrayList<>();
            searchResults.add(animalOptional.get());
            return searchResults;
        }

        else {
            return null;
        }
    }

    /**
     * Search the animals by their name
     *
     * @param allAnimals list of all animals
     * @param sT         search term
     * @return filtered list of animals with name meeting the search term
     */
    private List<Animal> searchAnimalByName(List<Animal> allAnimals, String sT) {
        String searchTerm = sT.toLowerCase();
        List<Animal> searchResults = new ArrayList<>();

        for (Animal eachAnimal : allAnimals) {
            if (eachAnimal.getName() != null && eachAnimal.getName().toLowerCase().contains(searchTerm)) {
                searchResults.add(eachAnimal);
                continue;
            }
        }

        return searchResults;
    }

    /**
     * Search the animals by their species
     *
     * @param allAnimals list of all animals
     * @param sT         search term
     * @return filtered list of animals with species meeting the search term
     */
    private List<Animal> searchAnimalBySpecies(List<Animal> allAnimals, String sT) {
        String searchTerm = sT.toLowerCase();
        List<Animal> searchResults = new ArrayList<>();

        for (Animal eachAnimal : allAnimals) {
            if (eachAnimal.getSpecies() != null && eachAnimal.getSpecies().toLowerCase().contains(searchTerm)) {
                searchResults.add(eachAnimal);
                continue;
            }
        }

        return searchResults;
    }

    /**
     * Search the animals by their subspecies
     *
     * @param allAnimals list of all animals
     * @param sT         search term
     * @return filtered list of animals with subspecies meeting the search term
     */
    private List<Animal> searchAnimalBySubSpecies(List<Animal> allAnimals, String sT) {
        String searchTerm = sT.toLowerCase();
        List<Animal> searchResults = new ArrayList<>();

        for (Animal eachAnimal : allAnimals) {
            if (eachAnimal.getSubSpecies() != null && eachAnimal.getSubSpecies().toLowerCase().contains(searchTerm)) {
                searchResults.add(eachAnimal);
                continue;
            }
        }

        return searchResults;
    }

    /**
     * Search the animals by their breed
     *
     * @param allAnimals list of all animals
     * @param sT         search term
     * @return filtered list of animals with breed meeting the search term
     */
    private List<Animal> searchAnimalByBreed(List<Animal> allAnimals, String sT) {
        String searchTerm = sT.toLowerCase();
        List<Animal> searchResults = new ArrayList<>();

        for (Animal eachAnimal : allAnimals) {
            if (eachAnimal.getBreed() != null && eachAnimal.getBreed().toLowerCase().contains(searchTerm)) {
                searchResults.add(eachAnimal);
                continue;
            }
        }

        return searchResults;
    }

    /**
     * Search the animals by their type
     *
     * @param allAnimals list of all animals
     * @param sT         search term
     * @return filtered list of animals with type meeting the search term
     */
    private List<Animal> searchAnimalByType(List<Animal> allAnimals, String sT) {
        String searchTerm = sT.toLowerCase();
        List<Animal> searchResults = new ArrayList<>();

        for (Animal eachAnimal : allAnimals) {
            if (eachAnimal.getType() != null && eachAnimal.getType().toLowerCase().contains(searchTerm)) {
                searchResults.add(eachAnimal);
                continue;
            }
        }

        return searchResults;
    }

    /**
     * Search the animals by their region
     *
     * @param allAnimals list of all animals
     * @param sT         search term
     * @return filtered list of animals with region meeting the search term
     */
    private List<Animal> searchAnimalByRegion(List<Animal> allAnimals, String sT) {
        String searchTerm = sT.toLowerCase();
        List<Animal> searchResults = new ArrayList<>();

        for (Animal eachAnimal : allAnimals) {
            if (eachAnimal.getRegion() != null && eachAnimal.getRegion().toLowerCase().contains(searchTerm)) {
                searchResults.add(eachAnimal);
                continue;
            }
        }

        return searchResults;
    }

    /**
     * Search the animals by their status
     *
     * @param allAnimals list of all animals
     * @param sT         search term
     * @return filtered list of animals having status as search term
     */
    private List<Animal> searchAnimalByStatus(List<Animal> allAnimals, String sT) {
        List<Animal> searchResults = new ArrayList<>();

        try {
            AnimalStatus searchTerm = AnimalStatus.valueOf(sT.toUpperCase());

            for (Animal eachAnimal : allAnimals) {
                if (eachAnimal.getStatus() == searchTerm) {
                    searchResults.add(eachAnimal);
                    continue;
                }
            }

            return searchResults;
        }

        catch (IllegalArgumentException e) {
            return searchResults;
        }
    }

    /**
     * Search the animals by their owner name
     *
     * @param allAnimals list of all animals
     * @param sT         search term
     * @return filtered list of animals with owner name meeting the search term
     */
    private List<Animal> searchAnimalByOwner(List<Animal> allAnimals, String sT) {
        String searchTerm = sT.toLowerCase();
        List<Animal> searchResults = new ArrayList<>();

        for (Animal eachAnimal : allAnimals) {
            if (eachAnimal.getOwnerName().toLowerCase().contains(searchTerm)) {
                searchResults.add(eachAnimal);
            }
        }

        return searchResults;
    }

    /**
     * Endpoint for GET - fetch animal by id
     *
     * @param id animal id
     * @return animal
     */
    @GetMapping(path = "{animalId}")
    public ResponseEntity<?> getAnimalById(@PathVariable("animalId") Long id) {
        List<Animal> a = this.searchAnimalById(id);

        if (a == null) {
            String message = "Animal not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND, message));
        }

        else {
            return ResponseEntity.status(HttpStatus.OK).body(a.get(0));
        }
    }

    /**
     * Endpoint for GET - search and fetch all animals meeting search criteria
     *
     * @param searchBy   where to search
     * @param searchTerm what to search
     * @return list of animals meeting search criteria
     */
    @GetMapping()
    public ResponseEntity<?> searchAnimal(@RequestParam(name = "searchBy", required = false) String searchBy,
            @RequestParam(name = "searchTerm", required = false) String searchTerm) {

        List<Animal> allAnimals = this.animalRepository.findAll();

        if (searchBy == null || searchTerm == null) {
            return ResponseEntity.status(HttpStatus.OK).body(allAnimals);
        }

        else if (searchBy.equals("") || searchTerm.equals("")) {
            return ResponseEntity.status(HttpStatus.OK).body(allAnimals);
        }

        else if (searchBy.equalsIgnoreCase("id")) {
            return ResponseEntity.status(HttpStatus.OK).body(this.searchAnimalById(Long.valueOf(searchTerm)));
        }

        else if (searchBy.equalsIgnoreCase("name")) {
            return ResponseEntity.status(HttpStatus.OK).body(this.searchAnimalByName(allAnimals, searchTerm));
        }

        else if (searchBy.equalsIgnoreCase("species")) {
            return ResponseEntity.status(HttpStatus.OK).body(this.searchAnimalBySpecies(allAnimals, searchTerm));
        }

        else if (searchBy.equalsIgnoreCase("subspecies")) {
            return ResponseEntity.status(HttpStatus.OK).body(this.searchAnimalBySubSpecies(allAnimals, searchTerm));
        }

        else if (searchBy.equalsIgnoreCase("breed")) {
            return ResponseEntity.status(HttpStatus.OK).body(this.searchAnimalByBreed(allAnimals, searchTerm));
        }

        else if (searchBy.equalsIgnoreCase("type")) {
            return ResponseEntity.status(HttpStatus.OK).body(this.searchAnimalByType(allAnimals, searchTerm));
        }

        else if (searchBy.equalsIgnoreCase("region")) {
            return ResponseEntity.status(HttpStatus.OK).body(this.searchAnimalByRegion(allAnimals, searchTerm));
        }

        else if (searchBy.equalsIgnoreCase("status")) {
            return ResponseEntity.status(HttpStatus.OK).body(this.searchAnimalByStatus(allAnimals, searchTerm));
        }

        else if (searchBy.equalsIgnoreCase("owner")) {
            return ResponseEntity.status(HttpStatus.OK).body(this.searchAnimalByOwner(allAnimals, searchTerm));
        }

        else {
            String message = "Invalid animal search operation";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomMessage(HttpStatus.BAD_REQUEST, message));
        }
    }

    /**
     * Endpoint for GET - fetch weight history of the animal with animal id
     *
     * @param id animal id
     * @return list of weights recorded
     */
    @GetMapping(path = "{animalId}/weights")
    public ResponseEntity<?> getWeights(@PathVariable("animalId") Long id) {
        Optional<Animal> animalOptional = this.animalRepository.findById(id);

        if (animalOptional.isPresent()) {
            Animal oneAnimal = animalOptional.get();
            return ResponseEntity.status(HttpStatus.OK).body(oneAnimal.fetchAnimalWeightList());
        }

        else {
            String message = "Animal not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND, message));
        }
    }

    /**
     * Endpoint for GET - fetch all photos of the animal with animal id
     *
     * @param id animal id
     * @return list of photos
     */
    @GetMapping(path = "{animalId}/photos")
    public ResponseEntity<?> getPhotos(@PathVariable("animalId") Long id) {
        Optional<Animal> animalOptional = this.animalRepository.findById(id);

        if (animalOptional.isPresent()) {
            Animal oneAnimal = animalOptional.get();
            return ResponseEntity.status(HttpStatus.OK).body(oneAnimal.fetchAnimalPhotoList());
        }

        else {
            String message = "Animal not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND, message));
        }
    }

    /**
     * Endpoint for GET - fetch the photo of the animal as a image for display
     *
     * @param animalId animal id - used just for checking
     * @param photoId  photo id of the photo to fetch
     * @return the image file
     */
    @GetMapping(path = "{animalId}/photos/{photoId}")
    public ResponseEntity<?> fetchPhotoById(@PathVariable("animalId") Long animalId,
            @PathVariable("photoId") Long photoId) {
        Optional<Animal> animalOptional = this.animalRepository.findById(animalId);

        if (animalOptional.isPresent()) {
            Optional<Photos> photoOptional = this.photosRepository.findById(photoId);

            if (photoOptional.isPresent()) {
                Photos onePhoto = photoOptional.get();
                Path file = Photos.storeLocation.resolve(onePhoto.getPhotoLink());

                try {
                    Resource resource = new UrlResource(file.toUri());

                    if (resource.exists() || resource.isReadable()) {
                        return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
                    }

                    else {
                        String message = "Animal photo could not be read";
                        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new CustomMessage(HttpStatus.NOT_FOUND, message));
                    }
                }

                catch (MalformedURLException e) {
                    String message = "Animal photo could not be fetched";
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(new CustomMessage(HttpStatus.NOT_FOUND, message));
                }
            }

            else {
                String message = "Animal photo not found";
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new CustomMessage(HttpStatus.NOT_FOUND, message));
            }
        }

        else {
            String message = "Animal not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND, message));
        }
    }

    /**
     * Endpoint for GET - fetch all issues related to the animal with animal id
     *
     * @param id animal id
     * @return list of issues
     */
    @GetMapping(path = "{animalId}/issues")
    public ResponseEntity<?> getIssues(@PathVariable("animalId") Long id) {
        Optional<Animal> animalOptional = this.animalRepository.findById(id);

        if (animalOptional.isPresent()) {
            Animal oneAnimal = animalOptional.get();
            return ResponseEntity.status(HttpStatus.OK).body(oneAnimal.fetchAnimalIssueList());
        }

        else {
            String message = "Animal not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND, message));
        }
    }

    /**
     * Endpoint for GET - fetch all comments made on the animal with animal id
     *
     * @param id animal id
     * @return list of comments
     */
    @GetMapping(path = "{animalId}/comments")
    public ResponseEntity<?> getComments(@PathVariable("animalId") Long id) {
        Optional<Animal> animalOptional = this.animalRepository.findById(id);

        if (animalOptional.isPresent()) {
            Animal oneAnimal = animalOptional.get();
            return ResponseEntity.status(HttpStatus.OK).body(oneAnimal.fetchAnimalCommentList());
        }

        else {
            String message = "Animal not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND, message));
        }
    }

    /**
     * Endpoint for GET - fetch all treatments administered to the animal with
     * animal id
     *
     * @param id animal id
     * @return list of treatments
     */
    @GetMapping(path = "{animalId}/treatments")
    public ResponseEntity<?> getTreatments(@PathVariable("animalId") Long id) {
        Optional<Animal> animalOptional = this.animalRepository.findById(id);

        if (animalOptional.isPresent()) {
            Animal oneAnimal = animalOptional.get();
            return ResponseEntity.status(HttpStatus.OK).body(oneAnimal.fetchAnimalTreatmentList());
        }

        else {
            String message = "Animal not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND, message));
        }
    }

    /**
     * Endpoint for POST - save the animal in db
     *
     * @param a animal details in json
     * @return the message
     */
    @PostMapping
    public ResponseEntity<?> addAnimal(@RequestBody Animal a) {
        // reset the id to 0 to prevent overwrite
        a.setAnimalId(0);

        // set the request status to available
        a.setRequestStatus(AnimalRequestStatus.AVAILABLE);

        // set the right owner or insert a new one
        Owner o = a.fetchTheOwner();
        o = this.findUniqueOwner(o);
        this.ownerRepository.save(o);
        a.setTheOwner(o);
        this.animalRepository.save(a);

        String message = "Animal added successfully";
        return ResponseEntity.status(HttpStatus.OK).body(new CustomMessage(HttpStatus.OK, message));
    }

    /**
     * Helper method to find unique owner by comparing contact number and email id
     *
     * @param o the owner to be compared
     * @return the unique owner, if found then returned from db, else o
     */
    private Owner findUniqueOwner(Owner o) {
        List<Owner> allOwners = this.ownerRepository.findAll();

        for (Owner eachOwner : allOwners) {
            if (eachOwner.getContactNumber() != null && eachOwner.getContactNumber().equals(o.getContactNumber())) {
                return eachOwner;
            }

            else if (eachOwner.getEmailId() != null && eachOwner.getEmailId().equalsIgnoreCase(o.getEmailId())) {
                return eachOwner;
            }
        }

        return o;
    }

    /**
     * Endpoint for POST - save the animal weight in db
     *
     * @param w  animal weight details in json
     * @param id animal id
     * @return the message
     */
    @PostMapping(path = "{animalId}/weights")
    public ResponseEntity<?> addWeight(@RequestBody Weights w, @PathVariable("animalId") Long id) {
        Optional<Animal> animalOptional = this.animalRepository.findById(id);

        // reset the id to 0 to prevent overwrite
        w.setWeightId(0);

        Long userId = w.fetchRecordedBy().getUserId();
        User u = this.userRepository.findById(userId).get();
        w.setRecordedBy(u);

        w.setRecordDate(LocalDate.now());

        if (animalOptional.isPresent()) {
            Animal oneAnimal = animalOptional.get();
            w.setTheAnimal(oneAnimal);
            w = this.weightsRepository.save(w);
            oneAnimal.fetchAnimalWeightList().add(w);
            this.animalRepository.save(oneAnimal);

            String message = "Animal weight added successfully";
            return ResponseEntity.status(HttpStatus.OK).body(new CustomMessage(HttpStatus.OK, message));
        }

        else {
            String message = "Animal not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND, message));
        }
    }

    /**
     * Generates a random string of length 20
     */
    private String generateRandomName() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 20;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    /**
     * Extracts the extension of the uploaded file
     *
     * @param filename the filename from which extension is to be extracted
     * @return the extension with .
     */
    private String getPhotoExtension(String filename) {
        String ext = "";

        if (filename == null || filename.equals("")) {
            return ext;
        }

        int dotIndex = filename.lastIndexOf(".");

        if (dotIndex > 0) {
            ext = filename.substring(dotIndex);
        }

        return ext;
    }

    /**
     * Endpoint for POST - save the animal photo in db
     *
     * @param p  animal photo details in json
     * @param id animal id
     * @return the message
     */
    @PostMapping(path = "{animalId}/photos")
    public ResponseEntity<?> addPhoto(@PathVariable("animalId") Long id, @RequestParam("image") MultipartFile file,
            @RequestParam Long userId, @RequestParam String photodesc, @RequestParam String alttext) {
        Optional<Animal> animalOptional = this.animalRepository.findById(id);

        Photos p = new Photos();
        p.setAltText(alttext);
        p.setPhotoDesc(photodesc);

        // set the uploader
        User u = this.userRepository.findById(userId).get();
        p.setUploader(u);

        // reset the id to 0 to prevent overwrite
        p.setPhotoId(0);

        // set upload date to now
        p.setUploadDate(LocalDate.now());

        // check if file is empty
        if (file.isEmpty()) {
            String message = "Cannot upload empty file";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new CustomMessage(HttpStatus.EXPECTATION_FAILED, message));
        }

        // set the name
        String photoName = this.generateRandomName() + this.getPhotoExtension(file.getOriginalFilename());

        Path destinationFile = Photos.storeLocation.resolve(Paths.get(photoName)).normalize().toAbsolutePath();

        // save the path
        p.setPhotoLink(photoName);

        if (animalOptional.isPresent()) {
            Animal oneAnimal = animalOptional.get();
            p.setTheAnimal(oneAnimal);

            try (InputStream inputStream = file.getInputStream()) {
                // copy the file to server
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);

                // write the photo details in the db
                p = this.photosRepository.save(p);

                // associate photo details with animal
                oneAnimal.fetchAnimalPhotoList().add(p);
                this.animalRepository.save(oneAnimal);

                String message = "Animal photo added successfully";
                return ResponseEntity.status(HttpStatus.OK).body(new CustomMessage(HttpStatus.OK, message));
            }

            catch (IOException e) {
                String message = "Failed to store the file";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new CustomMessage(HttpStatus.NOT_FOUND, message));
            }
        }

        else {
            String message = "Animal not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND, message));
        }
    }

    /**
     * Endpoint for POST - save the animal issue in db
     *
     * @param i  animal issue details in json
     * @param id animal id
     * @return the message
     */
    @PostMapping(path = "{animalId}/issues")
    public ResponseEntity<?> addIssue(@RequestBody Issues i, @PathVariable("animalId") Long id) {
        Optional<Animal> animalOptional = this.animalRepository.findById(id);

        // reset the id to 0 to prevent overwrite
        i.setIssueId(0);

        // all new reported issues are unresolved
        i.setResolved(false);

        Long userId = i.fetchRaisedBy().getUserId();
        User u = this.userRepository.findById(userId).get();
        i.setRaisedBy(u);

        i.setDetectedDate(LocalDate.now());

        if (animalOptional.isPresent()) {
            Animal oneAnimal = animalOptional.get();
            i.setTheAnimal(oneAnimal);
            i = this.issuesRepository.save(i);
            oneAnimal.fetchAnimalIssueList().add(i);
            this.animalRepository.save(oneAnimal);

            String message = "Animal issue added successfully";
            return ResponseEntity.status(HttpStatus.OK).body(new CustomMessage(HttpStatus.OK, message));
        }

        else {
            String message = "Animal not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND, message));
        }
    }

    /**
     * Endpoint for POST - save the animal comment in db
     *
     * @param c  animal comment in json
     * @param id animal id
     * @return the message
     */
    @PostMapping(path = "{animalId}/comments")
    public ResponseEntity<?> addComment(@RequestBody Comments c, @PathVariable("animalId") Long id) {
        Optional<Animal> animalOptional = this.animalRepository.findById(id);
        c.setCommentId(0);

        Long userId = c.fetchCommenter().getUserId();
        User u = this.userRepository.findById(userId).get();
        c.setCommenter(u);

        c.setCommentDate(LocalDate.now());

        if (animalOptional.isPresent()) {
            Animal oneAnimal = animalOptional.get();
            c.setTheAnimal(oneAnimal);
            c = this.commentsRepository.save(c);
            oneAnimal.fetchAnimalCommentList().add(c);
            this.animalRepository.save(oneAnimal);

            String message = "Animal comment added successfully";
            return ResponseEntity.status(HttpStatus.OK).body(new CustomMessage(HttpStatus.OK, message));
        }

        else {
            String message = "Animal not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND, message));
        }
    }

    /**
     * Endpoint for POST - save the animal treatment in db
     *
     * @param t  animal treatment in json
     * @param id animal id
     * @return the message
     */
    @PostMapping(path = "{animalId}/treatments")
    public ResponseEntity<?> addTreatment(@RequestBody Treatments t, @PathVariable("animalId") Long id) {
        Optional<Animal> animalOptional = this.animalRepository.findById(id);
        t.setTreatmentId(0);

        Long userId = t.fetchTreatedBy().getUserId();
        User u = this.userRepository.findById(userId).get();
        t.setTreatedBy(u);

        t.setTreatmentDate(LocalDate.now());

        if (animalOptional.isPresent()) {
            Animal oneAnimal = animalOptional.get();
            t.setTheAnimal(oneAnimal);
            t = this.treatmentsRepository.save(t);
            oneAnimal.fetchAnimalTreatmentList().add(t);
            this.animalRepository.save(oneAnimal);

            String message = "Animal treatment added successfully";
            return ResponseEntity.status(HttpStatus.OK).body(new CustomMessage(HttpStatus.OK, message));
        }

        else {
            String message = "Animal not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND, message));
        }
    }

    /**
     * Endpoint for PUT - update the animal in db
     *
     * @param a  updated animal details in json
     * @param id the animal to be updated
     * @return the updated animal details
     */
    @PutMapping(path = "{animalId}")
    public Animal updateAnimal(@RequestBody Animal a, @PathVariable("animalId") Long id) {
        Optional<Animal> animalOptional = this.animalRepository.findById(id);

        Animal updatedAnimal;

        if (animalOptional.isPresent()) {
            Animal animal = animalOptional.get();

            if (a.getName() != null) {
                animal.setName(a.getName());
            }

            if (a.getSpecies() != null) {
                animal.setSpecies(a.getSpecies());
            }

            if (a.getSubSpecies() != null) {
                animal.setSubSpecies(a.getSubSpecies());
            }

            if (a.getBreed() != null) {
                animal.setBreed(a.getBreed());
            }

            if (a.getType() != null) {
                animal.setType(a.getType());
            }

            if (a.getRegion() != null) {
                animal.setRegion(a.getRegion());
            }

            if (a.getSex() != null) {
                animal.setSex(a.getSex());
            }

            if (a.getBirthDate() != null) {
                animal.setBirthDate(a.getBirthDate());
            }

            if (a.getStatus() != null) {
                animal.setStatus(a.getStatus());
            }

            if (a.getRequestStatus() != null) {
                animal.setRequestStatus(a.getRequestStatus());
            }

            if (a.fetchTheOwner() != null) {
                animal.setTheOwner(this.findUniqueOwner(a.fetchTheOwner()));
            }

            if (a.fetchProfilePic() != null) {
                animal.setProfilePic(a.fetchProfilePic());
            }

            // if(a.getTattooNum() != null) {
            // animal.setTattooNum(a.getTattooNum());
            // }

            if (a.getCityTattoo() != null) {
                animal.setCityTattoo(a.getCityTattoo());
            }

            if (a.getRfidNumber() != null) {
                animal.setRfidNumber(a.getRfidNumber());
            }

            if (a.getMicroChipNumber() != null) {
                animal.setMicroChipNumber(a.getMicroChipNumber());
            }

            if (a.getCoatColor() != null) {
                animal.setCoatColor(a.getCoatColor());
            }

            if (a.getContinuousMedication() != null) {
                animal.setContinuousMedication(a.getContinuousMedication());
            }

            if (a.getDistinctFeature() != null) {
                animal.setDistinctFeature(a.getDistinctFeature());
            }

            updatedAnimal = this.animalRepository.save(animal);
        }

        else {
            a.setAnimalId(id);
            updatedAnimal = this.animalRepository.save(a);
        }

        return updatedAnimal;
    }

    /**
     * Endpoint for PUT - update animal comment in db
     *
     * @param c         the updated comment details in json
     * @param animalId  the animal whose comment it is
     * @param commentId the id the comment that is getting updated
     * @return the message
     */
    @PutMapping(path = "{animalId}/comments/{commentId}")
    public ResponseEntity<?> updateComment(@RequestBody Comments c, @PathVariable("animalId") Long animalId,
            @PathVariable("commentId") Long commentId) {
        Optional<Animal> animalOptional = this.animalRepository.findById(animalId);
        Optional<Comments> commentOptional = this.commentsRepository.findById(commentId);

        if (animalOptional.isPresent()) {
            Animal oneAnimal = animalOptional.get();

            if (commentOptional.isPresent()) {
                Comments comment = commentOptional.get();

                // refresh the comment date
                comment.setCommentDate(LocalDate.now());

                comment.setCommentDesc(c.getCommentDesc());
                comment.setCommenter(c.fetchCommenter());
                comment.setTheAnimal(oneAnimal);
                this.commentsRepository.save(comment);
            }

            else {
                c.setCommentId(commentId);
                this.commentsRepository.save(c);

                oneAnimal.fetchAnimalCommentList().add(c);
                this.animalRepository.save(oneAnimal);
            }

            String message = "Animal comment updated successfully";
            return ResponseEntity.status(HttpStatus.OK).body(new CustomMessage(HttpStatus.OK, message));
        }

        else {
            String message = "Animal not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND, message));
        }
    }

    /**
     * Endpoint for PUT - update animal issues in db
     *
     * @param i        the updated issue details in json
     * @param animalId the animal whose issue it is
     * @param issueId  the id the issue that is getting updated
     * @return the message
     */
    @PutMapping(path = "{animalId}/issues/{issueId}")
    public ResponseEntity<?> updateIssue(@RequestBody Issues i, @PathVariable("animalId") Long animalId,
            @PathVariable("issueId") Long issueId) {
        Optional<Animal> animalOptional = this.animalRepository.findById(animalId);
        Optional<Issues> issueOptional = this.issuesRepository.findById(issueId);

        if (animalOptional.isPresent()) {
            Animal oneAnimal = animalOptional.get();

            if (issueOptional.isPresent()) {
                Issues issue = issueOptional.get();

                // refresh the issue date
                issue.setDetectedDate(LocalDate.now());

                issue.setIssueDesc(i.getIssueDesc());
                issue.setRaisedBy(i.fetchRaisedBy());
                issue.setTheAnimal(oneAnimal);
                this.issuesRepository.save(issue);
            }

            else {
                i.setIssueId(issueId);
                this.issuesRepository.save(i);

                oneAnimal.fetchAnimalIssueList().add(i);
                this.animalRepository.save(oneAnimal);
            }

            String message = "Animal issue updated successfully";
            return ResponseEntity.status(HttpStatus.OK).body(new CustomMessage(HttpStatus.OK, message));
        }

        else {
            String message = "Animal not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND, message));
        }
    }

    /**
     * Endpoint for PUT - update animal treatments in db
     *
     * @param t           the updated treatment details in json
     * @param animalId    the animal whose treatment it is
     * @param treatmentId the id the treatment that is getting updated
     * @return the message
     */
    @PutMapping(path = "{animalId}/treatments/{treatmentId}")
    public ResponseEntity<?> updateTreatment(@RequestBody Treatments t, @PathVariable("animalId") Long animalId,
            @PathVariable("treatmentId") Long treatmentId) {
        Optional<Animal> animalOptional = this.animalRepository.findById(animalId);
        Optional<Treatments> treatmentOptional = this.treatmentsRepository.findById(treatmentId);

        if (animalOptional.isPresent()) {
            Animal oneAnimal = animalOptional.get();

            if (treatmentOptional.isPresent()) {
                Treatments treatment = treatmentOptional.get();

                // refresh the treatment date
                treatment.setTreatmentDate(LocalDate.now());

                treatment.setDeliveryMethod(t.getDeliveryMethod());
                treatment.setDrugDose(t.getDrugDose());
                treatment.setTreatmentDesc(t.getTreatmentDesc());
                treatment.setTreatedBy(t.fetchTreatedBy());
                treatment.setDrugName(t.getDrugName());
                treatment.setTheAnimal(oneAnimal);
                this.treatmentsRepository.save(treatment);
            }

            else {
                t.setTreatmentId(treatmentId);
                this.treatmentsRepository.save(t);

                oneAnimal.fetchAnimalTreatmentList().add(t);
                this.animalRepository.save(oneAnimal);
            }

            String message = "Animal treatment updated successfully";
            return ResponseEntity.status(HttpStatus.OK).body(new CustomMessage(HttpStatus.OK, message));
        }

        else {
            String message = "Animal not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND, message));
        }
    }

    /**
     * Helper method to remove a weight of the animal
     *
     * @param animalId animal id
     * @param weightId weight to be removed
     * @return true is remove, false otherwise
     */
    private boolean removeWeightFromAnimal(Long animalId, Long weightId) {
        Animal a = this.animalRepository.findById(animalId).get();
        boolean flag = false;
        List<Weights> newWeightList = new ArrayList<>();

        for (Weights w : a.fetchAnimalWeightList()) {
            if (w.getWeightId() != weightId) {
                newWeightList.add(w);
            }

            else {
                w.setTheAnimal(null);
                flag = true;
            }
        }

        a.setAnimalWeightList(newWeightList);
        return flag;
    }

    /**
     * Helper method to remove a photo of the animal
     *
     * @param animalId animal id
     * @param photoId  photo to be removed
     * @return true is remove, false otherwise
     */
    private boolean removePhotoFromAnimal(Long animalId, Long photoId) {
        Animal a = this.animalRepository.findById(animalId).get();
        boolean flag = false;
        List<Photos> newPhotoList = new ArrayList<>();

        for (Photos p : a.fetchAnimalPhotoList()) {
            if (p.getPhotoId() != photoId) {
                newPhotoList.add(p);
            }

            else {
                p.setTheAnimal(null);
                flag = true;
            }
        }

        a.setAnimalPhotoList(newPhotoList);
        return flag;
    }

    /**
     * Helper method to remove a comment on the animal
     *
     * @param animalId  animal id
     * @param commentId comment to be removed
     * @return true is remove, false otherwise
     */
    private boolean removeCommentFromAnimal(Long animalId, Long commentId) {
        Animal a = this.animalRepository.findById(animalId).get();
        boolean flag = false;
        List<Comments> newCommentList = new ArrayList<>();

        for (Comments c : a.fetchAnimalCommentList()) {
            if (c.getCommentId() != commentId) {
                newCommentList.add(c);
            }

            else {
                c.setTheAnimal(null);
                flag = true;
            }
        }

        a.setAnimalCommentList(newCommentList);
        return flag;
    }

    /**
     * Helper method to remove a issue of the animal
     *
     * @param animalId animal id
     * @param issueId  issue to be removed
     * @return true is remove, false otherwise
     */
    private boolean removeIssueFromAnimal(Long animalId, Long issueId) {
        Animal a = this.animalRepository.findById(animalId).get();
        boolean flag = false;
        List<Issues> newIssueList = new ArrayList<>();

        for (Issues i : a.fetchAnimalIssueList()) {
            if (i.getIssueId() != issueId) {
                newIssueList.add(i);
            }

            else {
                i.setTheAnimal(null);
                flag = true;
            }
        }

        a.setAnimalIssueList(newIssueList);
        return flag;
    }

    /**
     * Helper method to remove a treatment of the animal
     *
     * @param animalId    animal id
     * @param treatmentId treatment to be removed
     * @return true is remove, false otherwise
     */
    private boolean removeTreatmentFromAnimal(Long animalId, Long treatmentId) {
        Animal a = this.animalRepository.findById(animalId).get();
        boolean flag = false;
        List<Treatments> newTreatmentList = new ArrayList<>();

        for (Treatments t : a.fetchAnimalTreatmentList()) {
            if (t.getTreatmentId() != treatmentId) {
                newTreatmentList.add(t);
            }

            else {
                t.setTheAnimal(null);
                flag = true;
            }
        }

        a.setAnimalTreatmentList(newTreatmentList);
        return flag;
    }

    /**
     * Endpoint for DELETE - delete animal from db
     *
     * @param id animal id
     */
    @DeleteMapping(path = "{animalId}")
    public ResponseEntity<?> deleteAnimal(@PathVariable("animalId") Long id) {
        if (!this.animalRepository.existsById(id)) {
            String message = "Animal not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND, message));
        }

        Animal a = this.animalRepository.findById(id).get();

        // disconnect the owner
        a.setTheOwner(null);

        // removes all weights
        for (Weights w : a.fetchAnimalWeightList()) {
            this.removeWeightFromAnimal(a.getAnimalId(), w.getWeightId());
            this.weightsRepository.delete(w);
        }

        // removes all photos
        for (Photos p : a.fetchAnimalPhotoList()) {
            this.removePhotoFromAnimal(a.getAnimalId(), p.getPhotoId());
            this.photosRepository.delete(p);
        }

        // removes all comments
        for (Comments c : a.fetchAnimalCommentList()) {
            this.removeCommentFromAnimal(a.getAnimalId(), c.getCommentId());
            this.commentsRepository.delete(c);
        }

        // removes all issues
        for (Issues i : a.fetchAnimalIssueList()) {
            this.removeIssueFromAnimal(a.getAnimalId(), i.getIssueId());
            this.issuesRepository.delete(i);
        }

        // removes all treatments
        for (Treatments t : a.fetchAnimalTreatmentList()) {
            this.removeTreatmentFromAnimal(a.getAnimalId(), t.getTreatmentId());
            this.treatmentsRepository.delete(t);
        }

        this.animalRepository.deleteById(id);

        String message = "Animal deleted successfully";
        return ResponseEntity.status(HttpStatus.OK).body(new CustomMessage(HttpStatus.OK, message));
    }

    /**
     * Endpoint for DELETE - delete animal weight from db
     *
     * @param animalId animal id
     * @param weightId weight id
     */
    @DeleteMapping(path = "{animalId}/weights/{weightId}")
    public ResponseEntity<?> deleteWeights(@PathVariable("animalId") Long animalId,
            @PathVariable("weightId") Long weightId) {
        Optional<Weights> weightOptional = this.weightsRepository.findById(weightId);

        if (weightOptional.isPresent()) {
            Weights oneweight = weightOptional.get();

            if (this.removeWeightFromAnimal(animalId, weightId)) {
                this.weightsRepository.delete(oneweight);

                String message = "Animal weight deleted successfully";
                return ResponseEntity.status(HttpStatus.OK).body(new CustomMessage(HttpStatus.OK, message));
            }

            String message = "Animal weight couldnt be deleted";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND, message));
        }

        else {
            String message = "Animal or weight not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND, message));
        }
    }

    /**
     * Endpoint for DELETE - delete animal photo from db
     *
     * @param animalId animal id
     * @param photoId  photo id
     */
    @DeleteMapping(path = "{animalId}/photos/{photoId}")
    public ResponseEntity<?> deletePhotos(@PathVariable("animalId") Long animalId,
            @PathVariable("photoId") Long photoId) {
        Optional<Photos> photoOptional = this.photosRepository.findById(photoId);

        if (photoOptional.isPresent()) {
            Photos onePhoto = photoOptional.get();

            if (this.removePhotoFromAnimal(animalId, photoId)) {
                File fileToDelete = Photos.storeLocation.resolve(onePhoto.getPhotoLink()).toFile();

                // delete photo from database
                this.photosRepository.delete(onePhoto);

                // delete photo from file system
                fileToDelete.delete();

                String message = "Animal photo deleted successfully";
                return ResponseEntity.status(HttpStatus.OK).body(new CustomMessage(HttpStatus.OK, message));
            }

            String message = "Animal photo couldnt be deleted";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND, message));
        }

        else {
            String message = "Animal or photo not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND, message));
        }
    }

    /**
     * Endpoint for DELETE - delete animal comment from db
     *
     * @param animalId  animal id
     * @param commentId comment id
     */
    @DeleteMapping(path = "{animalId}/comments/{commentId}")
    public ResponseEntity<?> deleteComments(@PathVariable("animalId") Long animalId,
            @PathVariable("commentId") Long commentId) {
        Optional<Comments> commentOptional = this.commentsRepository.findById(commentId);

        if (commentOptional.isPresent()) {
            Comments oneComment = commentOptional.get();

            if (this.removeCommentFromAnimal(animalId, commentId)) {
                this.commentsRepository.delete(oneComment);

                String message = "Animal comment deleted successfully";
                return ResponseEntity.status(HttpStatus.OK).body(new CustomMessage(HttpStatus.OK, message));
            }

            String message = "Animal comment couldnt be deleted";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND, message));
        }

        else {
            String message = "Animal or comment not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND, message));
        }
    }

    /**
     * Endpoint for DELETE - delete animal issue from db
     *
     * @param animalId animal id
     * @param issueId  issue id
     */
    @DeleteMapping(path = "{animalId}/issues/{issueId}")
    public ResponseEntity<?> deleteIssues(@PathVariable("animalId") Long animalId,
            @PathVariable("issueId") Long issueId) {
        Optional<Issues> issueOptional = this.issuesRepository.findById(issueId);

        if (issueOptional.isPresent()) {
            Issues oneIssue = issueOptional.get();

            if (this.removeIssueFromAnimal(animalId, issueId)) {
                this.issuesRepository.delete(oneIssue);

                String message = "Animal issue deleted successfully";
                return ResponseEntity.status(HttpStatus.OK).body(new CustomMessage(HttpStatus.OK, message));
            }

            String message = "Animal issue couldnt be deleted";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND, message));
        }

        else {
            String message = "Animal or issue not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND, message));
        }
    }

    /**
     * Endpoint for DELETE - delete animal treatment from db
     *
     * @param animalId    animal id
     * @param treatmentId treatment id
     */
    @DeleteMapping(path = "{animalId}/treatments/{treatmentId}")
    public ResponseEntity<?> deleteTreatments(@PathVariable("animalId") Long animalId,
            @PathVariable("treatmentId") Long treatmentId) {
        Optional<Treatments> treatmentOptional = this.treatmentsRepository.findById(treatmentId);

        if (treatmentOptional.isPresent()) {
            Treatments oneTreatment = treatmentOptional.get();

            if (this.removeTreatmentFromAnimal(animalId, treatmentId)) {
                this.treatmentsRepository.delete(oneTreatment);

                String message = "Animal treatment deleted successfully";
                return ResponseEntity.status(HttpStatus.OK).body(new CustomMessage(HttpStatus.OK, message));
            }

            String message = "Animal treatment couldnt be deleted";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND, message));
        }

        else {
            String message = "Animal or treatment not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND, message));
        }
    }
}
