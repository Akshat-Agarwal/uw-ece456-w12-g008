package org.ece456.proj.gui.admin;

import org.ece456.proj.orm.objects.Admin;
import org.ece456.proj.orm.objects.Id;

public interface AdminPresenter {

    void showPasswordChange();

    void show(Id<Admin> id);

    void showPatientSearch();

}
