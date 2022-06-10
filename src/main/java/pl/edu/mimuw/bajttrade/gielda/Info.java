package pl.edu.mimuw.bajttrade.gielda;

import pl.edu.mimuw.bajttrade.gieldatyp.TypGieldy;
import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;

public class Info {
  private final int dlugosc;
  private final TypGieldy typGieldy;
  private final int kara_za_brak_ubran;
  private final Ceny ceny;

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

  public Ceny getCeny() {
    return this.ceny;
  }
}
