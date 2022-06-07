package pl.edu.mimuw.bajttrade.robotnicy;

import pl.edu.mimuw.bajttrade.Historia;
import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;
import pl.edu.mimuw.bajttrade.kariera.Kariera;
import pl.edu.mimuw.bajttrade.kupowanie.Kupowanie;
import pl.edu.mimuw.bajttrade.przedmioty.Produktywnosc;
import pl.edu.mimuw.bajttrade.przedmioty.Zasoby;
import pl.edu.mimuw.bajttrade.uczenie.Uczenie;
import pl.edu.mimuw.bajttrade.zmiana.Zmiana;

public abstract class Robotnik {
  private int id;
  private int poziom;
  private Kariera kariera;
  private Kupowanie kupowanie;
  private Uczenie uczenie;
  private Zmiana zmiana;
  private Produktywnosc produktywnosc;
  private Zasoby zasoby;

  private Zasoby wyprodukowane;

  protected Robotnik(int id, int poziom, Kariera kariera, Kupowanie kupowanie, Uczenie uczenie, Zmiana zmiana,
                     Produktywnosc produktywnosc, Zasoby zasoby) {
    this.id = id;
    this.poziom = poziom;
    this.kariera = kariera;
    this.kupowanie = kupowanie;
    this.uczenie = uczenie;
    this.zmiana = zmiana;
    this.produktywnosc = produktywnosc;
    this.zasoby = zasoby;
  }

  public int getId() {
    return this.id;
  }

  public Kariera getAktywnaKariera() {
    return this.kariera;
  }

  public int getProduktywnosc(Przedmiot p) {
    return this.produktywnosc.getProduktywnosc(p);
  }

  public int getIloscZasobow(Przedmiot p) {
    return this.zasoby.getIloscZasobow(p);
  }

  public int getIloscWyprodukowanych() {
    return this.wyprodukowane.getSumaIlosciZasobow();
  }

  public abstract Przedmiot coProdukuje(Historia h, int dzien);

  @Override
  public String toString() {
    var sb = new StringBuilder();

    sb.append("\t id: ").append(this.id).append("\n");
    sb.append("\t poziom: ").append(this.poziom).append("\n");
    sb.append("\t kariera: ").append(this.kariera).append("\n");
    sb.append("\t kupowanie: ").append(this.kupowanie).append("\n");
    sb.append("\t uczenie: ").append(this.uczenie).append("\n");
    sb.append("\t zmiana: ").append(this.zmiana).append("\n");
    sb.append("\t produktywnosc:\n").append(this.produktywnosc);
    sb.append("\t zasoby:\n").append(this.zasoby);

    return sb.toString();
  }
}
