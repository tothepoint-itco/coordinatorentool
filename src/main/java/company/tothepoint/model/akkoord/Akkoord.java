package company.tothepoint.model.akkoord;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class Akkoord {
    @Id
    private String id;

    private String projectCode;
    private String opdrachtId;
    private String consultantId;

    public String getOpdrachtId() {
        return opdrachtId;
    }

    private LocalDate informeelEindDatum;
    private LocalDate informeelStartDatum;

    public Akkoord() {
    }

    public Akkoord(String projectCode, String opdrachtId, String consultantId, LocalDate informeelEindDatum, LocalDate informeelStartDatum) {
        this.projectCode = projectCode;
        this.opdrachtId = opdrachtId;
        this.consultantId = consultantId;
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

    public void setOpdrachtId(String opdrachtId) {
        this.opdrachtId = opdrachtId;
    }

    public String getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
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
