package pl.edu.mimuw.bajttrade.oferty;

import pl.edu.mimuw.bajttrade.agenci.Agent;
import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;

public abstract class Oferta {
  private final int dzien;
  private int ilosc;
  private final int poziom;
  private final Przedmiot przedmiot;
  private final Agent wlasciciel;

  protected Oferta(int dzien, int ilosc, int poziom, Przedmiot przedmiot, Agent wlasciciel) {
    this.dzien = dzien;
    this.ilosc = ilosc;
    this.poziom = poziom;
    this.przedmiot = przedmiot;
    this.wlasciciel = wlasciciel;
  }

  public int getDzien() {
    return dzien;
  }

  public int getIlosc() {
    return ilosc;
  }

  public int getPoziom() {
    return poziom;
  }

  public Przedmiot getPrzedmiot() {
    return przedmiot;
  }

  public Agent getWlasciciel() {
    return wlasciciel;
  }

  @Override
  public String toString() {
    var sb = new StringBuilder();

    sb.append("dzien: ").append(dzien).append(" ilosc: ").append(ilosc).append(" poziom: ").append(poziom)
      .append(" przedmiot: ").append(przedmiot).append(" wlasciciel: ").append(wlasciciel.getId());

    return sb.toString();
  }

  public void odejmij(double ile) {
    this.ilosc -= ile;
  }
}
