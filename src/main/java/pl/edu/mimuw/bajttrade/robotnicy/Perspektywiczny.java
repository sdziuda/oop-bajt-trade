package pl.edu.mimuw.bajttrade.robotnicy;

import pl.edu.mimuw.bajttrade.Historia;
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
  public Przedmiot coProdukuje(Historia h, int dzien) {
    double maksimum = 0;
    Przedmiot wynik = null;

    for (var p : Przedmiot.values()) {
      double zmiana = h.getSredniaCena(1, dzien, p) - h.getSredniaCena(this.historiaPerspektywy, dzien, p);
      if (zmiana > maksimum) {
        maksimum = zmiana;
        wynik = p;
      }
    }

    return wynik;
  }

  @Override
  public String toString() {
    return super.toString() + "\t produkcja: perspektywiczny\n";
  }
}
