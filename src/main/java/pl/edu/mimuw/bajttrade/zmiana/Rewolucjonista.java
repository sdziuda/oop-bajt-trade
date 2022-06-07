package pl.edu.mimuw.bajttrade.zmiana;

import pl.edu.mimuw.bajttrade.Historia;
import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;
import pl.edu.mimuw.bajttrade.robotnicy.Robotnik;

public class Rewolucjonista implements Zmiana {
  @Override
  public Przedmiot karieraPoZmianie(Robotnik r, Historia h, int dzien) {
    return h.getNajczesciejWystepujacy(r.getId() % 17, dzien);
  }

  @Override
  public String toString() {
    return "rewolucjonista";
  }
}
