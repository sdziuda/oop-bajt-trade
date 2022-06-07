package pl.edu.mimuw.bajttrade.uczenie;

import pl.edu.mimuw.bajttrade.Historia;
import pl.edu.mimuw.bajttrade.robotnicy.Robotnik;

public class Pracus implements Uczenie {
  @Override
  public boolean czySieUczy(Robotnik r, Historia h, int dzien) {
    return false;
  }

  @Override
  public String toString() {
    return "pracus";
  }
}
