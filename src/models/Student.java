package models;


import java.io.Serializable;

/**
 * The type Student.
 */
public class Student implements Serializable, Comparable<Student> {
    //trantsient zorgt voor het uitsluiten van Serilazition
    private transient Docent hoortBij;
    private String naam;
    private int studentnummer;
    private double gekregenCijfer;
    private String feedback;

    /**
     * Instantiates a new Student.
     *
     * @param hoortBij       the hoort bij
     * @param naam           the naam
     * @param studentnummer  the studentnummer
     * @param gekregenCijfer the gekregen cijfer
     * @param feedback       the feedback
     */
    public Student(Docent hoortBij, String naam, int studentnummer, double gekregenCijfer, String feedback) {
        this.hoortBij = hoortBij;
        this.naam = naam;
        this.studentnummer = studentnummer;
        this.gekregenCijfer = gekregenCijfer;
        this.feedback = feedback;
    }

    public String toString() {
        return String.format("Naam:  %s, Studentnummer:  %d, Gekregen cijfer: %.1f, Feedback: %s"
                ,naam,studentnummer,gekregenCijfer,feedback);
    }

    /**
     * Gets hoort bij.
     *
     * @return the hoort bij
     */
    public Docent getHoortBij() {
        return hoortBij;
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
     * Gets studentnummer.
     *
     * @return the studentnummer
     */
    public int getStudentnummer() {
        return studentnummer;
    }

    /**
     * Gets gekregen cijfer.
     *
     * @return the gekregen cijfer
     */
    public double getGekregenCijfer() {
        return gekregenCijfer;
    }

    /**
     * Gets feedback.
     *
     * @return the feedback
     */
    public String getFeedback() {
        return feedback;
    }

    /**
     * Sets hoort bij.
     *
     * @param hoortBij the hoort bij
     */
    public void setHoortBij(Docent hoortBij) {
        this.hoortBij = hoortBij;
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
     * Sets studentnummer.
     *
     * @param studentnummer the studentnummer
     */
    public void setStudentnummer(int studentnummer) {
        this.studentnummer = studentnummer;
    }

    /**
     * Sets gekregen cijfer.
     *
     * @param gekregenCijfer the gekregen cijfer
     */
    public void setGekregenCijfer(double gekregenCijfer) {
        this.gekregenCijfer = gekregenCijfer;
    }

    /**
     * Sets feedback.
     *
     * @param feedback the feedback
     */
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public int compareTo(Student o) {
        return 0;
    }
}
