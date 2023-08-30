package gr.aueb.cf.schoolapppro.dto;

public class SpecialityUpdateDTO extends BaseDTO {
    private String specialityName;

    public SpecialityUpdateDTO() {}

    public SpecialityUpdateDTO(String specialityName, Integer id) {
        this.setId(id);
        this.specialityName = specialityName;
    }

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }
}
