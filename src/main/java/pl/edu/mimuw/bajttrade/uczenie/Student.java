package pl.edu.mimuw.bajttrade.uczenie;

import pl.edu.mimuw.bajttrade.gielda.Historia;
import pl.edu.mimuw.bajttrade.gielda.Info;
import pl.edu.mimuw.bajttrade.przedmioty.Przedmiot;
import pl.edu.mimuw.bajttrade.agenci.robotnicy.Robotnik;

public class Student implements Uczenie {
  private final int zapas;
  private final int okres;

  public Student(int zapas, int okres) {
    this.zapas = zapas;
    this.okres = okres;
  }

  @Override
  public boolean czySieUczy(Robotnik r, Historia h, Info info, int dzien) {
    return r.getDiamenty() >= 100 * zapas * h.getSredniaCenaKilkuDni(okres, dzien, info, Przedmiot.JEDZENIE);
  }

  @Override
  public int getOkres() {
    return okres;
  }

  @Override
  public int getZapas() {
    return zapas;
  }

  @Override
  public String toString() {
    return "student";
  }
}
