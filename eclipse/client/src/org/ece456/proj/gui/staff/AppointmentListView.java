package org.ece456.proj.gui.staff;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import org.ece456.proj.gui.shared.table.SimpleTable;
import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.Doctor;
import org.ece456.proj.orm.objects.Patient;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class AppointmentListView extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    protected final JTextField textFieldId;
    protected final JTextField textFieldName;
    protected final SimpleTable<Appointment> table;
    private final JMenuItem mntmNewAppointment;
    private final JMenuItem mntmRefresh;

    private final AppointmentListPresenter presenter;

    protected AppointmentListView(AppointmentListPresenter appointmentListPresenter) {
        this.presenter = appointmentListPresenter;
        getContentPane().setLayout(new BorderLayout(0, 0));
        setTitle("Appointment List");

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

        table = createAppointmentsTable(appointmentListPresenter);
        getContentPane().add(table, BorderLayout.CENTER);
        table.setPreferredSize(new Dimension(500, 300));

        JMenuBar menuBar = new JMenuBar();
        getContentPane().add(menuBar, BorderLayout.NORTH);

        JMenu mnNew = new JMenu("New");
        menuBar.add(mnNew);

        mntmNewAppointment = new JMenuItem("New Appointment");
        mntmNewAppointment.addActionListener(this);
        mnNew.add(mntmNewAppointment);

        JMenu mnView = new JMenu("View");
        menuBar.add(mnView);

        mntmRefresh = new JMenuItem("Refresh");
        mntmRefresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
        mntmRefresh.addActionListener(this);
        mnView.add(mntmRefresh);

        pack();
        setLocation(100, 100);
    }

    public void fillData(Patient patient) {
        textFieldId.setText(patient.getPatientId().toString());
        textFieldName.setText(patient.getName());
    }

    public void fillData(Doctor doctor) {
        textFieldId.setText(doctor.getDoctor_id().toString());
        textFieldName.setText(doctor.getName());
    }

    SimpleTable<Appointment> createAppointmentsTable(
            AppointmentListPresenter appointmentListPresenter) {
        return new AppointmentListTable(appointmentListPresenter);
    }

    public void fillAppointments(java.util.List<Appointment> apps) {
        table.update(apps);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();

        if (s == mntmNewAppointment) {
            presenter.newAppointment();
        } else if (s == mntmRefresh) {
            presenter.refresh();
        }
    }

    public void setMenuVisible(Boolean b) {
        mntmNewAppointment.setEnabled(b);
    }

    public void setType(int displayType) {
        // TODO Auto-generated method stub

    }
}
