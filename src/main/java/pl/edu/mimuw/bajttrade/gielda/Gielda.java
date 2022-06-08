package pl.edu.mimuw.bajttrade.gielda;

import pl.edu.mimuw.bajttrade.oferty.Oferta;
import pl.edu.mimuw.bajttrade.robotnicy.*;
import pl.edu.mimuw.bajttrade.spekulanci.Spekulant;

import java.util.ArrayList;
import java.util.List;

public class Gielda {
  private Info info;
  private Robotnik[] robotnicy;
  private Spekulant[] spekulanci;
  private int dzien;
  private Historia historia;

  public Gielda(Info info, Robotnik[] robotnicy, Spekulant[] spekulanci){
    this.info = info;
    this.robotnicy = robotnicy;
    this.spekulanci = spekulanci;
    this.dzien = 1;
    this.historia = new Historia();
  }

  public void symuluj() {
    while (dzien <= info.getDlugosc()) {
      System.out.println(this);

      List<Robotnik> robotnicyPracujacy = new ArrayList<>();
      List<Oferta> ofertySprzedazyRobotnikow = new ArrayList<>();
      List<Oferta> ofertyKupnaRobotnikow = new ArrayList<>();

      for (var r : robotnicy) {
        r.rozegrajPierwszyEtap(historia, dzien, info, robotnicyPracujacy, ofertySprzedazyRobotnikow,
          ofertyKupnaRobotnikow);
      }

      System.out.println(ofertySprzedazyRobotnikow);
      System.out.println(ofertyKupnaRobotnikow);
      System.out.println(historia);

      for (var r : robotnicyPracujacy) {
        r.rozegrajKoniecDnia();
      }

      dzien++;
    }
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