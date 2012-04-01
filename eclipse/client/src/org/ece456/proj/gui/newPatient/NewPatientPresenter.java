package org.ece456.proj.gui.newPatient;

import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Staff;

public interface NewPatientPresenter {

    void show(Id<Staff> id);

    void save();

    void reset();
}
