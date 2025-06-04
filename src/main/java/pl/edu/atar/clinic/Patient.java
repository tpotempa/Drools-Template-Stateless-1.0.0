package pl.edu.atar.clinic;

public class Patient implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String firstName;
    private String lastName;
    private String sex;
    private Double temperature = 0.0;
    private Boolean isHealthy = null;
    private transient StringBuilder diagnosis = new StringBuilder();
    private transient StringBuilder logger = new StringBuilder();

    public Patient() {
    }

    public Patient(Long id, String firstName,
                   String lastName, String sex, Double temperature) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.temperature = temperature;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Boolean isHealthy() {
        return this.isHealthy;
    }

    public void setHealthy(Boolean isHealthy) {
        this.isHealthy = isHealthy;
    }


    public void appendLogger(String logger) {
        if (logger != null && !logger.isEmpty()) {
            this.logger.append(logger).append("\n");
        }
    }

    public String getLogger() {
        return this.logger.toString();
    }

    public void setLogger(String logger) {
        this.logger = new StringBuilder(logger);
    }

    public String getDiagnosis() {
        return this.diagnosis.toString();
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = new StringBuilder(diagnosis);
    }

    public String getPatientInformationLogger() {
        String reasoningLogger = (this.logger.isEmpty()) ? "\n" : "\n\nRULES FIRED WITH THE FOLLOWING EXECUTION ORDER:\n" + this.logger;
        String healthy = (this.isHealthy == null) ? "UNKNOWN" : this.isHealthy.toString();
        String diagnosis = (this.diagnosis.isEmpty()) ? "None" : this.diagnosis.toString();

        return  "First & last name & sex: " + this.firstName + " " + this.lastName + " (" + this.sex + ")" +
                "\n⮕ Healthy: " + healthy +
                "\n⮕ Diagnosis: " + diagnosis +
                "\nObject reference: " + Integer.toHexString(System.identityHashCode(this)) +
                reasoningLogger;
    }
}