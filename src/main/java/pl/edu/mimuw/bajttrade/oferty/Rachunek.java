package pl.edu.mimuw.bajttrade.oferty;

import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;

public class Rachunek {
  private final int dzien;
  private final int ilosc;
  private final int cena;
  private final Przedmiot przedmiot;

  public Rachunek(int dzien, int ilosc, int cena, Przedmiot przedmiot) {
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

  public int getCena() {
    return cena;
  }

  public Przedmiot getPrzedmiot() {
    return przedmiot;
  }
}
