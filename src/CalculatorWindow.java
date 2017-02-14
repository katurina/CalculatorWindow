import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Bird on 01.02.2017.
 */
public class CalculatorWindow extends JFrame {

    private static JTextField text = new JTextField();
    private static String textWriter = new String();
    private static JButton buttonEnter = new JButton("=");
    private static JButton cleanButton = new JButton("←");
    private JButton summ = new JButton("+");
    private JButton subtraction = new JButton("-");
    private JButton multiplication = new JButton("*");
    private JButton divicion = new JButton("/");
    private JPanel panel = new JPanel();
    private JButton leftParenthese = new JButton("(");
    private JButton rightParenthese = new JButton(")");
    private JButton cosButton = new JButton("cos(");
    private JButton sinButton = new JButton("sin(");
    private JButton tgButton = new JButton("tg(");
    private JButton ctgButton = new JButton("ctg(");
    private JButton powButton = new JButton("^");


    public CalculatorWindow() {
        super("Calculator"); //Заголовок окна
        setBounds(200, 200, 450, 335);
        setResizable(false);
        panel.setLayout(null);

        int i = 60;

        SettingButtons.setButton(panel, summ, 3 * i, i, i, 60);
        SettingButtons.setButton(panel, subtraction, 3 * i, i * 2, i, i);
        SettingButtons.setButton(panel, multiplication, 3 * i, i * 3, i, i);
        SettingButtons.setButton(panel, divicion, 3 * i, i * 4, i, i);
        text.setBounds(0, 0, 3 * i, 60);


        List<JButton> buttonsNumber = new LinkedList<>();

        int s = 1, s2;

        JButton button = new JButton("0");
        SettingButtons.setButton(panel, button, 0, i * 4, i, i);
        buttonsNumber.add(button);
        for (int s1 = 1; s1 < 4; s1++) {
            for (s2 = 0; s2 < 3; s2++) {
                button = new JButton(Integer.toString(s));
                buttonsNumber.add(button);
                SettingButtons.setButton(panel, button, s2 * i, s1 * i, i, i);
                s++;
            }
        }

        button = new JButton(".");
        SettingButtons.setButton(panel, button, i, i * 4, i, i);



        buttonsNumber.add(summ);
        buttonsNumber.add(subtraction);
        buttonsNumber.add(multiplication);
        buttonsNumber.add(divicion);

        panel.add(text);

        SettingButtons.setButton(panel, buttonEnter, i * 2, i * 4, i, i);
        SettingButtons.setButton(panel, cleanButton, 3 * i, 0, i, 60);
        SettingButtons.setButton(panel, leftParenthese, 4 * i, 0, i, 60);
        SettingButtons.setButton(panel, rightParenthese, 5 * i, 0, i, 60);
        SettingButtons.setButton(panel, powButton, 6 * i, 0, i, i);
        SettingButtons.setButton(panel, cosButton, 4 * i, i * 2, i, 60);
        SettingButtons.setButton(panel, sinButton, 4 * i, i, i, 60);
        SettingButtons.setButton(panel, tgButton, 4 * i, 3 * i, i, 60);
        SettingButtons.setButton(panel, ctgButton, 4 * i, 4 * i, i, 60);

        buttonsNumber.add(cosButton);
        buttonsNumber.add(sinButton);
        buttonsNumber.add(ctgButton);
        buttonsNumber.add(powButton);
        buttonsNumber.add(tgButton);
        buttonsNumber.add(leftParenthese);
        buttonsNumber.add(button);
        buttonsNumber.add(rightParenthese);

        ListinerButtons.listenButtonNumber(buttonsNumber, cleanButton, buttonEnter, text);

        setContentPane(panel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //это нужно для того чтобы при
        //закрытии окна закрывалась и программа,
        //иначе она останется висеть в процессах
    }
}
