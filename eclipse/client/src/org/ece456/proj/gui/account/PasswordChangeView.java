package org.ece456.proj.gui.account;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.UserRole;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PasswordChangeView extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private final JTextField text_id;
    private final JPasswordField password_old;
    private final JPasswordField password_new0;
    private final JPasswordField password_new1;
    private final JTextField text_role;

    private final JButton btnChange;

    private String oldPassword;

    private final PasswordChangePresenter presenter;

    public PasswordChangeView(PasswordChangePresenterImpl presenter) {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.presenter = presenter;

        setTitle("Change password");
        getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"), FormFactory.RELATED_GAP_COLSPEC, },
                new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));

        JLabel lblRole = new JLabel("Role");
        panel.add(lblRole, "2, 2, right, default");

        text_role = new JTextField();
        text_role.setEditable(false);
        panel.add(text_role, "4, 2, fill, default");
        text_role.setColumns(10);

        JLabel lblAccountId = new JLabel("Account ID");
        panel.add(lblAccountId, "2, 4, right, default");

        text_id = new JTextField();
        text_id.setEditable(false);
        panel.add(text_id, "4, 4, fill, default");
        text_id.setColumns(10);

        JLabel lblOldPassword = new JLabel("Old password");
        panel.add(lblOldPassword, "2, 6, right, default");

        password_old = new JPasswordField();
        panel.add(password_old, "4, 6, fill, default");

        JLabel lblNewPassword = new JLabel("New password");
        panel.add(lblNewPassword, "2, 8, right, default");

        password_new0 = new JPasswordField();
        panel.add(password_new0, "4, 8, fill, default");

        JLabel lblRetypePassword = new JLabel("Retype password");
        panel.add(lblRetypePassword, "2, 10, right, default");

        password_new1 = new JPasswordField();
        panel.add(password_new1, "4, 10, fill, default");

        JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.SOUTH);

        btnChange = new JButton("Change");
        btnChange.addActionListener(this);
        panel_1.add(btnChange);

        pack();
        setLocation(100, 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnChange) {
            if (!oldPassword.equals(String.valueOf(password_old.getPassword()))) {
                // Old password was not typed correctly
                showError("Old password does not match.");
            } else if (!Arrays.equals(password_new0.getPassword(), password_new1.getPassword())) {
                // Retyped new password does not mach
                showError("Please re-type the new password");
            } else {
                // Everything is OK
                btnChange.setEnabled(false);
                btnChange.setText("Updating...");

                presenter.updatePassword(String.valueOf(password_new0.getPassword()));

                JOptionPane.showMessageDialog(new JFrame(), "Your password was changed.",
                        "Success!", JOptionPane.INFORMATION_MESSAGE);

                // Destroy the dialog
                dispose();
            }
        }
    }

    public void fillAccountData(UserRole role, Id<?> id, String oldPassword) {
        this.oldPassword = oldPassword;

        text_id.setText(String.valueOf(id.asInt()));
        text_role.setText(role.toString());
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(new JFrame(), message, "There was a problem...",
                JOptionPane.ERROR_MESSAGE);
    }
}
