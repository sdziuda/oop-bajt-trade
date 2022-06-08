package pl.edu.mimuw.bajttrade.adaptery;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import pl.edu.mimuw.bajttrade.gielda.Gielda;
import pl.edu.mimuw.bajttrade.gielda.Info;
import pl.edu.mimuw.bajttrade.gieldatyp.Kapitalistyczna;
import pl.edu.mimuw.bajttrade.gieldatyp.Socjalistyczna;
import pl.edu.mimuw.bajttrade.gieldatyp.Zrownowazona;

public class GieldaAdapter {
  @ToJson
  public String toJson(Gielda gielda) {
    return gielda.toString();
  }

  @FromJson
  public Gielda fromJson(GieldaJson gielda) {
    return new Gielda(gielda.info, gielda.robotnicy, gielda.spekulanci);
  }
}
