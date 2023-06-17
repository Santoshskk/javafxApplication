package models;

import java.io.Serializable;
import java.time.LocalDate;

public class Docent implements Serializable, Comparable<Docent>{
    private String naam;
    private String vak;
    private boolean stagaire;
    private LocalDate aangenomenOp;


    public Docent(String naam, String vak, boolean stagaire, LocalDate aangenomenOp) {
        this.naam = naam;
        this.vak = vak;
        this.stagaire = stagaire;
        this.aangenomenOp = aangenomenOp;
    }
    public String toString() {

        return String.format("Naam:  %s, Vak:  %s, Is aangenomen op: %s, Is stagiare:  %s"
                ,naam,vak,aangenomenOp,stagaire);
    }
    public String getNaam() {
        return naam;
    }

    public String getVak() {
        return vak;
    }

    public boolean isStagaire() {
        return stagaire;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setVak(String vak) {
        this.vak = vak;
    }

    public void setStagaire(boolean stagaire) {
        this.stagaire = stagaire;
    }

    public void setAangenomenOp(LocalDate aangenomenOp) {
        this.aangenomenOp = aangenomenOp;
    }

    public LocalDate getAangenomenOp() {
        return aangenomenOp;
    }

    @Override
    public int compareTo(Docent o) {
        return 0;
    }
}
