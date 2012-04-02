package org.ece456.proj.gui.doctor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.ece456.proj.orm.objects.Doctor;

import com.google.common.collect.Lists;

public class ConsultantEditor extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final JButton btnAdd;
    private final ConsultantTable table;
    private final PatientDoctorPresenter presenter;

    private final Set<Doctor> set = new HashSet<Doctor>();
    private final JButton btnSave;
    private final JButton btnRemoveSelected;

    public ConsultantEditor(PatientDoctorPresenter presenter) {
        this.presenter = presenter;

        setLayout(new BorderLayout(0, 0));

        table = new ConsultantTable();
        add(table, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        add(panel, BorderLayout.SOUTH);

        btnAdd = new JButton("Add Consultant");
        btnAdd.addActionListener(this);
        panel.add(btnAdd);

        btnRemoveSelected = new JButton("Remove Selected");
        panel.add(btnRemoveSelected);

        btnSave = new JButton("Save");
        btnSave.addActionListener(this);
        panel.add(btnSave);
    }

    public void addConsultant(Doctor c) {
        // Add one consultant; duplicates (by ID) are ignored
        set.add(c);
        updateTable();
    }

    public void setConsultants(List<Doctor> c) {
        // Completely replace the table
        set.clear();
        set.addAll(c);
        updateTable();
    }

    private void updateTable() {
        table.update(Lists.newArrayList(set));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();
        if (s == btnAdd) {
            presenter.addConsultant();
        } else if (s == btnSave) {
            presenter.saveConsultants(set);
        }
    }
}
