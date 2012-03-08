package org.ece456.proj.gui.appointment;

import org.ece456.proj.gui.shared.table.ColumnFactory;
import org.ece456.proj.gui.shared.table.ColumnFactory.CellRenderer;
import org.ece456.proj.gui.shared.table.ColumnFactory.ColumnModel;
import org.ece456.proj.gui.shared.table.SimpleTable;
import org.ece456.proj.gui.shared.table.SimpleTableModel;
import org.ece456.proj.orm.objects.Appointment;

import com.google.common.collect.ImmutableList;

/**
 * For Patient use only: lots of info is hidden from patients.
 * 
 * start time doctor_id length procedures prescriptions diagnoses
 */
public class AppointmentTable extends SimpleTable<Appointment> {
    private static final long serialVersionUID = 1L;

    @Override
    protected SimpleTableModel<Appointment> initModel() {
        return SimpleTableModel.create(ImmutableList.of(START_TIME, DURATION, DOCTOR_ID, DIAGNOSES,
                PRESCRIPTIONS, PROCEDURES));
    }

    // Columns below
    public static ColumnModel<Appointment> START_TIME = ColumnFactory.INSTANCE.create("Start Time",
            new CellRenderer<Appointment>() {
                @Override
                public String render(Appointment obj) {
                    return DATE_FORMAT.format(obj.getStart_time());
                }
            }, 300);

    public static ColumnModel<Appointment> DURATION = ColumnFactory.INSTANCE.create(
            "Duration (min)", new CellRenderer<Appointment>() {
                @Override
                public String render(Appointment obj) {
                    return String.valueOf(obj.getLength());
                }
            }, 60);

    public static ColumnModel<Appointment> DOCTOR_ID = ColumnFactory.INSTANCE.create("Doctor",
            new CellRenderer<Appointment>() {
                @Override
                public String render(Appointment obj) {
                    return obj.getDoctor_id().toString();
                }
            }, 150);

    public static ColumnModel<Appointment> PROCEDURES = ColumnFactory.INSTANCE.create("Procedures",
            new CellRenderer<Appointment>() {
                @Override
                public String render(Appointment obj) {
                    return obj.getProcedures();
                }
            }, 200);

    public static ColumnModel<Appointment> DIAGNOSES = ColumnFactory.INSTANCE.create("Diagnoses",
            new CellRenderer<Appointment>() {
                @Override
                public String render(Appointment obj) {
                    return obj.getDiagnoses();
                }
            }, 200);

    public static ColumnModel<Appointment> PRESCRIPTIONS = ColumnFactory.INSTANCE.create(
            "Prescriptions", new CellRenderer<Appointment>() {
                @Override
                public String render(Appointment obj) {
                    return obj.getPrescriptions();
                }
            }, 200);
}
