import javax.swing.*;
import java.util.ArrayList;
public abstract class Eveniment {
    private String nume;
    private String locatie;
    private String data;
    int pretBilet;

    //Constructor
    Eveniment(String nume, String locatie, String data, int pretBilet) {
        this.nume = nume;
        this.locatie = locatie;
        this.data = data;
        this.pretBilet = pretBilet;
    }
    public abstract String afisareEveniment();
    public abstract TipEveniment getTip();

    public abstract ArrayList<Artist> getArtisti();
//Getters
    public String getNume() {
        return nume;
    }
    //Getters
    public String getLocatie() {
        return locatie;
    }
    //Getters
    public String getData() {
        return data;
    }
    //Getters
    public int getPretBilet() {
        return pretBilet;
    }



}
