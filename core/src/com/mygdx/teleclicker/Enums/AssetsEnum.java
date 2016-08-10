package com.mygdx.teleclicker.Enums;

import com.mygdx.teleclicker.Core.Assets;

/**
 * Created by Senpai on 21.07.2016.
 */
public enum AssetsEnum {
    SPLASH_BG("img/bg/splash.png"),
    SHOP_BG("img/bg/shop_bg.png"),
    SETTINGS_BG("img/bg/settings_bg.png"),
    WARNING_BG("img/bg/warning_bg.png"),

    CASH_LABEL("img/bg/cash_label.png"),

    PLAYER_TEX("img/skins/player/phone_old1.png"),
    PHONE_OFF_TEX("img/skins/phone_corner_off.png"),
    PHONE_ON_TEX("img/skins/phone_corner_on.png"),
    SETTINGS_TEX("img/skins/settings.png"),

    MONEY_TEX("img/skins/flying_object/cash_1.png"),
    DIAMOND_TEX("img/skins/flying_object/diamond_1.png"),
    BOMB_TEX("img/skins/flying_object/bomb_1.png"),
    JEW_TEX("img/skins/flying_object/jew_greedy.png"),

    BUTTON_YES_TEX("img/button_yes.png"),
    BUTTON_NO_TEX("img/button_no.png"),
    CHECKBOX_ON_TEX("img/checkbox_on.png"),
    CHECKBOX_OFF_TEX("img/checkbox_off.png"),

    DIALOG_BOG_TEX("img/popups/dialog.png"),

    // Sounds and music
    POP_SOUND("sounds/pop.mp3"),
    CASH_REGISTER_SOUND("sounds/cash_register.mp3"),
    EVIL_LAUGH_SOUND("sounds/evil_laugh_jew.mp3"),
    BOMP_EXPLOSION_SOUND("sounds/bomb_explosion.mp3"),
    CLICK_SOUND_1("sounds/click_1.mp3"),

    CAKETOWN_MUSIC("music/caketown.mp3"),

    // Atlases
    RED_ATLAS("img/atlas/ui-red.atlas"),
    GREEN_ATLAS("img/atlas/ui-green.atlas");

    private String dir;

    private AssetsEnum(String dir){
        this.dir = dir;
    }

    // ToDo implements
    private AssetsEnum(String dir, String type){
        this.dir = dir;
        // ToDo Load asset to assetManager
    }

    @Override
    public String toString() {
        return dir;
    }

    public synchronized <T> T getAsset(){
        return Assets.getInstance().manager.get(toString());
    }
}
