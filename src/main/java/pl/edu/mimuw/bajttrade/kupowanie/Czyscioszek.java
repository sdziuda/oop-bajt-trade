package pl.edu.mimuw.bajttrade.kupowanie;

import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;
import pl.edu.mimuw.bajttrade.oferty.Oferta;
import pl.edu.mimuw.bajttrade.oferty.OfertaRobotnika;
import pl.edu.mimuw.bajttrade.agenci.robotnicy.Robotnik;

import java.util.ArrayList;
import java.util.List;

public class Czyscioszek implements Kupowanie {
  @Override
  public List<Oferta> coKupuje(Robotnik r, int dzien) {
    var wynik = new ArrayList<Oferta>();

    wynik.add(new OfertaRobotnika(dzien, 100, 1, Przedmiot.JEDZENIE, r));
    if (r.getIloscZasobow(Przedmiot.UBRANIA) < 200) {
      wynik.add(new OfertaRobotnika(dzien, 200 - r.getIloscZasobow(Przedmiot.UBRANIA), 1, Przedmiot.UBRANIA, r));
    }

    return wynik;
  }

  @Override
  public String toString() {
    return "czyscioszek";
  }
}
