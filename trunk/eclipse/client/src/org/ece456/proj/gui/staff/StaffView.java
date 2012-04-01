package org.ece456.proj.gui.staff;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.ece456.proj.orm.objects.Staff;

public class StaffView extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private final StaffPanel panel_staffInfo;

    private final JMenuItem mntmEditPatient;
    private final JMenuItem mntmEditAppointment;
    private final JMenuItem mntmNewPatient;
    private final JMenuItem mntmNewAppointment;
    private final JMenuItem mntmSearchDoctor;
    private final JMenuItem mntmSearchPatient;

    private final StaffPresenter presenter;

    private final JMenuItem mntmRefresh;

    private final JMenuItem mntmChangePassword;

    public StaffView(final StaffPresenter presenter) {
        this.presenter = presenter;

        // Set the frame's properties
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setTitle("Staff view");
        setBounds(100, 100, 600, 400);
        getContentPane().setLayout(new BorderLayout(0, 0));

        panel_staffInfo = new StaffPanel();
        getContentPane().add(panel_staffInfo, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnAccount = new JMenu("Account");
        menuBar.add(mnAccount);

        mntmChangePassword = new JMenuItem("Change password...");
        mntmChangePassword.addActionListener(this);
        mnAccount.add(mntmChangePassword);

        JMenu mnView = new JMenu("View");
        mnView.setMnemonic('v');
        menuBar.add(mnView);

        mntmRefresh = new JMenuItem("Refresh");
        mntmRefresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
        mntmRefresh.addActionListener(this);
        mnView.add(mntmRefresh);

        JMenu mnNew = new JMenu("New");
        mnNew.setMnemonic('v');
        menuBar.add(mnNew);

        mntmNewPatient = new JMenuItem("Patient");
        mntmNewPatient.addActionListener(this);
        mnNew.add(mntmNewPatient);

        mntmNewAppointment = new JMenuItem("Appointment");
        mntmNewAppointment.addActionListener(this);
        mnNew.add(mntmNewAppointment);

        mntmEditAppointment = new JMenuItem("Edit Appointment");
        mntmEditAppointment.addActionListener(this);
        mnNew.add(mntmEditAppointment);

        mntmEditPatient = new JMenuItem("Edit Patient");
        mntmEditPatient.addActionListener(this);
        mnNew.add(mntmEditPatient);

        // SEARCH MENU
        JMenu mnSearch = new JMenu("Search");
        menuBar.add(mnSearch);

        mntmSearchDoctor = new JMenuItem("Search Doctor");
        mntmSearchDoctor.addActionListener(this);
        mnSearch.add(mntmSearchDoctor);

        mntmSearchPatient = new JMenuItem("Search Patient");
        mntmSearchPatient.addActionListener(this);
        mnSearch.add(mntmSearchPatient);

        pack();
        setSize(400, 300);
    }

    public void fillStaffData(Staff staff) {
        // Title
        setTitle(String.format("Staff View: %s (%d)", staff.getName(), staff.getStaffId().asInt()));
        panel_staffInfo.fillStaffData(staff);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();

        if (s == mntmEditPatient) {
            presenter.showPatientSearch(1);
        } else if (s == mntmEditAppointment) {
            presenter.showPatientSearch(1);
        } else if (s == mntmRefresh) {
            presenter.refresh();
        } else if (s == mntmChangePassword) {
            presenter.showPasswordChange();
        } else if (s == mntmNewPatient) {
            presenter.showNewPatientView();
        } else if (s == mntmNewAppointment) {
            presenter.showPatientSearch(1);
        } else if (s == mntmSearchDoctor) {
            presenter.showDoctorSearch(1);
        } else if (s == mntmSearchPatient) {
            presenter.showPatientSearch(1);
        }
    }
}
