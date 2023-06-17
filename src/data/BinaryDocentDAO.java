package data;

import models.Docent;
import practicumopdracht.MainApplication;

import java.io.*;
import java.time.LocalDate;

public class BinaryDocentDAO extends DocentDAO{
    private final String FILENAME="docent.dat";
    @Override
    public boolean save() {
        File file = new File(FILENAME);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream)) {
            dataOutputStream.writeInt(docenten.size());
            //slaat de waardes op van alle in de listiew.

            for (Docent docent : docenten) {
                dataOutputStream.writeUTF(docent.getNaam());
                dataOutputStream.writeUTF(docent.getVak());
                dataOutputStream.writeBoolean(docent.isStagaire());
                dataOutputStream.writeInt(docent.getAangenomenOp().getYear());
                dataOutputStream.writeInt(docent.getAangenomenOp().getMonthValue());
                dataOutputStream.writeInt(docent.getAangenomenOp().getDayOfMonth());
            }
            return true;
        } catch (FileNotFoundException exception) {
            System.out.println("Error:"+FILENAME+" niet gevonden.");

        }catch (NullPointerException nullPointerException){
            System.out.println("Hets bestand is leeg");

        } catch(Exception exception) {
            System.out.println("Error: " + exception.getMessage());

        }

        return false;
    }

    @Override
    public boolean load() {
        File file = new File(FILENAME);
        try (FileInputStream fileInputStream = new FileInputStream(file);
             DataInputStream dataInputStream = new DataInputStream(fileInputStream)){
            int aantal = dataInputStream.readInt();

            for (int i = 0; i < aantal; i++) {
                String naam = dataInputStream.readUTF();

                String vak = dataInputStream.readUTF();

                boolean isStagaire= dataInputStream.readBoolean();

                int jaar = dataInputStream.readInt();
                int maand = dataInputStream.readInt();
                int dag = dataInputStream.readInt();
                LocalDate isAangenomenOp = LocalDate.of(jaar, maand, dag);

                MainApplication.getDocentDAO().addOrUpdate(new Docent(naam,vak,isStagaire,isAangenomenOp));
            }

            return true;
        } catch (FileNotFoundException exception) {
            System.out.println("Error:"+ FILENAME+" niet gevonden.");
            return false;
        } catch(EOFException eofException){
            System.out.println("bevat niet de juiste hoeveelheid informatie bij het loaden.");
            return false;
        }catch (Exception exception) {
            System.out.println("Error load: " + exception);
            return false;
        }

    }
}
