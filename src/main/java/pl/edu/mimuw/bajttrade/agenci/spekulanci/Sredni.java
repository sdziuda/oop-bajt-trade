package pl.edu.mimuw.bajttrade.agenci.spekulanci;

import pl.edu.mimuw.bajttrade.gielda.Historia;
import pl.edu.mimuw.bajttrade.gielda.Info;
import pl.edu.mimuw.bajttrade.oferty.OfertaSpekulanta;
import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;
import pl.edu.mimuw.bajttrade.przedmioty.Zasoby;

import java.util.ArrayList;
import java.util.List;

public class Sredni extends Spekulant {
  private final int historiaSpekulantaSredniego;

  public Sredni(int id, Zasoby zasoby, int historiaSpekulantaSredniego) {
    super(id, zasoby);
    this.historiaSpekulantaSredniego = historiaSpekulantaSredniego;
  }

  @Override
  public List<OfertaSpekulanta> coKupuje(Historia h, Info info, int dzien) {
    double cenaProgramow = h.getSredniaCenaKilkuDni(historiaSpekulantaSredniego, dzien, info, Przedmiot.PROGRAMY);
    if (this.zasoby.getIloscZasobow(Przedmiot.PROGRAMY) > 0) cenaProgramow *= 0.9;
    else cenaProgramow *= 0.95;
    double cenaNarzedzi = h.getSredniaCenaKilkuDni(historiaSpekulantaSredniego, dzien, info, Przedmiot.NARZEDZIA);
    if (this.zasoby.getIloscZasobow(Przedmiot.NARZEDZIA) > 0) cenaNarzedzi *= 0.9;
    else cenaNarzedzi *= 0.95;
    double cenaUbran = h.getSredniaCenaKilkuDni(historiaSpekulantaSredniego, dzien, info, Przedmiot.UBRANIA);
    if (this.zasoby.getIloscZasobow(Przedmiot.UBRANIA) > 0) cenaUbran *= 0.9;
    else cenaUbran *= 0.95;
    double cenaJedzenia = h.getSredniaCenaKilkuDni(historiaSpekulantaSredniego, dzien, info, Przedmiot.JEDZENIE);
    if (this.zasoby.getIloscZasobow(Przedmiot.JEDZENIE) > 0) cenaJedzenia *= 0.9;
    else cenaJedzenia *= 0.95;

    List<OfertaSpekulanta> wynik = new ArrayList<>();

    for (int i = 1; i <= dzien; i++) {
      wynik.add(new OfertaSpekulanta(dzien, 100, i, Przedmiot.NARZEDZIA, cenaNarzedzi, this));
      h.dodajOfertaSpekulanta(new OfertaSpekulanta(dzien, 100, i, Przedmiot.NARZEDZIA, cenaNarzedzi, this));
      wynik.add(new OfertaSpekulanta(dzien, 100, i, Przedmiot.PROGRAMY, cenaProgramow, this));
      h.dodajOfertaSpekulanta(new OfertaSpekulanta(dzien, 100, i, Przedmiot.PROGRAMY, cenaProgramow, this));
      wynik.add(new OfertaSpekulanta(dzien, 100, i, Przedmiot.UBRANIA, cenaUbran, this));
      h.dodajOfertaSpekulanta(new OfertaSpekulanta(dzien, 100, i, Przedmiot.UBRANIA, cenaUbran, this));
    }
    wynik.add(new OfertaSpekulanta(dzien, 100, 1, Przedmiot.JEDZENIE, cenaJedzenia, this));
    h.dodajOfertaSpekulanta(new OfertaSpekulanta(dzien, 100, 1, Przedmiot.JEDZENIE, cenaJedzenia, this));

    return wynik;
  }

  @Override
  public List<OfertaSpekulanta> coSprzedaje(Historia h, Info info, int dzien) {
    double cenaProgramow = h.getSredniaCenaKilkuDni(historiaSpekulantaSredniego, dzien, info, Przedmiot.PROGRAMY)
      * 1.1;
    double cenaNarzedzi = h.getSredniaCenaKilkuDni(historiaSpekulantaSredniego, dzien, info, Przedmiot.NARZEDZIA)
      * 1.1;
    double cenaUbran = h.getSredniaCenaKilkuDni(historiaSpekulantaSredniego, dzien, info, Przedmiot.UBRANIA) * 1.1;
    double cenaJedzenia = h.getSredniaCenaKilkuDni(historiaSpekulantaSredniego, dzien, info, Przedmiot.JEDZENIE)
      * 1.1;

    List<OfertaSpekulanta> wynik = new ArrayList<>();

    for (int i = 1; i <= dzien; i++) {
      if (this.zasoby.iloscNarzedziDanegoPoziomu(i) > 0) {
        wynik.add(new OfertaSpekulanta(dzien, this.zasoby.iloscNarzedziDanegoPoziomu(i), i, Przedmiot.NARZEDZIA,
          cenaNarzedzi, this));
        h.dodajOfertaSpekulanta(new OfertaSpekulanta(dzien, this.zasoby.iloscNarzedziDanegoPoziomu(i), i,
          Przedmiot.NARZEDZIA, cenaNarzedzi, this));
      }
      if (this.zasoby.iloscProgramowDanegoPoziomu(i) > 0) {
        wynik.add(new OfertaSpekulanta(dzien, this.zasoby.iloscProgramowDanegoPoziomu(i), i, Przedmiot.PROGRAMY,
          cenaProgramow, this));
        h.dodajOfertaSpekulanta(new OfertaSpekulanta(dzien, this.zasoby.iloscProgramowDanegoPoziomu(i), i,
          Przedmiot.PROGRAMY, cenaProgramow, this));
      }
      if (this.zasoby.iloscUbranDanegoPoziomu(i) > 0) {
        wynik.add(new OfertaSpekulanta(dzien, this.zasoby.iloscUbranDanegoPoziomu(i), i, Przedmiot.UBRANIA,
          cenaUbran, this));
        h.dodajOfertaSpekulanta(new OfertaSpekulanta(dzien, this.zasoby.iloscUbranDanegoPoziomu(i), i,
          Przedmiot.UBRANIA, cenaUbran, this));
      }
    }
    if (this.zasoby.getIloscZasobow(Przedmiot.JEDZENIE) > 0) {
      wynik.add(new OfertaSpekulanta(dzien, this.zasoby.getIloscZasobow(Przedmiot.JEDZENIE), 1, Przedmiot.JEDZENIE,
        cenaJedzenia, this));
      h.dodajOfertaSpekulanta(new OfertaSpekulanta(dzien, this.zasoby.getIloscZasobow(Przedmiot.JEDZENIE), 1,
        Przedmiot.JEDZENIE, cenaJedzenia, this));
    }

    return wynik;
  }

  @Override
  public int getHistoriaSpekulantaSredniego() {
    return historiaSpekulantaSredniego;
  }

  @Override
  public String toString() {
    return "sredni";
  }
}
