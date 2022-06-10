package pl.edu.mimuw.bajttrade.adaptery;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import pl.edu.mimuw.bajttrade.gielda.Gielda;

public class GieldaAdapter {
  @ToJson
  public GieldaWyjscieJson gieldaToJson(Gielda gielda) {
    return new GieldaWyjscieJson(gielda);
  }

  @FromJson
  public Gielda fromJson(GieldaWejscieJson gielda) {
    return new Gielda(gielda.info, gielda.robotnicy, gielda.spekulanci);
  }
}
