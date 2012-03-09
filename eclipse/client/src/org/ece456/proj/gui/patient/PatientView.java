package org.ece456.proj.gui.patient;

import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.ece456.proj.gui.appointment.AppointmentTable;
import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.Patient;

public class PatientView extends JFrame {

    private static final long serialVersionUID = 1L;

    private final PatientContactPanel panel_contact;
    private final PatientMedicalPanel panel_medical;
    private final AppointmentTable panel_appointments;

    public PatientView(final PatientPresenter presenter) {
        // Set the frame's properties
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setTitle("Patient View");
        setBounds(100, 100, 600, 400);
        getContentPane().setLayout(new BorderLayout(0, 0));

        // Set up the tab view
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        // Contact
        JPanel tab_contact = new JPanel();
        tabbedPane.addTab("Contact Data", null, tab_contact, null);
        tab_contact.setLayout(new BorderLayout(0, 0));

        panel_contact = new PatientContactPanel();
        panel_contact.setBackground(SystemColor.window);
        tab_contact.add(panel_contact, BorderLayout.CENTER);

        // Medical
        JPanel tab_medical = new JPanel();
        tab_medical.setBackground(SystemColor.window);
        tabbedPane.addTab("Medical Data", null, tab_medical, null);
        tab_medical.setLayout(new BorderLayout(0, 0));

        panel_medical = new PatientMedicalPanel();
        tab_medical.add(panel_medical, BorderLayout.CENTER);

        // Appointments
        JPanel tab_appointments = new JPanel();
        tab_appointments.setBackground(SystemColor.window);
        tabbedPane.addTab("Appointments & Visits", null, tab_appointments, null);
        tab_appointments.setLayout(new BorderLayout(0, 0));

        panel_appointments = new AppointmentTable();
        panel_appointments.setBackground(SystemColor.window);
        tab_appointments.add(panel_appointments, BorderLayout.CENTER);
    }

    public void fillPatientData(Patient patient, List<Appointment> appointments) {
        // Contact
        panel_contact.fillPatientData(patient.getPatientId(), patient.getContact());

        // Medical
        panel_medical.fillPatientData(patient.getMedical());

        // Appointments
        panel_appointments.update(appointments);
    }
}
