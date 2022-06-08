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
    File file = new File("dane.json");
    BufferedReader bf = new BufferedReader(new FileReader(file));
    String json;
    var sb = new StringBuilder();

    while ((json = bf.readLine()) != null) {
      sb.append(json).append("\n");
    }

    json = sb.toString();
    //System.out.println(json);

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

    gielda.symuluj();
  }
}
