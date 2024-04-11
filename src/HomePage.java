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
        PageService.set_homePannel(homePanel);
        pageService.cardPanel.add(homePanel, "home");

    }

    public void DisplayEvenimente(ArrayList<Eveniment> evenimente) {
        int y = 200;
        for (Eveniment eveniment : evenimente) {
            JButton button = new JButton(eveniment.getNume());
            button.setBounds(450, y, 600, 100);
            button.addActionListener(this);
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
