package pl.edu.mimuw.bajttrade.gielda;

import pl.edu.mimuw.bajttrade.gieldatyp.TypGieldy;
import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;

public class Info {
  private int dlugosc;
  private TypGieldy typGieldy;
  private int kara_za_brak_ubran;
  private Ceny ceny;

  public Info(int dlugosc, TypGieldy typGieldy, int kara_za_brak_ubran, Ceny ceny) {
    this.dlugosc = dlugosc;
    this.typGieldy = typGieldy;
    this.kara_za_brak_ubran = kara_za_brak_ubran;
    this.ceny = ceny;
  }

  public int getDlugosc() {
    return this.dlugosc;
  }

  public int getKaraZaBrakUbran() {
    return this.kara_za_brak_ubran;
  }

  public double getCena(Przedmiot p) {
    return this.ceny.getCena(p);
  }

  public TypGieldy getTypGieldy() {
    return this.typGieldy;
  }

  @Override
  public String toString() {
    var sb = new StringBuilder();

    sb.append("dlugosc: ").append(this.dlugosc).append("\n");
    sb.append("gielda: ").append(this.typGieldy).append("\n");
    sb.append("kara_za_brak_ubran: ").append(this.kara_za_brak_ubran).append("\n");
    sb.append("ceny:\n").append(this.ceny).append("\n");

    return sb.toString();
  }
}
