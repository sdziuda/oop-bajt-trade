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

public class Perspektywiczny extends Robotnik {
  private int historiaPerspektywy;

  public Perspektywiczny(int id, int poziom, Kariera kariera, Kupowanie kupowanie, Uczenie uczenie, Zmiana zmiana,
                         Produktywnosc produktywnosc, Zasoby zasoby, int historiaPerspektywy) {
    super(id, poziom, kariera, kupowanie, uczenie, zmiana, produktywnosc, zasoby);
    this.historiaPerspektywy = historiaPerspektywy;
  }

  @Override
  public Przedmiot coProdukuje(Historia h, Info info, int dzien) {
    double maksimum = Double.NEGATIVE_INFINITY;
    Przedmiot wynik = null;

    for (var p : Przedmiot.values()) {
      double zmiana = h.getSredniaCenaDanegoDnia(dzien - 1, info, p)
        - h.getSredniaCenaDanegoDnia(dzien - historiaPerspektywy, info, p);
      if (zmiana > maksimum) {
        maksimum = zmiana;
        wynik = p;
      }
    }

    return wynik;
  }

  @Override
  public int getHistoriaPerspektywy() {
    return historiaPerspektywy;
  }

  @Override
  public String toString() {
    return "perspektywiczny";
  }
}
