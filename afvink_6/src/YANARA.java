
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class YANARA extends JFrame implements KeyListener, ActionListener {


    // https://www.chem.wisc.edu/deptfiles/genchem/netorial/modules/biomolecules/modules/protein1/prot13.htm
    static final String[] POLAR = {"S", "T", "C", "N", "Q", "Q", "Y"};
    static final String[] APOLAR = {"G", "A", "V", "L", "I", "P", "F", "M", "W"};

    public static int WIDTH = 680, HEIGHT = WIDTH / 12 * 9;

    private JTextArea mainTextArea;
    private JTextField fileInputTextField;
    private JButton browseFileButton;
    private JButton analyseButton;
    private JLabel fileInputLabel;
    private JLabel informationLabel;
    private JLabel percentageLabel;

    private JPanel progressBarContainer;
    private JProgressBar polarityProgressBar;
    private JProgressBar aPolarityProgressBar;

    JFileChooser fileBrowser;


    public static Dimension prefferedButtonSizeDimension = new Dimension(100, 40);
    public static Dimension prefferedProgressBarSizeDimension = new Dimension(380, 50);


    private boolean firstPress = true;


    YANARA() {
        // constructor btw
        fileBrowser = new JFileChooser(".");


        setSize(WIDTH, HEIGHT);
//        YAGFTP gui = new YAGFTP(WIDTH, HEIGHT);
//        add(gui);
//        setVisible(true);
        setLayout(new BorderLayout(10, 5));
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        JPanel topPanel = new JPanel();
        add(topPanel, BorderLayout.NORTH);
        topPanel.setLayout(new BorderLayout());

        fileInputLabel = new JLabel("   bestand");
        topPanel.add(fileInputLabel, BorderLayout.WEST);

        JPanel topPanelCenter = new JPanel();
        topPanel.add(topPanelCenter, BorderLayout.CENTER);

        fileInputTextField = new JTextField();
        // todo: findout convention, set before add or after?
        topPanelCenter.add(fileInputTextField);
        fileInputTextField.setPreferredSize(new Dimension(250, 40));
        fileInputTextField.addKeyListener(this);
        browseFileButton = new JButton("blader");
        browseFileButton.setPreferredSize(prefferedButtonSizeDimension);
        browseFileButton.addKeyListener(this);
        browseFileButton.addActionListener(this);
        topPanelCenter.add(browseFileButton);
        analyseButton = new JButton("analyseren");
        analyseButton.setPreferredSize(prefferedButtonSizeDimension);
        analyseButton.addKeyListener(this);
        topPanelCenter.add(analyseButton);

        informationLabel = new JLabel("   informatie");
        add(informationLabel, BorderLayout.WEST);

        mainTextArea = new JTextArea();
        mainTextArea.setPreferredSize(new Dimension(300, 300));
        add(mainTextArea, BorderLayout.CENTER);
        mainTextArea.addKeyListener(this);

        // todo add a scrollbar
        progressBarContainer = new JPanel();
        JPanel progressBarContainerCenter = new JPanel();
        progressBarContainerCenter.setPreferredSize(new Dimension(380, 120));

        percentageLabel = new JLabel("   percentage");
        progressBarContainer.setLayout(new BorderLayout());
        progressBarContainer.add(percentageLabel, BorderLayout.WEST);

        // polaire indicator
        polarityProgressBar = new JProgressBar();
        polarityProgressBar.setPreferredSize(prefferedProgressBarSizeDimension);
        polarityProgressBar.setString("polair: 0/0");
        polarityProgressBar.setStringPainted(true);
        polarityProgressBar.setBackground(new Color(0xF60003));
        // apolaire indicator
        aPolarityProgressBar = new JProgressBar();
        aPolarityProgressBar.setPreferredSize(prefferedProgressBarSizeDimension);
        aPolarityProgressBar.setString("apolair: 0/0");
        aPolarityProgressBar.setStringPainted(true);
        aPolarityProgressBar.setBackground(new Color(0x5D61ED));
        polarityProgressBar.setForeground(new Color(239, 26, 37));
        aPolarityProgressBar.setForeground(new Color(131, 126, 238));


        progressBarContainerCenter.add(polarityProgressBar);
        progressBarContainerCenter.add(aPolarityProgressBar);
        progressBarContainer.add(progressBarContainerCenter, BorderLayout.CENTER);
        add(progressBarContainer, BorderLayout.SOUTH);


        // very ugly way to add a marigin lol
        JPanel eastFillPanel = new JPanel();
        JLabel fillLabel = new JLabel();
        fillLabel.setText("                       ");
        eastFillPanel.add(fillLabel);
        add(eastFillPanel, BorderLayout.EAST);

        setVisible(true);



        mainTextArea.setLineWrap(true);
        mainTextArea.setText("Press tab or shift-tab to move around the components \n press escape to quit the application at any time");
        mainTextArea.requestFocus();
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        YANARA main = new YANARA();
        // YAGFTP second = new YAGFTP();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        Object source = e.getSource();

        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(1);

        }
        if (firstPress) {
            if (source == mainTextArea) {
                firstPress = false;
                mainTextArea.setText("");
            }
        }

        if (source == mainTextArea) {
            if (key == KeyEvent.VK_TAB) {
                if (e.getModifiers() > 0) {
                    mainTextArea.transferFocusBackward();
                } else {
                    mainTextArea.transferFocus();
                }
                e.consume();
            }

        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    static boolean itsSomething(String c, String[] something) {
        for (int i = 0; i < something.length; i++) {
            if (something[i].equals(c)) {
                return true;
            }

        }
        return false;
    }

    static boolean itsPolar(String c) {
        return itsSomething(c, POLAR);
    }
    static boolean itsAPolar(String c) {
        return itsSomething(c, APOLAR);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == browseFileButton) {
            int returnVal = fileBrowser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileBrowser.getSelectedFile();

                // do stuff with file handle
                String filename = file.getName();

                // show file in textInputField
                fileInputTextField.setText(filename);
                fileInputTextField.setCaretPosition(0);     // user can read input file from [0->textbox.len]

                // read through file
                BufferedReader reader;
                try {
                    reader = new BufferedReader(new FileReader(filename));  // or should I pass the file?
                    String line = reader.readLine(); // header skip
                    mainTextArea.append(line + "\n");
                    line = reader.readLine();


                    int polar = 0;
                    int apolar = 0;
                    int seqLength = 0;

                    while (line != null) {

                        seqLength+= line.length();

                        if (firstPress) {
                            mainTextArea.setText("");
                            firstPress = false;
                        }
                        System.out.println(line);
                        mainTextArea.append(line+ "\n");

                        for (int i = 0; i < line.length(); i++) {
                            String c = Character.toString(line.charAt(i));
                            System.out.println(c);

                            if (itsPolar(c)) {
                                polar++;
                            } else if (itsAPolar(c)) {
                                apolar++;
                            }

                        }
                        // read line for next iteration
                        line = reader.readLine();




                    }

                    reader.close();
                    System.out.println("Polar: " + polar + " aPolar: " + apolar);
                    polarityProgressBar.setValue(polar);
                    polarityProgressBar.setString("polair: "+String.valueOf(polar) + "/" + seqLength);
                    polarityProgressBar.setMinimum(0);
                    polarityProgressBar.setMaximum(seqLength);

                    aPolarityProgressBar.setValue(apolar);
                    aPolarityProgressBar.setString("apolair: "+ String.valueOf(apolar) + "/" + seqLength);
                    aPolarityProgressBar.setMinimum(0);
                    aPolarityProgressBar.setMaximum(seqLength);
                } catch (IOException exc) {     // e already taken
                    exc.printStackTrace();
                }

            }

        }
    }
}
