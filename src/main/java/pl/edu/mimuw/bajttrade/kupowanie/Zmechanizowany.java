package pl.edu.mimuw.bajttrade.kupowanie;

import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;
import pl.edu.mimuw.bajttrade.oferty.Oferta;
import pl.edu.mimuw.bajttrade.oferty.OfertaRobotnika;
import pl.edu.mimuw.bajttrade.robotnicy.Robotnik;

import java.util.ArrayList;
import java.util.List;

public class Zmechanizowany implements Kupowanie {
  private final int liczbaNarzedzi;

  public Zmechanizowany(int liczbaNarzedzi) { this.liczbaNarzedzi = liczbaNarzedzi; }

  @Override
  public List<Oferta> coKupuje(Robotnik r, int dzien) {
    var wynik = new ArrayList<Oferta>();

    wynik.add(new OfertaRobotnika(dzien, 100, Przedmiot.JEDZENIE));
    wynik.add(new OfertaRobotnika(dzien, liczbaNarzedzi, Przedmiot.NARZEDZIA));
    if (r.getIloscZasobow(Przedmiot.UBRANIA) < 200) {
      wynik.add(new OfertaRobotnika(dzien, 200 - r.getIloscZasobow(Przedmiot.UBRANIA), Przedmiot.UBRANIA));
    }

    return wynik;
  }

  @Override
  public String toString() {
    return "zmechanizowany";
  }
}
