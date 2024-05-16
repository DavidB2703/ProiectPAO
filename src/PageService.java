import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class PageService {
    protected static final CardLayout cardLayout = new CardLayout();
    protected static final JPanel cardPanel = new JPanel(cardLayout);
    private static ArrayList<Eveniment> anumiteEvenimente = new ArrayList<>();
    private static JPanel homePannel = new JPanel();
    public static void set_homePannel(JPanel homePannel) {
        PageService.homePannel = homePannel;
    }

   private DbManager db = DbManager.getInstance();

    private void selectEvents(TipEveniment tip) {
        anumiteEvenimente.clear();
        //luam doar evenimentele de tipul tip din baza de date
        anumiteEvenimente = db.getEvenimente(tip);
    }

    private JButton deleteButton(){
        JButton deleteButton = new JButton("Delete");
        deleteButton.setFont(deleteButton.getFont().deriveFont(20.0f));
        deleteButton.setPreferredSize(new Dimension(200, 80));
        return deleteButton;
    }

    private JButton UpdateButton(){
        JButton backButton = new JButton("Update");
        backButton.setFont(backButton.getFont().deriveFont(20.0f));
        backButton.setPreferredSize(new Dimension(200, 80));
        return backButton;
    }
    private JButton backButton(){
        JButton backButton = new JButton("Back");
        backButton.setFont(backButton.getFont().deriveFont(20.0f));
        backButton.setPreferredSize(new Dimension(200, 80));
        return backButton;
    }
    private JButton sortButton(){
        JButton sortButton = new JButton("Sort");
        sortButton.setFont(sortButton.getFont().deriveFont(20.0f));
        sortButton.setPreferredSize(new Dimension(200, 80));
        return sortButton;
    }

    private JButton showArtistButton(){
        JButton showArtistButton = new JButton("Show Artist");
        showArtistButton.setFont(showArtistButton.getFont().deriveFont(20.0f));
        showArtistButton.setPreferredSize(new Dimension(200, 80));
        return showArtistButton;
    }

    private JButton showNewButton(){
        JButton showNewButton = new JButton("New");
        showNewButton.setFont(showNewButton.getFont().deriveFont(20.0f));
        showNewButton.setPreferredSize(new Dimension(200, 80));
        return showNewButton;
    }

    private void displaySingleEvent(Eveniment eveniment){

        JTextArea textArea = new JTextArea(eveniment.afisareEveniment());
        textArea.setFont(textArea.getFont().deriveFont(30.0f));
        textArea.setWrapStyleWord(true); // set word wrap
        textArea.setLineWrap(true); // set line wrap
        textArea.setEditable(false); // make text area non-editable
        textArea.setBorder(null); // remove border

        // Create a JPanel to hold the JTextArea
        JPanel textPanel = new JPanel(new BorderLayout());



        textPanel.add(textArea);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(textPanel); // Add the textPanel instead of the JTextArea
        JButton backButton = backButton();
        panel.add(backButton);
        JButton updateButton = UpdateButton();
        panel.add(updateButton);
        JButton deleleButton = deleteButton();
        panel.add(deleleButton);

        updateButton.addActionListener(e -> {
            //deschidem un dialog pentru a citi datele
            AuditService.logAction("Incercare Update event");
            JTextField nume = new JTextField();
            JTextField locatie = new JTextField();
            JTextField data = new JTextField();
            JTextField pretBilet = new JTextField();
            if (eveniment.getTip() == TipEveniment.CONCERT) {
                JTextField ora = new JTextField();
                JTextField artist = new JTextField();
                JPanel myPanel = new JPanel();
                myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
                myPanel.add(new JLabel("Nume:"));
                myPanel.add(nume);
                myPanel.add(new JLabel("Locatie:"));
                myPanel.add(locatie);
                myPanel.add(new JLabel("Data:"));
                myPanel.add(data);
                myPanel.add(new JLabel("Pret Bilet:"));
                myPanel.add(pretBilet);
                myPanel.add(new JLabel("Ora:"));
                myPanel.add(ora);
                myPanel.add(new JLabel("Artist:"));
                myPanel.add(artist);
                int result = JOptionPane.showConfirmDialog(null, myPanel,
                        "Please Enter the Event", JOptionPane.OK_CANCEL_OPTION);
                //cautam artistul in baza de date
                Artist artist1 = db.getArtistByName(artist.getText());
                if (result == JOptionPane.OK_OPTION) {
                    Concert concertToEdit = (Concert) eveniment;
                    int id = db.getConcertID((concertToEdit));
                    //adaugam in baza de date
                    Concert concert = new Concert(nume.getText(), locatie.getText(), data.getText(), Integer.parseInt(pretBilet.getText()), artist1, ora.getText());
                    ConcertRepository concertRepository = new ConcertRepository();
                    concertRepository.updateConcert(concert, id);
                    AuditService.logAction("Concert editat cu succes");

                }
            } else if (eveniment.getTip() == TipEveniment.FAN_MEETING) {
                JTextField durata = new JTextField();
                JTextField artist = new JTextField();
                JPanel myPanel = new JPanel();
                myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
                myPanel.add(new JLabel("Nume:"));
                myPanel.add(nume);
                myPanel.add(new JLabel("Locatie:"));
                myPanel.add(locatie);
                myPanel.add(new JLabel("Data:"));
                myPanel.add(data);
                myPanel.add(new JLabel("Pret Bilet:"));
                myPanel.add(pretBilet);
                myPanel.add(new JLabel("Durata:"));
                myPanel.add(durata);
                myPanel.add(new JLabel("Artist:"));
                myPanel.add(artist);
                int result = JOptionPane.showConfirmDialog(null, myPanel,
                        "Please Enter the Event", JOptionPane.OK_CANCEL_OPTION);
                //cautam artistul in baza de date
                Artist artist1 = db.getArtistByName(artist.getText());
                if (result == JOptionPane.OK_OPTION) {
                    FanMeeting fanMeetingToEdit = (FanMeeting) eveniment;
                    int id = db.getFanMeetingID(fanMeetingToEdit);
                    //adaugam in baza de date
                    FanMeeting fanMeeting = new FanMeeting(nume.getText(), locatie.getText(), data.getText(), Integer.parseInt(pretBilet.getText()), artist1, durata.getText());
                    FanMeetingRepository fanMeetingRepository = new FanMeetingRepository();
                    fanMeetingRepository.updateFanMeeting(fanMeeting, id);
                    AuditService.logAction("FanMeeting editat cu succes");
                }
            }
            else if (eveniment.getTip() == TipEveniment.PETRECERE){
                //e petrecere
                System.out.println("Petrecere");
                JTextField OraIncepere = new JTextField();
                JTextField Dj = new JTextField();
                JPanel myPanel = new JPanel();
                myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
                myPanel.add(new JLabel("Nume:"));
                myPanel.add(nume);
                myPanel.add(new JLabel("Locatie:"));
                myPanel.add(locatie);
                myPanel.add(new JLabel("Data:"));
                myPanel.add(data);
                myPanel.add(new JLabel("Pret Bilet:"));
                myPanel.add(pretBilet);
                myPanel.add(new JLabel("Ora Incepere:"));
                myPanel.add(OraIncepere);
                myPanel.add(new JLabel("Dj:"));
                myPanel.add(Dj);
                System.out.println("Dj");
                int result = JOptionPane.showConfirmDialog(null, myPanel,
                        "Please Enter the Event", JOptionPane.OK_CANCEL_OPTION);
                if(result == JOptionPane.OK_OPTION) {

                    Petrecere petrecere = (Petrecere) eveniment;
                    int id = db.getPetrecereID(petrecere);
                    Petrecere petrecereNoua = new Petrecere(nume.getText(), locatie.getText(), data.getText(), Integer.parseInt(pretBilet.getText()), OraIncepere.getText(), Dj.getText());
                    PetrecereRepository petrecereRepository = new PetrecereRepository();
                    petrecereRepository.updatePetrecere(petrecereNoua, id);
                    AuditService.logAction("Petrecere editata cu succes");
                }
            }

        });

        deleleButton.addActionListener(e -> {
            //stergem evenimentul
            //stergem evenimentul in functie de tip
            if(eveniment.getTip() == TipEveniment.CONCERT){
                Concert concert = (Concert) eveniment;
                ConcertRepository concertRepository = new ConcertRepository();
                concertRepository.deleteConcert(concert);
                AuditService.logAction("Stergere concert");
            }
            else if(eveniment.getTip() == TipEveniment.FAN_MEETING){
                FanMeeting fanMeeting = (FanMeeting) eveniment;
                FanMeetingRepository fanMeetingRepository = new FanMeetingRepository();
                fanMeetingRepository.deleteFanMeeting(fanMeeting);
                AuditService.logAction("Stergere fan meeting");
            }
            else{
                Petrecere petrecere = (Petrecere) eveniment;
                PetrecereRepository petrecereRepository = new PetrecereRepository();
                petrecereRepository.deletePetrecere(petrecere);
                AuditService.logAction("Stergere petrecere");
            }
            cardLayout.show(cardPanel, eveniment.getTip().toString());
        });

        backButton.addActionListener(e -> {cardLayout.show(cardPanel, eveniment.getTip().toString()); AuditService.logAction("Back to events");});
        cardPanel.add(panel, "singleEvent");
        cardLayout.show(cardPanel, "singleEvent");

        JButton showArtistButton = showArtistButton();
        panel.add(showArtistButton);
        showArtistButton.addActionListener(e -> {
            //afisam artistii, cu un alert
            ArrayList<Artist> artisti = eveniment.getArtisti();
            String artistiString = "";
            for (Artist artist : artisti) {
                artistiString += artist.toString() + "\n";
            }
            JOptionPane.showMessageDialog(null, artistiString);
        });

    }

    public void displayEvents(JPanel panel) {
        for (Eveniment eveniment : anumiteEvenimente) {

            JButton button = new JButton(eveniment.getNume());
            button.setFont(button.getFont().deriveFont(20.0f));
            button.setPreferredSize(new Dimension(500, 220));
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.addActionListener(e -> {
                this.displaySingleEvent(eveniment);
            });
            panel.add(button);
        }
    }

    public void displayEventsPage(TipEveniment tip) {

        AuditService.logAction("Afisare evenimente");
        JPanel panel = new JPanel();
        cardPanel.add(panel, tip.toString());
        selectEvents(tip);
        displayEvents(panel);
        JButton backButton = backButton();
        panel.add(backButton, BorderLayout.SOUTH);
        backButton.addActionListener(
                e -> {cardLayout.show(cardPanel, "home");AuditService.logAction("Back to home");});
        cardLayout.show(cardPanel, tip.toString()
        );
        //mai adaugam un buton de sort
        JButton sortButton = sortButton();
        panel.add(sortButton, BorderLayout.NORTH);
        sortButton.addActionListener(e -> {
            //sortam dupa pret
            anumiteEvenimente.sort((o1, o2) -> o1.getPretBilet() - o2.getPretBilet());
            cardPanel.remove(panel);
            JPanel newPanel = new JPanel();
            displayEvents(newPanel);
            newPanel.add(backButton, BorderLayout.SOUTH);
            newPanel.add(sortButton, BorderLayout.NORTH);
            cardPanel.add(newPanel, tip.toString());
            cardLayout.show(cardPanel, tip.toString());
            AuditService.logAction("Sortare dupa pret");
        });
        JButton showNewButton = showNewButton();
        panel.add(showNewButton);
        showNewButton.addActionListener(e -> {
            // citim din aplicatie un nou eveniment de tipul respectiv
            // si il adaugam in baza de date
            // apoi il afisam
            //deschidem un dialog pentru a citi datele
            AuditService.logAction("Incercare adaugare eveniment");
            JTextField nume = new JTextField();
            JTextField locatie = new JTextField();
            JTextField data = new JTextField();
            JTextField pretBilet = new JTextField();
            if (tip == TipEveniment.CONCERT) {
                JTextField ora = new JTextField();
                JTextField artist = new JTextField();
                JPanel myPanel = new JPanel();
                myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
                myPanel.add(new JLabel("Nume:"));
                myPanel.add(nume);
                myPanel.add(new JLabel("Locatie:"));
                myPanel.add(locatie);
                myPanel.add(new JLabel("Data:"));
                myPanel.add(data);
                myPanel.add(new JLabel("Pret Bilet:"));
                myPanel.add(pretBilet);
                myPanel.add(new JLabel("Ora:"));
                myPanel.add(ora);
                myPanel.add(new JLabel("Artist:"));
                myPanel.add(artist);
                int result = JOptionPane.showConfirmDialog(null, myPanel,
                        "Please Enter the Event", JOptionPane.OK_CANCEL_OPTION);
                //cautam artistul in baza de date
                Artist artist1 = db.getArtistByName(artist.getText());
                if (result == JOptionPane.OK_OPTION) {
                    //adaugam in baza de date
                    Concert concert = new Concert(nume.getText(), locatie.getText(), data.getText(), Integer.parseInt(pretBilet.getText()), artist1, ora.getText());
                    ConcertRepository concertRepository = new ConcertRepository();
                    concertRepository.addConcert(concert);
                    AuditService.logAction("Concert adaugat cu succes");
                }
            } else if (tip == TipEveniment.FAN_MEETING) {
                JTextField durata = new JTextField();
                JTextField artist = new JTextField();
                JPanel myPanel = new JPanel();
                myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
                myPanel.add(new JLabel("Nume:"));
                myPanel.add(nume);
                myPanel.add(new JLabel("Locatie:"));
                myPanel.add(locatie);
                myPanel.add(new JLabel("Data:"));
                myPanel.add(data);
                myPanel.add(new JLabel("Pret Bilet:"));
                myPanel.add(pretBilet);
                myPanel.add(new JLabel("Durata:"));
                myPanel.add(durata);
                myPanel.add(new JLabel("Artist:"));
                myPanel.add(artist);
                int result = JOptionPane.showConfirmDialog(null, myPanel,
                        "Please Enter the Event", JOptionPane.OK_CANCEL_OPTION);
                //cautam artistul in baza de date
                Artist artist1 = db.getArtistByName(artist.getText());
                if (result == JOptionPane.OK_OPTION) {
                    //adaugam in baza de date
                    FanMeeting fanMeeting = new FanMeeting(nume.getText(), locatie.getText(), data.getText(), Integer.parseInt(pretBilet.getText()), artist1, durata.getText());
                    FanMeetingRepository fanMeetingRepository = new FanMeetingRepository();
                    fanMeetingRepository.addFanMeeting(fanMeeting);
                    AuditService.logAction("FanMeeting adaugat cu succes");
                }
            } else if (tip == TipEveniment.FESTIVAL) {
                JTextField artist = new JTextField();
                JPanel myPanel = new JPanel();
                myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
                myPanel.add(new JLabel("Nume:"));
                myPanel.add(nume);
                myPanel.add(new JLabel("Locatie:"));
                myPanel.add(locatie);
                myPanel.add(new JLabel("Data:"));
                myPanel.add(data);
                myPanel.add(new JLabel("Pret Bilet:"));
                myPanel.add(pretBilet);
                myPanel.add(new JLabel("Artist:"));
                myPanel.add(artist);
                int result = JOptionPane.showConfirmDialog(null, myPanel,
                        "Please Enter the Event", JOptionPane.OK_CANCEL_OPTION);
            }
            else if (tip == TipEveniment.PETRECERE){
                //e petrecere
                System.out.println("Petrecere");
                JTextField OraIncepere = new JTextField();
                JTextField Dj = new JTextField();
                JPanel myPanel = new JPanel();
                myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
                myPanel.add(new JLabel("Nume:"));
                myPanel.add(nume);
                myPanel.add(new JLabel("Locatie:"));
                myPanel.add(locatie);
                myPanel.add(new JLabel("Data:"));
                myPanel.add(data);
                myPanel.add(new JLabel("Pret Bilet:"));
                myPanel.add(pretBilet);
                myPanel.add(new JLabel("Ora Incepere:"));
                myPanel.add(OraIncepere);
                myPanel.add(new JLabel("Dj:"));
                myPanel.add(Dj);
                System.out.println("Dj");
                int result = JOptionPane.showConfirmDialog(null, myPanel,
                        "Please Enter the Event", JOptionPane.OK_CANCEL_OPTION);
                Petrecere petrecereNoua = new Petrecere(nume.getText(), locatie.getText(), data.getText(), Integer.parseInt(pretBilet.getText()), OraIncepere.getText(), Dj.getText());
                PetrecereRepository petrecereRepository = new PetrecereRepository();
                petrecereRepository.writePetrecere(petrecereNoua);
                AuditService.logAction("Petrecere adaugata cu succes");
            }
        });


   }
}
