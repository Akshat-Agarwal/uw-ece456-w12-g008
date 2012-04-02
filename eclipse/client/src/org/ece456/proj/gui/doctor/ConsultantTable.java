package org.ece456.proj.gui.doctor;

import org.ece456.proj.gui.shared.table.ColumnFactory;
import org.ece456.proj.gui.shared.table.ColumnFactory.CellRenderer;
import org.ece456.proj.gui.shared.table.ColumnFactory.ColumnModel;
import org.ece456.proj.gui.shared.table.SimpleTable;
import org.ece456.proj.gui.shared.table.SimpleTableModel;
import org.ece456.proj.orm.objects.Doctor;

import com.google.common.collect.ImmutableList;

public class ConsultantTable extends SimpleTable<Doctor> {
    private static final long serialVersionUID = 1L;

    public ConsultantTable() {
        super(null, false, false);
    }

    @Override
    protected SimpleTableModel<Doctor> initModel() {
        return SimpleTableModel.create(ImmutableList.of(DOCTOR_ID, DOCTOR_NAME));
    }

    // Columns below.
    public static ColumnModel<Doctor> DOCTOR_ID = ColumnFactory.INSTANCE.create("ID",
            new CellRenderer<Doctor>() {
                @Override
                public String render(Doctor d) {
                    return String.valueOf(d.getDoctor_id());
                }
            }, 50);

    public static ColumnModel<Doctor> DOCTOR_NAME = ColumnFactory.INSTANCE.create(
            "Consultant Name", new CellRenderer<Doctor>() {
                @Override
                public String render(Doctor d) {
                    return d.getName();
                }
            }, 300);
}
