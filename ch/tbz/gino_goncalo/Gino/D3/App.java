package ch.tbz.gino_goncalo.Gino.D3;

import java.time.LocalDate;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    PlannerService planner = new PlannerService(new OpenMeteoClient());
    Scanner in = new Scanner(System.in);
    System.out.println("=== Wetter Planner KN-D3 (echte Schweizer API) ===");

    while (true) {
      try {
        System.out.print("Ort eingeben (oder exit): ");
        String city = in.nextLine();
        if (city == null) continue;
        if (city.equalsIgnoreCase("exit")) break;

        System.out.print("Datum (YYYY-MM-DD): ");
        String line = in.nextLine();
        if (line == null || line.isBlank()) {
          System.out.println("Datum darf nicht leer sein.");
          continue;
        }
        LocalDate date = LocalDate.parse(line.trim());

        Recommendation r = planner.recommend(city, date);
        System.out.println();
        System.out.println("Empfehlung: " + r.summary());
        System.out.println("Details: " + r.details());
        System.out.println();
      } catch (InputValidationException e) {
        System.out.println("Fehler: " + e.getMessage());
      } catch (ExternalServiceException e) {
        System.out.println("Service-Fehler: " + e.getMessage());
      } catch (Exception e) {
        System.out.println("Allgemeiner Fehler: " + e.getMessage());
      }
    }
    System.out.println("Tschuess!");
  }
}
