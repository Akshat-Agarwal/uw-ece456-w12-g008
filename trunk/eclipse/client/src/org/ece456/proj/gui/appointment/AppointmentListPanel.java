package org.ece456.proj.gui.appointment;

import java.util.Date;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;

import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.Doctor;
import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Patient;

import com.google.common.collect.ImmutableList;

public class AppointmentListPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private final JTable table;

    public AppointmentListPanel() {
        super();

        Appointment a = new Appointment();
        a.setComment("comment");
        a.setDiagnoses("diagnose");
        a.setDoctor_id(Id.<Doctor> of(123));
        a.setLast_modified(new Date());
        a.setLength(30);
        a.setPatient_id(Id.<Patient> of(456));
        a.setPrescriptions("presc");
        a.setProcedures("procs");
        a.setStart_time(new Date());

        List<Appointment> apps = ImmutableList.of(a);

        table = new JTable(new AppointmentTableModel(apps));
        add(table);
    }
}
