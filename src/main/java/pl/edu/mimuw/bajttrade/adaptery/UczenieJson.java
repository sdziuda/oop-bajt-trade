package pl.edu.mimuw.bajttrade.adaptery;

public class UczenieJson {
  public String typ;
  public int okresowosc_nauki;
  public int limit_diamentow;
  public int zapas;
  public int okres;

  public UczenieJson(String typ, int okresowosc_nauki, int limit_diamentow, int zapas, int okres) {
    this.typ = typ;
    this.okresowosc_nauki = okresowosc_nauki;
    this.limit_diamentow = limit_diamentow;
    this.zapas = zapas;
    this.okres = okres;
  }
}
