package barqsoft.footballscores;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yehya khaled on 3/3/2015.
 */
public class Utilities
{
    // Extra error case: legaue names/codes didn't match between
    // myFetchService and getLeague method
    public static final String SERIE_A_14 = "357";
    public static final String PREMIER_LEGAUE = "354";
    public static final String CHAMPIONS_LEAGUE = "362";
    public static final String PRIMERA_DIVISION_14 = "358";
    public static final String BUNDESLIGA = "351";
    public static final String BUNDESLIGA1 = "394";
    public static final String BUNDESLIGA2 = "395";
    public static final String LIGUE1 = "396";
    public static final String LIGUE2 = "397";
    public static final String PREMIER_LEAGUE = "398";
    public static final String PRIMERA_DIVISION = "399";
    public static final String SEGUNDA_DIVISION = "400";
    public static final String SERIE_A = "401";
    public static final String PRIMERA_LIGA = "402";
    public static final String BUNDESLIGA3 = "403";
    public static final String EREDIVISIE = "404";

    public static final Map<String, Integer> LEGAUES;
    static {
        LEGAUES = new HashMap();
        LEGAUES.put(SERIE_A_14, R.string.seriaa);
        LEGAUES.put(PREMIER_LEGAUE, R.string.premierleague);
        LEGAUES.put(CHAMPIONS_LEAGUE, R.string.champions_league);
        LEGAUES.put(PRIMERA_DIVISION_14, R.string.primeradivison);
        LEGAUES.put(BUNDESLIGA, R.string.bundesliga);
        LEGAUES.put(BUNDESLIGA1, R.string.bundesliga);
        LEGAUES.put(BUNDESLIGA2, R.string.bundesliga);
        LEGAUES.put(LIGUE1, R.string.ligue_1);
        LEGAUES.put(LIGUE2, R.string.ligue_2);
        LEGAUES.put(PREMIER_LEAGUE, R.string.premierleague);
        LEGAUES.put(PRIMERA_DIVISION, R.string.primeradivison);
        LEGAUES.put(SEGUNDA_DIVISION, R.string.primeradivison);
        LEGAUES.put(SERIE_A, R.string.seriaa);
        LEGAUES.put(PRIMERA_LIGA, R.string.primeira_liga);
        LEGAUES.put(BUNDESLIGA3, R.string.bundesliga3);
        LEGAUES.put(EREDIVISIE, R.string.eredivisie);
    }

    public static String getLeague(Context context, int leagueCode) {
        String leagueName;
        Integer resource = LEGAUES.get(String.valueOf(leagueCode));
        if (resource != null) {
            leagueName = context.getString(resource.intValue());
        } else {
            leagueName = context.getString(R.string.unknown_league);
        }
        return leagueName;
    }

    public static boolean isKnownLeague(String leagueCode) {
        return LEGAUES.containsKey(leagueCode);
    }

    public static String getMatchDay(Context context, int match_day, int league_num)
    {
        if(String.valueOf(league_num).equals(CHAMPIONS_LEAGUE))
        {
            if (match_day <= 6)
            {
                return context.getString(R.string.group_stage_text) + ", "
                        + context.getString(R.string.matchday_text) + " : 6";
            }
            else if(match_day == 7 || match_day == 8)
            {
                return context.getString(R.string.first_knockout_round);
            }
            else if(match_day == 9 || match_day == 10)
            {
                return context.getString(R.string.quarter_final);
            }
            else if(match_day == 11 || match_day == 12)
            {
                return context.getString(R.string.semi_final);
            }
            else
            {
                return context.getString(R.string.final_text);
            }
        }
        else
        {
            return context.getString(R.string.matchday_text)+ " : " + String.valueOf(match_day);
        }
    }

    public static String getScores(int home_goals,int awaygoals)
    {
        if(home_goals < 0 || awaygoals < 0)
        {
            return " - ";
        }
        else
        {
            return String.valueOf(home_goals) + " - " + String.valueOf(awaygoals);
        }
    }

    public static int getTeamCrestByTeamName (String teamname)
    {
        if (teamname==null){return R.drawable.no_icon;}
        switch (teamname)
        { //This is the set of icons that are currently in the app. Feel free to find and add more
            //as you go.
            case "Arsenal London FC" : return R.drawable.arsenal;
            case "Manchester United FC" : return R.drawable.manchester_united;
            case "Swansea City" : return R.drawable.swansea_city_afc;
            case "Leicester City" : return R.drawable.leicester_city_fc_hd_logo;
            case "Everton FC" : return R.drawable.everton_fc_logo1;
            case "West Ham United FC" : return R.drawable.west_ham;
            case "Tottenham Hotspur FC" : return R.drawable.tottenham_hotspur;
            case "West Bromwich Albion" : return R.drawable.west_bromwich_albion_hd_logo;
            case "Sunderland AFC" : return R.drawable.sunderland;
            case "Stoke City FC" : return R.drawable.stoke_city;
            case "Aston Villa FC" : return R.drawable.aston_villa;
            case "Burney FC" : return R.drawable.burney_fc_hd_logo;
            case "Chelsea FC" : return R.drawable.chelsea;
            case "Crystal Palace FC" : return R.drawable.crystal_palace_fc;
            case "Hull City AFC" : return R.drawable.hull_city_afc_hd_logo;
            case "Liverpool FC" : return R.drawable.liverpool;
            case "Manchester City FC" : return R.drawable.manchester_city;
            case "Newcastle United FC" : return R.drawable.newcastle_united;
            case "Queens Park Rangers" : return R.drawable.queens_park_rangers_hd_logo;
            case "Southampton FC" : return R.drawable.southampton_fc;
            default: return R.drawable.no_icon;
        }
    }
}
