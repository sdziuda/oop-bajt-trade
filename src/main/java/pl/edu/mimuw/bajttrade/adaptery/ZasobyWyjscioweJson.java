package pl.edu.mimuw.bajttrade.adaptery;

import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;
import pl.edu.mimuw.bajttrade.przedmioty.Zasoby;

public class ZasobyWyjscioweJson {
  public double diamenty;
  public int jedzenie;
  public int[] narzedzia;
  public int[] ubrania;
  public int[] programy;

  public ZasobyWyjscioweJson(Zasoby zasoby) {
    this.diamenty = zasoby.getDiamenty();
    this.jedzenie = zasoby.getIloscZasobow(Przedmiot.JEDZENIE);
    this.narzedzia = new int[zasoby.getNajwyzszyPoziomNarzedzia()];
    for (int i = 1; i <= zasoby.getNajwyzszyPoziomNarzedzia(); i++) {
      this.narzedzia[i - 1] = zasoby.iloscElementowDanegoPoziomu(i, Przedmiot.NARZEDZIA);
    }
    this.ubrania = new int[zasoby.getNajwyzszyPoziomUbrania()];
    for (int i = 1; i <= zasoby.getNajwyzszyPoziomUbrania(); i++) {
      this.ubrania[i - 1] = zasoby.iloscElementowDanegoPoziomu(i, Przedmiot.UBRANIA);
    }
    this.programy = new int[zasoby.getNajwyzszyPoziomProgramu()];
    for (int i = 1; i <= zasoby.getNajwyzszyPoziomProgramu(); i++) {
      this.programy[i - 1] = zasoby.iloscElementowDanegoPoziomu(i, Przedmiot.PROGRAMY);
    }
  }
}
