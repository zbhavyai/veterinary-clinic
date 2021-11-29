package ca.ucalgary.vetapp.controller;

import ca.ucalgary.vetapp.model.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/animals")
public class AnimalController {
    private final AnimalRepository animalRepository;
    private final OwnerRepository ownerRepository;
    private final UserRepository userRepository;
    private final WeightsRepository weightsRepository;
    private final PhotosRepository photosRepository;
    private final CommentsRepository commentsRepository;
    private final IssuesRepository issueRepository;
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
        this.issueRepository = ir;
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
    private List<Animal> searchAnimalByStatus(List<Animal> allAnimals, String sT) throws UnsupportedRequestException {
        try {
            AnimalStatus searchTerm = AnimalStatus.valueOf(sT.toUpperCase());
            List<Animal> searchResults = new ArrayList<>();

            for (Animal eachAnimal : allAnimals) {
                if (eachAnimal.getStatus() == searchTerm) {
                    searchResults.add(eachAnimal);
                    continue;
                }
            }

            return searchResults;
        }

        catch (IllegalArgumentException e) {
            throw new UnsupportedRequestException(e.getMessage());
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
     * @throws NotFoundException
     */
    @GetMapping(path = "{animalId}")
    public Animal getAnimalById(@PathVariable("animalId") Long id) throws NotFoundException {
        Animal a = this.searchAnimalById(id).get(0);

        if (a == null) {
            throw new NotFoundException("animal", id);
        }

        else {
            return a;
        }
    }

    /**
     * Endpoint for GET - search and fetch all animals meeting search criteria
     *
     * @param searchBy   where to search
     * @param searchTerm what to search
     * @return list of animals meeting search criteria
     * @throws UnsupportedRequestException
     */
    @GetMapping()
    public List<Animal> searchAnimal(@RequestParam(name = "searchBy", required = false) String searchBy,
            @RequestParam(name = "searchTerm", required = false) String searchTerm) throws UnsupportedRequestException {

        List<Animal> allAnimals = this.animalRepository.findAll();

        if (searchBy == null || searchTerm == null) {
            return allAnimals;
        }

        else if (searchBy.equals("") || searchTerm.equals("")) {
            return allAnimals;
        }

        else if (searchBy.equalsIgnoreCase("id")) {
            return this.searchAnimalById(Long.valueOf(searchTerm));
        }

        else if (searchBy.equalsIgnoreCase("name")) {
            return this.searchAnimalByName(allAnimals, searchTerm);
        }

        else if (searchBy.equalsIgnoreCase("species")) {
            return this.searchAnimalBySpecies(allAnimals, searchTerm);
        }

        else if (searchBy.equalsIgnoreCase("subspecies")) {
            return this.searchAnimalBySubSpecies(allAnimals, searchTerm);
        }

        else if (searchBy.equalsIgnoreCase("breed")) {
            return this.searchAnimalByBreed(allAnimals, searchTerm);
        }

        else if (searchBy.equalsIgnoreCase("type")) {
            return this.searchAnimalByType(allAnimals, searchTerm);
        }

        else if (searchBy.equalsIgnoreCase("region")) {
            return this.searchAnimalByRegion(allAnimals, searchTerm);
        }

        else if (searchBy.equalsIgnoreCase("status")) {
            return this.searchAnimalByStatus(allAnimals, searchTerm);
        }

        else if (searchBy.equalsIgnoreCase("owner")) {
            return this.searchAnimalByOwner(allAnimals, searchTerm);
        }

        else {
            throw new UnsupportedRequestException("Invalid search by");
        }
    }

    /**
     * Endpoint for GET - fetch weight history of the animal with animal id
     *
     * @param id animal id
     * @return list of weights recorded
     */
    @GetMapping(path = "{animalId}/weights")
    public List<Weights> getWeights(@PathVariable("animalId") Long id) {
        Optional<Animal> animalOptional = this.animalRepository.findById(id);

        if (animalOptional.isPresent()) {
            Animal oneAnimal = animalOptional.get();
            return oneAnimal.fetchAnimalWeightList();
        }

        else {
            throw new NotFoundException("animal", id);
        }
    }

    /**
     * Endpoint for GET - fetch all photos of the animal with animal id
     *
     * @param id animal id
     * @return list of photos
     */
    @GetMapping(path = "{animalId}/photos")
    public List<Photos> getPhotos(@PathVariable("animalId") Long id) {
        Optional<Animal> animalOptional = this.animalRepository.findById(id);

        if (animalOptional.isPresent()) {
            Animal oneAnimal = animalOptional.get();
            return oneAnimal.fetchAnimalPhotoList();
        }

        else {
            throw new NotFoundException("animal", id);
        }
    }

    /**
     * Endpoint for GET - fetch all issues related to the animal with animal id
     *
     * @param id animal id
     * @return list of issues
     */
    @GetMapping(path = "{animalId}/issues")
    public List<Issues> getIssues(@PathVariable("animalId") Long id) {
        Optional<Animal> animalOptional = this.animalRepository.findById(id);

        if (animalOptional.isPresent()) {
            Animal oneAnimal = animalOptional.get();
            return oneAnimal.fetchAnimalIssueList();
        }

        else {
            throw new NotFoundException("animal", id);
        }
    }

    /**
     * Endpoint for GET - fetch all comments made on the animal with animal id
     *
     * @param id animal id
     * @return list of comments
     */
    @GetMapping(path = "{animalId}/comments")
    public List<Comments> getComments(@PathVariable("animalId") Long id) {
        Optional<Animal> animalOptional = this.animalRepository.findById(id);

        if (animalOptional.isPresent()) {
            Animal oneAnimal = animalOptional.get();
            return oneAnimal.fetchAnimalCommentList();
        }

        else {
            throw new NotFoundException("animal", id);
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
    public List<Treatments> getTreatments(@PathVariable("animalId") Long id) {
        Optional<Animal> animalOptional = this.animalRepository.findById(id);

        if (animalOptional.isPresent()) {
            Animal oneAnimal = animalOptional.get();
            return oneAnimal.fetchAnimalTreatmentList();
        }

        else {
            throw new NotFoundException("animal", id);
        }
    }

    /**
     * Endpoint for POST - save the animal in db
     *
     * @param a animal details in json
     * @return the added animal details
     */
    @PostMapping
    public Animal addAnimal(@RequestBody Animal a) throws UnsupportedRequestException {
        Owner o = a.fetchTheOwner();

        o = this.findUniqueOwner(o);
        this.ownerRepository.save(o);
        a.setTheOwner(o);

        return this.animalRepository.save(a);
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
     * @return the added animal weight
     * @throws UnsupportedRequestException
     */
    @PostMapping(path = "{animalId}/weights")
    public Weights addWeight(@RequestBody Weights w, @PathVariable("animalId") Long id)
            throws UnsupportedRequestException {
        Optional<Animal> animalOptional = this.animalRepository.findById(id);
        w.setWeightId(0);

        if (animalOptional.isPresent()) {
            Animal oneAnimal = animalOptional.get();
            w.setTheAnimal(oneAnimal);
            w = this.weightsRepository.save(w);
            oneAnimal.fetchAnimalWeightList().add(w);
            this.animalRepository.save(oneAnimal);
        }

        else {
            throw new NotFoundException("animal", id);
        }

        return w;
    }

    /**
     * Endpoint for POST - save the animal photo in db
     *
     * @param p  animal photo details in json
     * @param id animal id
     * @return the added animal photo
     * @throws UnsupportedRequestException
     */
    @PostMapping(path = "{animalId}/photos")
    public Photos addPhoto(@RequestBody Photos p, @PathVariable("animalId") Long id)
            throws UnsupportedRequestException {
        Optional<Animal> animalOptional = this.animalRepository.findById(id);
        p.setPhotoId(0);

        if (animalOptional.isPresent()) {
            Animal oneAnimal = animalOptional.get();
            p.setTheAnimal(oneAnimal);
            p = this.photosRepository.save(p);
            oneAnimal.fetchAnimalPhotoList().add(p);
            this.animalRepository.save(oneAnimal);
        }

        else {
            throw new NotFoundException("animal", id);
        }

        return p;
    }

    /**
     * Endpoint for POST - save the animal issue in db
     *
     * @param i  animal issue details in json
     * @param id animal id
     * @return the added animal issue
     * @throws UnsupportedRequestException
     */
    @PostMapping(path = "{animalId}/issues")
    public Issues addIssue(@RequestBody Issues i, @PathVariable("animalId") Long id)
            throws UnsupportedRequestException {
        Optional<Animal> animalOptional = this.animalRepository.findById(id);
        i.setIssueId(0);
        i.setResolved(false);

        Long userId = i.fetchRaisedBy().getUserId();
        User u = this.userRepository.findById(userId).get();
        i.setRaisedBy(u);

        if (animalOptional.isPresent()) {
            Animal oneAnimal = animalOptional.get();
            i.setTheAnimal(oneAnimal);
            i = this.issueRepository.save(i);
            oneAnimal.fetchAnimalIssueList().add(i);
            this.animalRepository.save(oneAnimal);
        }

        else {
            throw new NotFoundException("animal", id);
        }

        return i;
    }

    /**
     * Endpoint for POST - save the animal comment in db
     *
     * @param c  animal comment in json
     * @param id animal id
     * @return the added animal comment
     * @throws UnsupportedRequestException
     */
    @PostMapping(path = "{animalId}/comments")
    public Comments addComment(@RequestBody Comments c, @PathVariable("animalId") Long id)
            throws UnsupportedRequestException {
        Optional<Animal> animalOptional = this.animalRepository.findById(id);
        c.setCommentId(0);

        Long userId = c.fetchCommenter().getUserId();
        User u = this.userRepository.findById(userId).get();
        c.setCommenter(u);

        if (animalOptional.isPresent()) {
            Animal oneAnimal = animalOptional.get();
            c.setTheAnimal(oneAnimal);
            c = this.commentsRepository.save(c);
            oneAnimal.fetchAnimalCommentList().add(c);
            this.animalRepository.save(oneAnimal);
        }

        else {
            throw new NotFoundException("animal", id);
        }

        return c;
    }

    /**
     * Endpoint for POST - save the animal treatment in db
     *
     * @param t  animal treatment in json
     * @param id animal id
     * @return the added animal treatment
     * @throws UnsupportedRequestException
     */
    @PostMapping(path = "{animalId}/treatments")
    public Treatments addTreatment(@RequestBody Treatments t, @PathVariable("animalId") Long id)
            throws UnsupportedRequestException {
        Optional<Animal> animalOptional = this.animalRepository.findById(id);
        t.setTreatmentId(0);

        Long userId = t.fetchTreatedBy().getUserId();
        User u = this.userRepository.findById(userId).get();
        t.setTreatedBy(u);

        if (animalOptional.isPresent()) {
            Animal oneAnimal = animalOptional.get();
            t.setTheAnimal(oneAnimal);
            t = this.treatmentsRepository.save(t);
            oneAnimal.fetchAnimalTreatmentList().add(t);
            this.animalRepository.save(oneAnimal);
        }

        else {
            throw new NotFoundException("animal", id);
        }

        return t;
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

            animal.setName(a.getName());
            animal.setSpecies(a.getSpecies());
            animal.setSubSpecies(a.getSubSpecies());
            animal.setBreed(a.getBreed());
            animal.setType(a.getType());
            animal.setRegion(a.getRegion());
            animal.setSex(a.getSex());
            animal.setBirthDate(a.getBirthDate());
            animal.setStatus(a.getStatus());
            animal.setTheOwner(this.findUniqueOwner(a.fetchTheOwner()));
            animal.setProfilePic(a.fetchProfilePic());
            animal.setTattooNum(a.getTattooNum());
            animal.setCityTattoo(a.getCityTattoo());
            animal.setRfidNumber(a.getRfidNumber());
            animal.setMicroChipNumber(a.getMicroChipNumber());
            animal.setCoatColor(a.getCoatColor());
            animal.setContinuousMedication(a.getContinuousMedication());
            animal.setDistinctFeature(a.getDistinctFeature());

            updatedAnimal = this.animalRepository.save(animal);
        }

        else {
            a.setAnimalId(id);
            updatedAnimal = this.animalRepository.save(a);
        }

        return updatedAnimal;
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
     * @throws NotFoundException
     */
    @DeleteMapping(path = "{animalId}")
    public void deleteAnimal(@PathVariable("animalId") Long id) throws NotFoundException {
        if (!this.animalRepository.existsById(id)) {
            throw new NotFoundException("animal", id);
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
            this.issueRepository.delete(i);
        }

        // removes all treatments
        for (Treatments t : a.fetchAnimalTreatmentList()) {
            this.removeTreatmentFromAnimal(a.getAnimalId(), t.getTreatmentId());
            this.treatmentsRepository.delete(t);
        }

        this.animalRepository.deleteById(id);
    }

    /**
     * Endpoint for DELETE - delete animal weight from db
     *
     * @param animalId animal id
     * @param weightId weight id
     * @throws NotFoundException
     */
    @DeleteMapping(path = "{animalId}/weights/{weightId}")
    public void deleteWeights(@PathVariable("animalId") Long animalId, @PathVariable("weightId") Long weightId)
            throws NotFoundException {
        Optional<Weights> weightOptional = this.weightsRepository.findById(weightId);

        if (weightOptional.isPresent()) {
            Weights oneweight = weightOptional.get();

            if (this.removeWeightFromAnimal(animalId, weightId)) {
                this.weightsRepository.delete(oneweight);
            }
        }

        else {
            throw new NotFoundException("weight", weightId);
        }
    }

    /**
     * Endpoint for DELETE - delete animal photo from db
     *
     * @param animalId animal id
     * @param photoId photo id
     * @throws NotFoundException
     */
    @DeleteMapping(path = "{animalId}/photos/{photoId}")
    public void deletePhotos(@PathVariable("animalId") Long animalId, @PathVariable("photoId") Long photoId)
            throws NotFoundException {
        Optional<Photos> photoOptional = this.photosRepository.findById(photoId);

        if (photoOptional.isPresent()) {
            Photos onePhoto = photoOptional.get();

            if (this.removePhotoFromAnimal(animalId, photoId)) {
                this.photosRepository.delete(onePhoto);
            }
        }

        else {
            throw new NotFoundException("photo", photoId);
        }
    }

    /**
     * Endpoint for DELETE - delete animal comment from db
     *
     * @param animalId animal id
     * @param commentId comment id
     * @throws NotFoundException
     */
    @DeleteMapping(path = "{animalId}/comments/{commentId}")
    public void deleteComments(@PathVariable("animalId") Long animalId, @PathVariable("commentId") Long commentId)
            throws NotFoundException {
        Optional<Comments> commentOptional = this.commentsRepository.findById(commentId);

        if (commentOptional.isPresent()) {
            Comments oneComment = commentOptional.get();

            if (this.removeCommentFromAnimal(animalId, commentId)) {
                this.commentsRepository.delete(oneComment);
            }
        }

        else {
            throw new NotFoundException("comment", commentId);
        }
    }

    /**
     * Endpoint for DELETE - delete animal issue from db
     *
     * @param animalId animal id
     * @param issueId issue id
     * @throws NotFoundException
     */
    @DeleteMapping(path = "{animalId}/issues/{issueId}")
    public void deleteIssues(@PathVariable("animalId") Long animalId, @PathVariable("issueId") Long issueId)
            throws NotFoundException {
        Optional<Issues> issueOptional = this.issueRepository.findById(issueId);

        if (issueOptional.isPresent()) {
            Issues oneIssue = issueOptional.get();

            if (this.removeIssueFromAnimal(animalId, issueId)) {
                this.issueRepository.delete(oneIssue);
            }
        }

        else {
            throw new NotFoundException("issue", issueId);
        }
    }

    /**
     * Endpoint for DELETE - delete animal treatment from db
     *
     * @param animalId animal id
     * @param treatmentId treatment id
     * @throws NotFoundException
     */
    @DeleteMapping(path = "{animalId}/treatments/{treatmentId}")
    public void deleteTreatments(@PathVariable("animalId") Long animalId,
            @PathVariable("treatmentId") Long treatmentId) throws NotFoundException {
        Optional<Treatments> treatmentOptional = this.treatmentsRepository.findById(treatmentId);

        if (treatmentOptional.isPresent()) {
            Treatments oneTreatment = treatmentOptional.get();

            if (this.removeTreatmentFromAnimal(animalId, treatmentId)) {
                this.treatmentsRepository.delete(oneTreatment);
            }
        }

        else {
            throw new NotFoundException("treatment", treatmentId);
        }
    }
}
