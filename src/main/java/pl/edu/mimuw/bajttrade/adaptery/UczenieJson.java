package pl.edu.mimuw.bajttrade.adaptery;

public class UczenieJson {
  public String typ;
  public Integer okresowosc_nauki;
  public Integer limit_diamentow;
  public Integer zapas;
  public Integer okres;

  public UczenieJson(String typ, int okresowosc_nauki, int limit_diamentow, int zapas, int okres) {
    this.typ = typ;
    this.okresowosc_nauki = typ.equals("okresowy") ? okresowosc_nauki : null;
    this.limit_diamentow = typ.equals("oszczedny") ? limit_diamentow : null;
    this.zapas = typ.equals("student") ? zapas : null;
    this.okres = typ.equals("student") ? okres : null;
  }
}
