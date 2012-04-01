package org.ece456.proj.gui.staff;

import org.ece456.proj.gui.search.SearchPresenter;
import org.ece456.proj.gui.search.SearchView;
import org.ece456.proj.gui.shared.table.ColumnFactory;
import org.ece456.proj.gui.shared.table.ColumnFactory.CellRenderer;
import org.ece456.proj.gui.shared.table.ColumnFactory.ColumnModel;
import org.ece456.proj.orm.objects.Doctor;
import org.ece456.proj.orm.objects.DoctorSearchOption;

import com.google.common.collect.ImmutableList;

public class SearchDoctorByStaffView extends SearchView<Doctor> {
    private static final long serialVersionUID = 1L;

    public SearchDoctorByStaffView(SearchPresenter<Doctor> presenter) {
        super("Search for a doctor", DoctorSearchOption.values(), ImmutableList.of(ID, NAME),
                presenter);
    }

    // Columns below.

    public static ColumnModel<Doctor> ID = ColumnFactory.INSTANCE.create("ID",
            new CellRenderer<Doctor>() {
                @Override
                public String render(Doctor p) {
                    return String.valueOf(p.getDoctor_id().asInt());
                }
            }, 20);

    public static ColumnModel<Doctor> NAME = ColumnFactory.INSTANCE.create("Name",
            new CellRenderer<Doctor>() {
                @Override
                public String render(Doctor p) {
                    return p.getName();
                }
            }, 150);
}
