package org.ece456.proj.gui.staff;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.ece456.proj.gui.shared.widgets.DateTimePicker;
import org.ece456.proj.orm.objects.Appointment;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class AppointmentView extends JFrame implements ActionListener {

    // public interface AppointmentPresenter {
    // void viewPatient(Id<Patient> id);
    //
    // void updateAppointment();;
    // }

    private static final long serialVersionUID = 1L;

    private final JTextField textPatient;
    private final JTextField textDoctor;
    private final DateTimePicker textStartTime;
    private final JTextField textDuration;
    private final DateTimePicker textLastModified;
    private final JTextField textProcedures;
    private final JTextField textPrescriptions;
    private final JTextField textDiagnoses;
    private final JTextArea textComment;
    private final JButton btnUpdate;
    private final JButton btnCancel;

    private final AppointmentListPresenter presenter;
    private final Appointment appointment;
    private final DateFormat df = new SimpleDateFormat("MMM dd yyyy HH:mm");

    public AppointmentView(Appointment a, AppointmentListPresenter p) {
        setTitle("Appointment View");
        this.presenter = p;
        this.appointment = a;

        setPreferredSize(new Dimension(400, 400));

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
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), }));

        JLabel lblPatient = new JLabel("Patient");
        panel.add(lblPatient, "2, 2, right, default");

        textPatient = new JTextField();
        textPatient.setText("");
        textPatient.setEditable(false);
        panel.add(textPatient, "4, 2, fill, default");

        JLabel lblDoctor = new JLabel("Doctor");
        panel.add(lblDoctor, "2, 4, right, default");

        textDoctor = new JTextField();
        textDoctor.setText("");
        textDoctor.setEditable(false);
        panel.add(textDoctor, "4, 4, fill, default");
        textDoctor.setColumns(10);

        JLabel lblStartTime = new JLabel("Start Time");
        panel.add(lblStartTime, "2, 6, right, default");

        textStartTime = new DateTimePicker();
        panel.add(textStartTime, "4, 6, fill, default");

        JLabel lblDuration = new JLabel("Duration (minutes)");
        panel.add(lblDuration, "2, 8, right, default");

        textDuration = new JTextField();
        textDuration.setEditable(true);
        textDuration.setText("");
        panel.add(textDuration, "4, 8, fill, default");
        textDuration.setColumns(10);

        JLabel lblLastModified = new JLabel("Last Modified");
        panel.add(lblLastModified, "2, 10, right, default");

        textLastModified = new DateTimePicker();
        textLastModified.setToolTipText("This will be updated automatically when saving");
        panel.add(textLastModified, "4, 10, fill, default");

        JLabel lblProcedures = new JLabel("Procedures");
        panel.add(lblProcedures, "2, 12, right, default");

        textProcedures = new JTextField();
        textProcedures.setEditable(false);
        textProcedures.setText("N/A");
        panel.add(textProcedures, "4, 12, fill, default");
        textProcedures.setColumns(10);

        JLabel lblPrescriptions = new JLabel("Prescriptions");
        panel.add(lblPrescriptions, "2, 14, right, default");

        textPrescriptions = new JTextField();
        textPrescriptions.setEditable(false);
        textPrescriptions.setText("N/A");
        panel.add(textPrescriptions, "4, 14, fill, default");
        textPrescriptions.setColumns(10);

        JLabel lblDiagnoses = new JLabel("Diagnoses");
        panel.add(lblDiagnoses, "2, 16, right, default");

        textDiagnoses = new JTextField();
        textDiagnoses.setEditable(false);
        textDiagnoses.setText("N/A");
        panel.add(textDiagnoses, "4, 16, fill, default");
        textDiagnoses.setColumns(10);

        JLabel lblDoctorsComment = new JLabel("Doctor's Comment");
        panel.add(lblDoctorsComment, "2, 18, right, default");

        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane, "4, 18, fill, fill");

        textComment = new JTextArea();
        textComment.setBackground(SystemColor.menu);
        scrollPane.setViewportView(textComment);
        textComment.setEditable(false);
        textComment.setText("");
        textComment.setLineWrap(true);

        fillData(a);

        pack();
        setLocation(100, 100);
    }

    private void fillData(Appointment a) {
        if (a.getPatient() != null)
            textPatient.setText(a.getPatient().getName());
        if (a.getDoctor() != null)
            textDoctor.setText(a.getDoctor().getName());
        if (a.getStart_time() != null)
            textStartTime.setValue(a.getStart_time());
        if (a.getLength() != 0)
            textDuration.setText(String.valueOf(a.getLength()));
        if (a.getLast_modified() != null)
            textLastModified.setValue(a.getLast_modified());
        if (a.getProcedures() != null)
            textProcedures.setText(a.getProcedures());
        if (a.getPrescriptions() != null)
            textPrescriptions.setText(a.getPrescriptions());
        if (a.getDiagnoses() != null)
            textDiagnoses.setText(a.getDiagnoses());
        if (textComment != null) {
            textComment.setText(a.getComment());
        }
    }

    public void createAppointment() {
        Appointment app = new Appointment();
        Calendar c = Calendar.getInstance();

        Date now = c.getTime();
        app.setPatient(appointment.getPatient());
        app.setStart_time(textStartTime.getValue());
        app.setDiagnoses(textDiagnoses.getText());
        app.setDoctor(appointment.getDoctor());
        app.setLength(Integer.parseInt(textDuration.getText()));
        app.setProcedures(textProcedures.getText());
        app.setPrescriptions(textPrescriptions.getText());
        app.setComment(textComment.getText());
        app.setLast_modified(now);

        this.presenter.updateAppointment(app);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            this.dispose();
        } else if (e.getSource() == btnUpdate) {
            createAppointment();
            this.dispose();
        }
    }

    public Date formatter(String s) {
        try {
            Date date = df.parse(s);
            return date;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
