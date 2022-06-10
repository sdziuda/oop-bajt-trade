package pl.edu.mimuw.bajttrade.agenci.spekulanci;

import pl.edu.mimuw.bajttrade.agenci.Agent;
import pl.edu.mimuw.bajttrade.gielda.Historia;
import pl.edu.mimuw.bajttrade.gielda.Info;
import pl.edu.mimuw.bajttrade.oferty.Oferta;
import pl.edu.mimuw.bajttrade.oferty.OfertaSpekulanta;
import pl.edu.mimuw.bajttrade.przedmioty.Zasoby;

import java.util.List;

public abstract class Spekulant extends Agent {
  protected Spekulant(int id, Zasoby zasoby) {
    super(id, zasoby);
  }

  public abstract List<OfertaSpekulanta> coKupuje(Historia h, Info info, int dzien);

  public abstract List<OfertaSpekulanta> coSprzedaje(Historia h, Info info, int dzien);

  public int getHistoriaSpekulantaSredniego() {
    return 0;
  }

  @Override
  public String toString() {
    return "\t id: " + this.id;
  }
}
