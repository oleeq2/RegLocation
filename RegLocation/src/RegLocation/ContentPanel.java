package RegLocation;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
        CheckboxGroup CBG = new CheckboxGroup();
        CB.add(new JLabel("Y"));
        final ArrayList<Checkbox> CBs = new ArrayList<Checkbox>();
        ItemListener itemListener = new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                Checkbox box = (Checkbox) e.getItemSelectable();
                y = CBs.indexOf(box);
                y /= 4;
            }
        };

        int i;
        for (i = 0; i < 5; i++)
        {
            Checkbox Item = new Checkbox(String.valueOf(i*0.25),CBG,false);
            CBs.add(i, Item);
            Item.addItemListener(itemListener);
            CB.add(Item);
        }

        return CB;
    }

    JPanel getComboPanel()
    {
        JPanel ret = new JPanel();
        String[] Descs = {"0", "0.25", "0.5", "0.75", "1"};
        JComboBox CBox = new JComboBox(Descs);
        CBox.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JComboBox source = (JComboBox) e.getSource();
                x = source.getSelectedIndex();
                x /= 4;
                silhouette.markAdd(x, y);
            }
        });

        ret.add(new JLabel("X"));
        ret.add(CBox);
        return ret;
    }

}
