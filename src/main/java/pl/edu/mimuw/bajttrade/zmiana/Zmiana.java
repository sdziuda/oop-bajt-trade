package pl.edu.mimuw.bajttrade.zmiana;

import pl.edu.mimuw.bajttrade.Historia;
import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;
import pl.edu.mimuw.bajttrade.robotnicy.Robotnik;

public interface Zmiana {
  Przedmiot karieraPoZmianie(Robotnik r, Historia h, int dzien);
}
