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
    private final JButton btnSave;
    private final JButton btnReset;
    private final NewPatientPanel panel_info;

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
                FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"), FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, }, new RowSpec[] {
                FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC, }));

        btnReset = new JButton("Reset");
        btnReset.addActionListener(this);
        panel_buttons.add(btnReset, "6, 2");

        btnSave = new JButton("Save");
        panel_buttons.add(btnSave, "8, 2");
        btnSave.addActionListener(this);

        setTitle("New Patient");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();

        if (s == btnReset) {
            presenter.reset();
        } else if (s == btnSave) {
            presenter.save();
        }
    }

    public void fillDoctors(List<Doctor> doctors) {
        panel_info.fillDoctorsComboBox(doctors);
    }

    public void reset() {
        panel_info.clearForm();
        panel_info.updateUI();
    }

    public Patient save() {
        // create a patient with info from form
        return panel_info.saveForm();
    }
}
