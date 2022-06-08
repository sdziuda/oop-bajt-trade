package pl.edu.mimuw.bajttrade.spekulanci;

import pl.edu.mimuw.bajttrade.gielda.Historia;
import pl.edu.mimuw.bajttrade.gielda.Info;
import pl.edu.mimuw.bajttrade.oferty.Oferta;
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
  public List<Oferta> coKupuje(Historia h, Info info, int dzien) {
    if (dzien == 1) {
      return Collections.emptyList();
    }

    double cenaProgramow = h.getSredniaCenaOstatnichDni(1, dzien, info, Przedmiot.PROGRAMY)
      * h.getLiczbaPrzedmiotowRobotnikow(dzien, Przedmiot.PROGRAMY)
      / Math.max(h.getLiczbaPrzedmiotowRobotnikow(dzien - 1, Przedmiot.PROGRAMY), 1) * 0.9;
    double cenaNarzedzi = h.getSredniaCenaOstatnichDni(1, dzien, info, Przedmiot.NARZEDZIA)
      * h.getLiczbaPrzedmiotowRobotnikow(dzien, Przedmiot.NARZEDZIA)
      / Math.max(h.getLiczbaPrzedmiotowRobotnikow(dzien - 1, Przedmiot.NARZEDZIA), 1) * 0.9;
    double cenaUbran = h.getSredniaCenaOstatnichDni(1, dzien, info, Przedmiot.UBRANIA)
      * h.getLiczbaPrzedmiotowRobotnikow(dzien, Przedmiot.UBRANIA)
      / Math.max(h.getLiczbaPrzedmiotowRobotnikow(dzien - 1, Przedmiot.UBRANIA), 1) * 0.9;
    double cenaJedzenia = h.getSredniaCenaOstatnichDni(1, dzien, info, Przedmiot.JEDZENIE)
      * h.getLiczbaPrzedmiotowRobotnikow(dzien, Przedmiot.JEDZENIE)
      / Math.max(h.getLiczbaPrzedmiotowRobotnikow(dzien - 1, Przedmiot.JEDZENIE), 1) * 0.9;

    List<Oferta> wynik = new ArrayList<>();

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
  public List<Oferta> coSprzedaje(Historia h, Info info, int dzien) {
    if (dzien == 1) {
      return Collections.emptyList();
    }

    double cenaProgramow = h.getSredniaCenaOstatnichDni(1, dzien, info, Przedmiot.PROGRAMY)
      * h.getLiczbaPrzedmiotowRobotnikow(dzien, Przedmiot.PROGRAMY)
      / Math.max(h.getLiczbaPrzedmiotowRobotnikow(dzien - 1, Przedmiot.PROGRAMY), 1) * 1.1;
    double cenaNarzedzi = h.getSredniaCenaOstatnichDni(1, dzien, info, Przedmiot.NARZEDZIA)
      * h.getLiczbaPrzedmiotowRobotnikow(dzien, Przedmiot.NARZEDZIA)
      / Math.max(h.getLiczbaPrzedmiotowRobotnikow(dzien - 1, Przedmiot.NARZEDZIA), 1) * 1.1;
    double cenaUbran = h.getSredniaCenaOstatnichDni(1, dzien, info, Przedmiot.UBRANIA)
      * h.getLiczbaPrzedmiotowRobotnikow(dzien, Przedmiot.UBRANIA)
      / Math.max(h.getLiczbaPrzedmiotowRobotnikow(dzien - 1, Przedmiot.UBRANIA), 1) * 1.1;
    double cenaJedzenia = h.getSredniaCenaOstatnichDni(1, dzien, info, Przedmiot.JEDZENIE)
      * h.getLiczbaPrzedmiotowRobotnikow(dzien, Przedmiot.JEDZENIE)
      / Math.max(h.getLiczbaPrzedmiotowRobotnikow(dzien - 1, Przedmiot.JEDZENIE), 1) * 1.1;

    List<Oferta> wynik = new ArrayList<>();

    for (int i = 1; i <= dzien; i++) {
      if (this.zasoby.iloscNarzedziDanegoPoziomu(i) > 0) {
        wynik.add(new OfertaSpekulanta(dzien, this.zasoby.iloscNarzedziDanegoPoziomu(i), i, Przedmiot.NARZEDZIA,
          cenaNarzedzi, this));
        h.dodajOfertaSpekulanta(new OfertaSpekulanta(dzien, this.zasoby.iloscNarzedziDanegoPoziomu(i), i,
          Przedmiot.NARZEDZIA, cenaNarzedzi, this));
      }
      if (this.zasoby.iloscProgramowDanegoPoziomu(i) > 0) {
        wynik.add(new OfertaSpekulanta(dzien, this.zasoby.iloscProgramowDanegoPoziomu(i), i, Przedmiot.PROGRAMY,
          cenaNarzedzi, this));
        h.dodajOfertaSpekulanta(new OfertaSpekulanta(dzien, this.zasoby.iloscProgramowDanegoPoziomu(i), i,
          Przedmiot.PROGRAMY, cenaNarzedzi, this));
      }
      if (this.zasoby.iloscUbranDanegoPoziomu(i) > 0) {
        wynik.add(new OfertaSpekulanta(dzien, this.zasoby.iloscUbranDanegoPoziomu(i), i, Przedmiot.UBRANIA,
          cenaNarzedzi, this));
        h.dodajOfertaSpekulanta(new OfertaSpekulanta(dzien, this.zasoby.iloscUbranDanegoPoziomu(i), i,
          Przedmiot.UBRANIA, cenaNarzedzi, this));
      }
    }
    if (this.zasoby.getIloscZasobow(Przedmiot.UBRANIA) > 0) {
      wynik.add(new OfertaSpekulanta(dzien, this.zasoby.getIloscZasobow(Przedmiot.UBRANIA), 1, Przedmiot.UBRANIA,
        cenaUbran, this));
      h.dodajOfertaSpekulanta(new OfertaSpekulanta(dzien, this.zasoby.getIloscZasobow(Przedmiot.UBRANIA), 1,
        Przedmiot.UBRANIA, cenaUbran, this));
    }

    return wynik;
  }

  @Override
  public String toString() {
    var sb = new StringBuilder();

    sb.append(super.toString()).append("\n");
    sb.append("\t kariera: regulujacy_rynek\n");
    sb.append("\t zasoby:\n").append(this.zasoby);

    return sb.toString();
  }
}
