package gr.aueb.cf.schoolapppro.dto;

public class SpecialityInsertDTO {
    private String specialityName;

    public SpecialityInsertDTO() {}

    public SpecialityInsertDTO(String specialityName) {
        this.specialityName = specialityName;
    }

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }
}
