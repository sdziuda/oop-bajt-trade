package pl.edu.mimuw.bajttrade.kariera;

import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;

public class Rzemieslnik extends Kariera {
  public Rzemieslnik() {
    super(Przedmiot.UBRANIA);
  }
  @Override
  public String toString() {
    return "rzemieslnik";
  }
}
