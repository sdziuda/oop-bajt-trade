package pl.edu.mimuw.bajttrade.oferty;

import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;
import pl.edu.mimuw.bajttrade.agenci.spekulanci.Spekulant;

public class OfertaSpekulanta extends Oferta {
  private final double cena;

  public OfertaSpekulanta(int dzien, int ilosc, int poziom, Przedmiot przedmiot, double cena, Spekulant wlasciciel) {
    super(dzien, ilosc, poziom, przedmiot, wlasciciel);
    this.cena = cena;
  }

  @Override
  public String toString() {
    return super.toString() + " cena: " + this.cena;
  }

  public double getCena() {
    return this.cena;
  }
}
