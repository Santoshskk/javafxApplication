package data;

import models.Student;
import practicumopdracht.MainApplication;

import java.io.*;

/**
 * The type Object student dao.
 */
public class ObjectStudentDAO extends StudentDAO implements Serializable {
    private final String FILENAME = "student.obj";

    @Override
    public boolean save() {
        File file = new File(FILENAME);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeInt(studenten.size());
            for (Student student : studenten) {
                objectOutputStream.writeObject(student);
                //dit is voor de hoort bij
                objectOutputStream.writeInt(MainApplication.getDocentDAO().getIdFor(student.getHoortBij()));
            }
            return true;

        } catch (Exception exception) {
            exception.printStackTrace();
            System.err.println("Er is een error bij het opslaan van de studenten: " + exception.getMessage());
        }
        return false;
    }

    @Override
    public boolean load() {
        File file = new File(FILENAME);
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            // hier pakt hij de array van welke masterdao hij bij hoort
            int aantal = objectInputStream.readInt();
            for (int i = 0; i < aantal; i++) {
                //laden van alle studenten
                Student student = (Student) objectInputStream.readObject();
                int hoortBij = objectInputStream.readInt();
                // in de goeie student neergezet
                student.setHoortBij(MainApplication.getDocentDAO().getById(hoortBij));
                studenten.add(student);
            }
            return true;

        } catch (Exception exception) {
            exception.printStackTrace();
            System.err.println("Er is een fout opgetreden bij het laden van de studenten: " + exception.getMessage());
        }
        return false;
    }
}
