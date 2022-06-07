package pl.edu.mimuw.bajttrade.kariera;

import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;

public class Inzynier extends Kariera {
  public Inzynier() {
    super(Przedmiot.NARZEDZIA);
  }

  @Override
  public String toString() {
    return "inzynier";
  }
}
