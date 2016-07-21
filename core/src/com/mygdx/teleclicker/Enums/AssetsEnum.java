package com.mygdx.teleclicker.Enums;

/**
 * Created by Senpai on 21.07.2016.
 */
public enum AssetsEnum {
    SPLASH_BG("img/bg/splash.png"),
    GAMEPLAY_BG("img/bg/basic_bg.png"),
    SHOP_BG("img/bg/shop_bg.png");

    private String dir;

    private AssetsEnum(String dir){
        this.dir = dir;
    }

    @Override
    public String toString() {
        return dir;
    }
}
