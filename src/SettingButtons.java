import javax.swing.*;

/**
 * Created by Bird on 14.02.2017.
 */
public class SettingButtons {
    public static JPanel setButton(JPanel panel, JButton button, int x, int y, int width, int height) {
        button.setBounds(x, y, width, height);
        panel.add(button);
        return panel;
    }

}
