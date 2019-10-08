import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;
import com.sun.xml.internal.ws.api.ha.StickyFeature;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class YANARA extends JFrame implements KeyListener {

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
    private JProgressBar aPolarityPorgessBar;



    public static Dimension prefferedButtonSizeDimension = new Dimension(100, 40);
    public static Dimension prefferedProgressBarSizeDimension = new Dimension(380, 50);


    private boolean firstPress = true;


    YANARA() {
        setSize(WIDTH, HEIGHT);
//        YAGFTP gui = new YAGFTP(WIDTH, HEIGHT);
//        add(gui);
//        setVisible(true);
        setLayout(new BorderLayout(10, 5));
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        JPanel topPanel = new JPanel();
        add(topPanel, BorderLayout.NORTH);
        topPanel.setLayout(new BorderLayout());

        fileInputLabel = new JLabel("bestand");
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
        topPanelCenter.add(browseFileButton);
        analyseButton = new JButton("analyseren");
        analyseButton.setPreferredSize(prefferedButtonSizeDimension);
        analyseButton.addKeyListener(this);
        topPanelCenter.add(analyseButton);

        informationLabel = new JLabel("informatie");
        add(informationLabel, BorderLayout.WEST);

        mainTextArea = new JTextArea();
        mainTextArea.setPreferredSize(new Dimension(300, 300));
        add(mainTextArea, BorderLayout.CENTER);
        mainTextArea.addKeyListener(this);

        // todo add a scrollbar
        progressBarContainer = new JPanel();
        JPanel progressBarContainerCenter = new JPanel();
        progressBarContainerCenter.setPreferredSize(new Dimension(380, 120));

        percentageLabel = new JLabel("percentage");
        progressBarContainer.setLayout(new BorderLayout());
        progressBarContainer.add(percentageLabel, BorderLayout.WEST);



        polarityProgressBar = new JProgressBar();
        polarityProgressBar.setPreferredSize(prefferedProgressBarSizeDimension);
        aPolarityPorgessBar = new JProgressBar();
        aPolarityPorgessBar.setPreferredSize(prefferedProgressBarSizeDimension);

        progressBarContainerCenter.add(polarityProgressBar);
        progressBarContainerCenter.add(aPolarityPorgessBar);
        progressBarContainer.add(progressBarContainerCenter, BorderLayout.CENTER);
        add(progressBarContainer, BorderLayout.SOUTH);


        // very ugly way to add a marigin lol
        JPanel eastFillPanel = new JPanel();
        JLabel fillLabel = new JLabel();
        fillLabel.setText("                       ");
        eastFillPanel.add(fillLabel);
        add(eastFillPanel, BorderLayout.EAST);

        setVisible(true);

        mainTextArea.setText("Press tab or shift-tab to move around the components \n press escape to quit the application at any time(??)");
        mainTextArea.requestFocus();
    }

    public static void main(String[] args) {

        YANARA main = new YANARA();
        YAGFTP second = new YAGFTP();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        Object source = e.getSource();

        System.out.println(key);
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(1);

        }
        if (firstPress) {
            if ( source == mainTextArea) {
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
}
