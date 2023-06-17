package models;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * The type Docent.
 */
public class Docent implements Serializable, Comparable<Docent>{
    private String naam;
    private String vak;
    private boolean stagaire;
    private LocalDate aangenomenOp;


    /**
     * Instantiates a new Docent.
     *
     * @param naam         the naam
     * @param vak          the vak
     * @param stagaire     the stagaire
     * @param aangenomenOp the aangenomen op
     */
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

    /**
     * Gets naam.
     *
     * @return the naam
     */
    public String getNaam() {
        return naam;
    }

    /**
     * Gets vak.
     *
     * @return the vak
     */
    public String getVak() {
        return vak;
    }

    /**
     * Is stagaire boolean.
     *
     * @return the boolean
     */
    public boolean isStagaire() {
        return stagaire;
    }

    /**
     * Sets naam.
     *
     * @param naam the naam
     */
    public void setNaam(String naam) {
        this.naam = naam;
    }

    /**
     * Sets vak.
     *
     * @param vak the vak
     */
    public void setVak(String vak) {
        this.vak = vak;
    }

    /**
     * Sets stagaire.
     *
     * @param stagaire the stagaire
     */
    public void setStagaire(boolean stagaire) {
        this.stagaire = stagaire;
    }

    /**
     * Sets aangenomen op.
     *
     * @param aangenomenOp the aangenomen op
     */
    public void setAangenomenOp(LocalDate aangenomenOp) {
        this.aangenomenOp = aangenomenOp;
    }

    /**
     * Gets aangenomen op.
     *
     * @return the aangenomen op
     */
    public LocalDate getAangenomenOp() {
        return aangenomenOp;
    }

    @Override
    public int compareTo(Docent o) {
        return 0;
    }
}
