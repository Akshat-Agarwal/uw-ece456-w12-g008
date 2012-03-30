package org.ece456.proj.gui.doctor;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.ece456.proj.orm.objects.Doctor;
import org.ece456.proj.orm.objects.Id;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class DoctorContactPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private final JTextField text_id;
    private final JTextField text_name;

    public DoctorContactPanel() {
        setLayout(new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"), FormFactory.RELATED_GAP_COLSPEC, },
                new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));

        JLabel lblId = new JLabel("Doctor ID");
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

    public void fillDoctorData(Id<Doctor> id, Doctor doctor) {
        text_id.setText(String.valueOf(id));
        text_name.setText(doctor.getName());
    }

    /**
     * A patient can modify his own address and phone.
     */

    public Doctor getData() {
        Doctor c = new Doctor();
        c.setName(text_name.getName());
        return c;
    }

    public void setEditableByDoctor(boolean b) {
        // TODO Auto-generated method stub

    }
}
