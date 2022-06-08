package pl.edu.mimuw.bajttrade.gielda;

import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;

public class Ceny {
  private final double programy;
  private final double jedzenie;
  private final double ubrania;
  private final double narzedzia;

  public Ceny(double programy, double jedzenie, double ubrania, double narzedzia) {
    this.programy = programy;
    this.jedzenie = jedzenie;
    this.ubrania = ubrania;
    this.narzedzia = narzedzia;
  }

  public double getCena(Przedmiot p) {
    switch (p) {
      case PROGRAMY:
        return programy;
      case JEDZENIE:
        return jedzenie;
      case UBRANIA:
        return ubrania;
      case NARZEDZIA:
        return narzedzia;
      default:
        throw new RuntimeException();
    }
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
