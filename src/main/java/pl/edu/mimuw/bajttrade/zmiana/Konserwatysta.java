package pl.edu.mimuw.bajttrade.zmiana;

import pl.edu.mimuw.bajttrade.gielda.Historia;
import pl.edu.mimuw.bajttrade.kariera.Kariera;
import pl.edu.mimuw.bajttrade.robotnicy.Robotnik;

public class Konserwatysta implements Zmiana {
  @Override
  public Kariera karieraPoZmianie(Robotnik r, Historia h, int dzien) {
    return r.getAktywnaKariera();
  }

  @Override
  public String toString() {
    return "konserwatysta";
  }
}
