package data;

import models.Docent;

import javax.print.Doc;
import java.time.LocalDate;

public class DocentDummyDAO extends DocentDAO{
    @Override
    public boolean save() {
        return false;
    }

    @Override
    public boolean load() {
        docenten.add(new Docent("Jan Kees","Duits",false, LocalDate.of(2021,12,2)));
        docenten.add(new Docent("Piet","Frans",true, LocalDate.of(2019,10,12)));
        docenten.add(new Docent("Johannes","Wiskunde",false, LocalDate.of(2015,8,2)));
        return true;
    }
}
