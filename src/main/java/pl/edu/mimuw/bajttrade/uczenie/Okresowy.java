package pl.edu.mimuw.bajttrade.uczenie;

import pl.edu.mimuw.bajttrade.Historia;
import pl.edu.mimuw.bajttrade.robotnicy.Robotnik;

public class Okresowy implements Uczenie {
  private final int okresowoscNauki;

  public Okresowy(int okresowoscNauki) {
    this.okresowoscNauki = okresowoscNauki;
  }

  @Override
  public boolean czySieUczy(Robotnik r, Historia h, int dzien) {
    return dzien % okresowoscNauki == 0;
  }

  @Override
  public String toString() {
    return "okresowy";
  }
}
