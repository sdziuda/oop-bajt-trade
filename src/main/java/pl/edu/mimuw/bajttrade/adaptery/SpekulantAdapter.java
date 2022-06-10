package pl.edu.mimuw.bajttrade.adaptery;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import pl.edu.mimuw.bajttrade.agenci.spekulanci.*;

public class SpekulantAdapter {
  @ToJson
  public SpekulantJson toJson(Spekulant spekulant) {
    return new SpekulantJson(spekulant.getId(), spekulant.toString(), spekulant.getZasoby(),
      spekulant.getHistoriaSpekulantaSredniego());
  }

  @FromJson
  public Spekulant fromJson(SpekulantJson spekulant) {
    switch (spekulant.kariera) {
      case "sredni":
        return new Sredni(spekulant.id, spekulant.zasoby, spekulant.historia_spekulanta_sredniego);
      case "wypukly":
        return new Wypukly(spekulant.id, spekulant.zasoby);
      case "regulujacy_rynek":
        return new RegulujacyRynek(spekulant.id, spekulant.zasoby);
      default:
        throw new RuntimeException();
    }
  }
}
