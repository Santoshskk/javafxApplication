package comparators;

import models.Docent;

import java.util.Comparator;


/**
 * de type Docent comporator aflopend.
 */
public class DocentComporatorAflopend implements Comparator<Docent> {

    @Override
    public int compare(Docent docent1, Docent docent2) {
        return docent2.getNaam().compareTo(docent1.getNaam());
    }

}
