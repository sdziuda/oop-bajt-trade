package pl.edu.mimuw.bajttrade.oferty;

import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;

public abstract class Oferta {
  private final int dzien;
  private final int ilosc;
  private final int poziom;
  private final Przedmiot przedmiot;

  protected Oferta(int dzien, int ilosc, int poziom, Przedmiot przedmiot) {
    this.dzien = dzien;
    this.ilosc = ilosc;
    this.poziom = poziom;
    this.przedmiot = przedmiot;
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

  @Override
  public String toString() {
    var sb = new StringBuilder();

    sb.append("dzien: ").append(dzien).append(" ilosc: ").append(ilosc).append(" poziom: ").append(poziom)
      .append(" przedmiot: ").append(przedmiot);

    return sb.toString();
  }
}
