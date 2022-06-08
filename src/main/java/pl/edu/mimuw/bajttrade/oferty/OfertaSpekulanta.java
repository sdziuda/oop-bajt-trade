package pl.edu.mimuw.bajttrade.oferty;

import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;
import pl.edu.mimuw.bajttrade.spekulanci.Spekulant;

public class OfertaSpekulanta extends Oferta {
  private double cena;
  private Spekulant wlasciciel;

  public OfertaSpekulanta(int dzien, int ilosc, int poziom, Przedmiot przedmiot, double cena, Spekulant wlasciciel) {
    super(dzien, ilosc, poziom, przedmiot);
    this.cena = cena;
    this.wlasciciel = wlasciciel;
  }

  @Override
  public String toString() {
    return super.toString() + " cena: " + this.cena;
  }
}
