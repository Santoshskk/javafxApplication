package data;

import models.Docent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * The type Docent text dao.
 */
public class DocentTextDAO extends DocentDAO {

    private final String FILENAME="docent.text";
    @Override
    public boolean save() {
        File file = new File(FILENAME);

        PrintWriter printWriter=null;
        try {

            printWriter= new PrintWriter(file);

            printWriter.println(docenten.size());

            for(Docent docent : docenten){
                printWriter.println(docent.getNaam());
                printWriter.println(docent.getVak());
                printWriter.println(docent.isStagaire());
                printWriter.println(docent.getAangenomenOp());


            }

            return true;
        } catch (FileNotFoundException notFoundException) {
            System.out.println("Bestand bestaat niet!");
        }catch (Exception exception){
            System.out.println("Bestand is niet gevonden!");
        }

        finally {
            printWriter.close();
        }

        return false;
    }

    @Override
    public boolean load() {
        File file = new File(FILENAME);
        try (Scanner scanner = new Scanner(file)){

            int aantalDocenten = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < aantalDocenten; i++) {
                String naam = scanner.nextLine();

                String vak = scanner.nextLine();

                String isStagaireString= scanner.nextLine();
                boolean isStagaire= Boolean.parseBoolean(isStagaireString);

                String datumstring = scanner.nextLine();;
                LocalDate isAangenomenOp= LocalDate.parse((datumstring));

                docenten.add(new Docent(naam,vak,isStagaire,isAangenomenOp));

            }

            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Bestnand is niet gevonden!");
        }catch (Exception e){
            System.out.println("Er gaat wat mis: " + e.getMessage());
        }

        return false;
    }
}
