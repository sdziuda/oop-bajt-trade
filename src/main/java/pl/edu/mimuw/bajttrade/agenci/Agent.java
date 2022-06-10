package pl.edu.mimuw.bajttrade.agenci;

import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;
import pl.edu.mimuw.bajttrade.przedmioty.Zasoby;

public abstract class Agent {
  protected int id;
  protected Zasoby zasoby;

  protected Agent(int id, Zasoby zasoby) {
    this.id = id;
    this.zasoby = zasoby;
  }

  public int getId() {
    return this.id;
  }

  public int getIloscZasobow(Przedmiot p) {
    return this.zasoby.getIloscZasobow(p);
  }

  public double getDiamenty() {
    return this.zasoby.getDiamenty();
  }

  public void odejmijDiamenty(double i) {
    this.zasoby.odejmijDiamenty(i);
  }

  public void dodajDiamenty(double v) {
    this.zasoby.dodajDiamenty(v);
  }

  public void dodajZasob(Przedmiot przedmiot, int ile, int poziom) {
    this.zasoby.dodajZasob(przedmiot, ile, poziom);
  }

  public void odejmijZasob(Przedmiot przedmiot, int ile, int poziom) {
    this.zasoby.odejmijZasob(przedmiot, ile, poziom);
  }

  public Zasoby getZasoby() {
    return this.zasoby;
  }
}
