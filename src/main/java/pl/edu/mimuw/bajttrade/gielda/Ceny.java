package pl.edu.mimuw.bajttrade.gielda;

public class Ceny {
  private double programy;
  private double jedzenie;
  private double ubrania;
  private double narzedzia;

  public Ceny(double programy, double jedzenie, double ubrania, double narzedzia) {
    this.programy = programy;
    this.jedzenie = jedzenie;
    this.ubrania = ubrania;
    this.narzedzia = narzedzia;
  }

  @Override
  public String toString() {
    var sb = new StringBuilder();

    sb.append("\t programy: ").append(this.programy).append("\n");
    sb.append("\t jedzenie: ").append(this.jedzenie).append("\n");
    sb.append("\t ubrania: ").append(this.ubrania).append("\n");
    sb.append("\t narzedzia: ").append(this.narzedzia).append("\n");

    return sb.toString();
  }
}
