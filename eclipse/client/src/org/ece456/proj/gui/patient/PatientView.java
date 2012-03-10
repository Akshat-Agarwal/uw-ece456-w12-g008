package org.ece456.proj.gui.patient;

import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import org.ece456.proj.gui.appointment.AppointmentTable;
import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.Patient;

public class PatientView extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private final PatientContactPanel panel_contact;
    private final PatientMedicalPanel panel_medical;
    private final AppointmentTable panel_appointments;

    private final JMenuItem mntmEditContactInfo;
    private final JMenuItem mntmSaveContactInfo;

    private final PatientPresenter presenter;

    public PatientView(final PatientPresenter presenter) {
        this.presenter = presenter;

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

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnEdit = new JMenu("Edit");
        mnEdit.setMnemonic('e');
        menuBar.add(mnEdit);

        mntmEditContactInfo = new JMenuItem("Edit");
        mntmEditContactInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
                InputEvent.CTRL_MASK));
        mntmEditContactInfo.addActionListener(this);
        mnEdit.add(mntmEditContactInfo);

        mntmSaveContactInfo = new JMenuItem("Save");
        mntmSaveContactInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                InputEvent.CTRL_MASK));
        mntmSaveContactInfo.addActionListener(this);
        mnEdit.add(mntmSaveContactInfo);
    }

    public void fillPatientData(Patient patient, List<Appointment> appointments) {
        // Contact
        panel_contact.fillPatientData(patient.getPatientId(), patient.getContact());

        // Medical
        panel_medical.fillPatientData(patient.getMedical());

        // Appointments
        panel_appointments.update(appointments);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mntmEditContactInfo) {
            mntmEditContactInfo.setEnabled(false);
            mntmSaveContactInfo.setEnabled(true);
            panel_contact.setEditableByPatient(true);
        } else if (e.getSource() == mntmSaveContactInfo) {
            mntmEditContactInfo.setEnabled(true);
            mntmSaveContactInfo.setEnabled(false);
            panel_contact.setEditableByPatient(false);
            presenter.savePersonalData(panel_contact.getData());
        }
    }
}
