package pl.edu.mimuw.bajttrade.oferty;

import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;
import pl.edu.mimuw.bajttrade.agenci.robotnicy.Robotnik;

public class OfertaRobotnika extends Oferta {
  public OfertaRobotnika(int dzien, int ilosc, int poziom, Przedmiot przedmiot, Robotnik wlasciciel) {
    super(dzien, ilosc, poziom, przedmiot, wlasciciel);
  }
}
