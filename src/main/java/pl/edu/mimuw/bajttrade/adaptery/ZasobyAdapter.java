package pl.edu.mimuw.bajttrade.adaptery;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;
import pl.edu.mimuw.bajttrade.przedmioty.Zasoby;

public class ZasobyAdapter {
  @ToJson
  public ZasobyWyjscioweJson zasobyToJson(Zasoby zasoby) {
    return new ZasobyWyjscioweJson(zasoby);
  }

  @FromJson
  public Zasoby fromJson(ZasobyJson zasoby) {
    return new Zasoby(zasoby.jedzenie, zasoby.diamenty, zasoby.narzedzia, zasoby.ubrania, zasoby.programy);
  }
}
