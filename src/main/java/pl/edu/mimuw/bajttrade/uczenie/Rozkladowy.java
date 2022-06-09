package pl.edu.mimuw.bajttrade.uczenie;

import pl.edu.mimuw.bajttrade.gielda.Historia;
import pl.edu.mimuw.bajttrade.gielda.Info;
import pl.edu.mimuw.bajttrade.agenci.robotnicy.Robotnik;

import java.util.Random;

public class Rozkladowy implements Uczenie {
  @Override
  public boolean czySieUczy(Robotnik r, Historia h, Info info, int dzien) {
    return new Random().nextInt(dzien + 3) == 0;
  }

  @Override
  public String toString() {
    return "rozkladowy";
  }
}
