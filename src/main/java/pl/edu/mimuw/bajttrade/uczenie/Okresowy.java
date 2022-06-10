package pl.edu.mimuw.bajttrade.uczenie;

import pl.edu.mimuw.bajttrade.gielda.Historia;
import pl.edu.mimuw.bajttrade.gielda.Info;
import pl.edu.mimuw.bajttrade.agenci.robotnicy.Robotnik;

public class Okresowy implements Uczenie {
  private final int okresowoscNauki;

  public Okresowy(int okresowoscNauki) {
    this.okresowoscNauki = okresowoscNauki;
  }

  @Override
  public boolean czySieUczy(Robotnik r, Historia h, Info info, int dzien) {
    return dzien % okresowoscNauki == 0;
  }

  @Override
  public int getOkresowoscNauki() {
    return okresowoscNauki;
  }

  @Override
  public String toString() {
    return "okresowy";
  }
}
