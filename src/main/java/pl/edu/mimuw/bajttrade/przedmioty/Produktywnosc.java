package pl.edu.mimuw.bajttrade.przedmioty;

public class Produktywnosc {
  private final int programy;
  private final int narzedzia;
  private final int ubrania;
  private final int jedzenie;
  private final int diamenty;

  public Produktywnosc(int programy, int narzedzia, int ubrania,
                       int jedzenie, int diamenty) {
    this.programy = programy;
    this.narzedzia = narzedzia;
    this.ubrania = ubrania;
    this.jedzenie = jedzenie;
    this.diamenty = diamenty;
  }

  public int getProduktywnosc(Przedmiot p) {
    switch (p) {
      case PROGRAMY:
        return programy;
      case NARZEDZIA:
        return narzedzia;
      case UBRANIA:
        return ubrania;
      case JEDZENIE:
        return jedzenie;
      case DIAMENTY:
        return diamenty;
      default:
        return 0;
    }
  }

  @Override
  public String toString() {
    var sb = new StringBuilder();

    sb.append("\t\t programy: ").append(this.programy).append("\n");
    sb.append("\t\t narzedzia: ").append(this.narzedzia).append("\n");
    sb.append("\t\t jedzenie: ").append(this.jedzenie).append("\n");
    sb.append("\t\t diamenty: ").append(this.diamenty).append("\n");
    sb.append("\t\t ubrania: ").append(this.ubrania).append("\n");

    return sb.toString();
  }
}
