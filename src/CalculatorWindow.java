import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private JButton tgButton = new JButton("tg(");
    private JButton ctgButton = new JButton("ctg(");
    private JButton powButton = new JButton("^");


    public CalculatorWindow() {
        super("Calculator"); //Заголовок окна
        setBounds(200, 200, 450, 335);
        setResizable(false);
        panel.setLayout(null);

        int i = 60;
        summ.setBounds(3 * i, i, i, 60);
        subtraction.setBounds(3 * i, i * 2, i, i);
        multiplication.setBounds(3 * i, i * 3, i, i);
        divicion.setBounds(3 * i, i * 4, i, i);
        text.setBounds(0, 0, 3 * i, 60);


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
        cleanButton.setBounds(3 * i, 0, i, 60);
        panel.add(cleanButton);
        leftParenthese.setBounds(4 * i, 0, i, 60);
        panel.add(rightParenthese);
        rightParenthese.setBounds(5 * i, 0, i, 60);
        panel.add(leftParenthese);
        powButton.setBounds(6 * i, 0, i, i);
        panel.add(powButton);
        buttonsNumber.add(powButton);


        cosButton.setBounds(4 * i, i * 2, i, 60);
        panel.add(cosButton);
        buttonsNumber.add(cosButton);


        sinButton.setBounds(4 * i, i, i, 60);
        panel.add(sinButton);
        buttonsNumber.add(sinButton);

        tgButton.setBounds(4 * i, 3 * i, i, 60);
        panel.add(tgButton);
        buttonsNumber.add(tgButton);

        ctgButton.setBounds(4 * i, 4 * i, i, 60);
        panel.add(ctgButton);
        buttonsNumber.add(ctgButton);


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
                case '^':
                    return 4;
                case 'c':
                case 's':
                case 't':
                case 'g':
                    return 3;
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
                    st.add(new BigDecimal(Math.sin(Math.toRadians(r))).setScale(3, RoundingMode.HALF_UP).doubleValue());
                    break;
                case 'c':
                    st.add(new BigDecimal(Math.cos(Math.toRadians(r))).setScale(3, RoundingMode.HALF_UP).doubleValue());
                    break;
                case 't':
                    st.add(new BigDecimal(Math.tan(Math.toRadians(r))).setScale(3, RoundingMode.HALF_UP).doubleValue());
                    break;
                case 'g':
                    st.add(new BigDecimal(Math.atan(Math.toRadians(r))).setScale(3, RoundingMode.HALF_UP).doubleValue());
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
                case '^':
                    l = st.removeLast();
                    st.add(Math.pow(l, r));
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
                if (c == 't') {
                    op.add('t');
                    i += 2;
                    continue;
                }
                if (c == 'c' && s.charAt(i + 1) == 'o') {
                    op.add('c');
                    i += 2;
                    continue;
                }
                if (c == 'c' && s.charAt(i + 1) == 't') {
                    op.add('g');
                    i += 2;
                    continue;
                }
                if (c == '^') {
                    op.add('^');
                    continue;
                }
                if (c == '(') {
                    op.add('(');
                } else if (c == ')') {
                    while (op.getLast() != '(')
                        processOperator(st, op.removeLast());
                    op.removeLast();
                } else if (!(c == '-' &&
                        ((i - 1) == -1 || !Character.isDigit(s.charAt(i - 1)))
                ) && isOperator(c)) {
                    while (!op.isEmpty() && priority(op.getLast()) >= priority(c))
                        processOperator(st, op.removeLast());
                    op.add(c);
                } else {
                    String operand = "";
                    while ((i < s.length() && Character.isDigit(s.charAt(i))) ||
                            (i < s.length() && s.charAt(i) == '.') ||
                            (i < s.length() && ((i - 1) == -1 || !Character.isDigit(s.charAt(i - 1))) && c == '-'))
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
