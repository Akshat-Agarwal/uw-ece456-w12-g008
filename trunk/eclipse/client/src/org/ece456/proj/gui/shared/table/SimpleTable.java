package org.ece456.proj.gui.shared.table;

import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public abstract class SimpleTable<T> extends JPanel {
    private static final long serialVersionUID = 1L;

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MMM dd yyyy HH:mm");

    private final JTable table;

    private final SimpleTableModel<T> model;

    public SimpleTable() {
        setBackground(SystemColor.window);
        setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane();

        model = initModel();
        table = new JTable(model);

        // table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < model.getColumnCount(); i++) {
            int w = model.getColumn(i).getPreferredWidth();
            table.getColumnModel().getColumn(i).setPreferredWidth(w);
        }

        scrollPane.setViewportView(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void update(List<T> data) {
        model.setData(data);
    }

    protected abstract SimpleTableModel<T> initModel();
}
