package pl.edu.mimuw.bajttrade.oferty;

import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;

public class Rachunek {
  private final int dzien;
  private final int ilosc;
  private final double cena;
  private final Przedmiot przedmiot;

  public Rachunek(int dzien, int ilosc, double cena, Przedmiot przedmiot) {
    this.dzien = dzien;
    this.ilosc = ilosc;
    this.cena = cena;
    this.przedmiot = przedmiot;
  }

  public int getDzien() {
    return dzien;
  }

  public int getIlosc() {
    return ilosc;
  }

  public double getCena() {
    return cena;
  }

  public Przedmiot getPrzedmiot() {
    return przedmiot;
  }
}
