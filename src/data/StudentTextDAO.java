package data;

import models.Docent;
import models.Student;
import practicumopdracht.MainApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class StudentTextDAO extends StudentDAO {
    private final String FILENAME = "student.text";

    @Override
    public boolean save() {
        File file = new File(FILENAME);
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(file);

            printWriter.println(studenten.size());

            for (Student student : studenten) {
                int docentId = MainApplication.getDocentDAO().getIdFor(student.getHoortBij());
                printWriter.println(docentId);
                printWriter.println(student.getNaam());
                printWriter.println(student.getStudentnummer());
                printWriter.println(student.getGekregenCijfer());
                printWriter.println(student.getFeedback());
            }

            return true;
        } catch (FileNotFoundException e) {
            System.err.println("Bestand niet gevonden: " + FILENAME);
        } catch (Exception e){
            System.out.println("Er gaat wat mis: " + e.getMessage());
        } finally{
            if (printWriter != null) {
                printWriter.close();
            }
        }

        return false;
    }

    @Override
    public boolean load() {
        File file = new File(FILENAME);
        try (Scanner scanner = new Scanner(file)) {
            int aantalStudenten = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < aantalStudenten; i++) {
                if(scanner.hasNextLine()) {
                    int docentId = Integer.parseInt(scanner.nextLine());
                    String naam = scanner.nextLine();
                    int studentNummer = Integer.parseInt(scanner.nextLine());
                    double gekregenCijfer = Double.parseDouble(scanner.nextLine());
                    String feedback = scanner.nextLine();



                    Student nieuweStudent = new Student(MainApplication.getDocentDAO().getById(docentId), naam, studentNummer, gekregenCijfer, feedback);
                    studenten.add(nieuweStudent);
                }
            }

            return true;
        } catch (FileNotFoundException e) {
            System.err.println("Bestand niet gevonden: " + FILENAME);
        }catch (Exception e){
            System.out.println("Er gaat wat mis: " + e.getMessage());
        }

        return false;
    }
}
