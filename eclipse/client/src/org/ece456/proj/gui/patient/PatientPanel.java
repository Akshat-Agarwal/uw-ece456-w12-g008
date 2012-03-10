package org.ece456.proj.gui.patient;

import java.awt.SystemColor;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.PatientContact;
import org.ece456.proj.orm.objects.PatientMedical;
import org.ece456.proj.orm.objects.Sex;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PatientPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    // Personal
    private final JTextField text_id;
    private final JTextField text_name;
    private final JTextField text_address;
    private final JTextField text_phone;

    // Medical
    private final JTextField text_sin;
    private final JTextField text_healthcard;
    private final JTextField text_numvisits;
    private final JTextField text_doctor;
    private final JTextField text_currenthealth;
    private final JComboBox combo_sex;
    private final JList list_consultants;

    public PatientPanel() {
        super();
        setBackground(SystemColor.window);
        setLayout(new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"), FormFactory.RELATED_GAP_COLSPEC, },
                new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), }));

        JLabel lblId = new JLabel("Patient ID");
        add(lblId, "2, 2, right, default");

        text_id = new JTextField();
        text_id.setEditable(false);
        add(text_id, "4, 2, fill, default");
        text_id.setColumns(10);

        JLabel lblName = new JLabel("Name");
        add(lblName, "2, 4, right, default");

        text_name = new JTextField();
        text_name.setEditable(false);
        add(text_name, "4, 4, fill, default");
        text_name.setColumns(10);

        JLabel lblAddress = new JLabel("Address");
        add(lblAddress, "2, 6, right, default");

        text_address = new JTextField();
        text_address.setEditable(false);
        add(text_address, "4, 6, fill, default");
        text_address.setColumns(10);

        JLabel lblNewLabel = new JLabel("Phone #");
        add(lblNewLabel, "2, 8, right, default");

        text_phone = new JTextField();
        text_phone.setEditable(false);
        add(text_phone, "4, 8, fill, default");
        text_phone.setColumns(10);

        JLabel label_sin = new JLabel("SIN");
        add(label_sin, "2, 10, right, default");

        text_sin = new JTextField();
        text_sin.setEditable(false);
        add(text_sin, "4, 10, fill, default");
        text_sin.setColumns(10);

        JLabel lblHeatlhCard = new JLabel("Health Card #");
        add(lblHeatlhCard, "2, 12, right, default");

        text_healthcard = new JTextField();
        text_healthcard.setEditable(false);
        add(text_healthcard, "4, 12, fill, default");
        text_healthcard.setColumns(10);

        JLabel lblVisits = new JLabel("# Visits");
        add(lblVisits, "2, 14, right, default");

        text_numvisits = new JTextField();
        text_numvisits.setEditable(false);
        add(text_numvisits, "4, 14, fill, default");
        text_numvisits.setColumns(10);

        JLabel lblSex = new JLabel("Sex");
        add(lblSex, "2, 16, right, default");

        combo_sex = new JComboBox();
        combo_sex.setEnabled(false);
        combo_sex.setModel(new DefaultComboBoxModel(Sex.values()));
        add(combo_sex, "4, 16, fill, default");

        JLabel lblAssignedDoctor = new JLabel("Assigned Doctor");
        add(lblAssignedDoctor, "2, 18, right, default");

        text_doctor = new JTextField();
        text_doctor.setEditable(false);
        add(text_doctor, "4, 18, fill, default");
        text_doctor.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Current Health");
        add(lblNewLabel_1, "2, 20, right, default");

        text_currenthealth = new JTextField();
        text_currenthealth.setEditable(false);
        add(text_currenthealth, "4, 20, fill, top");
        text_currenthealth.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Consultants");
        add(lblNewLabel_2, "2, 22, right, default");

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, "4, 22, fill, fill");

        list_consultants = new JList();
        list_consultants.setEnabled(false);
        scrollPane.setViewportView(list_consultants);
        list_consultants.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_consultants.setVisibleRowCount(3);
    }

    public void fillPatientData(Patient patient) {
        text_id.setText(String.valueOf(patient.getPatientId().asInt()));

        // Contact
        PatientContact c = patient.getContact();
        text_name.setText(c.getName());
        text_address.setText(c.getAddress());
        text_phone.setText(c.getPhoneNum());

        // Medical
        PatientMedical m = patient.getMedical();
        text_sin.setText(String.valueOf(m.getSin()));
        text_healthcard.setText(m.getHealthCardNumber());
        text_numvisits.setText(String.valueOf(m.getNumVisits()));
        combo_sex.setSelectedItem(m.getSex());
        text_doctor.setText(m.getDefaultDoctor().getName());
        text_currenthealth.setText(m.getCurrentHealth());
        list_consultants.setListData(m.getConsultants().toArray());
    }

    public void setEditable(boolean editable) {
        text_name.setEditable(editable);
        text_address.setEditable(editable);
        text_phone.setEditable(editable);
    }
}
