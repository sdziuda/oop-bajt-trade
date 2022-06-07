package pl.edu.mimuw.bajttrade.kariera;

import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;

public abstract class Kariera {
  private int poziom;
  private final Przedmiot premiowanyPrzedmiot;

  protected Kariera(Przedmiot premiowanyPrzedmiot) {
    this.poziom = 1;
    this.premiowanyPrzedmiot = premiowanyPrzedmiot;
  }

  public int getPoziom() {
    return poziom;
  }

  public void zwiekszPoziom() {
    poziom++;
  }

  public Przedmiot getPremiowanyPrzedmiot() {
    return this.premiowanyPrzedmiot;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Kariera o = (Kariera) obj;
    return this.premiowanyPrzedmiot == o.premiowanyPrzedmiot;
  }
}
