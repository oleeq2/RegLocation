package RegLocation;

import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        JFrame myFrame = new JFrame("RegLocation");
        myFrame.setContentPane(new ContentPanel());
        myFrame.setBounds(0,0,500,500);
        myFrame.setVisible(true);
    }
}
