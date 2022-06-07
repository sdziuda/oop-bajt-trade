package pl.edu.mimuw.bajttrade.spekulanci;

import pl.edu.mimuw.bajttrade.oferty.Oferta;
import pl.edu.mimuw.bajttrade.przedmioty.Zasoby;

import java.util.List;

public abstract class Spekulant {
  private int id;
  protected Zasoby zasoby;

  protected Spekulant(int id, Zasoby zasoby) {
    this.id = id;
    this.zasoby = zasoby;
  }

  public abstract List<Oferta> coKupuje();

  public abstract List<Oferta> coSprzedaje();

  @Override
  public String toString() {
    return "\t id: " + this.id;
  }
}
