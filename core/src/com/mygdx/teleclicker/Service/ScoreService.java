package com.mygdx.teleclicker.Service;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.teleclicker.Entities.Player;
import com.mygdx.teleclicker.Entities.PlayerStats;
import com.mygdx.teleclicker.Enums.DBStatusEnum;
import com.mygdx.teleclicker.TeleClicker;

import java.util.concurrent.TimeUnit;

/**
 * Created by Senpai on 21.07.2016.
 */
public class ScoreService {
    public final static String GAME_SCORE = "com.mygdx.clicker.prefs.score";
    public final static String GAME_PASSIVE_INCOME = "com.mygdx.clicker.prefs.pointspersec";
    public final static String GAME_SAVED_TIMESTAMP = "com.mygdx.clicker.prefs.timestamp";
    public final static String GAME_POINTS_PER_CLICK = "com.mygdx.clicker.prefs.pointsperclick";
    public final static String GAME_PASSIVE_INCOME_TIME = "com.mygdx.clicker.prefs.passiveincometime";

    public final static String GAME_NO_CLICKS = "com.mygdx.clicker.prefs.numberofclicks";

    public final static String GAME_NO_POINTS_PER_CLICK_BUYS = "com.mygdx.clicker.prefs.pointsperclickbuys";
    public final static String GAME_NO_POINTS_PER_SEC_BUYS = "com.mygdx.clicker.prefs.pointspersecbuys";

    private static ScoreService instance;

    private float points;
    private float pointsPerSec;
    private float pointsPerClick = 1.0f;
    private float passiveIncomeTimeInHour;

    private long delayTime;

    private long numberOfClicks;

    private int numberOfPointsPerClickPBuys;
    private int numberOfPointsPerSecBuys;

    private Preferences prefs;

    public DBStatusEnum connectionStatus;

    public static PlayerStats playerStats;

    private ScoreService() {
        this.prefs = TeleClicker.getPrefs();
        loadScore();
        calculateGainedPassiveIncome();
        initTimer();
    }

    public void saveStatsOnServer() {
        System.out.println(Player.ID);
    }

