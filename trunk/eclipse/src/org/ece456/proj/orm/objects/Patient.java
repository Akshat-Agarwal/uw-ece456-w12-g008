package org.ece456.proj.orm.objects;

import java.util.ArrayList;
import java.util.List;

public class Patient {

    public Id<Patient> patient_id;

    public class PatientContact {
        public String name;
        public String address;
        public String phone_num;
        public long password_hash;

        private void clear() {
            name = "";
            address = "";
            phone_num = "";
            password_hash = 0L;
        }
    }

    public class PatientMedical {
        public int sin;
        public String health_card_number;
        public int num_visits;
        public Sex sex;
        public Id<Doctor> default_doctor;
        public String current_health;
        public List<Id<Doctor>> consultants;

        public PatientMedical() {
            consultants = new ArrayList<Id<Doctor>>();

            clear();
        }

        private void clear() {
            sin = 0;
            health_card_number = "";
            num_visits = 0;
            sex = Sex.MALE;
            default_doctor = Id.invalidId();
            current_health = "";
            consultants.clear();
        }
    }

    public final PatientContact contact;
    public final PatientMedical medical;

    public Patient() {
        this.contact = new PatientContact();
        this.medical = new PatientMedical();

        clear();
    }

    private void clear() {
        contact.clear();
        medical.clear();
    }
}
