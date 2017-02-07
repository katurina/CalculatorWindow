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
        button = new JButton(".");
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
        }
        buttonEnter.addActionListener(
                e1 -> {
                    text.setText(Text.parserText(textWriter));
                    textWriter = new String();
                }
        );
    }

    private static class Text {
        static String parserText(String text) {
            if (text.isEmpty()) {
                return "Input expression";
            }
            return Double.toString(eval_(text, 0, text.length()));
        }

//        private static int eval(String expr, int from, int to) {
//            if (expr.charAt(from) == '(') {
//                return eval(expr, from + 1, to - 1);
//            } else {
//                int pos = from;
//                while (pos < to) {
//                    if (Character.isDigit(expr.charAt(pos))) {
//                        pos++;
//                    } else {
//                        int leftOperand = Integer.valueOf(expr.substring(from, pos));
//                        char operation = expr.charAt(pos);
//                        int rightOperand = eval(expr, pos + 1, to);
//                        switch (operation) {
//                            case '+':
//                                return leftOperand + rightOperand;
//                            case '-':
//                                return leftOperand - rightOperand;
//                            case '*':
//                                return leftOperand * rightOperand;
//                            case '/':
//                                return leftOperand / rightOperand;
//                        }
//                    }
//                }
//                return Integer.valueOf(expr.substring(from, to));
//            }
//        }

        private static double eval_(String expr, int from, int to) {
            int pos = from;
            while (pos < to) {
                if (Character.isDigit(expr.charAt(pos)) || expr.charAt(pos) == '.') {
                    pos++;
                } else {
                    double leftOperand = Double.valueOf(expr.substring(from, pos));
                    char operation = expr.charAt(pos);
                    double rightOperand = eval_(expr, pos + 1, to);
                    switch (operation) {
                        case '+':
                            return leftOperand + rightOperand;
                        case '-':
                            return leftOperand - rightOperand;
                        case '*':
                            return leftOperand * rightOperand;
                        case '/':
                            return leftOperand / rightOperand;
                    }
                }
            }
            return Double.valueOf(expr.substring(from, to));
        }
    }
}
