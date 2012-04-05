package org.ece456.proj.gui.shared.table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import org.ece456.proj.gui.shared.table.ColumnFactory.ColumnModel;

public abstract class SimpleTable<T> extends JPanel implements ActionListener, MouseListener {
    private static final long serialVersionUID = 1L;

    public interface Listener<T> {
        void onSelection(T selected);

        void onCancel();
    }

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MMM dd yyyy HH:mm");

    private final JTable table;

    private final SimpleTableModel<T> model;

    private List<T> data;

    private final JLabel labelResults;

    private final Listener<T> listener;

    private JButton btnCancel;

    private JButton btnSubmit;

    private final JPanel panel_top;

    private JPanel panel_buttons;

    public static <E> SimpleTable<E> create(final List<ColumnModel<E>> columns, Listener<E> listener) {
        return new SimpleTable<E>(listener, true, true) {
            private static final long serialVersionUID = 1L;

            @Override
            protected SimpleTableModel<E> initModel() {
                return SimpleTableModel.create(columns);
            }
        };
    }

    protected SimpleTable(Listener<T> listener, boolean showOpen, boolean showCancel) {
        this.listener = listener;

        setBackground(SystemColor.window);
        setLayout(new BorderLayout());

        panel_top = new JPanel();
        add(panel_top, BorderLayout.NORTH);
        panel_top.setLayout(new BorderLayout(0, 0));

        labelResults = new JLabel("0 results ");
        panel_top.add(labelResults, BorderLayout.EAST);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(null);
        model = initModel();

        table = new JTable(model);
        table.setBorder(null);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.addMouseListener(this);
        table.setAutoCreateRowSorter(true);

        for (int i = 0; i < model.getColumnCount(); i++) {
            int w = model.getColumn(i).getPreferredWidth();
            table.getColumnModel().getColumn(i).setPreferredWidth(w);
        }

        scrollPane.setViewportView(table);
        add(scrollPane, BorderLayout.CENTER);

        if (showOpen || showCancel) {
            panel_buttons = new JPanel();
            add(panel_buttons, BorderLayout.SOUTH);
            panel_buttons.setLayout(new BoxLayout(panel_buttons, BoxLayout.X_AXIS));

            Component horizontalGlue = Box.createHorizontalGlue();
            panel_buttons.add(horizontalGlue);

            if (showOpen) {
                btnSubmit = new JButton("Open");
                btnSubmit.addActionListener(this);
                panel_buttons.add(btnSubmit);
            }

            if (showOpen && showCancel) {
                Component horizontalStrut = Box.createHorizontalStrut(4);
                panel_buttons.add(horizontalStrut);
            }

            if (showCancel) {
                btnCancel = new JButton("Cancel");
                btnCancel.addActionListener(this);
                panel_buttons.add(btnCancel);
            }
        }
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();

        if (listener == null) {
            return;
        }

        if (s == btnCancel) {
            listener.onCancel();
        } else if (s == btnSubmit) {
            T selected = getSelected();
            if (selected != null) {
                listener.onSelection(selected);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == table) {
            if (e.getClickCount() == 2) {
                T selected = getSelected();
                if (selected != null && listener != null) {
                    listener.onSelection(selected);
                }
            }
        }
    }

    @Override
    public void setBackground(Color color) {
        super.setBackground(color);

        // setBackground can be also called in super() constructor, so need to check.
        if (table != null) {
            table.setBackground(color);
        }
        if (panel_top != null) {
            panel_top.setBackground(color);
        }
        if (panel_buttons != null) {
            panel_buttons.setBackground(color);
        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // do nothing
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // do nothing
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // do nothing
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // do nothing
    }
}
