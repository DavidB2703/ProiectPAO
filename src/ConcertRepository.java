public class ConcertRepository {
    private DbManager dbManager;

    public ConcertRepository() {
        dbManager = DbManager.getInstance();
    }

    public void addConcert(Concert concert) {
        String query = "INSERT INTO CONCERT (NUME, LOCATIE, DATA, ORA, ID_ARTIST, PRET_BILET) VALUES ('"
                + concert.getNume() + "', '" + concert.getLocatie() + "', '" + concert.getData() + "', '" +
                concert.getOra() + "', " + DbManager.getArtistID(concert.getArtist()) +  ", " + concert.getPretBilet() + ");";
        //System.out.println(query);
        dbManager.write(query);
    }

    public void readConcert(Concert concert) {
        int id = DbManager.getConcertID(concert);
        String query = "SELECT * FROM CONCERT WHERE ID = " + id + ";";
        dbManager.read(query);
    }

    public void deleteConcert(Concert concert) {
        int id = DbManager.getConcertID(concert);
        String query = "DELETE FROM CONCERT WHERE ID = " + id + ";";
        System.out.println(query);
        dbManager.write(query);
    }

    public void updateConcert(Concert newConcert, int idConcertToEdit) {
        String query = "UPDATE CONCERT SET NUME = '" + newConcert.getNume() + "', LOCATIE = '" + newConcert.getLocatie() +"', ORA = '" + newConcert.getOra() +"', DATA = '" + newConcert.getData() + "', PRET_BILET = " + newConcert.getPretBilet() + ",ID_ARTIST = " + DbManager.getArtistID(newConcert.getArtist()) + " WHERE ID = " + idConcertToEdit + ";";
        System.out.println(query);
        dbManager.write(query);
    }
}
