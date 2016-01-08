package barqsoft.footballscores.widget;

public class WidgetScore {
    String homeTeamName;
    String score;
    String awayTeamName;

    public WidgetScore() {
    }

    public WidgetScore(String homeTeamName, String awayTeamName, String score) {
        this.awayTeamName = awayTeamName;
        this.homeTeamName = homeTeamName;
        this.score = score;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
