package pl.edu.mimuw.bajttrade.gieldatyp;

import pl.edu.mimuw.bajttrade.oferty.Oferta;
import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Zrownowazona implements TypGieldy {
  @Override
  public List<Oferta> kolejnosc(List<Oferta> ofertyRobotnikow, int dzien) {
    Comparator<Oferta> comparator;
    if (dzien % 2 == 0) {
      comparator = Comparator.comparingDouble(o -> o.getWlasciciel().getDiamenty());
    } else {
      comparator = Comparator.comparingDouble((Oferta o) -> o.getWlasciciel().getDiamenty()).reversed();
    }
    comparator = comparator.thenComparingInt(o -> o.getWlasciciel().getId());

    return ofertyRobotnikow.stream().sorted(comparator).collect(Collectors.toList());
  }

  @Override
  public String toString() {
    return "zrownowazona";
  }
}
