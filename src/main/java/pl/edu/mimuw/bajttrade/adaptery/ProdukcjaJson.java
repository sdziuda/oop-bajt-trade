package pl.edu.mimuw.bajttrade.adaptery;

public class ProdukcjaJson {
  public String typ;
  public Integer historia_sredniej_produkcji;
  public Integer historia_perspektywy;

  public ProdukcjaJson(String typ, int historia_sredniej_produkcji, int historia_perspektywy) {
    this.typ = typ;
    this.historia_sredniej_produkcji = typ.equals("sredniak") ? historia_sredniej_produkcji : null;
    this.historia_perspektywy = typ.equals("perspektywiczny") ? historia_perspektywy : null;
  }
}
