package pl.edu.mimuw.bajttrade.zmiana;

import pl.edu.mimuw.bajttrade.Historia;
import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;
import pl.edu.mimuw.bajttrade.robotnicy.Robotnik;

public class Konserwatysta implements Zmiana {
  @Override
  public Przedmiot karieraPoZmianie(Robotnik r, Historia h, int dzien) {
    return r.getAktywnaKariera().getPremiowanyPrzedmiot();
  }

  @Override
  public String toString() {
    return "konserwatysta";
  }
}
