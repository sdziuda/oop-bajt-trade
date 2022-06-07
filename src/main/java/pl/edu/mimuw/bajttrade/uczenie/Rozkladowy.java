package pl.edu.mimuw.bajttrade.uczenie;

import pl.edu.mimuw.bajttrade.Historia;
import pl.edu.mimuw.bajttrade.robotnicy.Robotnik;

import java.util.Random;

public class Rozkladowy implements Uczenie {
  @Override
  public boolean czySieUczy(Robotnik r, Historia h, int dzien) {
    return new Random().nextInt(dzien + 3) == 0;
  }

  @Override
  public String toString() {
    return "rozkladowy";
  }
}
