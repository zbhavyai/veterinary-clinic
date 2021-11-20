package ca.ucalgary.vetapp.controller;

import ca.ucalgary.vetapp.model.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@RestController
@RequestMapping(path = "api/v1/animals")
public class AnimalController {
    private final AnimalRepository animalRepository;
    private final OwnerRepository ownerRepository;
    private final UserRepository userRepository;
    private final PhotosRepository photosRepository;
    private final CommentsRepository commentsRepository;
    private final IssuesRepository issueRepository;
    private final TreatmentsRepository treatmentsRepository;

    public AnimalController(AnimalRepository ar, OwnerRepository or, UserRepository ur, PhotosRepository pr, CommentsRepository cr,
            IssuesRepository ir, TreatmentsRepository tr) {
        this.animalRepository = ar;
        this.ownerRepository = or;
        this.userRepository = ur;
        this.photosRepository = pr;
        this.commentsRepository = cr;
        this.issueRepository = ir;
        this.treatmentsRepository = tr;
    }

    /**
     * Get one animal
     *
     * @param id
     * @return
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

    private List<Animal> searchAnimalByName(String sT) {
        String searchTerm = sT.toLowerCase();

        List<Animal> allAnimals = this.animalRepository.findAll();
        List<Animal> searchResults = new ArrayList<>();

        for (Animal eachAnimal : allAnimals) {
            if (eachAnimal.getName() != null && eachAnimal.getName().toLowerCase().contains(searchTerm)) {
                searchResults.add(eachAnimal);
                continue;
            }
        }

        return searchResults;
    }

    private List<Animal> searchAnimalByType(String sT) {
        String searchTerm = sT.toLowerCase();

        List<Animal> allAnimals = this.animalRepository.findAll();
        List<Animal> searchResults = new ArrayList<>();

        for (Animal eachAnimal : allAnimals) {
            if (eachAnimal.getType() != null && eachAnimal.getType().toLowerCase().contains(searchTerm)) {
                searchResults.add(eachAnimal);
                continue;
            }
        }

        return searchResults;
    }

    private List<Animal> searchAnimalByBreed(String sT) {
        String searchTerm = sT.toLowerCase();

        List<Animal> allAnimals = this.animalRepository.findAll();
        List<Animal> searchResults = new ArrayList<>();

        for (Animal eachAnimal : allAnimals) {
            if (eachAnimal.getBreed() != null && eachAnimal.getBreed().toLowerCase().contains(searchTerm)) {
                searchResults.add(eachAnimal);
                continue;
            }
        }

        return searchResults;
    }

    private List<Animal> searchAnimalByOwner(String sT) {
        String searchTerm = sT.toLowerCase();

        List<Animal> allAnimals = this.animalRepository.findAll();
        List<Animal> searchResults = new ArrayList<>();

        for (Animal eachAnimal : allAnimals) {
            if (eachAnimal.getOwnerName() != null && eachAnimal.getOwnerName().toLowerCase().contains(searchTerm)) {
                searchResults.add(eachAnimal);
            }
        }

        return searchResults;
    }

    private List<Animal> searchAnimalByStatus(String sT) throws UnsupportedRequestException {
        try {
            AnimalStatus searchTerm = AnimalStatus.valueOf(sT.toUpperCase());

            List<Animal> allAnimals = this.animalRepository.findAll();
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
            return this.searchAnimalByName(searchTerm);
        }

        else if (searchBy.equalsIgnoreCase("type")) {
            return this.searchAnimalByType(searchTerm);
        }

        else if (searchBy.equalsIgnoreCase("breed")) {
            return this.searchAnimalByBreed(searchTerm);
        }

        else if (searchBy.equalsIgnoreCase("owner")) {
            return this.searchAnimalByOwner(searchTerm);
        }

        else if (searchBy.equalsIgnoreCase("status")) {
            return this.searchAnimalByStatus(searchTerm);
        }

        else {
            throw new UnsupportedRequestException("Invalid search by");
        }
    }

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
     * Saves one animal
     *
     * @param a
     * @return
     */
    @PostMapping
    public Animal addAnimal(@RequestBody Animal a) throws UnsupportedRequestException {
        Owner o = a.fetchTheOwner();

        o = this.findUniqueOwner(o);
        this.ownerRepository.save(o);
        a.setTheOwner(o);

        return this.animalRepository.save(a);
    }

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

