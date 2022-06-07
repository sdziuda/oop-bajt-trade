package pl.edu.mimuw.bajttrade.przedmioty;

public class Ubranie {
  private int zuzycie;
  private final int poziom;

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
