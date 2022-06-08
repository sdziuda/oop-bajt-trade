package pl.edu.mimuw.bajttrade.adaptery;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import pl.edu.mimuw.bajttrade.robotnicy.*;

public class RobotnikAdapter {
  @ToJson
  public String toJson(Robotnik robotnik) {
    return robotnik.toString();
  }

  @FromJson
  public Robotnik fromJson(RobotnikJson robotnik) {
    switch (robotnik.produkcja) {
      case "chciwy":
        return new Chciwy(robotnik.id, robotnik.poziom, robotnik.kariera, robotnik.kupowanie, robotnik.uczenie,
          robotnik.zmiana, robotnik.produktywnosc, robotnik.zasoby);
      case "krotkowzroczny":
        return new Krotkowzroczny(robotnik.id, robotnik.poziom, robotnik.kariera, robotnik.kupowanie, robotnik.uczenie,
          robotnik.zmiana, robotnik.produktywnosc, robotnik.zasoby);
      case "losowy":
        return new Losowy(robotnik.id, robotnik.poziom, robotnik.kariera, robotnik.kupowanie, robotnik.uczenie,
          robotnik.zmiana, robotnik.produktywnosc, robotnik.zasoby);
      case "perspektywiczny":
        return new Perspektywiczny(robotnik.id, robotnik.poziom, robotnik.kariera, robotnik.kupowanie, robotnik.uczenie,
          robotnik.zmiana, robotnik.produktywnosc, robotnik.zasoby, robotnik.historia_perspektywy);
      case "sredniak":
        return new Sredniak(robotnik.id, robotnik.poziom, robotnik.kariera, robotnik.kupowanie, robotnik.uczenie,
          robotnik.zmiana, robotnik.produktywnosc, robotnik.zasoby, robotnik.historia_sredniej_produkcji);
      default:
        throw new RuntimeException();
    }
  }
}