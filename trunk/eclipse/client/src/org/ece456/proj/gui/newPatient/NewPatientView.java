package org.ece456.proj.gui.newPatient;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.ece456.proj.orm.objects.Doctor;
import org.ece456.proj.orm.objects.Patient;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class NewPatientView extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private final NewPatientPresenter presenter;
    private final JButton btnCreate;
    private final NewPatientPanel panel_info;
    private final JButton btnUpdate;

    public NewPatientView(final NewPatientPresenter presenter) {
        this.presenter = presenter;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));

        panel_info = new NewPatientPanel();
        getContentPane().add(panel_info, BorderLayout.CENTER);

        setBounds(100, 100, 600, 400);

        JPanel panel_buttons = new JPanel();
        getContentPane().add(panel_buttons, BorderLayout.SOUTH);
        panel_buttons.setLayout(new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.GROWING_BUTTON_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, }, new RowSpec[] {
                FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC, }));

        btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(this);
        panel_buttons.add(btnUpdate, "6, 2");

        btnCreate = new JButton("Create");
        btnCreate.addActionListener(this);
        panel_buttons.add(btnCreate, "8, 2");

        setTitle("New Patient");

    }

    public void fillData(List<Doctor> doctors, Patient p) {
        btnUpdate.setEnabled(false);
        btnCreate.setEnabled(false);
        panel_info.fillDoctorsComboBox(doctors);
        if (p != null) {
            panel_info.fillPatientData(p, doctors);
            btnUpdate.setEnabled(true);
        } else {
            btnCreate.setEnabled(true);
        }
    }

    public Patient getPatientInfo() {
        return panel_info.saveForm();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();

        if (s == btnCreate) {
            presenter.createPatient();
        } else if (s == btnUpdate) {
            presenter.updatePatient();
        }
    }

}
