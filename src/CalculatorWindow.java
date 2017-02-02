import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Bird on 01.02.2017.
 */
public class CalculatorWindow extends JFrame {

    private JButton summ = new JButton("+");
    private JButton subtraction = new JButton("-");
    private JButton multiplication = new JButton("*");
    private JButton divicion = new JButton("/");
    private JPanel panel = new JPanel();
    private static JTextField text = new JTextField();

    public CalculatorWindow() {
        super("Calculator"); //Заголовок окна
        setBounds(200, 200, 300, 335);
        panel.setLayout(null);

        int i = 60;
        int j = 300 / 4;
        summ.setBounds(300 - j, i, i, 60);
        subtraction.setBounds(300 - j, i * 2, i, i);
        multiplication.setBounds(300 - j, i * 3, i, i);
        divicion.setBounds(300 - j, i * 4, i, i);
        text.setBounds(0, 0, 300, 60);

        ArrayList<JButton> buttons = new ArrayList();


        panel.add(text);
        panel.add(summ);
        panel.add(subtraction);
        panel.add(multiplication);
        panel.add(divicion);
        int s = 1, s2;

        JButton button = new JButton("0");
        button.setBounds(0, i * 4, i, i);
        buttons.add(0, button);
        panel.add(button);
        for (int s1 = 1; s1 < 4; s1++) {
            for (s2 = 0; s2 < 3; s2++) {
                button = new JButton(Integer.toString(s));
                buttons.add(s, button);
                button.setBounds(s2 * i, s1 * i, i, i);
                panel.add(button);
                s++;
            }
        }
        button = new JButton(",");
        button.setBounds(i, i * 4, i, i);
        buttons.add(10, button);
        panel.add(button);

        button = new JButton("=");
        button.setBounds(i * 2, i * 4, i, i);
        buttons.add(11, button);
        panel.add(button);

        setContentPane(panel);


        listenButton(buttons);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //это нужно для того чтобы при
        //закрытии окна закрывалась и программа,
        //иначе она останется висеть в процессах
    }

    static void listenButton(ArrayList<JButton> button) {


        for (JButton buttonInMethod : button) {
            buttonInMethod.addActionListener(e -> {
                String textWriter = buttonInMethod.getText();
                text.setText(textWriter);
            });
        }
    }

}
