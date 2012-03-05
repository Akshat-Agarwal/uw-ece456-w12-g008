package org.ece456.proj.gui.main;

import org.ece456.proj.UserRole;

public class MainPresenterImpl implements MainPresenter {
    public MainPresenterImpl() {

    }

    @Override
    public void login(UserRole role, String username, char[] password) {
        String password_copy = String.valueOf(password);

        // clear password
        for (int i = 0; i < password.length; i++) {
            password[i] = 0;
        }

        System.out.printf("role: %s  username: %s  password: %s\n", role, username, password_copy);
    }
}
