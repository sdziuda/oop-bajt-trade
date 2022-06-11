package pl.edu.mimuw.bajttrade.adaptery;

import com.squareup.moshi.Json;
import pl.edu.mimuw.bajttrade.gielda.Ceny;

public class InfoWyjscioweJson {
  public int dzien;
  @Json(name = "ceny_srednie")
  public Ceny cenySrednie;
  @Json(name = "ceny_max")
  public Ceny cenyMax;
  @Json(name = "ceny_min")
  public Ceny cenyMin;

  public InfoWyjscioweJson(int dzien, Ceny cenySrednie, Ceny cenyMax, Ceny cenyMin) {
    this.dzien = dzien;
    this.cenySrednie = cenySrednie;
    this.cenyMax = cenyMax;
    this.cenyMin = cenyMin;
  }
}
