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
    float x, y;

    SilhouetteComponent silhouette;

    public ContentPanel()
    {
        R = 1f;
        silhouette = new SilhouetteComponent(R);

        final JLabel label = new JLabel("Nothing pressed");
        silhouette.addMarksListener(new MarksListener()
        {

            @Override
            public void actionPerformed(Mark mk)
            {
                label.setText(mk.toString());
            }
        });
        setLayout(new GridLayout(1, 2));

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        add(controlPanel);
        controlPanel.add(initSlider());
        add(silhouette);

        controlPanel.add(getCBPanel());

        controlPanel.add(getComboPanel());

        controlPanel.add(label);
    }

    JSlider initSlider()
    {
        JSlider slider = new JSlider(0, 10, 1);

        slider.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                JSlider source = (JSlider) e.getSource();
                if (source.getValueIsAdjusting())
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
        CB.add(new JLabel("Y"));
        final ArrayList<JCheckBox> CBs = new ArrayList<JCheckBox>();
        ChangeListener Listener = new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                y = CBs.indexOf((JCheckBox) e.getSource()) - 3;
            }
        };
        int i;
        for (i = 1; i < 6; i++)
        {
            JCheckBox Item = new JCheckBox(String.valueOf(i));
            CBs.add(i - 1, Item);
            Item.addChangeListener(Listener);
            CB.add(Item);
        }

        return CB;
    }

    JPanel getComboPanel()
    {
        JPanel ret = new JPanel();
        String[] Descs = {"1", "2", "3", "4", "5"};
        JComboBox CBox = new JComboBox(Descs);
        CBox.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JComboBox source = (JComboBox) e.getSource();
                x = source.getSelectedIndex() + 1;
                silhouette.markAdd(x, y);
            }
        });

        ret.add(new JLabel("X"));
        ret.add(CBox);
        return ret;
    }

}