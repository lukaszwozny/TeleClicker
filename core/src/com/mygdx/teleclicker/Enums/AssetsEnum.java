package com.mygdx.teleclicker.Enums;

/**
 * Created by Senpai on 21.07.2016.
 */
public enum AssetsEnum {
    SPLASH_BG("img/bg/splash.png"),
    GAMEPLAY_BG("img/bg/basic_bg.png"),
    SHOP_BG("img/bg/shop_bg.png"),

    PLAYER_TEX("img/skins/player/phone_old1.png"),

    // Sounds and music
    POP_SOUND("sounds/pop.mp3"),
    CASH_REGISTER_SOUND("sounds/cash_register.mp3"),
    EVIL_LAUGH_SOUND("sounds/evil_laugh_jew.mp3"),
    BOMP_EXPLOSION_SOUND("sounds/bomb_explosion.mp3"),
    CLICK_SOUND_1("sounds/click_1.mp3"),

    CAKETOWN_MUSIC("music/caketown.mp3");

    private String dir;

    private AssetsEnum(String dir){
        this.dir = dir;
    }

    @Override
    public String toString() {
        return dir;
    }
}
