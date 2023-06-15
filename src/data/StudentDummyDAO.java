package data;

import models.Student;
import practicumopdracht.MainApplication;

import java.time.LocalDate;

public class StudentDummyDAO extends StudentDAO{
    @Override
    public boolean save() {
        return false;
    }

    @Override
    public boolean load() {
        studenten.add(new Student(MainApplication.getDocentDAO().getById(0), "Kasper",53849283,5.5,"Kom op man doe je best"));
        studenten.add(new Student(MainApplication.getDocentDAO().getById(1), "Bas",983798235,7.8,"Goedzo man lekker"));
        studenten.add(new Student(MainApplication.getDocentDAO().getById(2), "Phillip",983893221,10,"Perfect i love u"));

        return true;
    }
}
