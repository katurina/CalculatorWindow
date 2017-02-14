import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;

/**
 * Created by Bird on 14.02.2017.
 */
public class Text {
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
