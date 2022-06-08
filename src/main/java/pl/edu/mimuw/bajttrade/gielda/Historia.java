package pl.edu.mimuw.bajttrade.gielda;

import pl.edu.mimuw.bajttrade.oferty.Rachunek;
import pl.edu.mimuw.bajttrade.oferty.Oferta;
import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;

import java.util.ArrayList;
import java.util.List;

public class Historia {
  private final List<Oferta> historiaOfert;
  private final List<Rachunek> historiaZfinalizowanych;

  public Historia() {
    this.historiaOfert = new ArrayList<>();
    this.historiaZfinalizowanych = new ArrayList<>();
  }

  public void dodajOferta(Oferta oferta) {
    historiaOfert.add(oferta);
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
        sumaCen[aktualnyDzien - r.getDzien()] += r.getCena() * r.getIlosc();
        liczbaPrzedmiotow[aktualnyDzien - r.getDzien()] += r.getIlosc();
        istnieje = true;
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

    for (var r : historiaOfert) {
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

  @Override
  public String toString() {
    var sb = new StringBuilder();

    sb.append("[");
    for (var o : historiaOfert) {
      sb.append(o).append("\n");
    }
    sb.replace(sb.lastIndexOf("\n"), sb.lastIndexOf("\n"), "]");

    return sb.toString();
  }
}
