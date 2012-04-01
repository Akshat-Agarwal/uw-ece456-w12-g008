package org.ece456.proj.gui.shared.widgets;

import java.awt.BorderLayout;
import java.beans.PropertyVetoException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.michaelbaranov.microba.calendar.DatePicker;

public class DateTimePicker extends JPanel {
    private static final long serialVersionUID = 1L;
    private final DatePicker day;
    private final JSpinner hour;
    private final JSpinner minute;

    public DateTimePicker() {
        setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        add(panel, BorderLayout.CENTER);
        panel.setLayout(new FormLayout(new ColumnSpec[] { ColumnSpec.decode("default:grow"),
                FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
                FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
                FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, },
                new RowSpec[] { FormFactory.DEFAULT_ROWSPEC, }));

        day = new DatePicker();
        panel.add(day, "1, 1");

        hour = new JSpinner();
        hour.setModel(new SpinnerNumberModel(0, 0, 23, 1));
        panel.add(hour, "3, 1");

        JLabel label = new JLabel(":");
        panel.add(label, "5, 1");

        minute = new JSpinner();
        minute.setModel(new SpinnerNumberModel(0, 0, 59, 1));
        panel.add(minute, "7, 1");
    }

    public void setValue(Date dateTime) {
        Calendar c = Calendar.getInstance();
        c.setTime(dateTime);
        try {
            day.setDate(c.getTime());
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        hour.setValue(c.get(Calendar.HOUR_OF_DAY));
        minute.setValue(c.get(Calendar.MINUTE));
    }

    public Date getValue() {
        Calendar c = Calendar.getInstance();
        c.setTime(day.getDate());
        c.set(Calendar.HOUR_OF_DAY, toInt(hour.getValue()));
        c.set(Calendar.MINUTE, toInt(minute.getValue()));
        return c.getTime();
    }

    @Override
    public void setEnabled(boolean enabled) {
        day.setEnabled(enabled);
        hour.setEnabled(enabled);
        minute.setEnabled(enabled);
    }

    private int toInt(Object o) {
        return Integer.parseInt(String.valueOf(o));
    }
}
