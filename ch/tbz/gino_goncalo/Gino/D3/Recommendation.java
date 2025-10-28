package ch.tbz.gino_goncalo.Gino.D3;

public class Recommendation {
  private final String summary;
  private final String details;

  public Recommendation(String summary, String details) {
    this.summary = summary;
    this.details = details;
  }

  public String summary() { return summary; }
  public String details() { return details; }
}
