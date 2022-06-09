package pl.edu.mimuw.bajttrade.uczenie;

import pl.edu.mimuw.bajttrade.gielda.Historia;
import pl.edu.mimuw.bajttrade.gielda.Info;
import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;
import pl.edu.mimuw.bajttrade.agenci.robotnicy.Robotnik;

public class Oszczedny implements Uczenie {
  private final int limitDiamentow;

  public Oszczedny(int limitDiamentow) {
    this.limitDiamentow = limitDiamentow;
  }

  @Override
  public boolean czySieUczy(Robotnik r, Historia h, Info info, int dzien) {
    return r.getDiamenty() > limitDiamentow;
  }

  @Override
  public String toString() {
    return "oszczedny";
  }
}
