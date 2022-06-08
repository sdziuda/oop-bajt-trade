package pl.edu.mimuw.bajttrade.adaptery;

import pl.edu.mimuw.bajttrade.gielda.Ceny;

public class InfoJson {
  public int dlugosc;
  public String gielda;
  public int x;
  public Ceny ceny;

  public InfoJson(int dlugosc, String gielda, int x, Ceny ceny) {
    this.dlugosc = dlugosc;
    this.gielda = gielda;
    this.x = x;
    this.ceny = ceny;
  }
}
