package pl.edu.mimuw.bajttrade.kupowanie;

import pl.edu.mimuw.bajttrade.oferty.Oferta;
import pl.edu.mimuw.bajttrade.robotnicy.Robotnik;

import java.util.List;

public interface Kupowanie {
  List<Oferta> coKupuje(Robotnik r, int dzien);
}
