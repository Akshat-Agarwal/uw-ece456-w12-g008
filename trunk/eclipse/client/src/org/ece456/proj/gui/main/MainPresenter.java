package org.ece456.proj.gui.main;

import org.ece456.proj.UserRole;

public interface MainPresenter {
    void login(UserRole role, String username, char[] password);
}
