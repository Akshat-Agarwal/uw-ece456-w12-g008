package org.ece456.proj.gui.admin;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        JMenuItem mntmPatient = new JMenuItem("Patient...");
        mnSearch.add(mntmPatient);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
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

        pack();
        setLocation(100, 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();
        if (s == mntmChangePassword) {
            presenter.showPasswordChange();
        }
    }

    public void fillAdminData(Admin admin) {
        text_id.setText(admin.getAdmin_id().toString());
        text_name.setText(admin.getName());
    }
}
