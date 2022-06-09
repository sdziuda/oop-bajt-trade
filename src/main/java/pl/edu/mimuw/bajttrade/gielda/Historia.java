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

  public double getSredniaCenaOstatnichDni(int liczbaDni, int aktualnyDzien, Info info, Przedmiot p) {
    if (p == Przedmiot.DIAMENTY) return 1;

    double[] sumaCen = new double[liczbaDni];
    int[] liczbaPrzedmiotow = new int[liczbaDni];
    boolean istnieje = false;

    for (var r : historiaZfinalizowanych) {
      if (r.getDzien() > aktualnyDzien - liczbaDni && r.getPrzedmiot() == p) {
        if (aktualnyDzien - r.getDzien() == 0) {
          sumaCen[aktualnyDzien - r.getDzien()] += info.getCena(p);
          liczbaPrzedmiotow[aktualnyDzien - r.getDzien()] += 1;
          istnieje = true;
        } else if (aktualnyDzien - r.getDzien() > 0) {
          sumaCen[aktualnyDzien - r.getDzien()] += r.getCena() * r.getIlosc();
          liczbaPrzedmiotow[aktualnyDzien - r.getDzien()] += r.getIlosc();
          istnieje = true;
        }
      }
    }

    if (!istnieje) return info.getCena(p);

    double sumaSrednich = 0;
    for (int i = 0; i < Math.min(liczbaDni, aktualnyDzien); i++) {
      if (liczbaPrzedmiotow[i] == 0) sumaSrednich += info.getCena(p);
      else sumaSrednich += sumaCen[i] / liczbaPrzedmiotow[i];
    }
    return sumaSrednich / Math.min(liczbaDni, aktualnyDzien);
  }

  public Przedmiot getNajczesciejWystepujacy(int liczbaDni, int aktualnyDzien) {
    int[] liczbaPrzedmiotow = new int[Przedmiot.values().length];
    int maksimum = 0;
    Przedmiot najczesciejWystepujacy = null;

    for (var r : historiaOfertRobotnikow) {
      if (r.getDzien() >= aktualnyDzien - liczbaDni) {
        liczbaPrzedmiotow[r.getPrzedmiot().ordinal()] += r.getIlosc();
      }
    }

    for (var r : historiaOfertSpekulantow) {
      if (r.getDzien() >= aktualnyDzien - liczbaDni) {
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

  @Override
  public String toString() {
    var sb = new StringBuilder();

    sb.append("[");
    for (var o : historiaOfertRobotnikow) {
      sb.append(o).append("\n");
    }
    for (var o : historiaOfertSpekulantow) {
      sb.append(o).append("\n");
    }
    sb.replace(sb.lastIndexOf("\n"), sb.lastIndexOf("\n"), "]");

    return sb.toString();
  }
}
