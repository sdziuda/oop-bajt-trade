package pl.edu.mimuw.bajttrade.uczenie;

import pl.edu.mimuw.bajttrade.gielda.Historia;
import pl.edu.mimuw.bajttrade.gielda.Info;
import pl.edu.mimuw.bajttrade.robotnicy.Robotnik;

public interface Uczenie {
  boolean czySieUczy(Robotnik r, Historia h, Info info, int dzien);
}
