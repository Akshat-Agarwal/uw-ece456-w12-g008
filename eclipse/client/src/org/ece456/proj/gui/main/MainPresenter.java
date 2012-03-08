package org.ece456.proj.gui.main;

import org.ece456.proj.orm.objects.UserRole;

public interface MainPresenter {
    String login(String host, UserRole role, int id, char[] password);
}
