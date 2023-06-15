package models;


public class Student {
    private Docent hoortBij;
    private String naam;
    private int studentnummer;
    private double gekregenCijfer;
    private String feedback;

    public Student(Docent hoortBij, String naam, int studentnummer, double gekregenCijfer, String feedback) {
        this.hoortBij = hoortBij;
        this.naam = naam;
        this.studentnummer = studentnummer;
        this.gekregenCijfer = gekregenCijfer;
        this.feedback = feedback;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Naam:  ").append(naam);
        sb.append(",  Studentnummer:  ").append(studentnummer);
        sb.append(",  Gekregen cijfer:  ").append(gekregenCijfer);
        sb.append("\nFeedback:  ").append(feedback);

        return sb.toString();
    }

    public Docent getHoortBij() {
        return hoortBij;
    }

    public String getNaam() {
        return naam;
    }

    public int getStudentnummer() {
        return studentnummer;
    }

    public double getGekregenCijfer() {
        return gekregenCijfer;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setHoortBij(Docent hoortBij) {
        this.hoortBij = hoortBij;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setStudentnummer(int studentnummer) {
        this.studentnummer = studentnummer;
    }

    public void setGekregenCijfer(double gekregenCijfer) {
        this.gekregenCijfer = gekregenCijfer;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
