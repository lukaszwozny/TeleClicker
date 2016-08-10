package com.mygdx.teleclicker.Service;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.teleclicker.Entities.Player;
import com.mygdx.teleclicker.Entities.PlayerStats;
import com.mygdx.teleclicker.Enums.DBStatusEnum;
import com.mygdx.teleclicker.Enums.ScreenEnum;
import com.mygdx.teleclicker.TeleClicker;
import com.mygdx.teleclicker.ui.CashLabel;

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

    public final static String GAME_LAST_LOGIN = "com.mygdx.clicker.prefs.lastlogin";
    public final static String GAME_LAST_PASSWORD = "com.mygdx.clicker.prefs.lastpassword";

    final HttpService httpService;

    private static ScoreService instance;

    // For the "remember me" option
    private String lastLogin;
    private String lastPassword;

    private float points;
    private float pointsPerSec;
    private float pointsPerClick = 1.0f;
    private float passiveIncomeTimeInHour;
    private long playTimeSec;

    private int clickSpeedPerSec;
    private int clickSpeedPerMinute;

    // Highscore
    private int bestTimeInGame;
    private int bestClckSpeedPerSec;
    private int bestClckSpeedPerMinute;

    private long delayTime;

    private long numberOfClicks;

    private int numberOfPointsPerClickPBuys;
    private int numberOfPointsPerSecBuys;


    private Preferences prefs;

    private DBStatusEnum loginStatus;
    private boolean isLoaded = false;
    private PlayerStats playerStats;

    private ScoreService() {
        this.httpService = new HttpService();
        this.prefs = TeleClicker.getPrefs();
        loadPlayerStatsFromLocal();
        calculateGainedPassiveIncome();
        initTimer();
    }

    public void saveStats() {
        putStatsToObject();
        putStatsOnServer();
    }

    private void putStatsOnServer() {
        final HttpService saveHttp = new HttpService();

        saveHttp.saveStatsRequest(playerStats);
        System.out.println("Save");

        final Timer saveTimer = new Timer();
        saveTimer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                System.out.println(saveHttp.getStatus().toString());
                switch (saveHttp.getStatus()) {
                    case CONNECTING:
                        loginStatus = saveHttp.getStatus();
                        break;
                    case SUCCES:
                        loginStatus = saveHttp.getStatus();
                        saveTimer.clear();
                        break;
                    default:
                        loginStatus = saveHttp.getStatus();
                        saveTimer.clear();
                        break;
                }
            }
        }, 0, 1);
    }

    private void putStatsToObject() {
        playerStats.setPoints(points);
        playerStats.setPointsPerSec(pointsPerSec);
        playerStats.setPointsPerClick(pointsPerClick);
        playerStats.setNumberOfClicks(numberOfClicks);
        playerStats.setNumberOfPointsPerClickPBuys(numberOfPointsPerClickPBuys);
        playerStats.setNumberOfPointsPerSecBuys(numberOfPointsPerSecBuys);
    }

    private void loadStatsFromPlayerStats(PlayerStats pStats) {
        points = pStats.getPoints();
        pointsPerSec = pStats.getPointsPerSec();
        pointsPerClick = pStats.getPointsPerClick();
        passiveIncomeTimeInHour = pStats.getPassiveIncomeTimeInHour();

        numberOfClicks = pStats.getNumberOfClicks();
        numberOfPointsPerClickPBuys = pStats.getNumberOfPointsPerClickPBuys();
        numberOfPointsPerSecBuys = pStats.getNumberOfPointsPerSecBuys();

    }

    public void loadScore(final String login, String password) {
        final HttpService loadHttp = new HttpService();
        loadHttp.loginRequest(login, password);

        final Timer logintimer = new Timer();
        logintimer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                switch (loadHttp.getStatus()) {
                    case SUCCES:
                        //Get player "id"
                        Json json = new Json();
                        PlayerStats pStat = json.fromJson(PlayerStats.class, loadHttp.getResponsStr());

                        //Get full stats
                        final HttpService statsdHttp = new HttpService();
                        statsdHttp.loadStatsRequest(Integer.toString(pStat.getId()));
                        final Timer statsTimer = new Timer();
                        statsTimer.scheduleTask(new Timer.Task() {
                            @Override
                            public void run() {
                                if (statsdHttp.getStatus() == DBStatusEnum.SUCCES) {
                                    Json json = new Json();
                                    PlayerStats pStat = json.fromJson(PlayerStats.class, statsdHttp.getResponsStr());

                                    DBStatusEnum statusEnum = DBStatusEnum.valueOf(pStat.getStatus());

                                    switch (statusEnum) {
                                        case SUCCES:
                                            playerStats = pStat;
                                            loadStatsFromPlayerStats(playerStats);
                                            loginStatus = statusEnum;
                                            ScreenService.getInstance().setScreen(ScreenEnum.GAMEPLAY);
                                            statsTimer.clear();
                                            break;
                                        default:
                                            loginStatus = statusEnum;
                                            statsTimer.clear();
                                            break;
                                    }
                                    logintimer.clear();
                                }
                            }
                        }, 0.2f, 4);
                        break;
                    default:
                        loginStatus = loadHttp.getStatus();
                        break;
                }
            }
        }, 0, 1);
    }

    private void loadPlayerStatsFromLocal() {
        playerStats = new PlayerStats();

        playerStats.setId(-1);
        playerStats.setPoints(prefs.getFloat(GAME_SCORE));
        playerStats.setPointsPerSec(prefs.getFloat(GAME_PASSIVE_INCOME));
        playerStats.setPointsPerClick(prefs.getFloat(GAME_POINTS_PER_CLICK, 1));
        playerStats.setNumberOfClicks(prefs.getLong(GAME_NO_CLICKS));
        playerStats.setNumberOfPointsPerSecBuys(prefs.getInteger(GAME_NO_POINTS_PER_SEC_BUYS));
        playerStats.setNumberOfPointsPerClickPBuys(prefs.getInteger(GAME_NO_POINTS_PER_CLICK_BUYS));

        System.out.println("last login: " + lastLogin);
        lastLogin = prefs.getString(GAME_LAST_LOGIN);
        lastPassword = prefs.getString(GAME_LAST_PASSWORD);

        loadStatsFromPlayerStats(playerStats);
    }

    private void initTimer() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                points += pointsPerSec / 10;
            }
        }, 1, 0.1f);
    }

    public void initPlayTimeTimer() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                playTimeSec++;
            }
        }, 1, 1);
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

    public void addClickSpeed() {
        clickSpeedPerSec++;
        clickSpeedPerMinute++;

        if (clickSpeedPerSec > bestClckSpeedPerSec)
            bestClckSpeedPerSec = clickSpeedPerSec;
        if (clickSpeedPerMinute > bestClckSpeedPerMinute)
            bestClckSpeedPerMinute = clickSpeedPerMinute;

        // Remove after sec
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                clickSpeedPerSec--;
            }
        }, 1);
        // Remove after minute
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                clickSpeedPerMinute--;
            }
        }, 60);
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

        // For "remember me"
        prefs.putString(GAME_LAST_LOGIN, lastLogin);
        prefs.putString(GAME_LAST_PASSWORD, lastPassword);

        // Shop values
        prefs.putInteger(GAME_NO_POINTS_PER_CLICK_BUYS, numberOfPointsPerClickPBuys);
        prefs.putInteger(GAME_NO_POINTS_PER_SEC_BUYS, numberOfPointsPerSecBuys);

        prefs.flush();
    }

    public static ScoreService getInstance() {
        if (instance == null) {
            instance = new ScoreService();
        }
        return instance;
    }

    public long getPlayTimeSec() {
        return playTimeSec;
    }

    public int getBestTimeInGame() {
        return bestTimeInGame;
    }

    public int getBestClckSpeedPerSec() {
        return bestClckSpeedPerSec;
    }

    public int getBestClckSpeedPerMinute() {
        return bestClckSpeedPerMinute;
    }

    /**
     * ---------------------
     * getters and setters
     */


    public boolean isLoaded() {
        return isLoaded;
    }

    public DBStatusEnum getLoginStatus() {
        return loginStatus;
    }

    public PlayerStats getPlayerStats() {
        return playerStats;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getLastPassword() {
        return lastPassword;
    }

    public void setLastPassword(String lastPassword) {
        this.lastPassword = lastPassword;
    }

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

    public int getClickSpeedPerSec() {
        return clickSpeedPerSec;
    }

    public int getClickSpeedPerMinute() {
        return clickSpeedPerMinute;
    }

}