    public void loadPlayerStatsFromServer(String id) {
        final HttpService httpService = new HttpService();
        httpService.loadStatsRequest(id);

        final Timer timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                System.out.println(httpService.getStatus().toString());
                if (httpService.getStatus() == DBStatusEnum.FAILED || httpService.getStatus() == DBStatusEnum.CANCELLED) {
                    timer.clear();
                } else {
                    if (httpService.getStatus() == DBStatusEnum.SUCCES) {
                        Json json = new Json();
                        PlayerStats loadedStats = json.fromJson(PlayerStats.class, httpService.getResponsStr());
                        ScoreService.playerStats = loadedStats;

                        System.out.println(loadedStats.toString());
                        timer.clear();
                    }
                }
            }
        }, 0, 1);

        Timer timeStop = new Timer();
        timeStop.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                if (!timer.isEmpty()) {
                    System.out.println("Conn TIMEOUt");
                    timer.clear();
                }
            }
        }, 5);
    }

    private void loadScore() {
        loadPlayerStatsFromLocal();

        PlayerStats loadedStats = ScoreService.playerStats;

        points = loadedStats.getPointsPerSec();
        pointsPerSec = loadedStats.getPointsPerSec();
        pointsPerClick = loadedStats.getPointsPerClick();
        passiveIncomeTimeInHour = loadedStats.getPassiveIncomeTimeInHour();

        numberOfClicks = loadedStats.getNumberOfClicks();
        numberOfPointsPerClickPBuys = loadedStats.getNumberOfPointsPerClickPBuys();
        numberOfPointsPerSecBuys = loadedStats.getNumberOfPointsPerSecBuys();

        delayTime = TimeUtils.millis() - prefs.getLong(GAME_SAVED_TIMESTAMP);


    }

    private void loadPlayerStatsFromLocal() {
        PlayerStats loadedStats = new PlayerStats();

        loadedStats.setId(-1);
        loadedStats.setPoints(prefs.getFloat(GAME_SCORE));
        loadedStats.setPointsPerSec(prefs.getFloat(GAME_PASSIVE_INCOME));
        loadedStats.setPointsPerClick(prefs.getFloat(GAME_POINTS_PER_CLICK, 1));
        loadedStats.setNumberOfClicks(prefs.getLong(GAME_NO_CLICKS));
        loadedStats.setNumberOfPointsPerSecBuys(prefs.getInteger(GAME_NO_POINTS_PER_SEC_BUYS));
        loadedStats.setNumberOfPointsPerClickPBuys(prefs.getInteger(GAME_NO_POINTS_PER_CLICK_BUYS));

        ScoreService.playerStats = loadedStats;
    }

    private void initTimer() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                points += pointsPerSec / 10;
            }
        }, 1, 0.1f);
    }

    public static ScoreService getInstance() {
        if (instance == null) {
            instance = new ScoreService();
        }
        return instance;
    }

    private void calculateGainedPassiveIncome() {
        final float multiplier = 0.1f;

        long delayTimeInSec = TimeUnit.MILLISECONDS.toSeconds(delayTime);
        long passiveIncomeTimeInSec = TimeUnit.HOURS.toSeconds((long) passiveIncomeTimeInHour);
        if (delayTimeInSec > passiveIncomeTimeInSec)
            delayTimeInSec = passiveIncomeTimeInSec;

        float pointsToAdd = delayTimeInSec * multiplier * pointsPerSec;
        points += pointsToAdd;
    }

    public void addPoints(float pointsToAdd) {
        points += pointsToAdd;
        if (points < 0)
            points = 0;
    }

    public void addPoint() {
        points += pointsPerClick;
    }

    public void addPointsPerClick(int add) {
        pointsPerClick += add;
    }

    public void resetGameScore() {
        points = 0.0f;
        pointsPerSec = 0.0f;
        pointsPerClick = 1.0f;
        passiveIncomeTimeInHour = 1.0f;

        numberOfPointsPerClickPBuys = 0;
        numberOfPointsPerSecBuys = 0;

        saveCurrentGameState();
    }

    public void addPointsPerSec(int value) {
        pointsPerSec += value;
        if (pointsPerSec < 0)
            pointsPerSec = 0;
    }

    public void increseNumberOfClick() {
        numberOfClicks++;
    }

    public void increseNumberOfPointsPerClickBuys() {
        numberOfPointsPerClickPBuys++;
    }

    public void increseNumberOfPointsPerSecBuys() {
        numberOfPointsPerSecBuys++;
    }


    public void saveCurrentGameState() {
        prefs.putFloat(GAME_SCORE, points);
        prefs.putFloat(GAME_PASSIVE_INCOME, pointsPerSec);
        prefs.putFloat(GAME_POINTS_PER_CLICK, pointsPerClick);
        prefs.putFloat(GAME_PASSIVE_INCOME_TIME, passiveIncomeTimeInHour);
        prefs.putLong(GAME_SAVED_TIMESTAMP, TimeUtils.millis());

        prefs.putLong(GAME_NO_CLICKS, numberOfClicks);

        // Shop values
        prefs.putInteger(GAME_NO_POINTS_PER_CLICK_BUYS, numberOfPointsPerClickPBuys);
        prefs.putInteger(GAME_NO_POINTS_PER_SEC_BUYS, numberOfPointsPerSecBuys);

        prefs.flush();
    }

    /**
     * ---------------------
     * getters and setters
     */

    public long getNumberOfClicks() {
        return numberOfClicks;
    }

    public float getPoints() {
        return points;
    }

    public float getPointsPerSec() {
        return pointsPerSec;
    }

    public float getPointsPerClick() {
        return pointsPerClick;
    }

    public int getNumberOfPointsPerClickBuys() {
        return numberOfPointsPerClickPBuys;
    }

    public int getNumberOfPointsPerSecBuys() {
        return numberOfPointsPerSecBuys;
    }

    public void multiplierPoints(float multiplier) {
        points *= multiplier;
    }
}
