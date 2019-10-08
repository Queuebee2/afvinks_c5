import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class YAGFTP extends JFrame {
    // yet another GUI for the public

    private JButton buttonAnalyse, buttonBrowseFile, buttonExtra = new JButton();


    YAGFTP() {
        // constructor

        setSize(500, 500);

        setLayout(new GridBagLayout());

        add(buttonAnalyse);

        setVisible(true);


    }
}
