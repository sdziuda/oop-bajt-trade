package pl.edu.mimuw.bajttrade.adaptery;

import pl.edu.mimuw.bajttrade.przedmioty.Zasoby;

public class SpekulantJson {
  public int id;
  public KarieraSpekulantaJson kariera;
  public Zasoby zasoby;

  public SpekulantJson(int id, KarieraSpekulantaJson kariera, Zasoby zasoby) {
    this.id = id;
    this.kariera = kariera;
    this.zasoby = zasoby;
  }
}
