import java.util.ArrayList;

public class FanMeeting extends Eveniment{
    private String durata;
    private Artist artist;

    FanMeeting(String nume, String locatie, String data, int pretBilet, Artist artist, String durata){
        super(nume, locatie, data, pretBilet);
        this.artist = artist;
        this.durata = durata;
    }

    @Override
    public String afisareEveniment() {
        return "Nume: " + super.getNume() + "\n" +
                "Locatie: " + super.getLocatie() + "\n" +
                "Data: " + super.getData() + "\n" +
                "Pret bilet: " + super.getPretBilet() + "\n" +
                "Artist: " + this.artist + "\n" +
                "Durata: " + this.durata;
    }
    @Override
    public TipEveniment getTip() {
        return TipEveniment.FAN_MEETING;
    }
    @Override
    public ArrayList<Artist> getArtisti() {
        ArrayList<Artist> artisti = new ArrayList<>();
        artisti.add(this.artist);
        return artisti;
    }
}
