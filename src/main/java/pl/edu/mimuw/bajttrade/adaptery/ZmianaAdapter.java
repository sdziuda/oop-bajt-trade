package pl.edu.mimuw.bajttrade.adaptery;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import pl.edu.mimuw.bajttrade.zmiana.*;

public class ZmianaAdapter {
  @ToJson
  public String toJson(Zmiana zmiana) {
    return zmiana.toString();
  }

  @FromJson
  public Zmiana fromJson(String zmiana) {
    switch (zmiana) {
      case "konserwatysta":
        return new Konserwatysta();
      case "rewolucjonista":
        return new Rewolucjonista();
      default:
        throw new RuntimeException("Nieznana zmiana: " + zmiana);
    }
  }
}
