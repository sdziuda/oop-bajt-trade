package pl.edu.mimuw.bajttrade.uczenie;

import pl.edu.mimuw.bajttrade.gielda.Historia;
import pl.edu.mimuw.bajttrade.gielda.Info;
import pl.edu.mimuw.bajttrade.agenci.robotnicy.Robotnik;

public class Pracus implements Uczenie {
  @Override
  public boolean czySieUczy(Robotnik r, Historia h, Info info, int dzien) {
    return false;
  }

  @Override
  public String toString() {
    return "pracus";
  }
}
