package pl.edu.mimuw.bajttrade.adaptery;

import pl.edu.mimuw.bajttrade.kariera.Kariera;
import pl.edu.mimuw.bajttrade.kupowanie.Kupowanie;
import pl.edu.mimuw.bajttrade.przedmioty.Produktywnosc;
import pl.edu.mimuw.bajttrade.przedmioty.Zasoby;
import pl.edu.mimuw.bajttrade.uczenie.Uczenie;
import pl.edu.mimuw.bajttrade.zmiana.Zmiana;

public class RobotnikJson {
  public int id;
  public int poziom;
  public Kariera kariera;
  public Kupowanie kupowanie;
  public ProdukcjaJson produkcja;
  public Uczenie uczenie;
  public Zmiana zmiana;
  public Produktywnosc produktywnosc;
  public Zasoby zasoby;

  public RobotnikJson(int id, int poziom, Kariera kariera, Kupowanie kupowanie, ProdukcjaJson produkcja, Uczenie uczenie,
                      Zmiana zmiana, Produktywnosc produktywnosc, Zasoby zasoby) {
    this.id = id;
    this.poziom = poziom;
    this.kariera = kariera;
    this.kupowanie = kupowanie;
    this.produkcja = produkcja;
    this.uczenie = uczenie;
    this.zmiana = zmiana;
    this.produktywnosc = produktywnosc;
    this.zasoby = zasoby;
  }
}
