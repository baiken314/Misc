package com.badlogic.game.object;

import com.badlogic.game.map.Tile;
import com.badlogic.game.map.TileMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import javax.xml.soap.Text;

public class Player extends MapObject {

    public Animation<TextureRegion> swimAnimation;
    public Texture nudibranchSheet;
    public float swimStateTime;
    public boolean facingRight;

    public float swimSpeed;
    public float jumpSpeed;
    public float speedInterval;

    public float maxFallSpeed;
    public float maxSwimSpeed;
    public float maxSpeed;
    public float friction;

    public double gravity;
    public boolean collided;
    public boolean grounded;

    public Tile topLeft;
    public Tile topRight;
    public Tile bottomLeft;
    public Tile bottomRight;

    public Player(TileMap tileMap, Texture texture, int x, int y, int width, int height) {

        super(tileMap, texture, x, y, width, height);

        nudibranchSheet = new Texture(Gdx.files.internal("graphics/sprites/nudibranch.png"));
        TextureRegion[][] temp = TextureRegion.split(nudibranchSheet, nudibranchSheet.getWidth() / 3, nudibranchSheet.getHeight());
        TextureRegion[] swimFrames = new TextureRegion[3];
        int index = 0;
        for (int i = 0; i < swimFrames.length; i++) {
            swimFrames[index] = temp[0][i];
            index += 1;
        }

        swimAnimation = new Animation<TextureRegion>(.25f, swimFrames);
        swimStateTime = 0;
        facingRight = true;

        solid = true;

        swimSpeed = .12f;
        jumpSpeed = .8f;

        maxFallSpeed = 4f;
        maxSwimSpeed = maxFallSpeed * -1.3f;

        speedInterval = .06f;
        maxSpeed = 2;
        friction = 1.05f;

        gravity = .06f;
        collided = false;
        grounded = false;

    }

    public void checkCorners(float x, float y) {

        topLeft = tileMap.getTileByPixels(x, y);
        topRight = tileMap.getTileByPixels(x + rect.width - .01f, y);
        bottomLeft = tileMap.getTileByPixels(x, y + rect.height - .01f);
        bottomRight = tileMap.getTileByPixels(x + rect.width - .01f, y + rect.height - .01f);

    }

    public void setNextPosition() {

        // get future positions
        float tox = (float)(rect.x + dx);
        float toy = (float)(rect.y + dy);

        // check corners and calculate bounds
        checkCorners(rect.x, toy);
        if (topLeft.solid || topRight.solid) {
            toy = topLeft.rect.y + topLeft.rect.height;
            dy = 0;
        }
        // touched ground
        if (bottomLeft.solid || bottomRight.solid) {
            toy = bottomLeft.rect.y - rect.height;
            dy = 0;
            grounded = true;
        }
        else {
            grounded = false;
        }

        checkCorners(tox, rect.y);
        if (topLeft.solid || bottomLeft.solid) {
            tox = topLeft.rect.x + topLeft.rect.width;
            dx = 0;
        }
        if (topRight.solid || bottomRight.solid) {
            tox = topRight.rect.x - rect.width;
            dx = 0;
        }

        // set next position
        rect.x = tox;
        rect.y = toy;

    }

    @Override
    public void update() {

        // handle inputs
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            dx -= speedInterval;
            if (dx < -maxSpeed) dx = -maxSpeed;
            facingRight = false;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            dx += speedInterval;
            if (dx > maxSpeed) dx = maxSpeed;
            facingRight = true;
        }
        else if (grounded) {
            dx /= friction;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if (grounded) {
                dy = -jumpSpeed; // jump from bottom
                grounded = false;
            }
            else {
                dy += -swimSpeed; // swim upward
            }
        }

        // handle movement
        dy += gravity;
        if (dy > maxFallSpeed) dy = maxFallSpeed;
        if (dy < maxSwimSpeed) dy = maxSwimSpeed;

        // get next positions
        setNextPosition();

        // set sprite
        swimStateTime += Gdx.graphics.getDeltaTime();
        sprite.setRegion(swimAnimation.getKeyFrame(swimStateTime, true));
        sprite.setFlip(!facingRight, true);
        sprite.setPosition((rect.x - tileMap.xOff), (rect.y - tileMap.yOff));
        if (grounded) {
            sprite.setPosition((rect.x - tileMap.xOff), (int)(rect.y - tileMap.yOff));
        }

    }

}
