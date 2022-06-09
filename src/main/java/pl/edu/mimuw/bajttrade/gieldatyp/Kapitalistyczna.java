package pl.edu.mimuw.bajttrade.gieldatyp;

import pl.edu.mimuw.bajttrade.oferty.Oferta;
import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Kapitalistyczna implements TypGieldy {
  @Override
  public List<Oferta> kolejnosc(List<Oferta> ofertyRobotnikow, int dzien) {
    Comparator<Oferta> comparator = Comparator.comparingDouble((Oferta o) -> o.getWlasciciel().getDiamenty())
      .reversed().thenComparingInt(o -> o.getWlasciciel().getId());

    return ofertyRobotnikow.stream().sorted(comparator).collect(Collectors.toList());
  }

  @Override
  public String toString() {
    return "kapitalistyczna";
  }
}
