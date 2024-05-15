import java.util.ArrayList;

public class Concert extends Eveniment{
    private Artist artist;
    private String ora;

    Concert(String nume, String locatie, String data, int pretBilet, Artist artist, String ora){
        super(nume, locatie, data, pretBilet);
        this.artist = artist;
        this.ora = ora;
    }

    @Override
    public String afisareEveniment() {
        return "Nume: " + super.getNume() + "\n" +
                "Locatie: " + super.getLocatie() + "\n" +
                "Data: " + super.getData() + "\n" +
                "Pret bilet: " + super.getPretBilet() + "\n" +
                "Artist: " + this.artist + "\n" +
                "Ora: " + this.ora;
    }
    @Override
    public TipEveniment getTip() {
        return TipEveniment.CONCERT;
    }

    @Override
    public ArrayList<Artist> getArtisti() {
        ArrayList<Artist> artisti = new ArrayList<>();
        artisti.add(this.artist);
        return artisti;
    }

    public String getOra() {
        return ora;
    }

    public Artist getArtist() {
        return artist;
    }
}
