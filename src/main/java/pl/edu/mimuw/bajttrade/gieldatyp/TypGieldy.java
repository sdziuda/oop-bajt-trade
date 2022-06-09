package pl.edu.mimuw.bajttrade.gieldatyp;

import pl.edu.mimuw.bajttrade.oferty.Oferta;

import java.util.List;

public interface TypGieldy {

  List<Oferta> kolejnosc(List<Oferta> ofertyRobotnikow, int dzien);

}
