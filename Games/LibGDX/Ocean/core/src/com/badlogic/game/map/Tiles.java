package com.badlogic.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Tiles {

    // stats
    public static final int AIR = 0;
    public static final boolean AIR_SOLID = false;
    public static final Texture AIR_TEXTURE =  new Texture(Gdx.files.internal("graphics/tiles/tile.png"));

    public static final int SOLID = 1;
    public static final boolean SOLID_SOLID = true;
    public static final Texture SOLID_TEXTURE = new Texture(Gdx.files.internal("graphics/tiles/tile.png"));

    // returns
    public static boolean isSolid(int type) {
        if (type == AIR) return AIR_SOLID;
        if (type == SOLID) return SOLID_SOLID;
        return true;
    }

    public static Texture getTexture(int type) {
        if (type == AIR) return AIR_TEXTURE;
        if (type == SOLID) return SOLID_TEXTURE;
        return AIR_TEXTURE;
    }

}
