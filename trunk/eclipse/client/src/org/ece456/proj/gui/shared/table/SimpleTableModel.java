package org.ece456.proj.gui.shared.table;

import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.ece456.proj.gui.shared.table.ColumnFactory.ColumnModel;

import com.google.common.collect.ImmutableList;

public class SimpleTableModel<T> extends AbstractTableModel {
    private static final long serialVersionUID = 1L;

    private final List<ColumnModel<T>> columns;
    private List<T> data;

    public static <T> SimpleTableModel<T> create(List<ColumnModel<T>> columns) {
        return new SimpleTableModel<T>(columns);
    }

    private SimpleTableModel(List<ColumnModel<T>> columns) {
        super();

        data = Collections.emptyList();

        this.columns = ImmutableList.copyOf(columns);
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public String getColumnName(int col) {
        return columns.get(col).getColumnTitle();
    }

    @Override
    public Object getValueAt(int row, int col) {
        T x = data.get(row);
        return columns.get(col).getCellValue(x);
    }

    public void setData(List<T> data) {
        this.data = data;
        fireTableDataChanged();
    }

    public ColumnModel<T> getColumn(int col) {
        return columns.get(col);
    }
}
