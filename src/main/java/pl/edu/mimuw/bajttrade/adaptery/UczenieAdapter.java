package pl.edu.mimuw.bajttrade.adaptery;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import pl.edu.mimuw.bajttrade.uczenie.*;

public class UczenieAdapter {
  @ToJson
  public UczenieJson uczenieToJson(Uczenie uczenie) {
    return new UczenieJson(uczenie.toString(), uczenie.getOkresowoscNauki(), uczenie.getLimitDiamentow(),
      uczenie.getZapas(), uczenie.getOkres());
  }

  @FromJson
  public Uczenie fromJson(UczenieJson uczenie) {
    switch (uczenie.typ) {
      case "okresowy":
        return new Okresowy(uczenie.okresowosc_nauki);
      case "oszczedny":
        return new Oszczedny(uczenie.limit_diamentow);
      case "pracus":
        return new Pracus();
      case "rozkladowy":
        return new Rozkladowy();
      case "student":
        return new Student(uczenie.zapas, uczenie.okres);
      default:
        throw new RuntimeException("Nieznany typ uczenia: " + uczenie.typ);
    }
  }
}
