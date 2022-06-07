package pl.edu.mimuw.bajttrade.spekulanci;

import pl.edu.mimuw.bajttrade.oferty.Oferta;
import pl.edu.mimuw.bajttrade.przedmioty.Zasoby;

import java.util.List;

public class Sredni extends Spekulant {
  private int historiaSpekulantaSredniego;

  public Sredni(int id, Zasoby zasoby, int historiaSpekulantaSredniego) {
    super(id, zasoby);
    this.historiaSpekulantaSredniego = historiaSpekulantaSredniego;
  }

  @Override
  public List<Oferta> coKupuje() {
    return null;
  }

  @Override
  public List<Oferta> coSprzedaje() {
    return null;
  }

  @Override
  public String toString() {
    var sb = new StringBuilder();

    sb.append(super.toString()).append("\n");
    sb.append("\t kariera: sredni\n");
    sb.append("\t zasoby:\n").append(this.zasoby);

    return sb.toString();
  }
}
