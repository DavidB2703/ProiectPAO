public class ArtistRepository {
    private DbManager dbManager;
    public ArtistRepository() {
        dbManager = DbManager.getInstance();
    }

    public void addArtist(Artist artist) {
        String query = "INSERT INTO ARTIST (NAME, GENMUZICAL, VARSTA) VALUES ('" + artist.getNume() + "', '" + artist.getGenMuzical().toString() + "', " + artist.getVarsta() + ");";
        dbManager.write(query);
    }

    public void readArtists(Artist artist) {
        int id = DbManager.getArtistID(artist);
        String query = "SELECT * FROM ARTIST WHERE ID = " + id + ";";
        dbManager.read(query);
    }

    public void deleteArtist(Artist artist) {
        int id = DbManager.getArtistID(artist);
        String query = "DELETE FROM ARTIST WHERE ID = " + id + ";";
        dbManager.write(query);
    }

    public void updateArtist(Artist newArtist, int idArtistToEdit) {
        String query = "UPDATE ARTIST SET NAME = '" + newArtist.getNume() + "', GENMUZICAL = '" + newArtist.getGenMuzical().toString() + "', VARSTA = " + newArtist.getVarsta() + " WHERE ID = " + idArtistToEdit + ";";
        dbManager.write(query);
    }

}
