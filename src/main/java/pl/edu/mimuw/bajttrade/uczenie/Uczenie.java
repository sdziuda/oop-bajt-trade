package pl.edu.mimuw.bajttrade.uczenie;

import pl.edu.mimuw.bajttrade.gielda.Historia;
import pl.edu.mimuw.bajttrade.gielda.Info;
import pl.edu.mimuw.bajttrade.agenci.robotnicy.Robotnik;

public interface Uczenie {
  boolean czySieUczy(Robotnik r, Historia h, Info info, int dzien);

  default int getOkresowoscNauki() {
    return 0;
  }

  default int getLimitDiamentow() {
    return 0;
  }

  default int getZapas() {
    return 0;
  }

  default int getOkres() {
    return 0;
  }
}
