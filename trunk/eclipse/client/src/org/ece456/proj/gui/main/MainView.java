package org.ece456.proj.gui.main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.ece456.proj.orm.objects.UserRole;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class MainView {

    private JFrame frame;
    private JTextField text_user_name;
    private JPasswordField password_field;
    private JComboBox comboBox;

    private final MainPresenter presenter;
    private JTextField text_host;

    /**
     * Create the application.
     */
    public MainView(MainPresenter presenter) {
        this.presenter = presenter;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("ECE 456 Project: G008");
        frame.setBounds(100, 100, 375, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel center_panel = new JPanel();
        frame.getContentPane().add(center_panel, BorderLayout.CENTER);
        center_panel.setLayout(new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC, FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
                ColumnSpec.decode("4dlu:grow"), FormFactory.RELATED_GAP_COLSPEC, }, new RowSpec[] {
                FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC, }));

        JLabel lblHost = new JLabel("Host");
        center_panel.add(lblHost, "2, 2, right, default");

        text_host = new JTextField();
        text_host.setText("localhost");
        center_panel.add(text_host, "4, 2, fill, default");
        text_host.setColumns(10);

        JLabel label_user_role = new JLabel("User Role");
        center_panel.add(label_user_role, "2, 4, right, default");

        comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(UserRole.values()));
        center_panel.add(comboBox, "4, 4, fill, default");

        JLabel label_user_name = new JLabel("User Name");
        center_panel.add(label_user_name, "2, 6, right, default");

        text_user_name = new JTextField();
        center_panel.add(text_user_name, "4, 6, fill, default");
        text_user_name.setColumns(10);

        JLabel lblPassword = new JLabel("Password");
        center_panel.add(lblPassword, "2, 9, right, default");

        password_field = new JPasswordField();
        password_field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (KeyEvent.VK_ENTER == e.getKeyCode()) {
                    login();
                }
            }
        });
        center_panel.add(password_field, "4, 9, fill, default");

        JPanel top_panel = new JPanel();
        frame.getContentPane().add(top_panel, BorderLayout.NORTH);

        JLabel label_title = new JLabel("Hospital G008 Database");
        top_panel.add(label_title);
        label_title.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        label_title.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel bottom_panel = new JPanel();
        frame.getContentPane().add(bottom_panel, BorderLayout.SOUTH);
        bottom_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        bottom_panel.add(btnLogin);
    }

    private void login() {
        String result = presenter.login(text_host.getText(), (UserRole) comboBox.getSelectedItem(),
                text_user_name.getText(), password_field.getPassword());
        if (result.length() > 0) {
            JOptionPane.showMessageDialog(new JFrame(), result, "There was a problem...",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}
