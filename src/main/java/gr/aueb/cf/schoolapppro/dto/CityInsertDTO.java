package gr.aueb.cf.schoolapppro.dto;

public class CityInsertDTO {
    private String cityName;

    public CityInsertDTO() {}

    public CityInsertDTO(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
