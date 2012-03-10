package org.ece456.proj.gui.account;

import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.UserRole;

public interface PasswordChangePresenter {

    void show(UserRole role, Id<?> id, String password);

    void updatePassword(String newPassword);
}
