package org.ece456.proj.gui.appointment;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.ece456.proj.orm.objects.Appointment;

/**
 * For Patient use only: lots of info is hidden from patients.
 * 
 * start time
 * 
 * doctor_id
 * 
 * length
 * 
 * procedures
 * 
 * prescriptions
 * 
 * diagnoses
 */
public class AppointmentTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;

    private final List<Appointment> data;

    private enum Column {
        START_TIME("Start Time"),
        LENGTH("Length"),
        DOCTOR("Doctor"),
        PROCEDURES("Procedures"),
        DIAGNOSES("Diagnoses"),
        PRESCRIPTIONS("Prescriptions");

        public final String title;

        private Column(String title) {
            this.title = title;
        }

        public static Column valueOf(int index) {
            return Column.values()[index];
        }
    }

    public AppointmentTableModel(List<Appointment> appointments) {
        super();
        data = new ArrayList<Appointment>();
        data.addAll(appointments);
    }

    @Override
    public int getColumnCount() {
        return Column.values().length;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public String getColumnName(int column) {
        return Column.valueOf(column).title;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Appointment a = data.get(row);
        switch (Column.valueOf(col)) {
            case START_TIME:
                return a.getStart_time();
            case LENGTH:
                return a.getLength();
            case DOCTOR:
                return a.getDoctor_id();
            case DIAGNOSES:
                return a.getDiagnoses();
            case PRESCRIPTIONS:
                return a.getPrescriptions();
            case PROCEDURES:
                return a.getProcedures();
            default:
                return null;
        }
    }
}
