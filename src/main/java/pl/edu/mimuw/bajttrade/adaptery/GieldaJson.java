package pl.edu.mimuw.bajttrade.adaptery;

import pl.edu.mimuw.bajttrade.gielda.Info;
import pl.edu.mimuw.bajttrade.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajttrade.agenci.spekulanci.Spekulant;

public class GieldaJson {
  public Info info;
  public Robotnik[] robotnicy;
  public Spekulant[] spekulanci;

  public GieldaJson(Info info, Robotnik[] robotnicy, Spekulant[] spekulanci) {
    this.info = info;
    this.robotnicy = robotnicy;
    this.spekulanci = spekulanci;
  }
}
