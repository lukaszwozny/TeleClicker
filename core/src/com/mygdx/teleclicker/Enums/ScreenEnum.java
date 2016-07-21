package com.mygdx.teleclicker.Enums;

import com.mygdx.teleclicker.Core.AbstractScreen;
import com.mygdx.teleclicker.Screens.AbstractScreen_old;
import com.mygdx.teleclicker.Screens.SplashScreen;

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
    GAMEPLAY {
        @Override
        public AbstractScreen getScreen() {
            return null;
        }
    },
    SHOP {
        @Override
        public AbstractScreen getScreen() {
            return null;
        }
    };

    public abstract AbstractScreen getScreen();
}
