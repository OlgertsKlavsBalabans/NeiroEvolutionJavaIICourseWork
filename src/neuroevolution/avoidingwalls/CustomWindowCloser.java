package neuroevolution.avoidingwalls;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CustomWindowCloser extends WindowAdapter {

    @Override
    public void windowClosing(WindowEvent evt) {
        System.exit(0);
    }
}
