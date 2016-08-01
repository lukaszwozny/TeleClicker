package com.mygdx.teleclicker.Enums;

import com.mygdx.teleclicker.Core.AbstractScreen;
import com.mygdx.teleclicker.Screens.*;

/**
 * Created by Senpai on 21.07.2016.
 */
public enum ScreenEnum {
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
    GAMEPLAY {
        @Override
        public AbstractScreen getScreen() {
            return new GameplayScreen();
        }
    },
    SHOP {
        @Override
        public AbstractScreen getScreen() {
            return new ShopScreen();
        }
    },
    SETTINGS {
        @Override
        public AbstractScreen getScreen() {
            return new SettingsScreen();
        }
    },
    HTTP {
        @Override
        public AbstractScreen getScreen() {
            return new HttpScreen();
        }
    };

    public abstract AbstractScreen getScreen();
}
