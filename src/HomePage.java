import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class HomePage implements ActionListener {

    private final PageService pageService = new PageService();
    private JFrame frame = new JFrame();
    private final JLabel label = new JLabel("Alege tipul de eveniment");
    private JPanel homePanel = new JPanel();
    private JPanel artistPanel = new JPanel();



    HomePage() {

        makeFrame();
        makeLabel();
        makeHomePanel();
        displayFrame();
    }

    private void makeFrame() {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(
                frame.getContentPane(), BoxLayout.Y_AXIS
            )
        );
    }
    private void displayFrame() {
        frame.add(pageService.cardPanel, BorderLayout.CENTER); // Add cardPanel to the center of the frame
        frame.setVisible(true);
    }

    private void makeLabel() {
        label.setFont(label.getFont().deriveFont(30.0f));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.TOP);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void makeHomePanel() {
        homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.Y_AXIS));
        homePanel.add(label);
        //adaugam tipurile de evenimente ca butoane
        for (TipEveniment tip : TipEveniment.values()) {
            JButton button = new JButton(tip.toString());
            button.setFont(button.getFont().deriveFont(20.0f));
            button.addActionListener(this);
            JPanel buttonPanel = new JPanel(new GridBagLayout());
            button.setPreferredSize(new Dimension(300, 120));
            buttonPanel.add(button);

            homePanel.add(buttonPanel);
        }

        //mai adaugam un buton de afisare al artistilor in dreapta jos
        JButton button = new JButton("Afiseaza artistii");
        button.setFont(button.getFont().deriveFont(20.0f));
        button.addActionListener(e -> {
            System.out.println("Afiseaza artistii");
            //luam toti artistii din baza de date
            //afisam artistii intr-un nou frame
            artistPanel.setLayout(new BoxLayout(artistPanel, BoxLayout.Y_AXIS));
            pageService.cardPanel.add(artistPanel, "artisti");
            ArrayList<Artist> artisti = DbManager.getAllArtists();
            for (Artist artist : artisti) {
                ArtistRepository artistRepository = new ArtistRepository();
                JPanel artistPanel1 = new JPanel();
                artistPanel1.setLayout(new BoxLayout(artistPanel1, BoxLayout.Y_AXIS));
                JButton artistButton = new JButton(artistRepository.readArtists(artist));
                artistPanel1.add(artistButton);
                artistButton.addActionListener(e1 -> {
                    //afisam informatii despre artist
                    AuditService.logAction("Afisare informatii despre artistul cu id-ul " + DbManager.getArtistID(artist));
                    //deschidem o noua pagina mica pentru afisare informatii despre artist
                    JPanel artistInfoPanel = new JPanel();
                    //vreau sa pot edita artistul aici
                    JTextField nume = new JTextField(artist.getNume());
                    JTextField gen = new JTextField(artist.getGenMuzical().toString());
                    JTextField varsta = new JTextField(String.valueOf(artist.getVarsta()));
                    artistInfoPanel.setLayout(new BoxLayout(artistInfoPanel, BoxLayout.Y_AXIS));
                    artistInfoPanel.add(new JLabel("Nume"));
                    artistInfoPanel.add(nume);
                    artistInfoPanel.add(new JLabel("Gen"));
                    artistInfoPanel.add(gen);
                    artistInfoPanel.add(new JLabel("Varsta"));
                    artistInfoPanel.add(varsta);
                    int result = JOptionPane.showConfirmDialog(null, artistInfoPanel, "Editeaza artist", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        int idArtistToEdit = DbManager.getArtistID(artist);
                        Artist newArtist = new Artist(nume.getText(), GenMuzical.valueOf(gen.getText()), Integer.parseInt(varsta.getText()));
                        artistRepository.updateArtist(newArtist, idArtistToEdit);
                    }
                });

                artistPanel.add(artistPanel1);
            }
            addArtistPanel();
            pageService.cardLayout.show(pageService.cardPanel, "artisti");
        });

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        button.setPreferredSize(new Dimension(300, 120));
        buttonPanel.add(button);
        homePanel.add(buttonPanel);
        PageService.set_homePannel(homePanel);
        pageService.cardPanel.add(homePanel, "home");

    }

    public void addArtistPanel()
    {
        //Adaufam buton de add
        JButton add = new JButton("Adauga artist");
        add.setFont(add.getFont().deriveFont(20.0f));
        add.addActionListener(e -> {
            AuditService.logAction("Adaugare artist nou");
            //deschidem o noua pagina mica pentru adaugare artist
            JTextField nume = new JTextField();
            JTextField gen = new JTextField();
            JTextField varsta = new JTextField();
            JPanel addArtistPanel = new JPanel();
            addArtistPanel.setLayout(new BoxLayout(addArtistPanel, BoxLayout.Y_AXIS));
            addArtistPanel.add(new JLabel("Nume"));
            addArtistPanel.add(nume);
            addArtistPanel.add(new JLabel("Gen"));
            addArtistPanel.add(gen);
            addArtistPanel.add(new JLabel("Varsta"));
            addArtistPanel.add(varsta);
            int result = JOptionPane.showConfirmDialog(null, addArtistPanel, "Adauga artist", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                Artist artist = new Artist(nume.getText(), GenMuzical.valueOf(gen.getText()), Integer.parseInt(varsta.getText()));
                ArtistRepository artistRepository = new ArtistRepository();
                artistRepository.addArtist(artist);
            }

        });
        artistPanel.add(add);

        //buton de back
        JButton back = new JButton("Back");
        back.setFont(back.getFont().deriveFont(20.0f));
        back.addActionListener(e -> {
            AuditService.logAction("Inapoi la pagina principala");
            //stergem toate componentele din artistPanel
            artistPanel.removeAll();
            pageService.cardLayout.show(pageService.cardPanel, "home");
        });
        artistPanel.add(back);






    }

    public void DisplayEvenimente(ArrayList<Eveniment> evenimente) {
        int y = 200;
        for (Eveniment eveniment : evenimente) {
            JButton button = new JButton(eveniment.getNume());
            button.setBounds(450, y, 600, 100);
            button.addActionListener(this);
            AuditService.logAction("Afisare eveniment " + eveniment.getNume());
            y += 130;
            button.setFont(button.getFont().deriveFont(20.0f));
            frame.add(button);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            String text = button.getText();
            TipEveniment tip = TipEveniment.valueOf(text);
            PageService pageService = new PageService();
            pageService.displayEventsPage(tip);

        }
    }
}
