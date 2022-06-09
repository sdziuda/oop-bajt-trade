package pl.edu.mimuw.bajttrade.adaptery;

public class ProdukcjaJson {
  public String typ;
  public int historia_sredniej_produkcji;
  public int historia_perspektywy;

  public ProdukcjaJson(String typ, int historia_sredniej_produkcji, int historia_perspektywy) {
    this.typ = typ;
    this.historia_sredniej_produkcji = historia_sredniej_produkcji;
    this.historia_perspektywy = historia_perspektywy;
  }
}
