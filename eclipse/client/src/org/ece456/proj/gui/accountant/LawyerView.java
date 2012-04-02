package org.ece456.proj.gui.accountant;

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

import org.ece456.proj.orm.objects.Lawyer;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class LawyerView extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private final JMenuItem mntmChangePassword;
    private final LawyerPresenter presenter;
    private final JTextField text_name;
    private final JTextField text_id;
    private final JMenuItem mntmDoctor;
    private final Box verticalBox;
    private final JButton btnSearchForDoctor;
    private final Box verticalBox_1;
    private final Component verticalStrut_1;
    private final Component verticalStrut_2;

    public LawyerView(LawyerPresenter presenter) {
        this.presenter = presenter;

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setTitle("Lawyer Console");

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

        mntmDoctor = new JMenuItem("Doctor...");
        mntmDoctor.addActionListener(this);
        mnSearch.add(mntmDoctor);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        verticalBox = Box.createVerticalBox();
        getContentPane().add(verticalBox);

        JPanel panel = new JPanel();
        verticalBox.add(panel);
        panel.setLayout(new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("max(150dlu;min):grow"), FormFactory.RELATED_GAP_COLSPEC, },
                new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, }));

        JLabel lblLawyerId = new JLabel("Lawyer ID");
        panel.add(lblLawyerId, "2, 2, right, default");

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

        verticalStrut_1 = Box.createVerticalStrut(20);
        verticalBox.add(verticalStrut_1);

        verticalBox_1 = Box.createVerticalBox();
        verticalBox_1.setAlignmentX(Component.CENTER_ALIGNMENT);
        verticalBox.add(verticalBox_1);

        btnSearchForDoctor = new JButton("Search for Doctor Details");
        btnSearchForDoctor.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSearchForDoctor.addActionListener(this);
        verticalBox_1.add(btnSearchForDoctor);

        verticalStrut_2 = Box.createVerticalStrut(20);
        verticalBox.add(verticalStrut_2);

        pack();
        setLocation(100, 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();
        if (s == mntmChangePassword) {
            presenter.showPasswordChange();
        } else if (s == mntmDoctor || s == btnSearchForDoctor) {
            presenter.showDoctorSearch();
        }
    }

    public void fillLawyerData(Lawyer lawyer) {
        // Title
        setTitle(String.format("Lawyer Console: %s (%d)", lawyer.getName(), lawyer.getLawyerId()
                .asInt()));

        text_id.setText(lawyer.getLawyerId().toString());
        text_name.setText(lawyer.getName());
    }
}
