package models;

import java.time.LocalDate;

public class Docent {
    private String naam;
    private String vak;
    private boolean stagaire;
    private LocalDate aangenomenOp;

    public Docent() {
        this("jan", "jan",false, LocalDate.of(2004 ,10,30));
    }

    public Docent(String naam, String vak, boolean stagaire, LocalDate aangenomenOp) {
        this.naam = naam;
        this.vak = vak;
        this.stagaire = stagaire;
        this.aangenomenOp = aangenomenOp;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Naam:  ").append(naam);
        sb.append(",  Vak:  ").append(vak);
        sb.append(" Is aangenomen op:  ").append(aangenomenOp).append(", \n");

        if (isStagaire()) {
            sb.append("Stagaire:  ja");
        } else {
            sb.append("Stagaire:  nee");
        }

        return sb.toString();
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
}
