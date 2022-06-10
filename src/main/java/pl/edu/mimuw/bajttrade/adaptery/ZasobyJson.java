package pl.edu.mimuw.bajttrade.adaptery;

import com.squareup.moshi.ToJson;

public class ZasobyJson {
  public double diamenty;
  public int ubrania;
  public int narzedzia;
  public int jedzenie;
  public int programy;

  public ZasobyJson(double diamenty, int ubrania, int narzedzia, int jedzenie, int programy) {
    this.diamenty = diamenty;
    this.ubrania = ubrania;
    this.narzedzia = narzedzia;
    this.jedzenie = jedzenie;
    this.programy = programy;
  }
}
