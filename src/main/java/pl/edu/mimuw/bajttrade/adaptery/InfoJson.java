package pl.edu.mimuw.bajttrade.adaptery;

import pl.edu.mimuw.bajttrade.gielda.Ceny;

public class InfoJson {
  public int dlugosc;
  public String gielda;
  public int kara_za_brak_ubran;
  public Ceny ceny;

  public InfoJson(int dlugosc, String gielda, int kara_za_brak_ubran, Ceny ceny) {
    this.dlugosc = dlugosc;
    this.gielda = gielda;
    this.kara_za_brak_ubran = kara_za_brak_ubran;
    this.ceny = ceny;
  }
}
