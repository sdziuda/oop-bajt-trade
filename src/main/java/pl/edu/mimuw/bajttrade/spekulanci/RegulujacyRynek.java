package pl.edu.mimuw.bajttrade.spekulanci;

import pl.edu.mimuw.bajttrade.oferty.Oferta;
import pl.edu.mimuw.bajttrade.przedmioty.Zasoby;

import java.util.List;

public class RegulujacyRynek extends Spekulant {
  public RegulujacyRynek(int id, Zasoby zasoby) {
    super(id, zasoby);
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
    sb.append("\t kariera: regulujacy_rynek\n");
    sb.append("\t zasoby:\n").append(this.zasoby);

    return sb.toString();
  }
}
