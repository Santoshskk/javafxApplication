package comparators;

import models.Student;

import java.util.Comparator;

public class StudentComporatorCijfersOplopend implements Comparator<Student> {

    @Override
    public int compare(Student cijfer1, Student cijfer2) {
        return Double.compare(cijfer1.getGekregenCijfer(),cijfer2.getGekregenCijfer());
    }
}
