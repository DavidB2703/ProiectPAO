import java.util.ArrayList;

public class Petrecere extends Eveniment{

    private String Dj;
    private String oraIncepere;

    Petrecere(String nume, String locatie, String data, int pretBilet, String Dj, String oraIncepere) {
        super(nume, locatie, data, pretBilet);
        this.Dj = Dj;
        this.oraIncepere = oraIncepere;
    }

    @Override
    public String afisareEveniment() {
        return "Petrecere: " + getNume() + " la " + getLocatie() + " in data de " + getData() + " la ora " + oraIncepere + " cu pretul unui bilet de " + getPretBilet() + " lei";
    }
    @Override
    public TipEveniment getTip() {
        return TipEveniment.PETRECERE;
    }

    @Override
    public ArrayList<Artist> getArtisti() {
        ArrayList<Artist> artisti = new ArrayList<>();
        artisti.add(new Artist(this.Dj, GenMuzical.RAP, 0));
        return artisti;
    }

}
