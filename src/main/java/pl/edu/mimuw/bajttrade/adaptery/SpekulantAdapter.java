package pl.edu.mimuw.bajttrade.adaptery;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import pl.edu.mimuw.bajttrade.agenci.spekulanci.*;

public class SpekulantAdapter {
  @ToJson
  public String toJson(Spekulant spekulant) {
    return spekulant.toString();
  }

  @FromJson
  public Spekulant fromJson(SpekulantJson spekulant) {
    switch (spekulant.kariera) {
      case "sredni":
        return new Sredni(spekulant.id, spekulant.zasoby, spekulant.historia_spekulanta_sredniego);
      case "wypukly":
        return new Wypukly(spekulant.id, spekulant.zasoby);
      case "regulujacy":
        return new RegulujacyRynek(spekulant.id, spekulant.zasoby);
      default:
        throw new RuntimeException();
    }
  }
}
