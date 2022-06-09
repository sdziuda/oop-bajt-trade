package pl.edu.mimuw.bajttrade.agenci.robotnicy;

import pl.edu.mimuw.bajttrade.agenci.Agent;
import pl.edu.mimuw.bajttrade.gielda.Historia;
import pl.edu.mimuw.bajttrade.gielda.Info;
import pl.edu.mimuw.bajttrade.oferty.Oferta;
import pl.edu.mimuw.bajttrade.oferty.OfertaRobotnika;
import pl.edu.mimuw.bajttrade.przedmioty.*;
import pl.edu.mimuw.bajttrade.kariera.Kariera;
import pl.edu.mimuw.bajttrade.kupowanie.Kupowanie;
import pl.edu.mimuw.bajttrade.uczenie.Uczenie;
import pl.edu.mimuw.bajttrade.zmiana.Zmiana;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Robotnik extends Agent {
  private Kariera kariera;
  private Kupowanie kupowanie;
  private Uczenie uczenie;
  private Zmiana zmiana;
  private Produktywnosc produktywnosc;

  private List<Integer> premie;
  private int iloscWyprodukowanych;
  private List<Kariera> sciezkiKariery;
  private int ileNieJadl;
  private boolean czyZyje;

  protected Robotnik(int id, int poziom, Kariera kariera, Kupowanie kupowanie, Uczenie uczenie, Zmiana zmiana,
                     Produktywnosc produktywnosc, Zasoby zasoby) {
    super(id, zasoby);
    this.kariera = kariera;
    this.kariera.setPoziom(poziom);
    this.kupowanie = kupowanie;
    this.uczenie = uczenie;
    this.zmiana = zmiana;
    this.produktywnosc = produktywnosc;

    this.sciezkiKariery = new ArrayList<>();
    this.sciezkiKariery.add(this.kariera);
    this.premie = new ArrayList<>();
    this.premie.add(50);
    this.ileNieJadl = 0;
    this.czyZyje = true;
  }

  public Kariera getAktywnaKariera() {
    return this.kariera;
  }

  public int getProduktywnosc(Przedmiot p) {
    return this.produktywnosc.getProduktywnosc(p);
  }

  public int getIloscWyprodukowanych() {
    return this.iloscWyprodukowanych;
  }

  public void rozegrajPierwszyEtap(Historia h, int dzien, Info info, List<Robotnik> listaRobotnikowPracujacych,
                                   List<Oferta> ofertySprzedazyRobotnikow, List<Oferta> ofertyKupnaRobotnikow) {
    if (!czyZyje) return;

    if (uczenie.czySieUczy(this, h, info, dzien)) {
      uczySie(h, dzien);
    } else {
      listaRobotnikowPracujacych.add(this);
      pracuje(h, dzien, info, listaRobotnikowPracujacych, ofertySprzedazyRobotnikow);
      if (!czyZyje) {
        listaRobotnikowPracujacych.remove(this);
        return;
      }
      kupuje(h, dzien, ofertyKupnaRobotnikow);
    }
  }

  public void rozegrajKoniecDnia() {
    zasoby.usunNarzedzia();
    if (zasoby.getIloscZasobow(Przedmiot.JEDZENIE) <= 0) {
      ileNieJadl++;
    } else {
      if (ileNieJadl == 1) {
        premie.remove(Integer.valueOf(-100));
      } else if (ileNieJadl == 2) {
        premie.remove(Integer.valueOf(-300));
      }
      ileNieJadl = 0;
      zasoby.usunJedzenie(100);
    }
    zasoby.zuzyjUbrania(100);
  }

  public abstract Przedmiot coProdukuje(Historia h, Info info, int dzien);

  @Override
  public String toString() {
    var sb = new StringBuilder();

    sb.append("\t id: ").append(this.id).append("\n");
    sb.append("\t poziom: ").append(this.kariera.getPoziom()).append("\n");
    sb.append("\t kariera: ").append(this.kariera).append("\n");
    sb.append("\t kupowanie: ").append(this.kupowanie).append("\n");
    sb.append("\t uczenie: ").append(this.uczenie).append("\n");
    sb.append("\t zmiana: ").append(this.zmiana).append("\n");
    sb.append("\t produktywnosc:\n").append(this.produktywnosc);
    sb.append("\t zasoby:\n").append(this.zasoby);

    return sb.toString();
  }

  private void uczySie(Historia h, int dzien) {
    if (ileNieJadl == 1) {
      premie.remove(Integer.valueOf(-100));
    } else if (ileNieJadl == 2) {
      premie.remove(Integer.valueOf(-300));
    }
    ileNieJadl = 0;
    Kariera karieraPoZmianie = zmiana.karieraPoZmianie(this, h, dzien);
    if (karieraPoZmianie.equals(this.kariera)) {
      kariera.zwiekszPoziom();
      kariera.usunPremie(this.premie, kariera.getPoziom() - 1);
      kariera.dodajPremie(this.premie, kariera.getPoziom());
    } else {
      if (sciezkiKariery.contains(karieraPoZmianie)) {
        kariera.usunPremie(premie, this.kariera.getPoziom());
        this.kariera = sciezkiKariery.get(sciezkiKariery.indexOf(karieraPoZmianie));
        kariera.dodajPremie(premie, this.kariera.getPoziom());
      } else {
        kariera.usunPremie(premie, this.kariera.getPoziom());
        this.kariera = karieraPoZmianie;
        kariera.dodajPremie(premie, this.kariera.getPoziom());
        sciezkiKariery.add(karieraPoZmianie);
      }
    }
  }

  private void pracuje(Historia h, int dzien, Info info, List<Robotnik> listaRobotnikowPracujacych,
                       List<Oferta> ofertySprzedazyRobotnikow) {
    if (zasoby.getIloscZasobow(Przedmiot.UBRANIA) < 100) premie.add(-1 * info.getKaraZaBrakUbran());
    premie.add(zasoby.getListaNarzedzi().stream().mapToInt(Narzedzie::getPoziom).sum());
    if (ileNieJadl == 1) premie.add(-100);
    else if (ileNieJadl == 2) {
      premie.remove(Integer.valueOf(-100));
      premie.add(-300);
    } else if (ileNieJadl >= 3) {
      this.czyZyje = false;
      this.zasoby.usunWszystkieDiamenty();
      return;
    }

    Przedmiot przedmiotProdukowany = coProdukuje(h, info, dzien);
    int sumaPremie = premie.stream().mapToInt(v -> v).sum() + 100;
    if (przedmiotProdukowany != kariera.getPremiowanyPrzedmiot()) sumaPremie -= kariera.premia();
    this.iloscWyprodukowanych = this.produktywnosc.getProduktywnosc(przedmiotProdukowany) / 100 * sumaPremie;
    if (iloscWyprodukowanych <= 0) return;

    if (przedmiotProdukowany != Przedmiot.DIAMENTY) {
      int ilosc = 0;
      while ((przedmiotProdukowany == Przedmiot.NARZEDZIA || przedmiotProdukowany == Przedmiot.UBRANIA)
        && ilosc < Math.min(this.iloscWyprodukowanych, this.zasoby.getIloscZasobow(Przedmiot.PROGRAMY))) {
        int maksPoziom = zasoby.getNajwyzszyPoziomProgramu();
        for (int i = maksPoziom; i > 0; i--) {
          int iloscDanegoPoziomu = zasoby.iloscProgramowDanegoPoziomu(i);
          if (iloscDanegoPoziomu + ilosc < iloscWyprodukowanych) {
            ilosc += iloscDanegoPoziomu;
          } else if (iloscDanegoPoziomu > 0) {
            iloscDanegoPoziomu = iloscWyprodukowanych - ilosc;
            ilosc = iloscWyprodukowanych;
          }
          ofertySprzedazyRobotnikow.add(new OfertaRobotnika(dzien, iloscDanegoPoziomu, i, przedmiotProdukowany, this));
          h.dodajOfertaRobotnika(new OfertaRobotnika(dzien, iloscDanegoPoziomu, i, przedmiotProdukowany, this));
          zasoby.usunProgramy(iloscDanegoPoziomu, i);
        }
      }
      int domyslnyPoziom = 1;
      if (this.kariera.getPremiowanyPrzedmiot() == Przedmiot.PROGRAMY && przedmiotProdukowany == Przedmiot.PROGRAMY) {
        domyslnyPoziom = this.kariera.getPoziom();
      }
      ofertySprzedazyRobotnikow.add(new OfertaRobotnika(dzien, iloscWyprodukowanych - ilosc, domyslnyPoziom,
        przedmiotProdukowany, this));
      h.dodajOfertaRobotnika(new OfertaRobotnika(dzien, iloscWyprodukowanych - ilosc, domyslnyPoziom, przedmiotProdukowany,
        this));
    } else {
      listaRobotnikowPracujacych.remove(this);
      zasoby.dodajDiamenty(this.iloscWyprodukowanych);
    }

    if (zasoby.getIloscZasobow(Przedmiot.UBRANIA) < 100) premie.remove(Integer.valueOf(-1 * info.getKaraZaBrakUbran()));
    premie.remove(Integer.valueOf(zasoby.getListaNarzedzi().stream().mapToInt(Narzedzie::getPoziom).sum()));
  }

  private void kupuje(Historia h, int dzien, List<Oferta> ofertyKupnaRobotnikow) {
    var listaZakupow = kupowanie.coKupuje(this, dzien);
    ofertyKupnaRobotnikow.addAll(listaZakupow.stream()
      .map(o -> new OfertaRobotnika(o.getDzien(), o.getIlosc(), o.getPoziom(), o.getPrzedmiot(), this))
      .collect(Collectors.toList()));
    listaZakupow.forEach(o -> h.dodajOfertaRobotnika(new OfertaRobotnika(o.getDzien(), o.getIlosc(), o.getPoziom(),
      o.getPrzedmiot(), this)));
  }
}
