package pl.edu.mimuw.bajttrade.oferty;

import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;

public abstract class Oferta {
  private final int dzien;
  private final int ilosc;
  private final Przedmiot przedmiot;

  protected Oferta(int dzien, int ilosc, Przedmiot przedmiot) {
    this.dzien = dzien;
    this.ilosc = ilosc;
    this.przedmiot = przedmiot;
  }

  public int getDzien() {
    return dzien;
  }

  public int getIlosc() {
    return ilosc;
  }

  public Przedmiot getPrzedmiot() {
    return przedmiot;
  }
}