    @PostMapping(path = "{animalId}/comments")
    public Comments addComment(@RequestBody Comments c, @PathVariable("animalId") Long id) throws UnsupportedRequestException {
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

    @PostMapping(path = "{animalId}/issues")
    public Issues addIssue(@RequestBody Issues i, @PathVariable("animalId") Long id) throws UnsupportedRequestException {
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

    @PostMapping(path = "{animalId}/treatments")
    public Treatments addTreatment(@RequestBody Treatments t, @PathVariable("animalId") Long id) throws UnsupportedRequestException {
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

    @PostMapping(path = "{animalId}/photos")
    public Photos addPhoto(@RequestBody Photos p, @PathVariable("animalId") Long id) throws UnsupportedRequestException {
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
     *
     * @param a
     * @param id
     * @return
     */
    @PutMapping(path = "{animalId}")
    public Animal updateAnimal(@RequestBody Animal a, @PathVariable("animalId") Long id) {
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
            animal.setTheOwner(this.findUniqueOwner(a.fetchTheOwner()));
            animal.setTattooNum(a.getTattooNum());
            animal.setRfidNumber(a.getRfidNumber());
            animal.setMicroChipNumber(a.getMicroChipNumber());
            animal.setWeight(a.getWeight());
            animal.setCoatColor(a.getCoatColor());
            animal.setContinuousMedication(a.getContinuousMedication());
            // animal.setAnimalPhotoList(a.getAnimalPhotoList());
            // animal.setAnimalTreatmentList(a.getAnimalTreatmentList());
            // animal.setAnimalIssueList(a.getAnimalIssueList());

            updatedAnimal = this.animalRepository.save(animal);
        }

        else {
            a.setAnimalId(id);
            updatedAnimal = this.animalRepository.save(a);
        }

        return updatedAnimal;
    }

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
     * Deletes one animal
     *
     * @param id
     */
    @DeleteMapping(path = "{animalId}")
    public void deleteAnimal(@PathVariable("animalId") Long id) {
        if (!this.animalRepository.existsById(id)) {
            throw new NotFoundException("animal", id);
        }

        Animal a = this.animalRepository.findById(id).get();

        // disconnect the owner
        a.setTheOwner(null);

        // removes all photos
        for(Photos p : a.fetchAnimalPhotoList()) {
            this.removePhotoFromAnimal(a.getAnimalId(),  p.getPhotoId());
            this.photosRepository.delete(p);
        }

        // removes all comments
        for(Comments c : a.fetchAnimalCommentList()) {
            this.removeCommentFromAnimal(a.getAnimalId(), c.getCommentId());
            this.commentsRepository.delete(c);
        }

        // removes all issues
        for(Issues i : a.fetchAnimalIssueList()) {
            this.removeIssueFromAnimal(a.getAnimalId(), i.getIssueId());
            this.issueRepository.delete(i);
        }

        // removes all treatments
        for(Treatments t : a.fetchAnimalTreatmentList()) {
            this.removeTreatmentFromAnimal(a.getAnimalId(), t.getTreatmentId());
            this.treatmentsRepository.delete(t);
        }

        this.animalRepository.deleteById(id);
    }

    @DeleteMapping(path = "{animalId}/photos/{photoId}")
    public void deletePhotos(@PathVariable("animalId") Long animalId, @PathVariable("photoId") Long photoId) {
        Optional<Photos> photoOptional = this.photosRepository.findById(photoId);

        if (photoOptional.isPresent()) {
            Photos onePhoto = photoOptional.get();

            if(this.removePhotoFromAnimal(animalId, photoId)) {
                this.photosRepository.delete(onePhoto);
            }
        }

        else {
            throw new NotFoundException("photo", photoId);
        }
    }

    @DeleteMapping(path = "{animalId}/comments/{commentId}")
    public void deleteComments(@PathVariable("animalId") Long animalId, @PathVariable("commentId") Long commentId) {
        Optional<Comments> commentOptional = this.commentsRepository.findById(commentId);

        if (commentOptional.isPresent()) {
            Comments oneComment = commentOptional.get();

            if(this.removeCommentFromAnimal(animalId, commentId)) {
                this.commentsRepository.delete(oneComment);
            }
        }

        else {
            throw new NotFoundException("comment", commentId);
        }
    }

    @DeleteMapping(path = "{animalId}/issues/{issueId}")
    public void deleteIssues(@PathVariable("animalId") Long animalId, @PathVariable("issueId") Long issueId) {
        Optional<Issues> issueOptional = this.issueRepository.findById(issueId);

        if (issueOptional.isPresent()) {
            Issues oneIssue = issueOptional.get();

            if(this.removeIssueFromAnimal(animalId, issueId)) {
                this.issueRepository.delete(oneIssue);
            }
        }

        else {
            throw new NotFoundException("issue", issueId);
        }
    }

    @DeleteMapping(path = "{animalId}/treatments/{treatmentId}")
    public void deleteTreatments(@PathVariable("animalId") Long animalId, @PathVariable("treatmentId") Long treatmentId) {
        Optional<Treatments> treatmentOptional = this.treatmentsRepository.findById(treatmentId);

        if (treatmentOptional.isPresent()) {
            Treatments oneTreatment = treatmentOptional.get();

            if(this.removeTreatmentFromAnimal(animalId, treatmentId)) {
                this.treatmentsRepository.delete(oneTreatment);
            }
        }

        else {
            throw new NotFoundException("treatment", treatmentId);
        }
    }
}
