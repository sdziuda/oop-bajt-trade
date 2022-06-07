package pl.edu.mimuw.bajttrade.uczenie;

import pl.edu.mimuw.bajttrade.Historia;
import pl.edu.mimuw.bajttrade.robotnicy.Robotnik;

public interface Uczenie {
  boolean czySieUczy(Robotnik r, Historia h, int dzien);
}
