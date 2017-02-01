import javax.swing.*;
import java.awt.*;

/**
 * Created by Bird on 01.02.2017.
 */
public class CalculatorWindow extends JFrame {

    private JButton summ = new JButton("+");
    private JButton subtraction = new JButton("-");
    private JButton multiplication=new JButton("*");
    private JButton divicion = new JButton("/");
    private JPanel panel = new JPanel();

    public CalculatorWindow(){
        super("Calculator"); //Заголовок окна
        setBounds(100, 100, 400, 400);

        panel.setLayout(new FlowLayout());
        panel.add(summ);
        panel.add(subtraction);
        panel.add(multiplication);
        panel.add(divicion);
        setContentPane(panel);



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //это нужно для того чтобы при
        //закрытии окна закрывалась и программа,
        //иначе она останется висеть в процессах
    }
}
