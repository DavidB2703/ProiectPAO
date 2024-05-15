import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Main {

    private static void creareArtistisiFestivale(){
        Artist artist1 = new Artist("Vama", GenMuzical.ROCK, 20);
        Artist artist2 = new Artist("5Gang", GenMuzical.POP, 20);
        Artist artist3 = new Artist("3SudEst", GenMuzical.POP, 20);
        Artist artist4 = new Artist("Delia", GenMuzical.POP, 20);
        Artist artist6 = new Artist("Selly", GenMuzical.FUNK, 20);
        //variaza genusurile muzicale in timp ce creezi artisti
        Artist artist5 = new Artist("Vlad Munteanu", GenMuzical.POP, 20);
        Artist artist7 = new Artist("GamiOS", GenMuzical.POP, 20);
        Artist artist8 = new Artist("Dj Rynno", GenMuzical.ELECTRONICA, 20);
        Artist artist9 = new Artist("Dj Dark", GenMuzical.ELECTRONICA, 20);
        Artist artist10 = new Artist("Dj Sava", GenMuzical.ELECTRONICA, 20);
        Artist artist11 = new Artist("Dj Project", GenMuzical.ELECTRONICA, 20);
        //mai multi artisiti
        Artist artist12 = new Artist("Dj Oda", GenMuzical.ELECTRONICA, 20);
        Artist artist13 = new Artist("Dj White", GenMuzical.ELECTRONICA, 20);
        Artist artist14 = new Artist("Dj OS", GenMuzical.ELECTRONICA, 20);
        Artist artist15 = new Artist("Dj Black", GenMuzical.ELECTRONICA, 20);

        ArrayList<Artist> ArtistiUndold = new ArrayList<>();
        ArtistiUndold.add(artist1);
        ArtistiUndold.add(artist2);
        ArtistiUndold.add(artist3);
        ArtistiUndold.add(artist4);
        ArrayList<Artist> ArtistiElectricCastle = new ArrayList<>();
        ArtistiElectricCastle.add(artist5);
        ArtistiElectricCastle.add(artist6);
        ArtistiElectricCastle.add(artist7);
        ArrayList<Artist> ArtistiSummerWell = new ArrayList<>();
        ArtistiSummerWell.add(artist8);
        ArtistiSummerWell.add(artist9);
        ArtistiSummerWell.add(artist10);
        ArrayList<Artist> ArtistiNeversea = new ArrayList<>();
        ArtistiNeversea.add(artist11);
        ArtistiNeversea.add(artist12);
        ArtistiNeversea.add(artist13);
        ArrayList<Artist> ArtistiBeachPlease = new ArrayList<>();
        ArtistiBeachPlease.add(artist14);
        ArtistiBeachPlease.add(artist15);

        //adaugare in baza de date

        //DbManager.makeDbUnpopulated();

//        DbManager.addArtists(ArtistiUndold);
//        DbManager.addArtists(ArtistiElectricCastle);
//        DbManager.addArtists(ArtistiSummerWell);
//        DbManager.addArtists(ArtistiNeversea);
//        DbManager.addArtists(ArtistiBeachPlease);

        //creare festivaluri
        Festival eveniment = new Festival("Undold", "Cluj", "03.08.2021", 50, ArtistiUndold);
        Festival eveniment1 = new Festival("Electric Castle", "Cluj", "03.08.2021", 100, ArtistiElectricCastle);
        Festival eveniment2 = new Festival("Summer Well", "Cluj", "03.08.2021", 5130, ArtistiSummerWell);
        Festival eveniment3 = new Festival("Neversea", "Cluj", "03.08.2021", 5013, ArtistiNeversea);
        Festival eveniment4 = new Festival("Beach Please", "Cluj", "03.08.2021", 50, ArtistiBeachPlease);

        Concert eveniment5 = new Concert("Concert1", "Arenele Romane", "03.08.2021", 50, artist3, "20:00");
        Concert eveniment6 = new Concert("Concert2", "Arenele Romane", "03.08.2021", 100, artist8, "20:00");
        Concert eveniment7 = new Concert("Concert3", "Arenele Romane", "03.08.2021", 5130, artist14, "20:00");
        Concert eveniment8 = new Concert("Concert4", "Arenele Romane", "03.08.2021", 5013, artist10, "20:00");
        FanMeeting eveniment9 = new FanMeeting("FanMeeting1", "Arenele Romane", "03.08.2021", 5130, artist12, "20:00");
        FanMeeting eveniment10 = new FanMeeting("FanMeeting2", "Romexpo", "03.08.2021", 1200, artist13, "20:00");
        FanMeeting eveniment11 = new FanMeeting("FanMeeting3", "Palatul Parlamentului", "03.08.2021", 5200, artist10, "20:00");
        Petrecere eveniment12 = new Petrecere("Petrecere1", "Arenele Romane", "03.08.2021", 2300, "Dj Rynno", "20:00");
        Petrecere eveniment13 = new Petrecere("Petrecere2", "Arenele Romane", "03.08.2021", 512300, "Dj Dark", "20:00");
        Petrecere eveniment14 = new Petrecere("Petrecere3", "Arenele Romane", "03.08.2021", 5320, "Dj Sava", "20:00");
        Petrecere eveniment15 = new Petrecere("Petrecere4", "Arenele Romane", "03.08.2021", 50230, "Dj Project", "20:00");
        //array de evenimente
        ArrayList<Eveniment> evenimente = new ArrayList<>();

        evenimente.add(eveniment);
        evenimente.add(eveniment1);
        evenimente.add(eveniment2);
        evenimente.add(eveniment3);
        evenimente.add(eveniment4);
        evenimente.add(eveniment5);
        evenimente.add(eveniment6);
        evenimente.add(eveniment7);
        evenimente.add(eveniment8);
        evenimente.add(eveniment9);
        evenimente.add(eveniment10);
        evenimente.add(eveniment11);
        evenimente.add(eveniment12);
        evenimente.add(eveniment13);
        evenimente.add(eveniment14);
        evenimente.add(eveniment15);

        //adaugare in baza de date
        //DbManager.addEvents(evenimente);
        //PageService.setEvenimente(evenimente);

        //DbManager.makeDbPopulated();

    }
    public static void main(String[] args) {

        new HomePage();
        creareArtistisiFestivale();
    }
}