package org.ece456.proj.gui.appointment;

import org.ece456.proj.gui.shared.table.ColumnFactory;
import org.ece456.proj.gui.shared.table.ColumnFactory.CellRenderer;
import org.ece456.proj.gui.shared.table.ColumnFactory.ColumnModel;
import org.ece456.proj.gui.shared.table.SimpleTable;
import org.ece456.proj.orm.objects.Appointment;

public class AppointmentTableColumns {

    public static ColumnModel<Appointment> START_TIME = ColumnFactory.INSTANCE.create("Start Time",
            new CellRenderer<Appointment>() {
                @Override
                public String render(Appointment obj) {
                    return SimpleTable.DATE_FORMAT.format(obj.getStart_time());
                }
            }, 200);

    public static ColumnModel<Appointment> DURATION = ColumnFactory.INSTANCE.create(
            "Duration (min)", new CellRenderer<Appointment>() {
                @Override
                public String render(Appointment obj) {
                    return String.valueOf(obj.getLength());
                }
            }, 60);

    public static ColumnModel<Appointment> PATIENT = ColumnFactory.INSTANCE.create("Patient",
            new CellRenderer<Appointment>() {
                @Override
                public String render(Appointment obj) {
                    return obj.getPatient().getName();
                }
            }, 150);

    public static ColumnModel<Appointment> DOCTOR = ColumnFactory.INSTANCE.create("Doctor",
            new CellRenderer<Appointment>() {
                @Override
                public String render(Appointment obj) {
                    return obj.getDoctor().getName();
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
