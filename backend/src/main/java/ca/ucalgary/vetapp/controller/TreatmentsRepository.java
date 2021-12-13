package ca.ucalgary.vetapp.controller;

import ca.ucalgary.vetapp.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentsRepository extends JpaRepository<Treatments, Long> {

}
