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

import org.ece456.proj.orm.objects.PatientMedical;
import org.ece456.proj.orm.objects.Sex;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PatientMedicalPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private final JTextField text_sin;
    private final JTextField text_healthcard;
    private final JTextField text_numvisits;
    private final JTextField text_doctor;
    private final JTextField text_currenthealth;
    private final JComboBox combo_sex;
    private final JList list_consultants;

    public PatientMedicalPanel() {
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
                        FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), }));

        JLabel label_sin = new JLabel("SIN");
        add(label_sin, "2, 2, right, default");

        text_sin = new JTextField();
        text_sin.setEditable(false);
        add(text_sin, "4, 2, fill, default");
        text_sin.setColumns(10);

        JLabel lblHeatlhCard = new JLabel("Health Card #");
        add(lblHeatlhCard, "2, 4, right, default");

        text_healthcard = new JTextField();
        text_healthcard.setEditable(false);
        add(text_healthcard, "4, 4, fill, default");
        text_healthcard.setColumns(10);

        JLabel lblVisits = new JLabel("# Visits");
        add(lblVisits, "2, 6, right, default");

        text_numvisits = new JTextField();
        text_numvisits.setEditable(false);
        add(text_numvisits, "4, 6, fill, default");
        text_numvisits.setColumns(10);

        JLabel lblSex = new JLabel("Sex");
        add(lblSex, "2, 8, right, default");

        combo_sex = new JComboBox();
        combo_sex.setEnabled(false);
        combo_sex.setModel(new DefaultComboBoxModel(Sex.values()));
        add(combo_sex, "4, 8, fill, default");

        JLabel lblAssignedDoctor = new JLabel("Assigned Doctor");
        add(lblAssignedDoctor, "2, 10, right, default");

        text_doctor = new JTextField();
        text_doctor.setEditable(false);
        add(text_doctor, "4, 10, fill, default");
        text_doctor.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Current Health");
        add(lblNewLabel_1, "2, 12, right, default");

        text_currenthealth = new JTextField();
        text_currenthealth.setEditable(false);
        add(text_currenthealth, "4, 12, fill, top");
        text_currenthealth.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Consultants");
        add(lblNewLabel_2, "2, 14, right, default");

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, "4, 14, fill, fill");

        list_consultants = new JList();
        scrollPane.setViewportView(list_consultants);
        list_consultants.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_consultants.setVisibleRowCount(3);
    }

    public void fillPatientData(PatientMedical patient) {
        text_sin.setText(String.valueOf(patient.getSin()));
        text_healthcard.setText(patient.getHealthCardNumber());
        text_numvisits.setText(String.valueOf(patient.getNumVisits()));
        combo_sex.setSelectedItem(patient.getSex());
        text_doctor.setText(patient.getDefaultDoctor().getName());
        text_currenthealth.setText(patient.getCurrentHealth());
        list_consultants.setListData(patient.getConsultants().toArray());
    }

}
