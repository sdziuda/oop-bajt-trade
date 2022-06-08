package pl.edu.mimuw.bajttrade.adaptery;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import pl.edu.mimuw.bajttrade.gielda.Info;
import pl.edu.mimuw.bajttrade.gieldatyp.*;

public class InfoAdapter {
  @ToJson
  public String toJson(Info info) {
    return info.toString();
  }

  @FromJson
  public Info fromJson(InfoJson info) {
    switch (info.gielda) {
      case "socjalistyczna":
        return new Info(info.dlugosc, new Socjalistyczna(), info.x, info.ceny);
      case "kapitalistyczna":
        return new Info(info.dlugosc, new Kapitalistyczna(), info.x, info.ceny);
      case "zrownowazona":
        return new Info(info.dlugosc, new Zrownowazona(), info.x, info.ceny);
      default:
        throw new RuntimeException();
    }
  }
}
