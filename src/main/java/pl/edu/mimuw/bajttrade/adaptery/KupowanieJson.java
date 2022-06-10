package pl.edu.mimuw.bajttrade.adaptery;

public class KupowanieJson {
  public String typ;
  public Integer liczba_narzedzi;

  public KupowanieJson(String typ, int liczba_narzedzi) {
    this.typ = typ;
    this.liczba_narzedzi = typ.equals("gadzeciarz") || typ.equals("zmechanizowany") ? liczba_narzedzi : null;
  }
}
