import java.util.ArrayList;

public class Artist {
    private String nume;
    private GenMuzical genMuzical;
    private int varsta;

    Artist(String nume, GenMuzical genMuzical, int varsta) {
        this.nume = nume;
        this.genMuzical = genMuzical;
        this.varsta = varsta;
    }

    public String toString() {
        return "Nume: " + nume + "\n" +
                "Gen muzical: " + genMuzical + "\n" +
                "Varsta: " + varsta + "\n";
    }
}
