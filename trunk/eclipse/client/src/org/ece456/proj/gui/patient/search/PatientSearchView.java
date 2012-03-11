package org.ece456.proj.gui.patient.search;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.PatientSearchOption;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PatientSearchView extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private final JTextField text_search;
    private final PatientSearchResultTable resultTable;

    private final JButton btnSearch;

    private final PatientSearchPresenter presenter;

    private final JComboBox comboBox;

    private final JButton btnCancel;

    private final JButton btnSelectPatient;

    public PatientSearchView(PatientSearchPresenter presenter) {
        this.presenter = presenter;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Search for a patient");
        getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        Box verticalBox = Box.createVerticalBox();
        panel.add(verticalBox);

        JPanel panel_search = new JPanel();
        verticalBox.add(panel_search);
        panel_search.setLayout(new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"), FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, }, new RowSpec[] {
                FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC, }));

        comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(PatientSearchOption.values()));
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

        JSeparator separator = new JSeparator();
        verticalBox.add(separator);

        JPanel panel_results = new JPanel();
        panel_results.setLayout(new BorderLayout(0, 0));

        resultTable = new PatientSearchResultTable();
        panel_results.add(resultTable);

        verticalBox.add(panel_results);

        JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.SOUTH);
        panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

        Component horizontalGlue = Box.createHorizontalGlue();
        panel_1.add(horizontalGlue);

        btnSelectPatient = new JButton("Select Patient");
        btnSelectPatient.addActionListener(this);
        panel_1.add(btnSelectPatient);

        Component horizontalStrut = Box.createHorizontalStrut(4);
        panel_1.add(horizontalStrut);

        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(this);
        panel_1.add(btnCancel);

        pack();
        setLocation(100, 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();

        if (s == btnSearch) {
            search();
        } else if (s == btnCancel) {
            presenter.onCancel();
        } else if (s == btnSelectPatient) {
            Patient selected = resultTable.getSelected();
            if (selected != null) {
                presenter.onSelection(selected);
            }
        }
    }

    private void search() {
        btnSearch.setEnabled(false);

        PatientSearchOption field = (PatientSearchOption) comboBox.getSelectedItem();
        List<Patient> searchResults = presenter.search(field, text_search.getText());
        resultTable.update(searchResults);

        btnSearch.setEnabled(true);
    }
}
