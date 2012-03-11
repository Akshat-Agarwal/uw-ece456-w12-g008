package org.ece456.proj.gui.search.patient;

import java.util.List;

import org.ece456.proj.gui.search.SearchView;
import org.ece456.proj.gui.shared.table.ColumnFactory;
import org.ece456.proj.gui.shared.table.ColumnFactory.CellRenderer;
import org.ece456.proj.gui.shared.table.ColumnFactory.ColumnModel;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.PatientSearchOption;

import com.google.common.collect.ImmutableList;

public class PatientSearchView extends SearchView<Patient> {
    private static final long serialVersionUID = 1L;

    private final PatientSearchPresenter presenter;

    public PatientSearchView(PatientSearchPresenter presenter) {
        super("Search for a patient", PatientSearchOption.values(), ImmutableList.of(ID, NAME,
                HEALTH_CARD, SIN));
        this.presenter = presenter;
    }

    @Override
    protected void onCancel() {
        presenter.onCancel();
    }

    @Override
    protected void onSelection(Patient selected) {
        presenter.onSelection(selected);
    }

    @Override
    protected List<Patient> search(Object field, String text) {
        return presenter.search((PatientSearchOption) field, text);
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
