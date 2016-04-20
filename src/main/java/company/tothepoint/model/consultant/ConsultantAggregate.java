package company.tothepoint.model.consultant;

import company.tothepoint.model.akkoord.AkkoordAggregate;

import java.util.List;

public class ConsultantAggregate {
    private Consultant consultant;
    private List<AkkoordAggregate> akkoorden;

    public ConsultantAggregate() {
    }

    public ConsultantAggregate(Consultant consultant, List<AkkoordAggregate> akkoorden) {
        this.consultant = consultant;
        this.akkoorden = akkoorden;
    }

    public Consultant getConsultant() {
        return consultant;
    }

    public void setConsultant(Consultant consultant) {
        this.consultant = consultant;
    }

    public List<AkkoordAggregate> getAkkoorden() {
        return akkoorden;
    }

    public void setAkkoorden(List<AkkoordAggregate> akkoorden) {
        this.akkoorden = akkoorden;
    }
}