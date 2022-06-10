package pl.edu.mimuw.bajttrade.adaptery;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import pl.edu.mimuw.bajttrade.gielda.Info;
import pl.edu.mimuw.bajttrade.gieldatyp.*;

public class InfoAdapter {
  @ToJson
  public InfoJson toJson(Info info) {
    return new InfoJson(info.getDlugosc(), info.getTypGieldy().toString(), info.getKaraZaBrakUbran(), info.getCeny());
  }

  @FromJson
  public Info fromJson(InfoJson info) {
    switch (info.gielda) {
      case "socjalistyczna":
        return new Info(info.dlugosc, new Socjalistyczna(), info.kara_za_brak_ubran, info.ceny);
      case "kapitalistyczna":
        return new Info(info.dlugosc, new Kapitalistyczna(), info.kara_za_brak_ubran, info.ceny);
      case "zrownowazona":
        return new Info(info.dlugosc, new Zrownowazona(), info.kara_za_brak_ubran, info.ceny);
      default:
        throw new RuntimeException();
    }
  }
}
