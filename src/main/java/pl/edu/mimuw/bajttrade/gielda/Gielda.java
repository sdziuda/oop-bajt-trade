package pl.edu.mimuw.bajttrade.gielda;

import pl.edu.mimuw.bajttrade.oferty.Oferta;
import pl.edu.mimuw.bajttrade.agenci.robotnicy.*;
import pl.edu.mimuw.bajttrade.agenci.spekulanci.Spekulant;
import pl.edu.mimuw.bajttrade.oferty.OfertaSpekulanta;
import pl.edu.mimuw.bajttrade.oferty.Rachunek;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Gielda {
  private final Info info;
  private final Robotnik[] robotnicy;
  private final Spekulant[] spekulanci;
  private final Historia historia;
  private int dzien;

  public Gielda(Info info, Robotnik[] robotnicy, Spekulant[] spekulanci) {
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
      List<OfertaSpekulanta> ofertySprzedazySpekulantow = new ArrayList<>();
      List<OfertaSpekulanta> ofertyKupnaSpekulantow = new ArrayList<>();

      for (var r : robotnicy) {
        r.rozegrajPierwszyEtap(historia, dzien, info, robotnicyPracujacy, ofertySprzedazyRobotnikow,
          ofertyKupnaRobotnikow);
      }

      ofertySprzedazyRobotnikow = info.getTypGieldy().kolejnosc(ofertySprzedazyRobotnikow, dzien);
      ofertyKupnaRobotnikow = info.getTypGieldy().kolejnosc(ofertyKupnaRobotnikow, dzien);

      for (var s : spekulanci) {
        ofertyKupnaSpekulantow.addAll(s.coKupuje(historia, info, dzien));
        ofertySprzedazySpekulantow.addAll(s.coSprzedaje(historia, info, dzien));
      }

      ofertySprzedazySpekulantow = kolejnoscOfertSpekulanta(ofertySprzedazySpekulantow);
      ofertyKupnaSpekulantow = kolejnoscOfertSpekulanta(ofertyKupnaSpekulantow);

      System.out.println("Oferty sprzedazy robotnikow: " + ofertySprzedazyRobotnikow);
      System.out.println("Oferty kupna robotnikow: " + ofertyKupnaRobotnikow);
      System.out.println("Oferty sprzedazy spekulantow: " + ofertySprzedazySpekulantow);
      System.out.println("Oferty kupna spekulantow: " + ofertyKupnaSpekulantow);
      //System.out.println("Historia:\n" + historia);

      dopasujOfertySprzedazyRobotnikow(ofertySprzedazyRobotnikow, ofertyKupnaSpekulantow);
      dopasujOfertyKupnaRobotnikow(ofertyKupnaRobotnikow, ofertySprzedazySpekulantow);

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
    for (var s : spekulanci) sb.append(s.toString());


    return sb.toString();
  }

  private void dopasujOfertySprzedazyRobotnikow(List<Oferta> ofertyRobotnikow, List<OfertaSpekulanta> ofertySpekulantow) {
    int i = 0;
    while (i < ofertyRobotnikow.size()) {
      var o = ofertyRobotnikow.get(i);

      dopasujSprzedaz(o, ofertySpekulantow);
      if (o.getIlosc() == 0) ofertyRobotnikow.remove(o);

      i++;
    }
  }

  private void dopasujSprzedaz(Oferta o, List<OfertaSpekulanta> ofertySpekulantow) {
    for (int i = 0; i < ofertySpekulantow.size(); i++) {
      var ofertaSpekulanta = ofertySpekulantow.get(i);
      if (o.getPrzedmiot() == ofertaSpekulanta.getPrzedmiot() && o.getPoziom() == ofertaSpekulanta.getPoziom()) {
        int mozeKupic = (int) Math.floor(ofertaSpekulanta.getWlasciciel().getDiamenty() / ofertaSpekulanta.getCena());

        if (mozeKupic > 0) {
          if (mozeKupic > ofertaSpekulanta.getIlosc()) mozeKupic = ofertaSpekulanta.getIlosc();
          if (mozeKupic > o.getIlosc()) mozeKupic = o.getIlosc();

          o.odejmij(mozeKupic);
          ofertaSpekulanta.odejmij(mozeKupic);
          ofertaSpekulanta.getWlasciciel().odejmijDiamenty(mozeKupic * ofertaSpekulanta.getCena());
          o.getWlasciciel().dodajDiamenty(mozeKupic * ofertaSpekulanta.getCena());
          ofertaSpekulanta.getWlasciciel().dodajZasob(o.getPrzedmiot(), mozeKupic, o.getPoziom());
          historia.dodajZfinalizowana(new Rachunek(dzien, mozeKupic, ofertaSpekulanta.getCena(), o.getPrzedmiot()));
        }

        if (o.getIlosc() == 0) return;
        if (ofertaSpekulanta.getIlosc() == 0) ofertySpekulantow.remove(i);
      }
    }
  }

  private void dopasujOfertyKupnaRobotnikow(List<Oferta> ofertyRobotnikow, List<OfertaSpekulanta> ofertySpekulantow) {
    int i = 0;
    while (i < ofertyRobotnikow.size()) {
      var o = ofertyRobotnikow.get(i);

      dopasujKupno(o, ofertySpekulantow);
      if (o.getIlosc() == 0) ofertyRobotnikow.remove(o);

      i++;
    }
  }

  private void dopasujKupno(Oferta o, List<OfertaSpekulanta> ofertySpekulantow) {
    for (int i = 0; i < ofertySpekulantow.size(); i++) {
      var ofertaSpekulanta = ofertySpekulantow.get(i);
      if (o.getPrzedmiot() == ofertaSpekulanta.getPrzedmiot() && o.getPoziom() == ofertaSpekulanta.getPoziom()) {
        int mozeKupic = (int) Math.floor(o.getWlasciciel().getDiamenty() / ofertaSpekulanta.getCena());

        if (mozeKupic > 0) {
          if (mozeKupic > ofertaSpekulanta.getIlosc()) mozeKupic = ofertaSpekulanta.getIlosc();
          if (mozeKupic > o.getIlosc()) mozeKupic = o.getIlosc();

          o.odejmij(mozeKupic);
          ofertaSpekulanta.odejmij(mozeKupic);
          o.getWlasciciel().odejmijDiamenty(mozeKupic * ofertaSpekulanta.getCena());
          ofertaSpekulanta.getWlasciciel().dodajDiamenty(mozeKupic * ofertaSpekulanta.getCena());
          o.getWlasciciel().dodajZasob(o.getPrzedmiot(), mozeKupic, o.getPoziom());
          ofertaSpekulanta.getWlasciciel().odejmijZasob(o.getPrzedmiot(), mozeKupic, o.getPoziom());
          historia.dodajZfinalizowana(new Rachunek(dzien, mozeKupic, ofertaSpekulanta.getCena(), o.getPrzedmiot()));
        }

        if (o.getIlosc() == 0) return;
        if (ofertaSpekulanta.getIlosc() == 0) ofertySpekulantow.remove(i);
      }
    }
  }

  private static List<OfertaSpekulanta> kolejnoscOfertSpekulanta(List<OfertaSpekulanta> oferty) {
    Comparator<OfertaSpekulanta> comparator = Comparator.comparingInt(
      (OfertaSpekulanta o) -> o.getPrzedmiot().ordinal()
    ).thenComparing((o1, o2) -> o2.getPoziom() - o1.getPoziom()
    ).thenComparing((o1, o2) -> Double.compare(o2.getCena(), o1.getCena()));

    return oferty.stream().sorted(comparator).collect(Collectors.toList());
  }
}
