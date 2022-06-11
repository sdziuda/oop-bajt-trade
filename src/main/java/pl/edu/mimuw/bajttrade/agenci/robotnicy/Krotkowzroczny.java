package pl.edu.mimuw.bajttrade.agenci.robotnicy;

import pl.edu.mimuw.bajttrade.gielda.Historia;
import pl.edu.mimuw.bajttrade.gielda.Info;
import pl.edu.mimuw.bajttrade.kariera.Kariera;
import pl.edu.mimuw.bajttrade.kupowanie.Kupowanie;
import pl.edu.mimuw.bajttrade.przedmioty.Produktywnosc;
import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;
import pl.edu.mimuw.bajttrade.przedmioty.Zasoby;
import pl.edu.mimuw.bajttrade.uczenie.Uczenie;
import pl.edu.mimuw.bajttrade.zmiana.Zmiana;

public class Krotkowzroczny extends Robotnik {
  public Krotkowzroczny(int id, int poziom, Kariera kariera, Kupowanie kupowanie, Uczenie uczenie, Zmiana zmiana,
                        Produktywnosc produktywnosc, Zasoby zasoby) {
    super(id, poziom, kariera, kupowanie, uczenie, zmiana, produktywnosc, zasoby);
  }

  @Override
  public Przedmiot coProdukuje(Historia h, Info info, int dzien) {
    double maks = 0;
    Przedmiot wynik = null;

    for (var p : Przedmiot.values()) {
      if (h.getSredniaCenaDanegoDnia(dzien - 1, info, p) > maks) {
        maks = h.getSredniaCenaDanegoDnia(dzien - 1, info, p);
        wynik = p;
      }
    }

    return wynik;
  }

  @Override
  public String toString() {
    return "krotkowzroczny";
  }
}
