package com.badlogic.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle

class Player(texture: Texture, x: Float, y: Float, width: Float, height: Float):
        SpriteObject(texture, x, y, width, height) {

    var gravity = .7f
    var maxFall = 80f
    var runSpeed = 6f
    var runAccel = .7f
    var jumpSpeed = 17f
    var friction = 1.1f

    var jumping = true

    init {
        color = Color(1f, .4f, .6f, 1f)
    }

    companion object { var texture = Texture(Gdx.files.internal("graphics/sprites/block2.png")) }

    override fun update() {

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            vect.x -= runAccel
            if (vect.x < -runSpeed) vect.x = -runSpeed
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            vect.x += runAccel
            if (vect.x > runSpeed) vect.x = runSpeed
        }
        if (!jumping && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            vect.y = -jumpSpeed
            jumping = true
        }

        if (vect.y > maxFall) vect.y = maxFall
        vect.y += gravity

        var tox = rect.x + vect.x
        var toy = rect.y + vect.y

        if (toy + rect.height > Gdx.graphics.height) {
            toy = Gdx.graphics.height - rect.height
            jumping = false
            vect.y = 0f
        }

        var toxRect = Rectangle(tox, rect.y, rect.width, rect.height)
        var toyRect = Rectangle(rect.x, toy, rect.width, rect.height)
        FallingBlock.blocks.map {
            if (it.rect.overlaps(toyRect)) {
                if (toyRect.y < it.rect.y) {
                    toy = it.rect.y - rect.height
                    jumping = false
                    vect.x /= friction
                }
                else {
                    toy = it.rect.y + it.rect.height
                    toxRect.y = toy
                }
                vect.y = 0f
            }
            if (it.rect.overlaps(toxRect)) {
                if (toxRect.x < it.rect.x) tox = it.rect.x - rect.width
                else tox = it.rect.x + it.rect.width
                vect.x = 0f
            }
        }

        rect.x = tox
        rect.y = toy
        sprite.x = rect.x
        sprite.y = rect.y
        sprite.setSize(rect.width, rect.height)
        sprite.setFlip(false, true)
        sprite.color = color

    }

}