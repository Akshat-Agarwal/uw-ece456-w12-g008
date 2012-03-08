package org.ece456.proj.gui.patient;

import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.ece456.proj.gui.appointment.AppointmentTable;
import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.Patient;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PatientView extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JTextField text_id;
    private final JTextField text_name;
    private final JTextField text_address;
    private final JTextField text_phone;

    private final PatientMedicalPanel panel_medical;
    private final AppointmentTable panel_appointments;

    private boolean editting = false;

    /**
     * Create the dialog.
     * 
     * @param presenter
     */
    public PatientView(final PatientPresenter presenter) {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setTitle("Patient View");
        setBounds(100, 100, 600, 400);
        getContentPane().setLayout(new BorderLayout(0, 0));

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        JPanel tab_contact = new JPanel();
        tabbedPane.addTab("Contact Data", null, tab_contact, null);
        tab_contact.setLayout(new BorderLayout(0, 0));

        JPanel panel_contact = new JPanel();
        panel_contact.setBackground(SystemColor.window);
        tab_contact.add(panel_contact, BorderLayout.CENTER);
        panel_contact.setLayout(new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"), FormFactory.RELATED_GAP_COLSPEC, },
                new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));

        JLabel lblId = new JLabel("Patient ID");
        panel_contact.add(lblId, "2, 2, right, default");

        text_id = new JTextField();
        text_id.setEditable(false);
        panel_contact.add(text_id, "4, 2, fill, default");
        text_id.setColumns(10);

        JLabel lblName = new JLabel("Name");
        panel_contact.add(lblName, "2, 4, right, default");

        text_name = new JTextField();
        text_name.setEditable(false);
        panel_contact.add(text_name, "4, 4, fill, default");
        text_name.setColumns(10);

        JLabel lblAddress = new JLabel("Address");
        panel_contact.add(lblAddress, "2, 6, right, default");

        text_address = new JTextField();
        text_address.setEditable(false);
        panel_contact.add(text_address, "4, 6, fill, default");
        text_address.setColumns(10);

        JLabel lblNewLabel = new JLabel("Phone #");
        panel_contact.add(lblNewLabel, "2, 8, right, default");

        text_phone = new JTextField();
        text_phone.setEditable(false);
        panel_contact.add(text_phone, "4, 8, fill, default");
        text_phone.setColumns(10);

        JPanel panel_contact_buttons = new JPanel();
        panel_contact_buttons.setBackground(SystemColor.window);
        tab_contact.add(panel_contact_buttons, BorderLayout.SOUTH);

        final JButton btnEdit = new JButton("Edit");
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (editting) {
                    setEditable(false);
                    btnEdit.setText("Edit");
                } else {
                    setEditable(true);
                    btnEdit.setText("Save");
                }
                editting = !editting;
            }
        });
        panel_contact_buttons.add(btnEdit);

        JPanel tab_medical = new JPanel();
        tab_medical.setBackground(SystemColor.window);
        tabbedPane.addTab("Medical Data", null, tab_medical, null);
        tab_medical.setLayout(new BorderLayout(0, 0));

        panel_medical = new PatientMedicalPanel();
        tab_medical.add(panel_medical, BorderLayout.NORTH);

        JPanel tab_appointments = new JPanel();
        tab_appointments.setBackground(SystemColor.window);
        tabbedPane.addTab("Appointments & Visits", null, tab_appointments, null);
        tab_appointments.setLayout(new BorderLayout(0, 0));

        panel_appointments = new AppointmentTable();
        panel_appointments.setBackground(SystemColor.window);
        tab_appointments.add(panel_appointments, BorderLayout.CENTER);
    }

    private void setEditable(boolean editable) {
        text_name.setEditable(editable);
        text_address.setEditable(editable);
        text_phone.setEditable(editable);
    }

    public void fillPatientData(Patient patient) {
        text_id.setText(String.valueOf(patient.getPatientId().asInt()));

        // Contact
        text_name.setText(patient.getContact().getName());
        text_address.setText(patient.getContact().getAddress());
        text_phone.setText(patient.getContact().getPhoneNum());

        // Medical
        panel_medical.fillPatientData(patient.getMedical());
    }

    public void fillAppointmentData(List<Appointment> appointments) {
        panel_appointments.update(appointments);
    }
}
