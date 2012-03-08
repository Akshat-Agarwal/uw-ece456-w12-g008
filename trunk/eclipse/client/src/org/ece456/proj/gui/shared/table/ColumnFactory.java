package org.ece456.proj.gui.shared.table;

public enum ColumnFactory {
    INSTANCE;

    public abstract class ColumnModel<T> {

        private final int preferredWidth;

        public ColumnModel() {
            this.preferredWidth = 100;
        }

        public ColumnModel(int preferredWidth) {
            this.preferredWidth = preferredWidth;
        }

        public abstract String getCellValue(T obj);

        public abstract String getColumnTitle();

        public int getPreferredWidth() {
            return preferredWidth;
        }
    }

    public interface CellRenderer<T> {
        String render(T obj);
    }

    public <T> ColumnModel<T> create(final String title, final CellRenderer<T> cell) {
        return create(title, cell, 100);
    }

    public <T> ColumnModel<T> create(final String title, final CellRenderer<T> cell,
            int preferredWidth) {
        return new ColumnModel<T>(preferredWidth) {
            @Override
            public String getCellValue(T obj) {
                return cell.render(obj);
            }

            @Override
            public String getColumnTitle() {
                return title;
            }
        };
    }
}
