package org.ece456.proj.gui.admin;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.ece456.proj.orm.objects.Admin;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class AdminView extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private final JMenuItem mntmChangePassword;
    private final AdminPresenter presenter;
    private final JTextField text_name;
    private final JTextField text_id;
    private final JMenuItem mntmPatient;
    private final JMenuItem mntmDoctor;
    private final JMenuItem mntmStaff;
    private final Box verticalBox;
    private final Component verticalStrut;
    private final JButton btnSearchForPatient;
    private final JButton btnSearchForDoctor;
    private final JButton btnSearchForStaff;
    private final Box verticalBox_1;
    private final Component verticalStrut_1;
    private final Component verticalStrut_2;
    private final Component verticalStrut_3;

    public AdminView(AdminPresenter presenter) {
        this.presenter = presenter;

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setTitle("Admin Console");

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnAccount = new JMenu("Account");
        mnAccount.setMnemonic('a');
        menuBar.add(mnAccount);

        mntmChangePassword = new JMenuItem("Change password...");
        mntmChangePassword.addActionListener(this);
        mnAccount.add(mntmChangePassword);

        JMenu mnSearch = new JMenu("Search");
        mnSearch.setMnemonic('s');
        menuBar.add(mnSearch);

        mntmPatient = new JMenuItem("Patient...");
        mntmPatient.addActionListener(this);
        mnSearch.add(mntmPatient);

        mntmDoctor = new JMenuItem("Doctor...");
        mntmDoctor.addActionListener(this);
        mnSearch.add(mntmDoctor);

        mntmStaff = new JMenuItem("Staff...");
        mntmStaff.addActionListener(this);
        mnSearch.add(mntmStaff);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        verticalBox_1 = Box.createVerticalBox();
        getContentPane().add(verticalBox_1);

        JPanel panel = new JPanel();
        verticalBox_1.add(panel);
        panel.setLayout(new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("max(150dlu;min):grow"), FormFactory.RELATED_GAP_COLSPEC, },
                new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));

        JLabel lblAdminId = new JLabel("Admin ID");
        panel.add(lblAdminId, "2, 2, right, default");

        text_id = new JTextField();
        text_id.setEditable(false);
        panel.add(text_id, "4, 2, fill, default");
        text_id.setColumns(10);

        JLabel lblName = new JLabel("Name");
        panel.add(lblName, "2, 4, right, default");

        text_name = new JTextField();
        text_name.setEditable(false);
        panel.add(text_name, "4, 4, fill, default");
        text_name.setColumns(10);

        verticalStrut = Box.createVerticalStrut(20);
        verticalBox_1.add(verticalStrut);

        verticalBox = Box.createVerticalBox();
        verticalBox_1.add(verticalBox);
        verticalBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnSearchForPatient = new JButton("Search for a patient...");
        btnSearchForPatient.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSearchForPatient.addActionListener(this);
        verticalBox.add(btnSearchForPatient);

        verticalStrut_1 = Box.createVerticalStrut(20);
        verticalBox.add(verticalStrut_1);

        btnSearchForDoctor = new JButton("Search for a doctor...");
        btnSearchForDoctor.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSearchForDoctor.addActionListener(this);
        verticalBox.add(btnSearchForDoctor);

        verticalStrut_2 = Box.createVerticalStrut(20);
        verticalBox.add(verticalStrut_2);

        btnSearchForStaff = new JButton("Search for a staff...");
        btnSearchForStaff.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSearchForStaff.addActionListener(this);
        verticalBox.add(btnSearchForStaff);

        verticalStrut_3 = Box.createVerticalStrut(20);
        verticalBox_1.add(verticalStrut_3);

        pack();
        setLocation(100, 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();
        if (s == mntmChangePassword) {
            presenter.showPasswordChange();
        } else if (s == mntmPatient || s == btnSearchForPatient) {
            presenter.showPatientSearch();
        } else if (s == mntmDoctor || s == btnSearchForDoctor) {
            presenter.showDoctorSearch();
        } else if (s == mntmStaff || s == btnSearchForStaff) {
            presenter.showStaffSearch();
        }
    }

    public void fillAdminData(Admin admin) {
        // Title
        setTitle(String.format("Admin Console: %s (%d)", admin.getName(), admin.getAdmin_id()
                .asInt()));

        text_id.setText(admin.getAdmin_id().toString());
        text_name.setText(admin.getName());
    }
}
