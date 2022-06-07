package pl.edu.mimuw.bajttrade.gielda;

public class Info {
  private int dlugosc;
  private String gielda;
  private int x;
  private Ceny ceny;

  public Info(int dlugosc, String gielda, int x, Ceny ceny) {
    this.dlugosc = dlugosc;
    this.gielda = gielda;
    this.x = x;
    this.ceny = ceny;
  }

  @Override
  public String toString() {
    var sb = new StringBuilder();

    sb.append("dlugosc: ").append(this.dlugosc).append("\n");
    sb.append("typ: ").append(this.gielda).append("\n");
    sb.append("x: ").append(this.x).append("\n");
    sb.append("ceny:\n").append(this.ceny).append("\n");

    return sb.toString();
  }
}
