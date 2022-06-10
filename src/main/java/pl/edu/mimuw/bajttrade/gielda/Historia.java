package pl.edu.mimuw.bajttrade.gielda;

import pl.edu.mimuw.bajttrade.oferty.Rachunek;
import pl.edu.mimuw.bajttrade.oferty.Oferta;
import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;

import java.util.ArrayList;
import java.util.List;

public class Historia {
  private final List<Oferta> historiaOfertRobotnikow;
  private final List<Oferta> historiaOfertSpekulantow;
  private final List<Rachunek> historiaZfinalizowanych;

  public Historia() {
    this.historiaOfertRobotnikow = new ArrayList<>();
    this.historiaOfertSpekulantow = new ArrayList<>();
    this.historiaZfinalizowanych = new ArrayList<>();
  }

  public void dodajOfertaRobotnika(Oferta oferta) {
    historiaOfertRobotnikow.add(oferta);
  }

  public void dodajOfertaSpekulanta(Oferta oferta) {
    historiaOfertSpekulantow.add(oferta);
  }

  public void dodajZfinalizowana(Rachunek rachunek) {
    historiaZfinalizowanych.add(rachunek);
  }

  public double getSredniaCenaKilkuDni(int liczbaDni, int aktualnyDzien, Info info, Przedmiot p) {
    if (p == Przedmiot.DIAMENTY) return 1;

    double sumaCen = 0;

    for (int i = aktualnyDzien - liczbaDni; i < aktualnyDzien; i++) {
      if (i < 0) continue;
      sumaCen += getSredniaCenaDanegoDnia(i, info, p);
    }

    return sumaCen / Math.min(liczbaDni, aktualnyDzien);
  }

  public double getSredniaCenaDanegoDnia(int dzien, Info info, Przedmiot p) {
    if (p == Przedmiot.DIAMENTY) return 1;

    double sumaCen = 0;
    int liczbaPrzedmiotow = 0;

    for (var r : historiaZfinalizowanych) {
      if (r.getDzien() == dzien && r.getPrzedmiot() == p) {
        sumaCen += r.getCena() * r.getIlosc();
        liczbaPrzedmiotow += r.getIlosc();
      }
    }

    if (liczbaPrzedmiotow == 0) return info.getCena(p);
    return sumaCen / liczbaPrzedmiotow;
  }

  public Przedmiot getNajczesciejWystepujacy(int liczbaDni, int aktualnyDzien) {
    int[] liczbaPrzedmiotow = new int[Przedmiot.values().length];
    int maksimum = 0;
    Przedmiot najczesciejWystepujacy = null;

    for (var r : historiaOfertRobotnikow) {
      if (r.getDzien() >= aktualnyDzien - liczbaDni && r.getDzien() < aktualnyDzien) {
        liczbaPrzedmiotow[r.getPrzedmiot().ordinal()] += r.getIlosc();
      }
    }

    for (var r : historiaOfertSpekulantow) {
      if (r.getDzien() >= aktualnyDzien - liczbaDni && r.getDzien() < aktualnyDzien) {
        liczbaPrzedmiotow[r.getPrzedmiot().ordinal()] += r.getIlosc();
        if (liczbaPrzedmiotow[r.getPrzedmiot().ordinal()] > maksimum) {
          maksimum = liczbaPrzedmiotow[r.getPrzedmiot().ordinal()];
          najczesciejWystepujacy = r.getPrzedmiot();
        }
      }
    }

    return najczesciejWystepujacy;
  }

  public int getLiczbaPrzedmiotowRobotnikow(int dzien, Przedmiot p) {
    int wynik = 0;
    for (var r : historiaOfertRobotnikow) {
      if (r.getPrzedmiot() == p && r.getDzien() == dzien) {
        wynik += r.getIlosc();
      }
    }
    return wynik;
  }

  public double getNajnizszaCena(int dzien, Info info, Przedmiot p) {
    double najnizszaCena = Double.MAX_VALUE;
    boolean istnieje = false;

    for (var r : historiaZfinalizowanych) {
      if (r.getPrzedmiot() == p && r.getDzien() == dzien) {
        najnizszaCena = Math.min(najnizszaCena, r.getCena());
        istnieje = true;
      }
    }

    if (!istnieje) return info.getCena(p);
    else return najnizszaCena;
  }

  public double getNajwyzszaCena(int dzien, Info info, Przedmiot p) {
    double najwyzszaCena = Double.NEGATIVE_INFINITY;
    boolean istnieje = false;

    for (var r : historiaZfinalizowanych) {
      if (r.getPrzedmiot() == p && r.getDzien() == dzien) {
        najwyzszaCena = Math.max(najwyzszaCena, r.getCena());
        istnieje = true;
      }
    }

    if (!istnieje) return info.getCena(p);
    else return najwyzszaCena;
  }
}
