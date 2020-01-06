import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaardenRace extends JFrame implements ActionListener {

    public static int AANTALPAARDEN = 6;
    public static int MAXSTEPSIZE = 55;
    public static int FINISHLINE = 200;
    public static int WIDTH = 800, HEIGHT = 400;

    private RaceManager raceManager;

    private JButton startKnop;
    private JPanel raceField;

    public PaardenRace() {

        createAndShowGUI();
        raceManager = new RaceManager(raceField.getGraphics());
    }

    public static void main(String[] args) {
        PaardenRace race = new PaardenRace();
    }


    public void createAndShowGUI() {

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        setLocationRelativeTo(null);         // zet scherm in het midden van het scherm

        // code voor paardrace

        this.startKnop = new JButton("start race");
        add(startKnop);
        startKnop.addActionListener(this);

        this.raceField = new JPanel();
        raceField.setPreferredSize(new Dimension(300,300));

        add(raceField);
        raceField.setBackground(Color.GREEN);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == startKnop) {
            System.out.println("klik!");
            if (raceManager.RaceState == RaceManager.STATE.Waiting ||
                    raceManager.RaceState == RaceManager.STATE.RaceFinished) {
                raceManager.start();
            }

        }
    }

}
