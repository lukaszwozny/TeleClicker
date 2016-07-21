package com.mygdx.teleclicker.Enums;

import com.mygdx.teleclicker.Screens.AbstractScreen_old;

/**
 * Created by Senpai on 21.07.2016.
 */
public enum ScreenEnum {
    SPLASH {
        @Override
        public AbstractScreen_old getAbstractScreen() {
            return null;
        }
    },
    GAMEPLAY {
        @Override
        public AbstractScreen_old getAbstractScreen() {
            return null;
        }
    },
    SHOP {
        @Override
        public AbstractScreen_old getAbstractScreen() {
            return null;
        }
    };

    public abstract AbstractScreen_old getAbstractScreen();
}
