package org.ece456.proj.gui.accountant;


/**
 * Financial Department
 * 
 * ability to monitor any doctor to determine how many patients he/she saw in a given time period,
 * how many times a given patient was seen, what the diagnosis/result was, any drugs prescribed,
 * etc.
 */
public interface AccountantPresenter {

    void showPasswordChange();

    void showDoctorSearch();

    void show(int id);

}
