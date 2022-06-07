package pl.edu.mimuw.bajttrade.kariera;

import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;

public class Programista extends Kariera {
  public Programista() {
    super(Przedmiot.PROGRAMY);
  }
  @Override
  public String toString() {
    return "programista";
  }
}
