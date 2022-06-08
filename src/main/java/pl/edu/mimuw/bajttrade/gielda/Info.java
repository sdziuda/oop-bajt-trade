package pl.edu.mimuw.bajttrade.gielda;

import pl.edu.mimuw.bajttrade.gieldatyp.TypGieldy;
import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;

public class Info {
  private int dlugosc;
  private TypGieldy typGieldy;
  private int x;
  private Ceny ceny;

  public Info(int dlugosc, TypGieldy typGieldy, int x, Ceny ceny) {
    this.dlugosc = dlugosc;
    this.typGieldy = typGieldy;
    this.x = x;
    this.ceny = ceny;
  }

  public int getDlugosc() {
    return this.dlugosc;
  }

  public int getX() {
    return this.x;
  }

  public double getCena(Przedmiot p) {
    return this.ceny.getCena(p);
  }

  @Override
  public String toString() {
    var sb = new StringBuilder();

    sb.append("dlugosc: ").append(this.dlugosc).append("\n");
    sb.append("gielda: ").append(this.typGieldy).append("\n");
    sb.append("x: ").append(this.x).append("\n");
    sb.append("ceny:\n").append(this.ceny).append("\n");

    return sb.toString();
  }
}
