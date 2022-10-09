package pl.kurs.test3advancedlevel.dto;

public class JobSimpleDto {

    private String uuid;

    public JobSimpleDto() {
    }

    public JobSimpleDto(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
