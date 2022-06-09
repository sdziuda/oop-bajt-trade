package pl.edu.mimuw.bajttrade.zmiana;

import pl.edu.mimuw.bajttrade.gielda.Historia;
import pl.edu.mimuw.bajttrade.kariera.Kariera;
import pl.edu.mimuw.bajttrade.agenci.robotnicy.Robotnik;

public interface Zmiana {
  Kariera karieraPoZmianie(Robotnik r, Historia h, int dzien);
}
