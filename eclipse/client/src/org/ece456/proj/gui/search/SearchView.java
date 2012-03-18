package org.ece456.proj.gui.search;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.ece456.proj.gui.shared.table.ColumnFactory.ColumnModel;
import org.ece456.proj.gui.shared.table.SimpleTable;
import org.ece456.proj.gui.shared.table.SimpleTable.Listener;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public abstract class SearchView<T> extends JFrame implements ActionListener, Listener<T> {
    private static final long serialVersionUID = 1L;

    private final JTextField text_search;
    private final SimpleTable<T> resultTable;

    private final JButton btnSearch;
    private final JComboBox comboBox;

    private final SearchPresenter<T> presenter;

    public SearchView(String title, Object[] searchOptions, List<ColumnModel<T>> columns,
            SearchPresenter<T> presenter) {
        this.presenter = presenter;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(title);
        getContentPane().setLayout(new BorderLayout(0, 0));

        resultTable = SimpleTable.create(columns, this);
        resultTable.setPreferredSize(new Dimension(500, 400));

        JPanel panel_search = new JPanel();
        getContentPane().add(panel_search, BorderLayout.NORTH);
        panel_search.setLayout(new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"), FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, }, new RowSpec[] {
                FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC, }));

        comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(searchOptions));
        panel_search.add(comboBox, "2, 2, fill, default");

        text_search = new JTextField();
        panel_search.add(text_search, "4, 2, fill, default");
        text_search.setColumns(10);
        text_search.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (KeyEvent.VK_ENTER == e.getKeyCode()) {
                    search();
                }
            }
        });

        btnSearch = new JButton("Search");
        btnSearch.addActionListener(this);
        panel_search.add(btnSearch, "6, 2");

        JPanel panel_results = new JPanel();
        getContentPane().add(panel_results, BorderLayout.CENTER);
        panel_results.setLayout(new BorderLayout(0, 0));
        panel_results.add(resultTable);

        resultTable.setPreferredSize(new Dimension(400, 200));
        pack();
        setLocation(100, 100);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();

        if (s == btnSearch) {
            search();
        }
    }

    private void search() {
        btnSearch.setEnabled(false);

        List<T> searchResults = presenter.search(comboBox.getSelectedItem(), text_search.getText());
        resultTable.update(searchResults);

        btnSearch.setEnabled(true);
    }

    @Override
    public void onSelection(T selected) {
        if (selected != null) {
            presenter.onSelection(selected);
        }
    }

    @Override
    public void onCancel() {
        presenter.onCancel();
    }
}
