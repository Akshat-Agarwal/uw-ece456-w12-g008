package org.ece456.proj.gui.staff;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.ece456.proj.orm.objects.Staff;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class StaffPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private final JTextField text_id;
    private final JTextField text_name;

    public StaffPanel() {
        setLayout(new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"), FormFactory.RELATED_GAP_COLSPEC, },
                new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));

        JLabel lblId = new JLabel("Staff ID");
        add(lblId, "2, 2, right, default");

        text_id = new JTextField();
        text_id.setEditable(false);
        add(text_id, "4, 2, fill, default");
        text_id.setColumns(10);

        JLabel lblName = new JLabel("Name");
        add(lblName, "2, 4, right, default");

        text_name = new JTextField();
        text_name.setEditable(false);
        add(text_name, "4, 4, fill, default");
        text_name.setColumns(10);
    }

    public void fillStaffData(Staff staff) {
        text_id.setText(String.valueOf(staff.getStaffId()));
        text_name.setText(staff.getName());
    }
}
