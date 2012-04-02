package org.ece456.proj.gui.doctor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.ece456.proj.gui.shared.table.SimpleTable;
import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.Doctor;
import org.ece456.proj.orm.objects.Patient;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PatientDoctorView extends JFrame {
    private static final long serialVersionUID = 1L;

    protected final JTextField textFieldId;
    protected final JTextField textFieldName;
    protected final SimpleTable<Appointment> table;

    private final ConsultantEditor consultantTable;

    protected PatientDoctorView(PatientDoctorPresenter patientAppointmentPresenter) {
        getContentPane().setLayout(new BorderLayout(0, 0));
        setTitle("My Patient's Details");

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

        JLabel lblDoctorId = new JLabel("ID");
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
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        table = createAppointmentsTable(patientAppointmentPresenter);

        JPanel panel = new JPanel();
        tabbedPane.addTab("Appointments", null, panel, null);
        panel.setLayout(new BorderLayout(0, 0));
        panel.add(table);

        JPanel panel_1 = new JPanel();
        tabbedPane.addTab("Consultants", null, panel_1, null);
        panel_1.setLayout(new BorderLayout(0, 0));

        consultantTable = new ConsultantEditor(patientAppointmentPresenter);
        panel_1.add(consultantTable, BorderLayout.CENTER);

        setPreferredSize(new Dimension(600, 400));
        pack();
        setLocation(100, 100);
    }

    public void fillData(Patient patient) {
        textFieldId.setText(patient.getPatientId().toString());
        textFieldName.setText(patient.getName());
    }

    public void fillConsultants(List<Doctor> consultants) {
        consultantTable.setConsultants(consultants);
    }

    SimpleTable<Appointment> createAppointmentsTable(
            PatientDoctorPresenter patientAppointmentPresenter) {
        return new DoctorPatientsAppointmentTable(patientAppointmentPresenter);
    }

    public void fillAppointments(java.util.List<Appointment> apps) {
        table.update(apps);
    }

    public void addConsultant(Doctor consultant) {
        consultantTable.addConsultant(consultant);
    }

    public void removeConsultant(Doctor consultant) {
        consultantTable.addConsultant(consultant);
    }
}