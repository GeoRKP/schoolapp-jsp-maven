package gr.aueb.cf.schoolapppro.model;

public class Speciality {
    private Integer id;
    private String specialityName;

    public Speciality() {}

    public Speciality(Integer id, String specialityName) {
        this.id = id;
        this.specialityName = specialityName;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }
}
