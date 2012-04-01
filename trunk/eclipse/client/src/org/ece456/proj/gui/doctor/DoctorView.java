package org.ece456.proj.gui.doctor;

import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import org.ece456.proj.orm.objects.Doctor;

public class DoctorView extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private final DoctorContactPanel panel_contact;
    private final JMenuItem mntmSearchPatient;

    private final DoctorPresenter presenter;

    private final JMenuItem mntmRefresh;

    private final JMenuItem mntmChangePassword;

    public DoctorView(final DoctorPresenter presenter) {
        this.presenter = presenter;

        // Set the frame's properties
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setTitle("Doctor View");
        setBounds(100, 100, 600, 400);
        getContentPane().setLayout(new BorderLayout(0, 0));

        // Set up the tab view
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        // Contact
        JPanel tab_contact = new JPanel();
        tabbedPane.addTab("Contact Data", null, tab_contact, null);
        tab_contact.setLayout(new BorderLayout(0, 0));

        panel_contact = new DoctorContactPanel();
        panel_contact.setBackground(SystemColor.window);
        tab_contact.add(panel_contact, BorderLayout.CENTER);

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

        JMenu mnSearch = new JMenu("Search");
        menuBar.add(mnSearch);

        mntmSearchPatient = new JMenuItem("Search Patient");
        mntmSearchPatient.addActionListener(this);
        mnSearch.add(mntmSearchPatient);
    }

    public void fillDoctorData(Doctor doctor) {
        // Title
        setTitle(String.format("Doctor View: %s (%d)", doctor.getName(), doctor.getDoctor_id()
                .asInt()));

        // Contact
        panel_contact.fillDoctorData(doctor.getDoctor_id(), doctor);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();
    }
}
