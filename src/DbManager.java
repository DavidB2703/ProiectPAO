import javax.xml.transform.Templates;
import java.sql.*;
import java.util.ArrayList;

public class DbManager {
    private static final String url = "jdbc:mysql://localhost:3306/eventapp";
    private static final String username = "root";
    private static final String password = "0000";
    private static DbManager instance = null;

    private static Boolean isDbPopulated = false;

    private DbManager() {
    }

    public static DbManager getInstance(){
    if (instance == null) {
            instance = new DbManager();
        }
        return instance;
    }

    public static ArrayList<Artist> getAllArtists(){
        ArrayList<Artist> artisti = new ArrayList<>();
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, username, password);
            String sql = "SELECT * FROM ARTIST";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Artist artist = new Artist(rs.getString("NAME"), GenMuzical.valueOf(rs.getString("GENMUZICAL")), rs.getInt("VARSTA"));
                artisti.add(artist);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get artists");
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return artisti;

    }

    public ArrayList<Eveniment> getEvenimente(TipEveniment tip){
        ArrayList<Eveniment> evenimente = new ArrayList<>();
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, username, password);
            String sql = "";
            if(tip == TipEveniment.CONCERT)
            {
                sql = "SELECT * FROM CONCERT";
            }
            else if(tip == TipEveniment.FAN_MEETING)
            {
                sql = "SELECT * FROM FAN_MEETING";
            }
            else if(tip == TipEveniment.PETRECERE)
            {
                sql = "SELECT * FROM PETRECERE";
            }
            else
            {
                sql = "SELECT * FROM FESTIVAL";
            }
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if(tip == TipEveniment.CONCERT)
                {
                    Artist artist = getArtist(rs.getInt("id_artist"));
                    Concert concert = new Concert(rs.getString("nume"), rs.getString("locatie"), rs.getString("data"),
                            rs.getInt("pret_bilet"), artist, rs.getString("ora"));
                    evenimente.add(concert);
                }
                else if(tip == TipEveniment.FAN_MEETING)
                {
                    Artist artist = getArtist(rs.getInt("id_artist"));
                    FanMeeting fanMeeting = new FanMeeting(rs.getString("nume"), rs.getString("locatie"), rs.getString("data"),
                             rs.getInt("pret_bilet"),artist , rs.getString("durata"));
                    evenimente.add(fanMeeting);
                }
                else if(tip == TipEveniment.PETRECERE)
                {
                    Petrecere petrecere = new Petrecere(rs.getString("nume"), rs.getString("locatie"), rs.getString("data"),
                            rs.getInt("pret_bilet"), rs.getString("dj"), rs.getString("ora_incepere"));
                    evenimente.add(petrecere);
                }
                else
                {
                    ArrayList<Artist> artisti = new ArrayList<>();
                    sql = "SELECT ID_ARTIST FROM PARTICIPA WHERE ID_FESTIVAL = ?;";
                    stmt = con.prepareStatement(sql);
                    stmt.setInt(1, rs.getInt("ID"));
                    ResultSet rs1 = stmt.executeQuery();
                    while (rs1.next()) {
                        artisti.add(getArtist(rs1.getInt("ID_ARTIST")));
                    }

                    Festival festival = new Festival(rs.getString("nume"), rs.getString("locatie"), rs.getString("data"),
                            rs.getInt("pret_bilet"), artisti);
                    evenimente.add(festival);
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to get events");
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return evenimente;
    }

    public void write(String query) {
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println("Failed to write to the database");
            e.printStackTrace();
        }
    }

    public String read(String query) {
        String result = "";
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result += rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + "\n";
                // afiseaza tot rezultatul query-ului, oricat de mare ar fi
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));

            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Failed to read from the database");
            e.printStackTrace();
        }
        return result;
    }

    public static int getFanMeetingID(FanMeeting fanMeeting) {
        int id = -1;
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, username, password);
            String sql = "SELECT ID FROM FAN_MEETING WHERE NUME = ?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, fanMeeting.getNume());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("ID");
            }
        } catch (SQLException e) {
            System.out.println("Failed to get fan meeting ID");
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return id;
    }
    public static int getConcertID(Concert concert) {
        int id = -1;
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, username, password);
            String sql = "SELECT ID FROM CONCERT WHERE NUME = ?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, concert.getNume());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("ID");
            }
        } catch (SQLException e) {
            System.out.println("Failed to get concert ID");
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return id;
    }

    public static int getPetrecereID(Petrecere petrecere) {
        int id = -1;
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, username, password);
            String sql = "SELECT ID FROM PETRECERE WHERE NUME = ?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, petrecere.getNume());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("ID");
            }
        } catch (SQLException e) {
            System.out.println("Failed to get petrecere ID");
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return id;
    }
    public static int getArtistID(Artist artist)
    {
        int id = -1;
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, username, password);
            String sql = "SELECT ID FROM ARTIST WHERE NAME = ?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, artist.getNume());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("ID");
            }
        } catch (SQLException e) {
            System.out.println("Failed to get artist ID");
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return id;
    }

    public static Artist getArtistByName(String Name){
        Artist artist = null;
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, username, password);
            String sql = "SELECT * FROM ARTIST WHERE NAME = ?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, Name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                artist = new Artist(rs.getString("NAME"), GenMuzical.valueOf(rs.getString("GENMUZICAL")), rs.getInt("VARSTA"));
            }
        } catch (SQLException e) {
            System.out.println("Failed to get artist by name");
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return artist;
    }

    //functie care returneaza un artist dupa id
    public static Artist getArtist(int id) {
        Artist artist = null;
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, username, password);
            String sql = "SELECT * FROM ARTIST WHERE ID = ?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                artist = new Artist(rs.getString("NAME"), GenMuzical.valueOf(rs.getString("GENMUZICAL")), rs.getInt("VARSTA"));
            }
        } catch (SQLException e) {
            System.out.println("Failed to get artist");
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return artist;
    }

    public static void addArtists(ArrayList<Artist> artisti) {

        if (isDbPopulated) {
            System.out.println("Database already populated with artists");
            return;
        }

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            for (Artist artist : artisti) {
                String sql = String.format("INSERT INTO ARTIST (NAME, GENMUZICAL, VARSTA) VALUES ('%s', '%s', %d);",
                        artist.getNume(), artist.getGenMuzical(), artist.getVarsta());
                System.out.println(sql);
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.executeUpdate();
            }
            System.out.println("Artist added to the database");
            con.close();
        } catch (SQLException e) {
            System.out.println("Failed to populate the database");
            e.printStackTrace();
        }
    }

    public static void addEvents(ArrayList<Eveniment> evenimente) {

        if (isDbPopulated) {
            System.out.println("Database already populated with events");
            return;
        }
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            for (Eveniment eveniment : evenimente) {
                if(eveniment.getTip() == TipEveniment.CONCERT)
                {
                   String  sql = String.format("INSERT INTO CONCERT (nume, locatie, data, pret_Bilet, ora, id_artist) VALUES ('%s', '%s', '%s', '%d', '%s','%d');",
                            eveniment.getNume(), eveniment.getLocatie(), eveniment.getData(),
                            eveniment.getPretBilet(),((Concert) eveniment).getOra(),
                            getArtistID(((Concert) eveniment).getArtist()));
                    //execute the query
                    PreparedStatement stmt = con.prepareStatement(sql);
                    stmt.executeUpdate();
                }
                else if (eveniment.getTip() == TipEveniment.FAN_MEETING)
                {
                    String sql = String.format("INSERT INTO FAN_MEETING (nume, locatie, data, durata, pret_bilet, id_artist) VALUES ('%s', '%s', '%s', '%s', '%d','%d');",
                            eveniment.getNume(), eveniment.getLocatie(), eveniment.getData(),
                            ((FanMeeting) eveniment).getDurata(), eveniment.getPretBilet(),
                            getArtistID(((FanMeeting) eveniment).getArtist()));

                    //execute the query
                    PreparedStatement stmt = con.prepareStatement(sql);
                    stmt.executeUpdate();
                }
                else if (eveniment.getTip() == TipEveniment.PETRECERE)
                {
                    String sql = String.format("INSERT INTO PETRECERE (nume, locatie, data, pret_bilet, dj, ora_incepere) VALUES ('%s', '%s', '%s', '%d','%s', 's');",
                            eveniment.getNume(), eveniment.getLocatie(), eveniment.getData(),
                            eveniment.getPretBilet(), ((Petrecere) eveniment).getDj(), ((Petrecere) eveniment).getOraIncepere());

                    //execute the query
                    PreparedStatement stmt = con.prepareStatement(sql);
                    stmt.executeUpdate();

                }
                else
                {
                    //e festival
                    ArrayList<Artist> artisti = eveniment.getArtisti();
                    int numar_artisti = artisti.size();
                     String sql = String.format("INSERT INTO FESTIVAL (nume, locatie, data, pret_bilet, numar_artisti, numar_zile, pret_bilet_pe_zi) VALUES ('%s', '%s', '%s', '%d', '%d', '%d', '%d');",
                            eveniment.getNume(), eveniment.getLocatie(), eveniment.getData(),
                            eveniment.getPretBilet(), numar_artisti, ((Festival) eveniment).getNumarZile(), ((Festival) eveniment).getPretBiletOZi());
                    //execute the query
                    PreparedStatement stmt = con.prepareStatement(sql);
                    stmt.executeUpdate();
                    //get the id of the festival
                    sql = "SELECT ID FROM FESTIVAL WHERE NUME = ?;";
                    stmt = con.prepareStatement(sql);
                    stmt.setString(1, eveniment.getNume());
                    ResultSet rs = stmt.executeQuery();
                    int id = -1;
                    if (rs.next()) {
                        id = rs.getInt("ID");
                    }
                    //add the artists to the festival
                    for (Artist artist : artisti) {
                        sql = String.format("INSERT INTO PARTICIPA (ID_FESTIVAL, ID_ARTIST) VALUES ('%d', '%d');",
                                id, getArtistID(artist));
                        stmt = con.prepareStatement(sql);
                        stmt.executeUpdate();
                    }
                }


            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Failed to populate the database");
            e.printStackTrace();
        }
    }


    public static void makeDbPopulated() {
        isDbPopulated = true;
    }
    public static void makeDbUnpopulated() {
        isDbPopulated = false;
    }

}