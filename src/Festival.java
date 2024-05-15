import java.util.ArrayList;

//nrArtisti = pretBilet/5, nrZile = nrArtisti/10, pretBiletOZi= pretBilet/nrZile *1.1
public class Festival extends Eveniment{
    private int nrArtisti;
    private ArrayList<Artist> artisti;
    private int nrZile;
    int pretBiletOZi;

    Festival(String nume, String locatie, String data, int pretBilet, ArrayList<Artist> artisti){
        super(nume, locatie, data, pretBilet);
        this.artisti = artisti;
        this.nrArtisti = artisti.size();
        this.nrZile = this.nrArtisti + 2;
        this.pretBiletOZi = super.getPretBilet()/this.nrZile * 11/10;

    }
    @Override
    public String afisareEveniment() {
        return "Nume: " + super.getNume() + "\n" +
                "Locatie: " + super.getLocatie() + "\n" +
                "Data: " + super.getData() + "\n" +
                "Pret bilet: " + super.getPretBilet() + "\n" +
                "Numar artisti: " + this.nrArtisti + "\n" +
                "Numar zile: " + this.nrZile + "\n" +
                "Pret bilet pe zi: " + this.pretBiletOZi;
    }
    @Override
    public TipEveniment getTip() {
        return TipEveniment.FESTIVAL;
    }

    @Override
    public ArrayList<Artist> getArtisti() {
        return this.artisti;
    }

    public int getNumarZile() {
        return nrZile;
    }

    public int getPretBiletOZi() {
        return pretBiletOZi;
    }



}
