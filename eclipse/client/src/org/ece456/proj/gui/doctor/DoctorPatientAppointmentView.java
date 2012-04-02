package org.ece456.proj.gui.doctor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import org.ece456.proj.gui.shared.table.SimpleTable;
import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Patient;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class DoctorPatientAppointmentView extends JFrame implements ActionListener {

    public interface AppointmentPresenter {
        void viewPatient(Id<Patient> id);
    }

    private static final long serialVersionUID = 1L;
    private final JButton btnUpdate;
    private final JButton btnCancel;
    private final JLabel btnPatient;

    private final JTextField textStartTime;
    private final JTextField textDuration;
    private final JTextField textLastModified;
    private final JTextField textProcedures;
    private final JTextField textPrescriptions;
    private final JTextField textDiagnoses;

    private final PatientDoctorPresenter presenter;

    private final Appointment appointment;
    private final JTextPane textComment;

    public DoctorPatientAppointmentView(Appointment a, PatientDoctorPresenter presenter) {
        setTitle("Appointment View");
        this.presenter = presenter;
        this.appointment = a;

        JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.SOUTH);

        JSplitPane splitPane = new JSplitPane();
        panel_1.add(splitPane);

        btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(this);
        splitPane.setLeftComponent(btnUpdate);

        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(this);
        splitPane.setRightComponent(btnCancel);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"), FormFactory.RELATED_GAP_COLSPEC, },
                new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"),
                        FormFactory.RELATED_GAP_ROWSPEC, }));

        JLabel lblPatient = new JLabel("Patient");
        panel.add(lblPatient, "2, 2, right, default");

        btnPatient = new JLabel("");
        panel.add(btnPatient, "4, 2");

        JLabel lblStartTime = new JLabel("Start Time");
        panel.add(lblStartTime, "2, 4, right, default");

        textStartTime = new JTextField();
        textStartTime.setEditable(false);
        panel.add(textStartTime, "4, 4, fill, default");
        textStartTime.setColumns(10);

        JLabel lblDuration = new JLabel("Duration (minutes)");
        panel.add(lblDuration, "2, 6, right, default");

        textDuration = new JTextField();
        textDuration.setEditable(true);
        panel.add(textDuration, "4, 6, fill, default");
        textDuration.setColumns(10);

        JLabel lblLastModified = new JLabel("Last Modified");
        panel.add(lblLastModified, "2, 8, right, default");

        textLastModified = new JTextField();
        textLastModified.setEditable(false);
        panel.add(textLastModified, "4, 8, fill, default");
        textLastModified.setColumns(10);

        JLabel lblProcedures = new JLabel("Procedures");
        panel.add(lblProcedures, "2, 10, right, default");

        textProcedures = new JTextField();
        textProcedures.setEditable(true);
        panel.add(textProcedures, "4, 10, fill, default");
        textProcedures.setColumns(10);

        JLabel lblPrescriptions = new JLabel("Prescriptions");
        panel.add(lblPrescriptions, "2, 12, right, default");

        textPrescriptions = new JTextField();
        textPrescriptions.setEditable(true);
        panel.add(textPrescriptions, "4, 12, fill, default");
        textPrescriptions.setColumns(10);

        JLabel lblDiagnoses = new JLabel("Diagnoses");
        panel.add(lblDiagnoses, "2, 14, right, default");

        textDiagnoses = new JTextField();
        textDiagnoses.setEditable(true);
        panel.add(textDiagnoses, "4, 14, fill, default");
        textDiagnoses.setColumns(10);

        JLabel lblDoctorsComment = new JLabel("Doctor's Comment");
        panel.add(lblDoctorsComment, "2, 16, right, default");

        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane, "4, 16, fill, fill");

        textComment = new JTextPane();
        scrollPane.setViewportView(textComment);

        fillData(a);

        setPreferredSize(new Dimension(400, 400));
        pack();
        setLocation(100, 100);
    }

    private void fillData(Appointment a) {
        btnPatient.setText(a.getPatient().getName());
        textStartTime.setText(SimpleTable.DATE_FORMAT.format(a.getStart_time()));
        textDuration.setText(String.valueOf(a.getLength()));
        textLastModified.setText(SimpleTable.DATE_FORMAT.format(a.getLast_modified()));
        textProcedures.setText(a.getProcedures());
        textPrescriptions.setText(a.getPrescriptions());
        textDiagnoses.setText(a.getDiagnoses());
        textComment.setText(a.getComment());
    }

    public void saveAppointment() {
        Calendar calendar = Calendar.getInstance();
        Appointment app = new Appointment();
        app.setComment(textComment.getText());
        app.setDiagnoses(textDiagnoses.getText());
        app.setLast_modified(calendar.getTime());
        app.setDoctor(appointment.getDoctor());
        app.setLength(Integer.parseInt(textDuration.getText()));
        app.setPatient(appointment.getPatient());
        app.setPrescriptions(textPrescriptions.getText());
        app.setProcedures(textProcedures.getText());
        app.setStart_time(appointment.getStart_time());

        this.presenter.saveAppointment(app);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnUpdate) {
            saveAppointment();
        } else if (e.getSource() == btnCancel) {
            this.dispose();

        } else if (e.getSource() == btnPatient) {
            if (presenter != null) {
                presenter.viewPatient(appointment.getPatient().getPatientId());
            }
        }
    }
}
