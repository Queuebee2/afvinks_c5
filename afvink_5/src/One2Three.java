import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class One2Three extends JFrame {

    public int WIDTH = 600, HEIGHT = WIDTH / 12 * 6;
    private Translator translator;

    One2Three() {
        this.translator = new Translator();
        createAndShowGUI(WIDTH, HEIGHT);
    }

    public static void main(String[] args) {
        One2Three main = new One2Three();
    }


    private JPanel rootPanel;
    private JTextArea oneCharText;
    private JTextArea threeCharText;
    private JButton translateButton;
    private static String lastChars = "";


    private void createAndShowGUI(int width, int height) {


        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);



        rootPanel = new JPanel();
        rootPanel.setLayout(new FlowLayout());
        add(rootPanel);

        oneCharText = new JTextArea();
        oneCharText.setPreferredSize(new Dimension((width/3)-(width/12),height-(height/12)));
        threeCharText = new JTextArea();
        threeCharText.setPreferredSize(new Dimension((width/3)-(width/12),height-(height/12)));
        translateButton = new JButton();
        translateButton.setPreferredSize(new Dimension(100, 30));
        translateButton.setText("Translate!");

        translateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newChars = null;
                String oneChars = oneCharText.getText();
                String threeChars = threeCharText.getText();
                if ((oneChars.length() > 1) && !(lastChars.equals(oneChars))) {
                    newChars = translator.translateOne2Three(oneChars);
                    threeCharText.setText(newChars);
                    lastChars = newChars;
                } else if (threeChars.length() > 1 && (!lastChars.equals(threeChars))) {
                    try {
                        newChars = translator.translateThree2One(threeChars);
                        oneCharText.setText(newChars);
                        lastChars = newChars;
                    } catch (WrongLengthAA wrongLengthAA) {
                        System.out.println("Wrong 3 code LENGTH!");
                    }

                }
            }
        });

        rootPanel.setPreferredSize(new Dimension(width, height));
        rootPanel.setLayout(new FlowLayout());
        rootPanel.add(oneCharText);
        rootPanel.add(translateButton);
        rootPanel.add(threeCharText);


        setVisible(true);

        }
}
