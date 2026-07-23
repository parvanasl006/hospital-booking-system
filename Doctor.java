package models;

public class Doctor {
    private int doctorId;
    private String name;
    private String specialization;
    private String email;
    private String phone;
    private int experienceYears;
    private String availabilityStatus;
    private double consultationFee;

    public Doctor(String name, String specialization, String email, String phone, 
                  int experienceYears, String availabilityStatus, double consultationFee) {
        this.name = name;
        this.specialization = specialization;
        this.email = email;
        this.phone = phone;
        this.experienceYears = experienceYears;
        this.availabilityStatus = availabilityStatus;
        this.consultationFee = consultationFee;
    }

    // Getters and Setters
    public int getDoctorId() { return doctorId; }
    public void setDoctorId(int doctorId) { this.doctorId = doctorId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public int getExperienceYears() { return experienceYears; }
    public void setExperienceYears(int experienceYears) { this.experienceYears = experienceYears; }
    
    public String getAvailabilityStatus() { return availabilityStatus; }
    public void setAvailabilityStatus(String availabilityStatus) { this.availabilityStatus = availabilityStatus; }
    
    public double getConsultationFee() { return consultationFee; }
    public void setConsultationFee(double consultationFee) { this.consultationFee = consultationFee; }
}
