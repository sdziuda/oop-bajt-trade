package pl.edu.mimuw.bajttrade.adaptery;

public class KarieraSpekulantaJson {
  public String typ;
  public Integer historia_spekulanta_sredniego;

  public KarieraSpekulantaJson(String typ, int historia_spekulanta_sredniego) {
    this.typ = typ;
    this.historia_spekulanta_sredniego = typ.equals("sredni") ? historia_spekulanta_sredniego : null;
  }
}
