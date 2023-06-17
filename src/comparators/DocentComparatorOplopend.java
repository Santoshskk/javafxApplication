package comparators;

import models.Docent;

import java.util.Comparator;

public class DocentComparatorOplopend implements Comparator<Docent> {

    @Override
    public int compare(Docent docent1, Docent docent2) {
        return docent1.getNaam().compareTo(docent2.getNaam());
    }
}
