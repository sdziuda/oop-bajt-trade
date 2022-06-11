package pl.edu.mimuw.bajttrade.przedmioty;

import java.util.ArrayList;
import java.util.List;

public class Zasoby {
  private int jedzenie;
  private double diamenty;
  private int ubrania;
  private int narzedzia;
  private int programy;
  private List<Narzedzie> listaNarzedzi;
  private List<Ubranie> listaUbran;
  private List<Program> listaProgramow;

  public Zasoby(int jedzenie, double diamenty, int narzedzia, int ubrania, int programy) {
    this.jedzenie = jedzenie;
    this.diamenty = diamenty;
    this.narzedzia = narzedzia;
    this.ubrania = ubrania;
    this.programy = programy;
    this.listaNarzedzi = new ArrayList<>();
    for (int i = 0; i < narzedzia; i++) this.listaNarzedzi.add(new Narzedzie(1));
    this.listaUbran = new ArrayList<>();
    for (int i = 0; i < ubrania; i++) this.listaUbran.add(new Ubranie(0, 1));
    this.listaProgramow = new ArrayList<>();
    for (int i = 0; i < programy; i++) this.listaProgramow.add(new Program(1));
  }

  public int getIloscZasobow(Przedmiot p) {
    switch (p) {
      case JEDZENIE:
        return jedzenie;
      case NARZEDZIA:
        return narzedzia;
      case UBRANIA:
        return ubrania;
      case PROGRAMY:
        return programy;
      default:
        throw new RuntimeException("Nie powinno się tak pobierać diamentów lub innych zasobów");
    }
  }

  public double getDiamenty() {
    return diamenty;
  }

  public void dodajDiamenty(double ilosc) {
    this.diamenty += ilosc;
  }

  public void usunWszystkieDiamenty() {
    this.diamenty = 0;
  }

  public List<Narzedzie> getListaNarzedzi() {
    return this.listaNarzedzi;
  }

  public int getNajwyzszyPoziomProgramu() {
    int maks = 0;
    for (var p : listaProgramow) {
      if (p.getPoziom() > maks) maks = p.getPoziom();
    }
    return maks;
  }

  public int getNajwyzszyPoziomNarzedzia() {
    int maks = 0;
    for (var n : listaNarzedzi) {
      if (n.getPoziom() > maks) maks = n.getPoziom();
    }
    return maks;
  }

  public int getNajwyzszyPoziomUbrania() {
    int maks = 0;
    for (var u : listaUbran) {
      if (u.getPoziom() > maks) maks = u.getPoziom();
    }
    return maks;
  }

  public int iloscProgramowDanegoPoziomu(int poziom) {
    int wynik = 0;
    for (var p : listaProgramow) {
      if (p.getPoziom() == poziom) wynik++;
    }
    return wynik;
  }

  public int iloscNarzedziDanegoPoziomu(int poziom) {
    int wynik = 0;
    for (var p : listaNarzedzi) {
      if (p.getPoziom() == poziom) wynik++;
    }
    return wynik;
  }

  public int iloscUbranDanegoPoziomu(int poziom) {
    int wynik = 0;
    for (var p : listaUbran) {
      if (p.getPoziom() == poziom) wynik++;
    }
    return wynik;
  }

  public int iloscElementowDanegoPoziomu(int poziom, Przedmiot p) {
    switch (p) {
      case NARZEDZIA:
        return iloscNarzedziDanegoPoziomu(poziom);
      case UBRANIA:
        return iloscUbranDanegoPoziomu(poziom);
      case PROGRAMY:
        return iloscProgramowDanegoPoziomu(poziom);
      default:
        return 0;
    }
  }

  public void usunProgramy(int ile, int poziom) {
    int usuniete = 0;
    for (int i = 0; i < listaProgramow.size(); i++) {
      if (listaProgramow.get(i).getPoziom() == poziom && usuniete < ile) {
        usuniete++;
        listaProgramow.remove(i);
      }
    }
    programy -= ile;

    if (programy < 0) {
      programy = 0;
      listaProgramow = new ArrayList<>();
    }
  }

  public void usunWszystkieNarzedzia() {
    this.narzedzia = 0;
    this.listaNarzedzi = new ArrayList<>();
  }

  public void usunNarzedzia(int ile, int poziom) {
    int usuniete = 0;
    for (int i = 0; i < listaNarzedzi.size(); i++) {
      if (listaNarzedzi.get(i).getPoziom() == poziom && usuniete < ile) {
        usuniete++;
        listaNarzedzi.remove(i);
      }
    }
    narzedzia -= ile;

    if (narzedzia < 0) {
      narzedzia = 0;
      listaNarzedzi = new ArrayList<>();
    }
  }

  public void usunJedzenie(int ile) {
    this.jedzenie -= ile;
    if (this.jedzenie < 0) this.jedzenie = 0;
  }

  public void zuzyjUbrania(int ile) {
    int ileFaktycznie = Math.min(ile, ubrania);
    int i = 0;
    int zmniejszonych = 0;

    while (zmniejszonych < ileFaktycznie) {
      zmniejszonych++;
      listaUbran.get(i).dodajZuzycie();
      if (listaUbran.get(i).getZuzycie() == listaUbran.get(i).getPoziom() * listaUbran.get(i).getPoziom()) {
        listaUbran.remove(i);
        ubrania--;
      } else {
        i++;
      }
    }
  }

  public void usunUbrania(int ile, int poziom) {
    int usuniete = 0;
    for (int i = 0; i < listaUbran.size(); i++) {
      if (listaUbran.get(i).getPoziom() == poziom && usuniete < ile) {
        usuniete++;
        listaUbran.remove(i);
      }
    }
    ubrania -= ile;

    if (ubrania < 0) {
      ubrania = 0;
      listaUbran = new ArrayList<>();
    }
  }

  public void odejmijDiamenty(double i) {
    this.diamenty -= i;
  }

  public void dodajZasob(Przedmiot przedmiot, int ile, int poziom) {
    switch (przedmiot) {
      case JEDZENIE:
        this.jedzenie += ile;
        break;
      case NARZEDZIA:
        this.narzedzia += ile;
        for (int i = 0; i < ile; i++) {
          this.listaNarzedzi.add(new Narzedzie(poziom));
        }
        break;
      case UBRANIA:
        this.ubrania += ile;
        for (int i = 0; i < ile; i++) {
          this.listaUbran.add(new Ubranie(0, poziom));
        }
        break;
      case PROGRAMY:
        this.programy += ile;
        for (int i = 0; i < ile; i++) {
          this.listaProgramow.add(new Program(poziom));
        }
        break;
      default:
        break;
    }
  }

  public void odejmijZasob(Przedmiot przedmiot, int ile, int poziom) {
    switch (przedmiot) {
      case JEDZENIE:
        this.jedzenie -= ile;
        break;
      case NARZEDZIA:
        usunNarzedzia(ile, poziom);
        break;
      case UBRANIA:
        usunUbrania(ile, poziom);
        break;
      case PROGRAMY:
        usunProgramy(ile, poziom);
        break;
      default:
        break;
    }
  }
}
