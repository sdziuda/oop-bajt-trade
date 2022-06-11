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

public class Sredniak extends Robotnik {
  private final int historiaSredniejProdukcji;

  public Sredniak(int id, int poziom, Kariera kariera, Kupowanie kupowanie, Uczenie uczenie, Zmiana zmiana,
                  Produktywnosc produktywnosc, Zasoby zasoby, int historiaSredniejProdukcji) {
    super(id, poziom, kariera, kupowanie, uczenie, zmiana, produktywnosc, zasoby);
    this.historiaSredniejProdukcji = historiaSredniejProdukcji;
  }

  @Override
  public Przedmiot coProdukuje(Historia h, Info info, int dzien) {
    double maksimum = 0;
    Przedmiot wynik = null;

    for (var p : Przedmiot.values()) {
      double sredniaCena = h.getSredniaCenaKilkuDni(this.historiaSredniejProdukcji, dzien, info, p);
      if (sredniaCena > maksimum) {
        maksimum = sredniaCena;
        wynik = p;
      }
    }

    return wynik;
  }

  @Override
  public int getHistoriaSredniejProdukcji() {
    return this.historiaSredniejProdukcji;
  }

  @Override
  public String toString() {
    return "sredniak";
  }
}
