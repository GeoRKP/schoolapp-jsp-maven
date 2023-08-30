package gr.aueb.cf.schoolapppro.dto;

public abstract class BaseDTO {
    private Integer id;

    public BaseDTO() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
