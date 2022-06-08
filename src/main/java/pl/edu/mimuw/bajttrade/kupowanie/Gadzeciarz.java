package pl.edu.mimuw.bajttrade.kupowanie;

import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;
import pl.edu.mimuw.bajttrade.oferty.Oferta;
import pl.edu.mimuw.bajttrade.oferty.OfertaRobotnika;
import pl.edu.mimuw.bajttrade.robotnicy.Robotnik;

import java.util.ArrayList;
import java.util.List;

public class Gadzeciarz implements Kupowanie {
  private final int liczbaNarzedzi;

  public Gadzeciarz(int liczbaNarzedzi) {
    this.liczbaNarzedzi = liczbaNarzedzi;
  }

  @Override
  public List<Oferta> coKupuje(Robotnik r, int dzien) {
    var wynik = new ArrayList<Oferta>();

    wynik.add(new OfertaRobotnika(dzien, 100, 1, Przedmiot.JEDZENIE, r));
    wynik.add(new OfertaRobotnika(dzien, liczbaNarzedzi, 1, Przedmiot.NARZEDZIA, r));
    if (r.getIloscZasobow(Przedmiot.UBRANIA) < 200) {
      wynik.add(new OfertaRobotnika(dzien, 200 - r.getIloscZasobow(Przedmiot.UBRANIA), 1, Przedmiot.UBRANIA, r));
    }
    if (r.getIloscWyprodukowanych() > 1) {
      wynik.add(new OfertaRobotnika(dzien, r.getIloscWyprodukowanych(), 1, Przedmiot.PROGRAMY, r));
    }

    return wynik;
  }

  @Override
  public String toString() {
    return "gadzeciarz";
  }
}
