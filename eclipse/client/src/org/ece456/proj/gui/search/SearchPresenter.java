package org.ece456.proj.gui.search;

import java.util.List;

public interface SearchPresenter<T> {

    void show();

    List<T> search();

    List<T> search(Object field, String text);

    void onSelection(T selected);

    void onCancel();
}
