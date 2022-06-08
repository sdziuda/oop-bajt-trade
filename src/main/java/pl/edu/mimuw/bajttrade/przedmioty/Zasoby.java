package pl.edu.mimuw.bajttrade.przedmioty;

import java.util.ArrayList;
import java.util.List;

public class Zasoby {
  private int jedzenie;
  private int diamenty;
  private int ubrania;
  private int narzedzia;
  private int programy;
  private List<Narzedzie> listaNarzedzi;
  private List<Ubranie> listaUbran;
  private List<Program> listaProgramow;

  public Zasoby(int jedzenie, int diamenty, int narzedzia, int ubrania, int programy) {
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
      case DIAMENTY:
        return diamenty;
      case NARZEDZIA:
        return narzedzia;
      case UBRANIA:
        return ubrania;
      case PROGRAMY:
        return programy;
      default:
        return 0;
    }
  }

  public int getSumaIlosciZasobow() {
    return jedzenie + diamenty + narzedzia + ubrania + programy;
  }

  public void dodajDiamenty(int ilosc) {
    this.diamenty += ilosc;
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

  public int iloscProgramowDanegoPoziomu(int poziom) {
    int wynik = 0;
    for (var p : listaProgramow) {
      if (p.getPoziom() == poziom) wynik++;
    }
    return wynik;
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

  public void usunNarzedzia() {
    this.narzedzia = 0;
    this.listaNarzedzi = new ArrayList<>();
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

  @Override
  public String toString() {
    var sb = new StringBuilder();

    sb.append("\t\t programy: ").append(this.programy).append("\n");
    sb.append("\t\t narzedzia: ").append(this.narzedzia).append("\n");
    sb.append("\t\t jedzenie: ").append(this.jedzenie).append("\n");
    sb.append("\t\t diamenty: ").append(this.diamenty).append("\n");
    sb.append("\t\t ubrania: ").append(this.ubrania).append("\n");

    return sb.toString();
  }
}
