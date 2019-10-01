import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RaceGUI extends JFrame implements ActionListener {

    private int trackHeight = 25;
    protected static int fieldSizeY = 250;
    public int CLICKS = 0;
    protected  Graphics g;

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, fieldSizeY);
    }

    private JPanel raceFieldPanel;
    private JPanel rootPanel;
    private JTabbedPane rootTabs;
    private JPanel topBarPanel;
    private JPanel buttonPanel;
    private JButton startRaceButton;
    private JTextArea statisticsLogTextArea;
    private JButton actualRaceButton;
    private JScrollPane raceFieldScrollPane;
    private int raceSteps;


    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);

    }

    public RaceGUI(){

        //position in the middle of the screen
//        setLocationRelativeTo(null);


        add(rootPanel);
        setTitle("Horse Race");
        setSize(new Dimension(900, 500));
        rootPanel.setSize(new Dimension(900, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setResizable(true);

        rootTabs.setPreferredSize(new Dimension(250,250));

        // Panels
        buttonPanel.setPreferredSize(new Dimension(150,250));
        raceFieldPanel.setPreferredSize(new Dimension(400, fieldSizeY));
//        raceFieldPanel.setOpaque(false);
//        raceFieldPanel.setForeground(Color.GREEN);
        raceFieldPanel.setBackground(Color.GREEN);

        // Text things
        statisticsLogTextArea.setLineWrap(true);

        // buttons
        startRaceButton.addActionListener(this);
        actualRaceButton.addActionListener(this);
    }

    public void log(String s){
        statisticsLogTextArea.append(s + "\n");
    }

    public void createTrack(int x, int y, int trackLength, Graphics g) {
        log("creating track at " + x + " " + y);
        g.setColor(new Color(169, 140, 0));
        g.fillRect(x, y, trackLength, trackHeight);
        raceFieldPanel.paintComponents(g);
    }

    public void createTrack(int x, int y, int trackLength){
        log("creating track at " + x + " " + y);
        g = raceFieldPanel.getGraphics();
        g.setColor(new Color(169, 140, 0));
        g.fillRect(x, y, trackLength, trackHeight);

        //set raceField height to be higher (for scrollbar adjustment)
        if (y * 30 > fieldSizeY) fieldSizeY = y*30;



        // code that requests raceFielPanel grahpics and draws to it
    }

    public void fillTrack(int x, int y, int pathLength, String name){
        log("filling track");
        Graphics g = raceFieldPanel.getGraphics();
        g.setColor(Color.BLUE);
        g.fillRect(x, y+2, pathLength, trackHeight-4);

        // horse label
        g.setColor(Color.green);
        g.fillRect(x+3, y - 11, 100, 10);
        g.setFont(Font.getFont("Comic Sans"));
        g.setColor(Color.RED); // later for unavailable fields but now just a labelbox
        g.drawRect(x+3, y-11,name.length()*6, 10);
        g.setColor(Color.ORANGE);
        g.drawString(name, x+3, y-1);
        /*
        am i understanding this?

            Labels appear with x,y at bottom left?
            while bounding boxes are x,y top left?
         */
        //raceFieldPanel.paintComponents(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // statisticsLogTextArea.append("logging test \n" + e.toString() + "\n" + e.getSource() + "\n");


        Graphics g = raceFieldPanel.getGraphics();

        int x = 0;
        JButton source = (JButton) e.getSource();
        if (source == startRaceButton){
            CLICKS++;
            //refactor clicks to TracksMade, refactor startRace to makeTracks
            // check horse track <= horseavailable (or draw red track)
            //
            createTrack(x, (CLICKS *10)+(CLICKS *30),10000, g);
        }
        else if (source == actualRaceButton){
            raceSteps++;
            // c
            for (int i = 1; i <= CLICKS; i++) {
                int rand = ThreadLocalRandom.current().nextInt(1, 15+1);
                Random name = new Random();
                int r = (int) name.nextLong();
                fillTrack(x, (i * 10) + (i * 30), raceSteps*rand, Integer.toString(r) );

                }
            }
        }

    }

