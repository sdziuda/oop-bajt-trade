package pl.edu.mimuw.bajttrade.uczenie;

import pl.edu.mimuw.bajttrade.Historia;
import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;
import pl.edu.mimuw.bajttrade.robotnicy.Robotnik;

public class Oszczedny implements Uczenie {
  private final int limitDiamentow;

  public Oszczedny(int limitDiamentow) {
    this.limitDiamentow = limitDiamentow;
  }

  @Override
  public boolean czySieUczy(Robotnik r, Historia h, int dzien) {
    return r.getIloscZasobow(Przedmiot.DIAMENTY) > limitDiamentow;
  }

  @Override
  public String toString() {
    return "oszczedny";
  }
}
