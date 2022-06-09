package pl.edu.mimuw.bajttrade.agenci.spekulanci;

import pl.edu.mimuw.bajttrade.gielda.Historia;
import pl.edu.mimuw.bajttrade.gielda.Info;
import pl.edu.mimuw.bajttrade.oferty.OfertaSpekulanta;
import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;
import pl.edu.mimuw.bajttrade.przedmioty.Zasoby;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Wypukly extends Spekulant {
  public Wypukly(int id, Zasoby zasoby) {
    super(id, zasoby);
  }

  @Override
  public List<OfertaSpekulanta> coKupuje(Historia h, Info info, int dzien) {
    List<OfertaSpekulanta> wynik = new ArrayList<>();

    for (var p : Przedmiot.values()) {
      if (p == Przedmiot.DIAMENTY) continue;
      double cena3DniTemu = h.getSredniaCenaOstatnichDni(1, dzien - 2, info, p);
      double cena2DniTemu = h.getSredniaCenaOstatnichDni(1, dzien - 1, info, p);
      double cenaDzienTemu = h.getSredniaCenaOstatnichDni(1, dzien, info, p);
      if (cena2DniTemu <= cenaDzienTemu && cena2DniTemu <= cena3DniTemu) {
        double cenaFaktyczna = cenaDzienTemu * 0.9;
        if (p == Przedmiot.JEDZENIE) {
          wynik.add(new OfertaSpekulanta(dzien, 100, 1, p, cenaFaktyczna, this));
          h.dodajOfertaSpekulanta(new OfertaSpekulanta(dzien, 100, 1, p, cenaFaktyczna, this));
          continue;
        }
        for (int i = 1; i <= dzien; i++) {
          wynik.add(new OfertaSpekulanta(dzien, 100, i, p, cenaFaktyczna, this));
          h.dodajOfertaSpekulanta(new OfertaSpekulanta(dzien, 100, i, p, cenaFaktyczna, this));
        }
      }
    }

    if (wynik.size() == 0) return Collections.emptyList();
    else return wynik;
  }

  @Override
  public List<OfertaSpekulanta> coSprzedaje(Historia h, Info info, int dzien) {
    List<OfertaSpekulanta> wynik = new ArrayList<>();

    for (var p : Przedmiot.values()) {
      if (p == Przedmiot.DIAMENTY) continue;
      double cena3DniTemu = h.getSredniaCenaOstatnichDni(1, dzien - 2, info, p);
      double cena2DniTemu = h.getSredniaCenaOstatnichDni(1, dzien - 1, info, p);
      double cenaDzienTemu = h.getSredniaCenaOstatnichDni(1, dzien, info, p);
      if (cena2DniTemu >= cenaDzienTemu && cena2DniTemu >= cena3DniTemu) {
        double cenaFaktyczna = cenaDzienTemu * 1.1;
        if (p == Przedmiot.JEDZENIE && this.zasoby.getIloscZasobow(p) > 0) {
          wynik.add(new OfertaSpekulanta(dzien, this.zasoby.getIloscZasobow(p), 1, p, cenaFaktyczna, this));
          h.dodajOfertaSpekulanta(new OfertaSpekulanta(dzien, this.zasoby.getIloscZasobow(p), 1, p,
            cenaFaktyczna, this));
          continue;
        }
        for (int i = 1; i <= dzien; i++) {
          if (this.zasoby.iloscElementowDanegoPoziomu(i, p) > 0) {
            wynik.add(new OfertaSpekulanta(dzien, this.zasoby.iloscElementowDanegoPoziomu(i, p), i, p, cenaFaktyczna,
              this));
            h.dodajOfertaSpekulanta(new OfertaSpekulanta(dzien, this.zasoby.iloscElementowDanegoPoziomu(i, p), i, p,
              cenaFaktyczna, this));
          }
        }
      }
    }

    if (wynik.size() == 0) return Collections.emptyList();
    else return wynik;
  }

  @Override
  public String toString() {
    var sb = new StringBuilder();

    sb.append(super.toString()).append("\n");
    sb.append("\t kariera: wypukly\n");
    sb.append("\t zasoby:\n").append(this.zasoby);

    return sb.toString();
  }
}
