package pl.edu.mimuw;

import com.squareup.moshi.*;
import pl.edu.mimuw.bajttrade.adaptery.*;
import pl.edu.mimuw.bajttrade.gielda.Gielda;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Main {

  public static void main(String[] args) throws IOException {
    File wejscie = new File(args[0]);
    BufferedReader bf = new BufferedReader(new FileReader(wejscie));
    String json;
    var sb = new StringBuilder();

    while ((json = bf.readLine()) != null) {
      sb.append(json).append("\n");
    }
    json = sb.toString();

    Moshi moshi = new Moshi.Builder()
      .add(new GieldaAdapter())
      .add(new InfoAdapter())
      .add(new KarieraAdapter())
      .add(new KupowanieAdapter())
      .add(new RobotnikAdapter())
      .add(new UczenieAdapter())
      .add(new ZmianaAdapter())
      .add(new ZasobyAdapter())
      .add(new SpekulantAdapter())
      .build();
    JsonAdapter<Gielda> jsonAdapter = moshi.adapter(Gielda.class);
    Gielda gielda = jsonAdapter.fromJson(json);

    if (gielda != null) {
      try {
        gielda.symuluj(jsonAdapter, args[1]);
      } catch (Exception e) {
        System.out.println("Wystąpił błąd: " + e.getMessage());
      }
    }
  }
}
