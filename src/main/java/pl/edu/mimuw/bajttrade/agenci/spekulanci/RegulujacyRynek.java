package pl.edu.mimuw.bajttrade.agenci.spekulanci;

import pl.edu.mimuw.bajttrade.gielda.Historia;
import pl.edu.mimuw.bajttrade.gielda.Info;
import pl.edu.mimuw.bajttrade.oferty.OfertaSpekulanta;
import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;
import pl.edu.mimuw.bajttrade.przedmioty.Zasoby;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegulujacyRynek extends Spekulant {
  public RegulujacyRynek(int id, Zasoby zasoby) {
    super(id, zasoby);
  }

  @Override
  public List<OfertaSpekulanta> coKupuje(Historia h, Info info, int dzien) {
    if (dzien == 1) {
      return Collections.emptyList();
    }

    double cenaProgramow = h.getSredniaCenaDanegoDnia(dzien - 1, info, Przedmiot.PROGRAMY)
      * h.getLiczbaPrzedmiotowRobotnikow(dzien, Przedmiot.PROGRAMY)
      / Math.max(h.getLiczbaPrzedmiotowRobotnikow(dzien - 1, Przedmiot.PROGRAMY), 1) * 0.9;
    double cenaNarzedzi = h.getSredniaCenaDanegoDnia(dzien - 1, info, Przedmiot.NARZEDZIA)
      * h.getLiczbaPrzedmiotowRobotnikow(dzien, Przedmiot.NARZEDZIA)
      / Math.max(h.getLiczbaPrzedmiotowRobotnikow(dzien - 1, Przedmiot.NARZEDZIA), 1) * 0.9;
    double cenaUbran = h.getSredniaCenaDanegoDnia(dzien - 1, info, Przedmiot.UBRANIA)
      * h.getLiczbaPrzedmiotowRobotnikow(dzien, Przedmiot.UBRANIA)
      / Math.max(h.getLiczbaPrzedmiotowRobotnikow(dzien - 1, Przedmiot.UBRANIA), 1) * 0.9;
    double cenaJedzenia = h.getSredniaCenaDanegoDnia(dzien - 1, info, Przedmiot.JEDZENIE)
      * h.getLiczbaPrzedmiotowRobotnikow(dzien, Przedmiot.JEDZENIE)
      / Math.max(h.getLiczbaPrzedmiotowRobotnikow(dzien - 1, Przedmiot.JEDZENIE), 1) * 0.9;

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
    if (dzien == 1) {
      return Collections.emptyList();
    }

    double cenaProgramow = h.getSredniaCenaDanegoDnia(dzien - 1, info, Przedmiot.PROGRAMY)
      * h.getLiczbaPrzedmiotowRobotnikow(dzien, Przedmiot.PROGRAMY)
      / Math.max(h.getLiczbaPrzedmiotowRobotnikow(dzien - 1, Przedmiot.PROGRAMY), 1) * 1.1;
    double cenaNarzedzi = h.getSredniaCenaDanegoDnia(dzien - 1, info, Przedmiot.NARZEDZIA)
      * h.getLiczbaPrzedmiotowRobotnikow(dzien, Przedmiot.NARZEDZIA)
      / Math.max(h.getLiczbaPrzedmiotowRobotnikow(dzien - 1, Przedmiot.NARZEDZIA), 1) * 1.1;
    double cenaUbran = h.getSredniaCenaDanegoDnia(dzien - 1, info, Przedmiot.UBRANIA)
      * h.getLiczbaPrzedmiotowRobotnikow(dzien, Przedmiot.UBRANIA)
      / Math.max(h.getLiczbaPrzedmiotowRobotnikow(dzien - 1, Przedmiot.UBRANIA), 1) * 1.1;
    double cenaJedzenia = h.getSredniaCenaDanegoDnia(dzien - 1, info, Przedmiot.JEDZENIE)
      * h.getLiczbaPrzedmiotowRobotnikow(dzien, Przedmiot.JEDZENIE)
      / Math.max(h.getLiczbaPrzedmiotowRobotnikow(dzien - 1, Przedmiot.JEDZENIE), 1) * 1.1;

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
  public String toString() {
    return "regulujacy_rynek";
  }
}
