package pl.edu.mimuw.bajttrade.oferty;

import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;
import pl.edu.mimuw.bajttrade.robotnicy.Robotnik;

public class OfertaRobotnika extends Oferta {
  private Robotnik wlasciciel;

  public OfertaRobotnika(int dzien, int ilosc, int poziom, Przedmiot przedmiot, Robotnik wlasciciel) {
    super(dzien, ilosc, poziom, przedmiot);
    this.wlasciciel = wlasciciel;
  }
}
