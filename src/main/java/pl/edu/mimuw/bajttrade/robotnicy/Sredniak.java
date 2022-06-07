package pl.edu.mimuw.bajttrade.robotnicy;

import pl.edu.mimuw.bajttrade.Historia;
import pl.edu.mimuw.bajttrade.kariera.Kariera;
import pl.edu.mimuw.bajttrade.kupowanie.Kupowanie;
import pl.edu.mimuw.bajttrade.przedmioty.Produktywnosc;
import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;
import pl.edu.mimuw.bajttrade.przedmioty.Zasoby;
import pl.edu.mimuw.bajttrade.uczenie.Uczenie;
import pl.edu.mimuw.bajttrade.zmiana.Zmiana;

public class Sredniak extends Robotnik {
  private int historiaSredniejProdukcji;

  public Sredniak (int id, int poziom, Kariera kariera, Kupowanie kupowanie, Uczenie uczenie, Zmiana zmiana,
                 Produktywnosc produktywnosc, Zasoby zasoby, int historiaSredniejProdukcji) {
    super(id, poziom, kariera, kupowanie, uczenie, zmiana, produktywnosc, zasoby);
    this.historiaSredniejProdukcji = historiaSredniejProdukcji;
  }

  @Override
  public Przedmiot coProdukuje(Historia h, int dzien) {
    double maksimum = 0;
    Przedmiot wynik = null;

    for (var p : Przedmiot.values()) {
      double sredniaCena = h.getSredniaCena(this.historiaSredniejProdukcji, dzien, p);
      if (sredniaCena > maksimum) {
        maksimum = sredniaCena;
        wynik = p;
      }
    }

    return wynik;
  }

  @Override
  public String toString() {
    return super.toString() + "\t produkcja: sredniak\n";
  }
}
