package comparators;

import models.Docent;
import java.time.LocalDate;
import java.util.Comparator;


/**
 * de type Docent comporator datum.
 */
public class DocentComporatorDatum implements Comparator<Docent> {


    @Override
    public int compare(Docent docentDatum1, Docent docentDatum2) {
        LocalDate datum1 = docentDatum1.getAangenomenOp();
        LocalDate datum2 = docentDatum2.getAangenomenOp();
        return datum1.compareTo(datum2);
    }
}

