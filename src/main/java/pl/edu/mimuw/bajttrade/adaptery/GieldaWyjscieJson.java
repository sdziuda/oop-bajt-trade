package pl.edu.mimuw.bajttrade.adaptery;

import pl.edu.mimuw.bajttrade.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajttrade.agenci.spekulanci.Spekulant;
import pl.edu.mimuw.bajttrade.gielda.Gielda;

public class GieldaWyjscieJson {
  public InfoWyjscioweJson info;
  public Robotnik[] robotnicy;
  public Spekulant[] spekulanci;

  public GieldaWyjscieJson(Gielda g) {
    this.info = g.generujInfoWyjsciowe();
    this.robotnicy = g.getRobotnicy();
    this.spekulanci = g.getSpekulanci();
  }
}
