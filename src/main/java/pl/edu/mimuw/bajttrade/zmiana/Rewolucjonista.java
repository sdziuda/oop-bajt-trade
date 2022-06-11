package pl.edu.mimuw.bajttrade.zmiana;

import pl.edu.mimuw.bajttrade.gielda.Historia;
import pl.edu.mimuw.bajttrade.kariera.*;
import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;
import pl.edu.mimuw.bajttrade.agenci.robotnicy.Robotnik;

public class Rewolucjonista implements Zmiana {
  @Override
  public Kariera karieraPoZmianie(Robotnik r, Historia h, int dzien) {
    if (dzien % 7 == 0) {
      Przedmiot najczesciej = h.getNajczesciejWystepujacy(Math.max(r.getId() % 17, 1), dzien);

      switch (najczesciej) {
        case DIAMENTY:
          return new Gornik();
        case UBRANIA:
          return new Rzemieslnik();
        case JEDZENIE:
          return new Rolnik();
        case PROGRAMY:
          return new Programista();
        case NARZEDZIA:
          return new Inzynier();
        default:
          throw new RuntimeException("Nieznany przedmiot");
      }
    } else {
      return r.getAktywnaKariera();
    }
  }

  @Override
  public String toString() {
    return "rewolucjonista";
  }
}
