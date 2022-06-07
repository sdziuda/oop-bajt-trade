package pl.edu.mimuw.bajttrade.uczenie;

import pl.edu.mimuw.bajttrade.Historia;
import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;
import pl.edu.mimuw.bajttrade.robotnicy.Robotnik;

public class Student implements Uczenie {
  private final int zapas;
  private final int okres;

  public Student(int zapas, int okres) {
    this.zapas = zapas;
    this.okres = okres;
  }

  @Override
  public boolean czySieUczy(Robotnik r, Historia h, int dzien) {
    return r.getIloscZasobow(Przedmiot.DIAMENTY) >= 100 * zapas * h.getSredniaCena(okres, dzien, Przedmiot.JEDZENIE);
  }

  @Override
  public String toString() {
    return "student";
  }
}
