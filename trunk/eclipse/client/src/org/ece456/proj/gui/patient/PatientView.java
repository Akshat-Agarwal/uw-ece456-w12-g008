package org.ece456.proj.gui.patient;

import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.Sex;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PatientView extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JTextField text_id;
    private final JTextField text_name;
    private final JTextField text_address;
    private final JTextField text_phone;

    private boolean editting = false;
    private final JTextField text_sin;
    private final JTextField text_healthcard;
    private final JTextField text_numvisits;
    private final JTextField text_doctor;
    private final JTextField text_currenthealth;
    private final JComboBox combo_sex;
    private final JList list_consultants;

    /**
     * Create the dialog.
     * 
     * @param presenter
     */
    public PatientView(final PatientPresenter presenter) {
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setTitle("Patient View");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout(0, 0));

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        getContentPane().add(tabbedPane, BorderLayout.NORTH);

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

        JPanel panel_medical = new JPanel();
        panel_medical.setBackground(SystemColor.window);
        tab_medical.add(panel_medical, BorderLayout.NORTH);
        panel_medical.setLayout(new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"), FormFactory.RELATED_GAP_COLSPEC, },
                new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), }));

        JLabel label_sin = new JLabel("SIN");
        panel_medical.add(label_sin, "2, 2, right, default");

        text_sin = new JTextField();
        text_sin.setEditable(false);
        panel_medical.add(text_sin, "4, 2, fill, default");
        text_sin.setColumns(10);

        JLabel lblHeatlhCard = new JLabel("Health Card #");
        panel_medical.add(lblHeatlhCard, "2, 4, right, default");

        text_healthcard = new JTextField();
        text_healthcard.setEditable(false);
        panel_medical.add(text_healthcard, "4, 4, fill, default");
        text_healthcard.setColumns(10);

        JLabel lblVisits = new JLabel("# Visits");
        panel_medical.add(lblVisits, "2, 6, right, default");

        text_numvisits = new JTextField();
        text_numvisits.setEditable(false);
        panel_medical.add(text_numvisits, "4, 6, fill, default");
        text_numvisits.setColumns(10);

        JLabel lblSex = new JLabel("Sex");
        panel_medical.add(lblSex, "2, 8, right, default");

        combo_sex = new JComboBox();
        combo_sex.setEnabled(false);
        combo_sex.setModel(new DefaultComboBoxModel(Sex.values()));
        panel_medical.add(combo_sex, "4, 8, fill, default");

        JLabel lblAssignedDoctor = new JLabel("Assigned Doctor");
        panel_medical.add(lblAssignedDoctor, "2, 10, right, default");

        text_doctor = new JTextField();
        text_doctor.setEditable(false);
        panel_medical.add(text_doctor, "4, 10, fill, default");
        text_doctor.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Current Health");
        panel_medical.add(lblNewLabel_1, "2, 12, right, default");

        text_currenthealth = new JTextField();
        text_currenthealth.setEditable(false);
        panel_medical.add(text_currenthealth, "4, 12, fill, top");
        text_currenthealth.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Consultants");
        panel_medical.add(lblNewLabel_2, "2, 14, right, default");

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panel_medical.add(scrollPane, "4, 14, fill, fill");

        list_consultants = new JList();
        scrollPane.setViewportView(list_consultants);
        list_consultants.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_consultants.setVisibleRowCount(3);

        JPanel tab_appointments = new JPanel();
        tab_appointments.setBackground(SystemColor.window);
        tabbedPane.addTab("Appointments & Visits", null, tab_appointments, null);
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
        text_sin.setText(String.valueOf(patient.getMedical().getSin()));
        text_healthcard.setText(patient.getMedical().getHealthCardNumber());
        text_numvisits.setText(String.valueOf(patient.getMedical().getNumVisits()));
        combo_sex.setSelectedItem(patient.getMedical().getSex());
        text_doctor.setText(String.valueOf(patient.getMedical().getDefaultDoctor().asInt()));
        text_currenthealth.setText(patient.getMedical().getCurrentHealth());
        list_consultants.setListData(patient.getMedical().getConsultants().toArray());
    }
}
