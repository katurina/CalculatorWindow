import javax.swing.*;
import java.awt.*;

/**
 * Created by Bird on 01.02.2017.
 */
public class CalculatorWindow extends JFrame {

    private JButton summ = new JButton("+");
    private JButton subtraction = new JButton("-");
    private JButton multiplication = new JButton("*");
    private JButton divicion = new JButton("/");
    private JPanel panel = new JPanel();
    private JTextField text = new JTextField();

    public CalculatorWindow() {
        super("Calculator"); //Заголовок окна
        setBounds(200, 200, 300, 335);
        panel.setLayout(null);

        int i = 60;
        int j = 300 / 4;
        summ.setBounds(300-j, i, i, 60);
        subtraction.setBounds(300-j,i*2,i,i);
        multiplication.setBounds(300-j, i*3,i,i);
        divicion.setBounds(300-j,i*4,i,i);
        text.setBounds(0,0,300,60);


        panel.add(text);
        panel.add(summ);
        panel.add(subtraction);
        panel.add(multiplication);
        panel.add(divicion);
        int s=1,s2;
        for (int s1 = 1; s1 < 4; s1++) {
            for (s2 = 0; s2 < 3; s2++) {

                JButton button = new JButton(Integer.toString(s));
                button.setBounds(s2*i,s1*i , i, i);
                panel.add(button);
                s++;
            }
//       it was     s1=1;
        }
        setContentPane(panel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //это нужно для того чтобы при
        //закрытии окна закрывалась и программа,
        //иначе она останется висеть в процессах
    }
}
