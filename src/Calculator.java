import javax.swing.*;

public class Calculator{ //Наследуя от JFrame мы получаем всю функциональность окна


    public static void main(String[] args) { //эта функция может быть и в другом классе
        CalculatorWindow app = new CalculatorWindow(); //Создаем экземпляр нашего приложения
        app.setVisible(true); //С этого момента приложение запущено!
    }
}
