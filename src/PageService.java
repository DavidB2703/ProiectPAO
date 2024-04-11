import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class PageService {
    private static ArrayList<Eveniment> evenimente = new ArrayList<>();
    protected static final CardLayout cardLayout = new CardLayout();
    protected static final JPanel cardPanel = new JPanel(cardLayout);
    private static ArrayList<Eveniment> anumiteEvenimente = new ArrayList<>();
    private static JPanel homePannel = new JPanel();
    public static void set_homePannel(JPanel homePannel) {
        PageService.homePannel = homePannel;
    }
    public static void setEvenimente(ArrayList<Eveniment> evenimente) {
        PageService.evenimente = evenimente;
    }


    private void selectEvents(TipEveniment tip) {
        anumiteEvenimente.clear();
        for (Eveniment eveniment : evenimente) {
            if (eveniment.getTip().equals(tip)) {
                anumiteEvenimente.add(eveniment);
            }
        }
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
        backButton.addActionListener(e -> cardLayout.show(cardPanel, eveniment.getTip().toString()));
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

        JPanel panel = new JPanel();
        cardPanel.add(panel, tip.toString());
        selectEvents(tip);
        displayEvents(panel);
        JButton backButton = backButton();
        panel.add(backButton, BorderLayout.SOUTH);
        backButton.addActionListener(
                e -> cardLayout.show(cardPanel, "home"));
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
        });


   }
}
