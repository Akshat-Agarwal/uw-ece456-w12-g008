package org.ece456.proj.gui.accountant;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.Doctor;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.michaelbaranov.microba.calendar.DatePicker;

public class DoctorFinancialView extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private final JTextField textFieldId;
    private final JTextField textFieldName;

    private final DatePicker datePickerEnd;

    private final DatePicker datePickerStart;

    private final JButton btnSearch;

    private final DoctorFinancialPresenter presenter;

    private final DoctorsAppointmentTable appointmentTable;

    public DoctorFinancialView(DoctorFinancialPresenter presenter) {
        this.presenter = presenter;

        setTitle("View Doctor's Appointments");
        getContentPane().setLayout(new BorderLayout(0, 0));

        Box verticalBox = Box.createVerticalBox();
        getContentPane().add(verticalBox, BorderLayout.NORTH);

        JPanel panelInfo = new JPanel();
        verticalBox.add(panelInfo);
        panelInfo.setLayout(new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"), FormFactory.RELATED_GAP_COLSPEC, },
                new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, }));

        JLabel lblDoctorId = new JLabel("Doctor ID");
        panelInfo.add(lblDoctorId, "2, 2, right, default");

        textFieldId = new JTextField();
        textFieldId.setEditable(false);
        panelInfo.add(textFieldId, "4, 2, fill, default");
        textFieldId.setColumns(10);

        JLabel lblName = new JLabel("Name");
        panelInfo.add(lblName, "2, 4, right, default");

        textFieldName = new JTextField();
        textFieldName.setEditable(false);
        panelInfo.add(textFieldName, "4, 4, fill, default");
        textFieldName.setColumns(10);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
                "Search for doctor's appointments: Start Date (inclusive) ~ End Date (exclusive)",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        verticalBox.add(panel);
        panel.setLayout(new BorderLayout(0, 0));

        JPanel panelDateRange = new JPanel();
        panel.add(panelDateRange);
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

        appointmentTable = new DoctorsAppointmentTable();
        getContentPane().add(appointmentTable, BorderLayout.CENTER);
        appointmentTable.setPreferredSize(new Dimension(500, 300));

        pack();
        setLocation(100, 100);
    }

    public void fillData(Doctor doctor) {
        textFieldId.setText(doctor.getDoctor_id().toString());
        textFieldName.setText(doctor.getName());
    }

    public void fillAppointments(List<Appointment> apps) {
        appointmentTable.update(apps);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();
        if (s == btnSearch) {
            Date start = datePickerStart.getDate();
            Date end = datePickerEnd.getDate();

            presenter.searchAppointments(start, end);
        }
    }
}
