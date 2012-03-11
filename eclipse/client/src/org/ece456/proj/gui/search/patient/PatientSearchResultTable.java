package org.ece456.proj.gui.search.patient;

import org.ece456.proj.gui.shared.table.ColumnFactory;
import org.ece456.proj.gui.shared.table.ColumnFactory.CellRenderer;
import org.ece456.proj.gui.shared.table.ColumnFactory.ColumnModel;
import org.ece456.proj.gui.shared.table.SimpleTable;
import org.ece456.proj.gui.shared.table.SimpleTableModel;
import org.ece456.proj.orm.objects.Patient;

import com.google.common.collect.ImmutableList;

public class PatientSearchResultTable extends SimpleTable<Patient> {
    private static final long serialVersionUID = 1L;

    @Override
    protected SimpleTableModel<Patient> initModel() {
        return SimpleTableModel.create(ImmutableList.of(ID, NAME, HEALTH_CARD, SIN));
    }

    // Columns below

    public static ColumnModel<Patient> ID = ColumnFactory.INSTANCE.create("ID",
            new CellRenderer<Patient>() {
                @Override
                public String render(Patient p) {
                    return String.valueOf(p.getPatientId().asInt());
                }
            }, 20);

    public static ColumnModel<Patient> NAME = ColumnFactory.INSTANCE.create("Name",
            new CellRenderer<Patient>() {
                @Override
                public String render(Patient p) {
                    return p.getContact().getName();
                }
            }, 150);

    public static ColumnModel<Patient> HEALTH_CARD = ColumnFactory.INSTANCE.create("Health Card",
            new CellRenderer<Patient>() {
                @Override
                public String render(Patient p) {
                    return p.getMedical().getHealthCardNumber();
                }
            }, 90);

    public static ColumnModel<Patient> SIN = ColumnFactory.INSTANCE.create("SIN",
            new CellRenderer<Patient>() {
                @Override
                public String render(Patient p) {
                    return String.valueOf(p.getMedical().getSin());
                }
            }, 90);

}
