package RegLocation;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ContentPanel extends JPanel
{
    float R;
    Silhouette silhouette;
    float x,y;
    public ContentPanel()
    {
        R = 1f;
        silhouette = new Silhouette(R);
        setLayout(new GridLayout(1,2));

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        add(controlPanel);
        controlPanel.add(initSlider());
        add(silhouette);
        controlPanel.add(getCBPanel());
        controlPanel.add(getComboPanel());
    }

    JSlider initSlider()
    {
        JSlider slider = new JSlider(0,10,1);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                if(source.getValueIsAdjusting())
                {
                    int value = source.getValue();
                    R = value;
                    silhouette.setR(value);
                }
            }
        });

        return slider;
    }

    JPanel getCBPanel()
    {
        JPanel CB = new JPanel();
        final ArrayList<JCheckBox> CBs = new ArrayList<JCheckBox>();
        ChangeListener Listener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                y = CBs.indexOf((JCheckBox)e.getSource()) + 1;
            }
        };
        int i;
        for(i = 1; i < 6; i++)
        {
            JCheckBox item = new JCheckBox(new Integer(i).toString());
            CBs.add(i-1,item);
            item.addChangeListener(Listener);
            CB.add(item);
        }

        return CB;
    }

    JComboBox getComboPanel()
    {
        String[] strs = {"1", "2", "3", "4", "5"};
        JComboBox ret = new JComboBox(strs);
        ret.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox source = (JComboBox)e.getSource();
                x = source.getSelectedIndex() + 1;
                silhouette.markAddInR(x,y);
            }
        });
        return ret;
    }

    public static void main(String[] args)
    {
        JWindow window = new JWindow();
        window.add(new ContentPanel()) ;
        window.setBounds(0,0,500,500);
        window.setVisible(true);
    }

}
