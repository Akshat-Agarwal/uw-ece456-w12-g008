package org.ece456.proj.gui.accountant;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.ece456.proj.gui.shared.table.SimpleTable;
import org.ece456.proj.gui.shared.widgets.DateRangePicker;
import org.ece456.proj.gui.shared.widgets.DateRangePicker.Listener;
import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.Doctor;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.UserRole;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class FinancialView extends JFrame implements Listener {
    private static final long serialVersionUID = 1L;

    protected final JTextField textFieldId;
    protected final JTextField textFieldName;
    protected final SimpleTable<Appointment> appointments;
    protected final SimpleTable<Patient> patients;

    protected final FinancialPresenter presenter;

    protected FinancialView(UserRole role, FinancialPresenter presenter) {
        this.presenter = presenter;

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

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        getContentPane().add(tabbedPane, BorderLayout.SOUTH);

        JPanel panel = new JPanel();
        tabbedPane.addTab("Appointments", null, panel, null);
        panel.setLayout(new BorderLayout(0, 0));

        JPanel panelSearch = new DateRangePicker(this);
        panel.add(panelSearch, BorderLayout.NORTH);

        appointments = new FinancialAppointmentTable(role, null);
        panel.add(appointments);
        appointments.setPreferredSize(new Dimension(500, 300));

        JPanel panel_1 = new JPanel();
        tabbedPane.addTab("Patients", null, panel_1, null);
        panel_1.setLayout(new BorderLayout(0, 0));

        patients = new FinancialPatientTable();
        panel_1.add(patients);
        patients.setPreferredSize(new Dimension(500, 300));

        pack();
        setLocation(100, 100);
    }

    public void fillData(Doctor doctor, List<Patient> patients) {
        textFieldId.setText(doctor.getDoctor_id().toString());
        textFieldName.setText(doctor.getName());
        this.patients.update(patients);
    }

    public void fillAppointments(List<Appointment> apps) {
        appointments.update(apps);
    }

    @Override
    public void onSearch(Date start, Date end) {
        presenter.searchAppointments(start, end);
    }
}
