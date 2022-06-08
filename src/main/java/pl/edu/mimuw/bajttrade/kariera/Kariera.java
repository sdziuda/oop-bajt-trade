package pl.edu.mimuw.bajttrade.kariera;

import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;

import java.util.List;

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

  public void setPoziom(int poziom) {
    this.poziom = poziom;
  }

  public void zwiekszPoziom() {
    poziom++;
  }

  public Przedmiot getPremiowanyPrzedmiot() {
    return this.premiowanyPrzedmiot;
  }

  public void usunPremie(List<Integer> p, int poziom) {
    if (poziom == 1) p.remove(Integer.valueOf(50));
    if (poziom == 2) p.remove(Integer.valueOf(150));
    if (poziom == 3) p.remove(Integer.valueOf(300));
    else p.remove(Integer.valueOf(300 + (poziom - 3) * 25));
  }

  public void dodajPremie(List<Integer> p, int poziom) {
    if (poziom == 1) p.add(50);
    if (poziom == 2) p.add(150);
    if (poziom == 3) p.add(300);
    else p.add(300 + (poziom - 3) * 25);
  }

  public int premia() {
    if (poziom == 1) return 50;
    if (poziom == 2) return 150;
    if (poziom == 3) return 300;
    else return 300 + (poziom - 3) * 25;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Kariera o = (Kariera) obj;
    return this.premiowanyPrzedmiot == o.premiowanyPrzedmiot;
  }
}
