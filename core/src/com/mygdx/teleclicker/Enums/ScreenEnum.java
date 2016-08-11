package com.mygdx.teleclicker.Enums;

import com.mygdx.teleclicker.Core.AbstractScreen;
import com.mygdx.teleclicker.Screens.*;

/**
 * Created by Senpai on 21.07.2016.
 */
public enum ScreenEnum {
    LOADING {
        @Override
        public AbstractScreen getScreen() {
            return new LoadingScreen();
        }
    },
    SPLASH {
        @Override
        public AbstractScreen getScreen() {
            return new SplashScreen();
        }
    },
    LOGIN {
        @Override
        public AbstractScreen getScreen() {
            return new LoginScreen();
        }
    },
    NEW_PLAYER {
        @Override
        public AbstractScreen getScreen() {
            return new NewPlayerScreen();
        }
    },
    GAMEPLAY2 {
        @Override
        public AbstractScreen getScreen() {
            return new GameplayScreen();
        }
    },
    GAMEPLAY {
        @Override
        public AbstractScreen getScreen() {
            return new GameplayScreen_old();
        }
    },
    SHOP {
        @Override
        public AbstractScreen getScreen() {
            return new ShopScreen();
        }
    },
    STATS {
        @Override
        public AbstractScreen getScreen() {
            return new StatsScreen();
        }
    },
    SETTINGS {
        @Override
        public AbstractScreen getScreen() {
            return new SettingsScreen();
        }
    };

    public abstract AbstractScreen getScreen();
}
