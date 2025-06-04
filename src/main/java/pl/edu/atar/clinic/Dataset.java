package pl.edu.atar.clinic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dataset {

    public Dataset() {
    }

    public List<Patient> getPatients() {

        return new ArrayList<Patient>(Arrays.asList(
                new Patient(1L, "Anna", "Kowalewska", "Female", 39.5),
                new Patient(2L, "Jacek", "Nowak", "Male", 37.0),
                new Patient(3L, "Ewa", "Wiśniowa", "Female", 37.4),
                new Patient(4L, "Karol", "Gruszka", "Male", 36.9),
                new Patient(5L, "Ewelina", "Jabłoń", "Female", 36.6)
        ));
    }
}