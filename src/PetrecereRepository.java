public class PetrecereRepository {

    private DbManager dbManager;

    public PetrecereRepository() {
        dbManager = DbManager.getInstance();
    }

    public void writePetrecere(Petrecere petrecere) {
        String query = "INSERT INTO PETRECERE (NUME, LOCATIE, DATA, Dj, ORA_INCEPERE, PRET_BILET) " +
                "VALUES ('" + petrecere.getNume() + "', '" + petrecere.getLocatie() + "', '" + petrecere.getData() + "', '" + petrecere.getDj() + "', '" + petrecere.getOraIncepere() + "' ,'" +
                 + petrecere.getPretBilet() + "');";
        System.out.println(query);
        dbManager.write(query);
    }

    public void readPetrecere(Petrecere petrecere) {
        int id = DbManager.getPetrecereID(petrecere);
        String query = "SELECT * FROM PETRECERE WHERE ID = " + id + ";";
        dbManager.read(query);
    }

    public void deletePetrecere(Petrecere petrecere) {
        int id = DbManager.getPetrecereID(petrecere);
        String query = "DELETE FROM PETRECERE WHERE ID = " + id + ";";
        dbManager.write(query);
    }

    public void updatePetrecere(Petrecere newPetrecere, int idPetrecereToEdit) {
        String query = "UPDATE PETRECERE SET NUME = '" + newPetrecere.getNume() + "', LOCATIE = '" + newPetrecere.getLocatie() + "', DATA = '" + newPetrecere.getData() + "', Dj = '" + newPetrecere.getDj() + "' , ORA_INCEPERE = '" + newPetrecere.getOraIncepere() + "', PRET_BILET = " + newPetrecere.getPretBilet() + " WHERE ID = " + idPetrecereToEdit + ";";
        System.out.println(query);
        dbManager.write(query);
    }


}
