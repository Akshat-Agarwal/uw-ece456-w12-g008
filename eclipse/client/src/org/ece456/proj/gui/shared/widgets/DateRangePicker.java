package org.ece456.proj.gui.shared.widgets;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.michaelbaranov.microba.calendar.DatePicker;

public class DateRangePicker extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;

    private final DatePicker datePickerEnd;

    private final DatePicker datePickerStart;

    private final JButton btnSearch;

    private final Listener listener;

    public interface Listener {
        void onSearch(Date start, Date end);
    }

    public DateRangePicker(Listener listener) {
        this.listener = listener;
        setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
                "Search: Start Date (inclusive) ~ End Date (exclusive)", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));
        setLayout(new BorderLayout(0, 0));

        JPanel panelDateRange = new JPanel();
        add(panelDateRange);
        panelDateRange.setLayout(new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"), FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"), FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, }));

        JLabel lblStartDate = new JLabel("Start");
        panelDateRange.add(lblStartDate, "2, 2");

        datePickerStart = new DatePicker(null);
        panelDateRange.add(datePickerStart, "4, 2");

        JLabel lblEndDate = new JLabel("End");
        panelDateRange.add(lblEndDate, "6, 2");

        datePickerEnd = new DatePicker(null);
        panelDateRange.add(datePickerEnd, "8, 2");

        btnSearch = new JButton("Search");
        btnSearch.addActionListener(this);
        panelDateRange.add(btnSearch, "10, 2");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();
        if (s == btnSearch) {
            Date start = datePickerStart.getDate();
            Date end = datePickerEnd.getDate();

            listener.onSearch(start, end);
        }
    }
}
