package pl.edu.mimuw.bajttrade.adaptery;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import pl.edu.mimuw.bajttrade.kariera.*;

public class KarieraAdapter {
  @ToJson
  public String toJson(Kariera kariera) {
    return kariera.toString();
  }

  @FromJson
  public Kariera fromJson(String kariera) {
    switch (kariera) {
      case "programista":
        return new Programista();
      case "gornik":
        return new Gornik();
      case "inzynier":
        return new Inzynier();
      case "rolnik":
        return new Rolnik();
      case "rzemieslnik":
        return new Rzemieslnik();
      default:
        throw new RuntimeException("Nieznany typ kariery: " + kariera);
    }
  }
}
