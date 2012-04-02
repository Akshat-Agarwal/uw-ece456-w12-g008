package org.ece456.proj.gui.accountant;

import org.ece456.proj.gui.shared.table.ColumnFactory;
import org.ece456.proj.gui.shared.table.ColumnFactory.CellRenderer;
import org.ece456.proj.gui.shared.table.ColumnFactory.ColumnModel;
import org.ece456.proj.gui.shared.table.SimpleTable;
import org.ece456.proj.gui.shared.table.SimpleTableModel;
import org.ece456.proj.orm.objects.Patient;

import com.google.common.collect.ImmutableList;

public class FinancialPatientTable extends SimpleTable<Patient> {
    private static final long serialVersionUID = 1L;

    public FinancialPatientTable() {
        super(null, false, false);
    }

    @Override
    protected SimpleTableModel<Patient> initModel() {
        return SimpleTableModel.create(ImmutableList.of(PATIENT_ID, NUM_VISITS));
    }

    // Columns below.

    public static ColumnModel<Patient> PATIENT_ID = ColumnFactory.INSTANCE.create("ID",
            new CellRenderer<Patient>() {
                @Override
                public String render(Patient p) {
                    return String.valueOf(p.getPatientId());
                }
            }, 150);

    public static ColumnModel<Patient> NUM_VISITS = ColumnFactory.INSTANCE.create("Num visits",
            new CellRenderer<Patient>() {
                @Override
                public String render(Patient p) {
                    return String.valueOf(p.getMedical().getNumVisits());
                }
            }, 150);
}
