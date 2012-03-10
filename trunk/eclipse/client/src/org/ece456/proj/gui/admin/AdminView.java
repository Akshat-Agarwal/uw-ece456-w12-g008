package org.ece456.proj.gui.admin;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class AdminView extends JFrame {
    public AdminView() {
        setTitle("Admin Console");

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnAccount = new JMenu("Account");
        menuBar.add(mnAccount);

        JMenuItem mntmChangePassword = new JMenuItem("Change password...");
        mnAccount.add(mntmChangePassword);

        JMenu mnSearch = new JMenu("Search");
        mnSearch.setMnemonic('s');
        menuBar.add(mnSearch);

        JMenuItem mntmPatient = new JMenuItem("Patient...");
        mnSearch.add(mntmPatient);
    }

    private static final long serialVersionUID = 1L;

}
