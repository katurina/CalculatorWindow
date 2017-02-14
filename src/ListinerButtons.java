import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * Created by Bird on 14.02.2017.
 */
public class ListinerButtons {
    public static void listenButtonNumber(List<JButton> button, JButton cleanButton, JButton buttonEnter, JTextField text) {
        final String[] textWriter = {""};
        for (JButton buttonInMethod : button) {
            buttonInMethod.addActionListener(e -> {
                textWriter[0] += buttonInMethod.getText();
                text.setText(textWriter[0]);
            });
        }
        text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (true) {
                    e.consume(); //ignore all symbols out of keyboard
                }
            }
        });
        buttonEnter.addActionListener(
                e1 -> {
                    text.setText(Text.parserText(textWriter[0]));
                    textWriter[0] = new String();
                }
        );
        cleanButton.addActionListener(e2 -> {
            if (!textWriter[0].isEmpty()) {
                textWriter[0] = textWriter[0].substring(0, textWriter[0].length() - 1);
                text.setText(textWriter[0]);
            } else {
                text.setText("There is nothing to delete");
            }
        });
    }
}
