import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Bird on 01.02.2017.
 */
public class CalculatorWindow extends JFrame {

    private static JTextField text = new JTextField();
    private static String textWriter = new String();
    private JButton summ = new JButton("+");
    private JButton subtraction = new JButton("-");
    private JButton multiplication = new JButton("*");
    private JButton divicion = new JButton("/");
    private JPanel panel = new JPanel();

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


//        list doesn't want to be protected
        List<JButton> buttonsNumber = new LinkedList<>();


        int s = 1, s2;

        JButton button = new JButton("0");
        button.setBounds(0, i * 4, i, i);
        buttonsNumber.add(button);
        panel.add(button);
        for (int s1 = 1; s1 < 4; s1++) {
            for (s2 = 0; s2 < 3; s2++) {
                button = new JButton(Integer.toString(s));
                buttonsNumber.add(button);
                button.setBounds(s2 * i, s1 * i, i, i);
                panel.add(button);
                s++;
            }
        }
        button = new JButton(",");
        button.setBounds(i, i * 4, i, i);
        buttonsNumber.add(button);
        panel.add(button);

        buttonsNumber.add(summ);
        buttonsNumber.add(subtraction);
        buttonsNumber.add(multiplication);
        buttonsNumber.add(divicion);

        panel.add(text);
        panel.add(summ);
        panel.add(subtraction);
        panel.add(multiplication);
        panel.add(divicion);

        JButton buttonEnter = new JButton("=");
        buttonEnter.setBounds(i * 2, i * 4, i, i);
        panel.add(buttonEnter);

        setContentPane(panel);


        listenButtonNumber(buttonsNumber);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //это нужно для того чтобы при
        //закрытии окна закрывалась и программа,
        //иначе она останется висеть в процессах
    }

    static void listenButtonNumber(List<JButton> button) {
        for (JButton buttonInMethod : button) {
            buttonInMethod.addActionListener(e -> {
                textWriter += buttonInMethod.getText();
                text.setText(textWriter);
            });
//            listen calculator panel then
//            do it tomorrow
//            if (buttonInMethod.getText().equals("9")) {
//                textWriter.trim();
//                break;
//            }
        }
    }

}
