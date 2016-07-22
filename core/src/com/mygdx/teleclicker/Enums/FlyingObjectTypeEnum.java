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
            return Assets.getInstance().manager.get(AssetsEnum.MONEY_TEX.toString());
        }
    },
    MONEY_DOWN {
        @Override
        public Texture getTexture() {
            return Assets.getInstance().manager.get(AssetsEnum.BOMB_TEX.toString());
        }
    },
    PASSIVE {
        @Override
        public Texture getTexture() {
            return Assets.getInstance().manager.get(AssetsEnum.DIAMOND_TEX.toString());
        }
    },
    PASSIVE_DOWN {
        @Override
        public Texture getTexture() {
            return Assets.getInstance().manager.get(AssetsEnum.JEW_TEX.toString());
        }
    };

    public abstract Texture getTexture();
}
