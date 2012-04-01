package org.ece456.proj.gui.staff;

import org.ece456.proj.gui.search.SearchPresenter;
import org.ece456.proj.gui.search.SearchView;
import org.ece456.proj.gui.shared.table.ColumnFactory;
import org.ece456.proj.gui.shared.table.ColumnFactory.CellRenderer;
import org.ece456.proj.gui.shared.table.ColumnFactory.ColumnModel;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.PatientSearchOption;

import com.google.common.collect.ImmutableList;

public class SearchPatientByStaffView extends SearchView<Patient> {
    private static final long serialVersionUID = 1L;

    public SearchPatientByStaffView(SearchPresenter<Patient> presenter) {
        super("Search for a patient", PatientSearchOption.values(), ImmutableList.of(ID, NAME),
                presenter);
    }

    // Columns below.

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
}
