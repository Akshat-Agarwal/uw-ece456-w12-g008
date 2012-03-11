package org.ece456.proj.gui.shared.table;

public interface SelectionListener<T> {

    public enum AfterAction {
        CLOSE_DIALOG,
        DO_NOTHING;
    }

    AfterAction onSelection(T selected);

    void onCancel();
}
