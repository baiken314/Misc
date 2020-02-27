package com.badlogic.game.object;

import com.badlogic.game.map.TileMap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MapObject {

    public Rectangle rect;
    public Sprite sprite;
    public TileMap tileMap;

    public double dx;
    public double dy;
    public boolean solid;

    public MapObject(TileMap tileMap, Texture texture, int x, int y, int width, int height) {

        this.tileMap = tileMap;
        rect = new Rectangle(x, y, width, height);
        sprite = new Sprite(texture);
        sprite.setSize(rect.width, rect.height);
        sprite.setPosition(rect.x, rect.y);
        sprite.setFlip(false, true);

        dx = 0;
        dy = 0;
        solid = false;

    }

    public void update() {

        rect.x += dx;
        rect.y += dy;

        sprite.setPosition((int)(rect.x - tileMap.xOff), (int)(rect.y - tileMap.yOff));

    }

    public void draw(SpriteBatch batch) {

        sprite.setSize(rect.width * tileMap.scale, rect.height * tileMap.scale);
        sprite.setPosition((rect.x - tileMap.xOff) * tileMap.scale, (rect.y) * tileMap.scale);
        sprite.draw(batch);

    }

}
