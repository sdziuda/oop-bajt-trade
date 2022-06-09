package pl.edu.mimuw.bajttrade.kupowanie;

import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;
import pl.edu.mimuw.bajttrade.oferty.Oferta;
import pl.edu.mimuw.bajttrade.oferty.OfertaRobotnika;
import pl.edu.mimuw.bajttrade.agenci.robotnicy.Robotnik;

import java.util.Collections;
import java.util.List;

public class Technofob implements Kupowanie {
  @Override
  public List<Oferta> coKupuje(Robotnik r, int dzien) {
    return Collections.singletonList(new OfertaRobotnika(dzien, 100, 1, Przedmiot.JEDZENIE, r));
  }

  @Override
  public String toString() {
    return "technofob";
  }
}
