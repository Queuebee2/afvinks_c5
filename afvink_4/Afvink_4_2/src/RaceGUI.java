import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class RaceGUI extends JFrame {

    //protected JPanel raceFieldPanel;
    protected JPanel rootPanel;
    protected JTabbedPane rootTabs;
    protected JPanel buttonPanel;
    protected JScrollPane raceFieldScrollPane;
    protected JPanel topBarPanel;
    protected JButton createTrackButton;
    protected JButton runRaceButton;
    protected JTextArea statisticsLogTextArea;
    protected JLabel title;
    protected JLabel text1;

    public RaceGUI(int width, int height, Game game) {

        setTitle("HorseRacingGame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        setSize(width, height);

        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new FlowLayout());

        rootTabs = new JTabbedPane();
        rootTabs.addTab("log", new JPanel());
        rootTabs.addTab("statistics",new JPanel());
        rootTabs.addTab("Game", new JPanel());
        rootTabs.setPreferredSize(new Dimension(200, 400));

        JPanel sidePanel = new JPanel();
        title = new JLabel("Horse Racing Game");
        sidePanel.add(title);
        sidePanel.add(rootTabs);
        sidePanel.setPreferredSize(new Dimension(200, 400));

        JPanel raceField = new JPanel();

        add(sidePanel);
        add(raceField);
        raceField.add(game);
        game.setPreferredSize(new Dimension(500, 400));


        setVisible(true);

        //add(game);








        game.start();

    }


}