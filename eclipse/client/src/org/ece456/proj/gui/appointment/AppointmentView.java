package org.ece456.proj.gui.appointment;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.ece456.proj.gui.shared.table.SimpleTable;
import org.ece456.proj.orm.objects.Appointment;
import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.Patient;
import org.ece456.proj.orm.objects.UserRole;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class AppointmentView extends JFrame implements ActionListener {

    public interface AppointmentPresenter {
        void viewPatient(Id<Patient> id);
    }

    private static final long serialVersionUID = 1L;

    private JButton textPatient;
    private JTextField textDoctor;
    private final JTextField textStartTime;
    private final JTextField textDuration;
    private final JTextField textLastModified;
    private final JTextField textProcedures;
    private final JTextField textPrescriptions;
    private final JTextField textDiagnoses;

    private JTextArea textComment;

    private final AppointmentPresenter presenter;

    private final Appointment appointment;

    public AppointmentView(Appointment a, UserRole role, AppointmentPresenter presenter) {
        setTitle("Appointment View");
        this.presenter = presenter;
        this.appointment = a;

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
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), }));

        if (a.getPatient() != null && role != UserRole.PATIENT) {
            JLabel lblPatient = new JLabel("Patient");
            panel.add(lblPatient, "2, 2, right, default");

            textPatient = new JButton();
            textPatient.setText("View this patient");
            textPatient.addActionListener(this);
            panel.add(textPatient, "4, 2, fill, default");
        }

        if (a.getDoctor() != null) {
            JLabel lblDoctor = new JLabel("Doctor");
            panel.add(lblDoctor, "2, 4, right, default");

            textDoctor = new JTextField();
            textDoctor.setEditable(false);
            panel.add(textDoctor, "4, 4, fill, default");
            textDoctor.setColumns(10);
        }

        JLabel lblStartTime = new JLabel("Start Time");
        panel.add(lblStartTime, "2, 6, right, default");

        textStartTime = new JTextField();
        textStartTime.setEditable(false);
        panel.add(textStartTime, "4, 6, fill, default");
        textStartTime.setColumns(10);

        JLabel lblDuration = new JLabel("Duration (minutes)");
        panel.add(lblDuration, "2, 8, right, default");

        textDuration = new JTextField();
        textDuration.setEditable(false);
        panel.add(textDuration, "4, 8, fill, default");
        textDuration.setColumns(10);

        JLabel lblLastModified = new JLabel("Last Modified");
        panel.add(lblLastModified, "2, 10, right, default");

        textLastModified = new JTextField();
        textLastModified.setEditable(false);
        panel.add(textLastModified, "4, 10, fill, default");
        textLastModified.setColumns(10);

        JLabel lblProcedures = new JLabel("Procedures");
        panel.add(lblProcedures, "2, 12, right, default");

        textProcedures = new JTextField();
        textProcedures.setEditable(false);
        panel.add(textProcedures, "4, 12, fill, default");
        textProcedures.setColumns(10);

        JLabel lblPrescriptions = new JLabel("Prescriptions");
        panel.add(lblPrescriptions, "2, 14, right, default");

        textPrescriptions = new JTextField();
        textPrescriptions.setEditable(false);
        panel.add(textPrescriptions, "4, 14, fill, default");
        textPrescriptions.setColumns(10);

        JLabel lblDiagnoses = new JLabel("Diagnoses");
        panel.add(lblDiagnoses, "2, 16, right, default");

        textDiagnoses = new JTextField();
        textDiagnoses.setEditable(false);
        panel.add(textDiagnoses, "4, 16, fill, default");
        textDiagnoses.setColumns(10);

        boolean canViewComments = role != UserRole.PATIENT;
        if (canViewComments) {
            JLabel lblDoctorsComment = new JLabel("Doctor's Comment");
            panel.add(lblDoctorsComment, "2, 18, right, default");

            JScrollPane scrollPane = new JScrollPane();
            panel.add(scrollPane, "4, 18, fill, fill");

            textComment = new JTextArea();
            scrollPane.setViewportView(textComment);
            textComment.setEditable(false);
            textComment.setLineWrap(true);
        }

        fillData(a);

        setPreferredSize(new Dimension(400, 300));
        pack();
        setLocation(100, 100);
    }

    private void fillData(Appointment a) {
        if (textPatient != null) {
            textPatient.setText(a.getPatient().getName());
        }
        if (textDoctor != null) {
            textDoctor.setText(a.getDoctor().getName());
        }
        textStartTime.setText(SimpleTable.DATE_FORMAT.format(a.getStart_time()));
        textDuration.setText(String.valueOf(a.getLength()));
        textLastModified.setText(SimpleTable.DATE_FORMAT.format(a.getLast_modified()));
        textProcedures.setText(a.getProcedures());
        textPrescriptions.setText(a.getPrescriptions());
        textDiagnoses.setText(a.getDiagnoses());
        if (textComment != null) {
            textComment.setText(a.getComment());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == textPatient) {
            if (presenter != null) {
                presenter.viewPatient(appointment.getPatient().getPatientId());
            }
        }
    }
}
