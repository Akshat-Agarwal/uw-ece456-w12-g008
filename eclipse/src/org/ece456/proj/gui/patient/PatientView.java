package org.ece456.proj.gui.patient;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PatientView extends JDialog {

    private static final long serialVersionUID = 1L;

    private final JPanel contentPanel = new JPanel();

    /**
     * Create the dialog.
     * 
     * @param presenter
     */
    public PatientView(PatientPresenter presenter) {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setTitle("Patient View");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        JLabel lblThisIsA = new JLabel("I got the username: " + presenter.getUsername());
        contentPanel.add(lblThisIsA);
    }
}
