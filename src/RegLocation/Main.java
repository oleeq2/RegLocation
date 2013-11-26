package RegLocation;


import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        JWindow MainWindow = new JWindow();
        MainWindow.setContentPane(new ContentPanel());
        MainWindow.pack();
        MainWindow.setBounds(0,0,500,500);
        MainWindow.setVisible(true);
    }
}
