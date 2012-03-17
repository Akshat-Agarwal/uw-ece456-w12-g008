package org.ece456.proj.gui.shared.table;

import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import org.ece456.proj.gui.shared.table.ColumnFactory.ColumnModel;

public abstract class SimpleTable<T> extends JPanel {
    private static final long serialVersionUID = 1L;

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MMM dd yyyy HH:mm");

    private final JTable table;

    private final SimpleTableModel<T> model;

    private List<T> data;

    private final JLabel labelResults;

    public static <E> SimpleTable<E> create(final List<ColumnModel<E>> columns) {
        return new SimpleTable<E>() {
            private static final long serialVersionUID = 1L;

            @Override
            protected SimpleTableModel<E> initModel() {
                return SimpleTableModel.create(columns);
            }
        };
    }

    protected SimpleTable() {
        setBackground(SystemColor.window);
        setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(null);

        model = initModel();
        table = new JTable(model);
        table.setBorder(null);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        for (int i = 0; i < model.getColumnCount(); i++) {
            int w = model.getColumn(i).getPreferredWidth();
            table.getColumnModel().getColumn(i).setPreferredWidth(w);
        }

        scrollPane.setViewportView(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        add(panel, BorderLayout.SOUTH);
        panel.setLayout(new BorderLayout(0, 0));

        labelResults = new JLabel("0 results ");
        panel.add(labelResults, BorderLayout.EAST);

    }

    public void update(List<T> data) {
        this.data = data;
        model.setData(data);
        labelResults.setText(String.format("%d results ", data.size()));
    }

    public T getSelected() {
        int i = table.getSelectedRow();
        if (i == -1) {
            return null;
        } else {
            return data.get(i);
        }
    }

    protected abstract SimpleTableModel<T> initModel();
}
