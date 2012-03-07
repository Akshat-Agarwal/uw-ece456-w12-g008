package org.ece456.proj.gui.main;

import java.awt.EventQueue;

import javax.swing.UIManager;

public class ClientMain {

    /**
     * Launch the client application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                    MainPresenter presenter = new MainPresenterImpl();
                    MainView window = new MainView(presenter);
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
