import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Bird on 01.02.2017.
 */
public class CalculatorWindow extends JFrame {

    private static JTextField text = new JTextField();
    private static String textWriter = new String();
    private static JButton buttonEnter = new JButton("=");
    private static JButton cleanButton = new JButton("<-");
    private JButton summ = new JButton("+");
    private JButton subtraction = new JButton("-");
    private JButton multiplication = new JButton("*");
    private JButton divicion = new JButton("/");
    private JPanel panel = new JPanel();
    private JButton leftParenthese = new JButton("(");
    private JButton rightParenthese = new JButton(")");
    private JButton cosButton = new JButton("cos(");
    private JButton sinButton = new JButton("sin(");


    public CalculatorWindow() {
        super("Calculator"); //Заголовок окна
        setBounds(200, 200, 375, 335);
        setResizable(false);
        panel.setLayout(null);

        int i = 60;
        int j = 300 / 4;
        summ.setBounds(300 - j, i, i, 60);
        subtraction.setBounds(300 - j, i * 2, i, i);
        multiplication.setBounds(300 - j, i * 3, i, i);
        divicion.setBounds(300 - j, i * 4, i, i);
        text.setBounds(0, 0, 300 - j, 60);


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
        cleanButton.setBounds(300 - j, 0, i, 60);
        panel.add(cleanButton);
        leftParenthese.setBounds(300, 0, i, 60);
        panel.add(rightParenthese);
        rightParenthese.setBounds(300, 60, i, 60);
        panel.add(leftParenthese);


        cosButton.setBounds(300, 120, i, 60);
        panel.add(cosButton);
        buttonsNumber.add(cosButton);

        sinButton.setBounds(300, 180, i, 60);
        panel.add(sinButton);
        buttonsNumber.add(sinButton);

        buttonsNumber.add(leftParenthese);
        buttonsNumber.add(rightParenthese);

        listenButtonNumber(buttonsNumber);

        setContentPane(panel);
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
                    text.setText(Text.parserText(textWriter));
                    textWriter = new String();
                }
        );
        cleanButton.addActionListener(e2 -> {
            if (!textWriter.isEmpty()) {
                textWriter = textWriter.substring(0, textWriter.length() - 1);
                text.setText(textWriter);
            } else {
                text.setText("There is nothing to delete");
            }
        });
    }

    private static class Text {
        static String parserText(String text) {
            if (text.isEmpty()) {
                return "Input expression";
            }
            return Double.toString(eval(text));
        }

        static boolean isDelim(char c) { // тру если пробел
            return c == ' ';
        }

        static boolean isOperator(char c) { // возвращяем тру если один из символов ниже
            return c == '+' || c == '-' || c == '*' || c == '/';
        }

        static int priority(char op) {
            switch (op) { // при + или - возврат 1, при * / % 2 иначе -1
                case '+':
                case '-':
                    return 1;
                case '*':
                case '/':
                    return 2;
                default:
                    return -1;
            }
        }

        static void processOperator(LinkedList<Double> st, char op) {
            double r = st.removeLast(); // выдёргиваем из упорядоченного листа последний элемент
            switch (op) {
                case 's':
                    st.add((double) (Math.round(Math.sin(Math.toRadians(r)))));
                    break;
                case 'c':
                    st.add((double) (Math.round(Math.cos(Math.toRadians(r)))));
                    break;
                case '+':
                    double l = st.removeLast();
                    st.add(l + r);
                    break;
                case '-':
                    l = st.removeLast();
                    st.add(l - r);
                    break;
                case '*':
                    l = st.removeLast();
                    st.add(l * r);
                    break;
                case '/':
                    l = st.removeLast();
                    st.add(l / r);
                    break;
                case '%':
                    l = st.removeLast();
                    st.add(l % r);
                    break;
            }
        }

        public static double eval(String s) {
            LinkedList<Double> st = new LinkedList<>(); // сюда наваливают цифры

            LinkedList<Character> op = new LinkedList<>(); // сюда опрераторы и st и op в порядке поступления

            for (int i = 0; i < s.length(); i++) { // парсим строку с выражением и вычисляем
                char c = s.charAt(i);
                if (isDelim(c)) {
                    continue;
                }
                if (c == 's') {
                    op.add('s');
                    i += 2;
                    continue;
                }
                if (c == 'c') {
                    op.add('c');
                    i += 2;
                    continue;
                }
                if (c == '(') {
                    op.add('(');
                } else if (c == ')') {
                    while (op.getLast() != '(')
                        processOperator(st, op.removeLast());
                    op.removeLast();
                } else if (isOperator(c)) {
                    while (!op.isEmpty() && priority(op.getLast()) >= priority(c))
                        processOperator(st, op.removeLast());
                    op.add(c);
                } else {
                    String operand = "";
                    while ((i < s.length() && Character.isDigit(s.charAt(i))) || (i < s.length() && s.charAt(i) == '.'))
                        operand += s.charAt(i++);
                    --i;
                    st.add(Double.parseDouble(operand));
                }
            }
            while (!op.isEmpty())
                processOperator(st, op.removeLast());
            return st.get(0);  // возврат результата
        }
    }
}
