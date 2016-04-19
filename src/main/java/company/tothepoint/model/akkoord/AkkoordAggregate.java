package company.tothepoint.model.akkoord;

import company.tothepoint.model.bestelbon.Bestelbon;
import company.tothepoint.model.opdracht.Opdracht;

import java.util.List;

public class AkkoordAggregate {
    private Akkoord akkoord;
    private Opdracht opdracht;
    private List<Bestelbon> bestelbonnen;

    public AkkoordAggregate() {
    }

    public AkkoordAggregate(Akkoord akkoord, Opdracht opdracht, List<Bestelbon> bestelbonnen) {
        this.akkoord = akkoord;
        this.opdracht = opdracht;
        this.bestelbonnen = bestelbonnen;
    }

    public Akkoord getAkkoord() {
        return akkoord;
    }

    public void setAkkoord(Akkoord akkoord) {
        this.akkoord = akkoord;
    }

    public Opdracht getOpdracht() {
        return opdracht;
    }

    public void setOpdracht(Opdracht opdracht) {
        this.opdracht = opdracht;
    }

    public List<Bestelbon> getBestelbonnen() {
        return bestelbonnen;
    }

    public void setBestelbonnen(List<Bestelbon> bestelbonnen) {
        this.bestelbonnen = bestelbonnen;
    }
}
