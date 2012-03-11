package org.ece456.proj.gui.search.staff;

import org.ece456.proj.gui.search.SearchPresenter;
import org.ece456.proj.gui.search.SearchView;
import org.ece456.proj.gui.shared.table.ColumnFactory;
import org.ece456.proj.gui.shared.table.ColumnFactory.CellRenderer;
import org.ece456.proj.gui.shared.table.ColumnFactory.ColumnModel;
import org.ece456.proj.orm.objects.Staff;
import org.ece456.proj.orm.objects.StaffSearchOption;

import com.google.common.collect.ImmutableList;

public class StaffSearchView extends SearchView<Staff> {
    private static final long serialVersionUID = 1L;

    public StaffSearchView(SearchPresenter<Staff> presenter) {
        super("Search for a staff...", StaffSearchOption.values(), ImmutableList.of(ID, NAME),
                presenter);
    }

    // Columns below

    public static ColumnModel<Staff> ID = ColumnFactory.INSTANCE.create("ID",
            new CellRenderer<Staff>() {
                @Override
                public String render(Staff p) {
                    return String.valueOf(p.getStaffId().asInt());
                }
            }, 20);

    public static ColumnModel<Staff> NAME = ColumnFactory.INSTANCE.create("Name",
            new CellRenderer<Staff>() {
                @Override
                public String render(Staff p) {
                    return p.getName();
                }
            }, 150);
}
