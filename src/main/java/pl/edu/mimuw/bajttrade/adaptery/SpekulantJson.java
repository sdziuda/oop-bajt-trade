package pl.edu.mimuw.bajttrade.adaptery;

import pl.edu.mimuw.bajttrade.przedmioty.Zasoby;

public class SpekulantJson {
  public int id;
  public String kariera;
  public Zasoby zasoby;
  public int historia_spekulanta_sredniego;

  public SpekulantJson(int id, String kariera, Zasoby zasoby, int historia_spekulanta_sredniego) {
    this.id = id;
    this.kariera = kariera;
    this.zasoby = zasoby;
    this.historia_spekulanta_sredniego = historia_spekulanta_sredniego;
  }
}
