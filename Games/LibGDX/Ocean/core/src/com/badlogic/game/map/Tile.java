package com.badlogic.game.map;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Tile {

    public Sprite sprite;
    public Rectangle rect;

    public int type;
    public boolean solid;

    public Tile(int type, int x, int y, int size) {

        this.type = type;

        sprite = new Sprite(Tiles.getTexture(type));
        solid = Tiles.isSolid(type);
        rect = new Rectangle(x, y, size, size);

        sprite.setFlip(false, true);
        sprite.setPosition((int)rect.x, (int)rect.y);
        sprite.setOrigin(0, 0);

    }

    public void update() {
        sprite.setPosition((int)rect.x, (int)rect.y);
    }

    public void update(float xOff, float yOff, float scale) {
        sprite.setPosition(scale*(rect.x - xOff), scale*(rect.y - yOff));
    }

    public void draw(SpriteBatch batch, int scale) {
        if (type != Tiles.AIR) {
            sprite.setScale(scale);
            //sprite.setPosition(rect.x * scale, rect.y * scale);
            sprite.draw(batch);
        }
    }

}
