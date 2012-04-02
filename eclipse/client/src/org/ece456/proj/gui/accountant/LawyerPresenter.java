package org.ece456.proj.gui.accountant;

import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Lawyer;

/**
 * Financial Department
 * 
 * ability to monitor any doctor to determine how many patients he/she saw in a given time period,
 * how many times a given patient was seen, what the diagnosis/result was, any drugs prescribed,
 * etc.
 */
public interface LawyerPresenter {

    void showPasswordChange();

    void showDoctorSearch();

    void show(Id<Lawyer> id);

}
