package pl.edu.mimuw.bajttrade.adaptery;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import pl.edu.mimuw.bajttrade.kupowanie.*;

public class KupowanieAdapter {
  @ToJson
  public KupowanieJson kupowanieToJson(Kupowanie kupowanie) {
    return new KupowanieJson(kupowanie.toString(), kupowanie.getLiczbaNarzedzi());
  }

  @FromJson
  public Kupowanie fromJson(KupowanieJson kupowanie) {
    switch (kupowanie.typ) {
      case "czyscioszek":
        return new Czyscioszek();
      case "gadzeciarz":
        return new Gadzeciarz(kupowanie.liczba_narzedzi);
      case "technofob":
        return new Technofob();
      case "zmechanizowany":
        return new Zmechanizowany(kupowanie.liczba_narzedzi);
      default:
        throw new RuntimeException();
    }
  }
}
