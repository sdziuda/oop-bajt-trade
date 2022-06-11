package pl.edu.mimuw.bajttrade.adaptery;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import pl.edu.mimuw.bajttrade.agenci.robotnicy.*;

public class RobotnikAdapter {
  @ToJson
  public RobotnikJson toJson(Robotnik robotnik) {
    return new RobotnikJson(robotnik.getId(), robotnik.getAktywnaKariera().getPoziom(), robotnik.getAktywnaKariera(),
      robotnik.getKupowanie(), new ProdukcjaJson(robotnik.toString(), robotnik.getHistoriaSredniejProdukcji(),
      robotnik.getHistoriaPerspektywy()), robotnik.getUczenie(), robotnik.getZmiana(), robotnik.getCalaProduktywnosc(),
      robotnik.getZasoby());
  }

  @FromJson
  public Robotnik fromJson(RobotnikJson robotnik) {
    switch (robotnik.produkcja.typ) {
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
          robotnik.zmiana, robotnik.produktywnosc, robotnik.zasoby, robotnik.produkcja.historia_perspektywy);
      case "sredniak":
        return new Sredniak(robotnik.id, robotnik.poziom, robotnik.kariera, robotnik.kupowanie, robotnik.uczenie,
          robotnik.zmiana, robotnik.produktywnosc, robotnik.zasoby, robotnik.produkcja.historia_sredniej_produkcji);
      default:
        throw new RuntimeException("Nieznany typ robotnika");
    }
  }
}
