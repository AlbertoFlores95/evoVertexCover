package bi.zum.lab3;

import cz.cvut.fit.zum.ZumGraphFrame;
import org.openide.util.Exceptions;

import javax.swing.*;

/**
 * @author Tomáš Řehořek
 */
public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            Exceptions.printStackTrace(e);
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    ZumGraphFrame app = new ZumGraphFrame();
                    app.setSize(800, 925);
                    app.setVisible(true);
                    app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                } catch (Exception e) {
                    Exceptions.printStackTrace(e);
                }
            }
        });
    }
}
