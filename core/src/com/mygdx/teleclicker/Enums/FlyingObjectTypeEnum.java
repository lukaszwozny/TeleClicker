package com.mygdx.teleclicker.Enums;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.teleclicker.Core.Assets;

/**
 * Created by Senpai on 21.07.2016.
 */
public enum FlyingObjectTypeEnum {
    MONEY {
        @Override
        public Texture getTexture() {
            return AssetsEnum.MONEY_TEX.getAsset();
        }
    },
    MONEY_DOWN {
        @Override
        public Texture getTexture() {
            return AssetsEnum.BOMB_TEX.getAsset();
        }
    },
    PASSIVE {
        @Override
        public Texture getTexture() {
            return AssetsEnum.DIAMOND_TEX.getAsset();
        }
    },
    PASSIVE_DOWN {
        @Override
        public Texture getTexture() {
            return AssetsEnum.JEW_TEX.getAsset();
        }
    };

    public abstract Texture getTexture();
}
