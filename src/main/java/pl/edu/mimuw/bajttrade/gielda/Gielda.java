package pl.edu.mimuw.bajttrade.gielda;

import com.squareup.moshi.JsonAdapter;
import pl.edu.mimuw.bajttrade.adaptery.InfoWyjscioweJson;
import pl.edu.mimuw.bajttrade.oferty.Oferta;
import pl.edu.mimuw.bajttrade.agenci.robotnicy.*;
import pl.edu.mimuw.bajttrade.agenci.spekulanci.Spekulant;
import pl.edu.mimuw.bajttrade.oferty.OfertaSpekulanta;
import pl.edu.mimuw.bajttrade.oferty.Rachunek;
import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;

import java.io.BufferedWriter;
import java.io.FileWriter;
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

  public Info getInfo() {
    return this.info;
  }

  public Robotnik[] getRobotnicy() {
    return this.robotnicy;
  }

  public Spekulant[] getSpekulanci() {
    return this.spekulanci;
  }

  public void symuluj(JsonAdapter<Gielda> adapter) throws Exception {
    BufferedWriter bw = new BufferedWriter(new FileWriter("wyjscie.json"));
    bw.append("[\n");

    while (dzien <= info.getDlugosc()) {
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

      dopasujOfertySprzedazyRobotnikow(ofertySprzedazyRobotnikow, ofertyKupnaSpekulantow);
      dopasujOfertyKupnaRobotnikow(ofertyKupnaRobotnikow, ofertySprzedazySpekulantow);
      skupOfertRobotnika(ofertySprzedazyRobotnikow);

      for (var r : robotnicyPracujacy) {
        r.rozegrajKoniecDnia();
      }

      bw.append(adapter.indent("  ").toJson(this)).append(dzien == info.getDlugosc() ? "\n" : ",\n");
      dzien++;
    }

    bw.append("]");
    bw.close();
  }

  public InfoWyjscioweJson generujInfoWyjsciowe() {
    Ceny cenySrednie = new Ceny(historia.getSredniaCenaDanegoDnia(dzien, info, Przedmiot.PROGRAMY),
      historia.getSredniaCenaDanegoDnia(dzien, info, Przedmiot.JEDZENIE),
      historia.getSredniaCenaDanegoDnia(dzien, info, Przedmiot.UBRANIA),
      historia.getSredniaCenaDanegoDnia(dzien, info, Przedmiot.NARZEDZIA));
    Ceny cenyMax = new Ceny(historia.getNajwyzszaCena(dzien, info, Przedmiot.PROGRAMY),
      historia.getNajwyzszaCena(dzien, info, Przedmiot.JEDZENIE),
      historia.getNajwyzszaCena(dzien, info, Przedmiot.UBRANIA),
      historia.getNajwyzszaCena(dzien, info, Przedmiot.NARZEDZIA));
    Ceny cenyMin = new Ceny(historia.getNajnizszaCena(dzien, info, Przedmiot.PROGRAMY),
      historia.getNajnizszaCena(dzien, info, Przedmiot.JEDZENIE),
      historia.getNajnizszaCena(dzien, info, Przedmiot.UBRANIA),
      historia.getNajnizszaCena(dzien, info, Przedmiot.NARZEDZIA));

    return new InfoWyjscioweJson(dzien, cenySrednie, cenyMax, cenyMin);
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

  private void skupOfertRobotnika(List<Oferta> ofertyRobotnikow) {
    int i = 0;
    while (i < ofertyRobotnikow.size()) {
      var o = ofertyRobotnikow.get(i);
      var cena = this.historia.getNajnizszaCena(dzien - 1, info, o.getPrzedmiot());

      o.getWlasciciel().dodajDiamenty(cena * o.getIlosc());
      this.historia.dodajZfinalizowana(new Rachunek(dzien, o.getIlosc(), cena, o.getPrzedmiot()));
      ofertyRobotnikow.remove(o);

      i++;
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
