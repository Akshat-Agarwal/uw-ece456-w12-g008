package org.ece456.proj.gui.doctor;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.ece456.proj.gui.shared.table.SimpleTable;
import org.ece456.proj.gui.shared.widgets.DateRangePicker.Listener;
import org.ece456.proj.orm.objects.Appointment;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public abstract class AbstractPatientDoctorView extends JFrame implements Listener {
    private static final long serialVersionUID = 1L;

    protected final JTextField textFieldId;
    protected final JTextField textFieldName;
    protected final SimpleTable<Appointment> table;

    protected AbstractPatientDoctorView(PatientDoctorPresenter appointmentPresenter) {
        getContentPane().setLayout(new BorderLayout(0, 0));

        Box verticalBox = Box.createVerticalBox();
        getContentPane().add(verticalBox, BorderLayout.NORTH);

        JPanel panelInfo = new JPanel();
        verticalBox.add(panelInfo);
        panelInfo.setLayout(new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"), FormFactory.RELATED_GAP_COLSPEC, },
                new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, }));

        JLabel lblDoctorId = new JLabel("ID");
        panelInfo.add(lblDoctorId, "2, 2, right, default");

        textFieldId = new JTextField();
        textFieldId.setEditable(false);
        panelInfo.add(textFieldId, "4, 2, fill, default");
        textFieldId.setColumns(10);

        JLabel lblName = new JLabel("Name");
        panelInfo.add(lblName, "2, 4, right, default");

        textFieldName = new JTextField();
        textFieldName.setEditable(false);
        panelInfo.add(textFieldName, "4, 4, fill, default");
        textFieldName.setColumns(10);

        // JPanel panelSearch = new DateRangePicker(this);
        // verticalBox.add(panelSearch);

        table = createAppointmentsTable(appointmentPresenter);
        getContentPane().add(table, BorderLayout.CENTER);
        table.setPreferredSize(new Dimension(500, 300));

        pack();
        setLocation(100, 100);
    }

    abstract SimpleTable<Appointment> createAppointmentsTable(PatientDoctorPresenter p);
}
