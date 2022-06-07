package pl.edu.mimuw.bajttrade;

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

  public double getSredniaCena(int liczbaDni, int aktualnyDzien, Przedmiot przedmiot) {
    double sumaCen = 0;
    int liczbaJedzenia = 0;

    for (var r : historiaZfinalizowanych) {
      if (r.getDzien() >= aktualnyDzien - liczbaDni && r.getPrzedmiot() == przedmiot) {
        sumaCen += r.getCena() * r.getIlosc();
        liczbaJedzenia += r.getIlosc();
      }
    }

    if (liczbaJedzenia == 0) {
      return 0;
    }
    return sumaCen / liczbaJedzenia;
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
}
