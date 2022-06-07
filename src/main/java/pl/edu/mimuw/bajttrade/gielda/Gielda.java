package pl.edu.mimuw.bajttrade.gielda;

import pl.edu.mimuw.bajttrade.robotnicy.*;
import pl.edu.mimuw.bajttrade.spekulanci.Spekulant;

public class Gielda {
  private Info info;
  private Robotnik[] robotnicy;
  private Spekulant[] spekulanci;

  public Gielda(Info info, Robotnik[] robotnicy, Spekulant[] spekulanci){
    this.info = info;
    this.robotnicy = robotnicy;
    this.spekulanci = spekulanci;
  }

  @Override
  public String toString() {
    var sb = new StringBuilder();

    sb.append(info.toString()).append("robotnicy:\n");
    for (var r : robotnicy) sb.append(r.toString());
    sb.append("\nspekulanci:\n");
    for (var s: spekulanci) sb.append(s.toString());


    return sb.toString();
  }
}
