package comparators;

import models.Docent;
import models.Student;


import java.util.Comparator;

public class StudentComporatorCijferAflopend implements Comparator<Student> {

    @Override
    public int compare(Student cijfer1, Student cijfer2) {
        return Double.compare(cijfer2.getGekregenCijfer(),cijfer1.getGekregenCijfer());
    }
}
