package gr.aueb.cf.schoolapppro.dto;

public class CityUpdateDTO extends BaseDTO {
    private String cityName;

    public CityUpdateDTO() {}

    public CityUpdateDTO(String cityName, Integer id) {
        this.setId(id);
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
