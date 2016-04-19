package company.tothepoint.model.akkoord;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class Akkoord {
    @Id
    private String id;

    private String projectCode;
    private LocalDate informeelEindDatum;
    private LocalDate informeelStartDatum;

    public Akkoord() {
    }

    public Akkoord(String projectCode, LocalDate informeelEindDatum, LocalDate informeelStartDatum) {
        this.projectCode = projectCode;
        this.informeelEindDatum = informeelEindDatum;
        this.informeelStartDatum = informeelStartDatum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public LocalDate getInformeelEindDatum() {
        return informeelEindDatum;
    }

    public void setInformeelEindDatum(LocalDate informeelEindDatum) {
        this.informeelEindDatum = informeelEindDatum;
    }

    public LocalDate getInformeelStartDatum() {
        return informeelStartDatum;
    }

    public void setInformeelStartDatum(LocalDate informeelStartDatum) {
        this.informeelStartDatum = informeelStartDatum;
    }
}
