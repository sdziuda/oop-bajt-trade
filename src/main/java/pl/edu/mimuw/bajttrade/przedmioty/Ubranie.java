package pl.edu.mimuw.bajttrade.przedmioty;

public class Ubranie {
  private final int poziom;
  private int zuzycie;

  public Ubranie(int zuzycie, int poziom) {
    this.zuzycie = zuzycie;
    this.poziom = poziom;
  }

  public int getZuzycie() {
    return zuzycie;
  }

  public int getPoziom() {
    return poziom;
  }

  public void dodajZuzycie() {
    this.zuzycie++;
  }
}
