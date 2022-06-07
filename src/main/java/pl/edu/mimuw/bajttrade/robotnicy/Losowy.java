package pl.edu.mimuw.bajttrade.robotnicy;

import pl.edu.mimuw.bajttrade.Historia;
import pl.edu.mimuw.bajttrade.kariera.Kariera;
import pl.edu.mimuw.bajttrade.kupowanie.Kupowanie;
import pl.edu.mimuw.bajttrade.przedmioty.Produktywnosc;
import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;
import pl.edu.mimuw.bajttrade.przedmioty.Zasoby;
import pl.edu.mimuw.bajttrade.uczenie.Uczenie;
import pl.edu.mimuw.bajttrade.zmiana.Zmiana;

import java.util.Random;

public class Losowy extends Robotnik {
  public Losowy (int id, int poziom, Kariera kariera, Kupowanie kupowanie, Uczenie uczenie, Zmiana zmiana,
                        Produktywnosc produktywnosc, Zasoby zasoby) {
    super(id, poziom, kariera, kupowanie, uczenie, zmiana, produktywnosc, zasoby);
  }

  @Override
  public Przedmiot coProdukuje(Historia h, int dzien) {
    return Przedmiot.values()[new Random().nextInt(Przedmiot.values().length)];
  }

  @Override
  public String toString() {
    return super.toString() + "\t produkcja: losowy\n";
  }
}
