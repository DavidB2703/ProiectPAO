public class FanMeetingRepository {
    //FanMeeting are NUME, LOCATIE, DATA, PRET_BILET, ARTIST, DURATA
    private DbManager dbManager;

    public FanMeetingRepository() {
        dbManager = DbManager.getInstance();
    }

    public void addFanMeeting(FanMeeting fanMeeting) {
        String query = "INSERT INTO FAN_MEETING (NUME, LOCATIE, DATA, PRET_BILET, ID_ARTIST, DURATA) VALUES ('" + fanMeeting.getNume() + "', '" + fanMeeting.getLocatie() + "', '" + fanMeeting.getData() + "', " + fanMeeting.getPretBilet() + ", " + DbManager.getArtistID(fanMeeting.getArtist()) + ", '" + fanMeeting.getDurata() + "');";
        dbManager.write(query);
    }

    public void readFanMeeting(FanMeeting fanMeeting) {
        int id = DbManager.getFanMeetingID(fanMeeting);
        String query = "SELECT * FROM FAN_MEETING WHERE ID = " + id + ";";
        dbManager.read(query);
    }

    public void deleteFanMeeting(FanMeeting fanMeeting) {
        int id = DbManager.getFanMeetingID(fanMeeting);
        String query = "DELETE FROM FAN_MEETING WHERE ID = " + id + ";";
        dbManager.write(query);
    }

    public void updateFanMeeting(FanMeeting newFanMeeting, int idFanMeetingToEdit) {
        String query = "UPDATE FAN_MEETING SET NUME = '" + newFanMeeting.getNume() + "', LOCATIE = '" + newFanMeeting.getLocatie() + "', DATA = '" + newFanMeeting.getData() + "', PRET_BILET = " + newFanMeeting.getPretBilet() + ", ID_ARTIST = " + DbManager.getArtistID(newFanMeeting.getArtist()) + ", DURATA = '" + newFanMeeting.getDurata() + "' WHERE ID = " + idFanMeetingToEdit + ";";
        dbManager.write(query);
    }
}
