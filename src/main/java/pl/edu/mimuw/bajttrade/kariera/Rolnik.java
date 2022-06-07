package pl.edu.mimuw.bajttrade.kariera;

import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;

public class Rolnik extends Kariera {
  public Rolnik() {
    super(Przedmiot.JEDZENIE);
  }
  @Override
  public String toString() {
    return "rolnik";
  }
}
