package org.ece456.proj.gui.main;

import org.ece456.proj.orm.objects.UserRole;

public interface MainPresenter {
    void login(UserRole role, String username, char[] password);
}
