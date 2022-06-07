package pl.edu.mimuw.bajttrade.oferty;

import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;

public class OfertaSpekulanta extends Oferta {
  private double cena;

  public OfertaSpekulanta(int dzien, int ilosc, Przedmiot przedmiot, double cena) {
    super(dzien, ilosc, przedmiot);
    this.cena = cena;
  }
}
