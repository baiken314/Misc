/**
 * Superclass for all objects with associated sprites
 * - handles location, movement, and has default update and draw functions
 *
 * @author Bradley
 * @date   13 Feb 2020
 *
 */

package com.badlogic.game

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2

open class SpriteObject
    (texture: Texture, x: Float = 0f, y: Float = 0f, width: Float = 16f, height: Float = 16f) {

    var rect = Rectangle()
    var vect = Vector2()
    var sprite: Sprite = Sprite(texture)
    var color = Color.WHITE

    init {
        rect.x = x
        rect.y = y
        rect.width = width
        rect.height = height
        sprite.setSize(rect.width, rect.height)
        sprite.setFlip(false, true)
        sprite.color = color
    }

    open fun update() {
        rect.x += vect.x
        rect.y += vect.y
        sprite.x = rect.x
        sprite.y = rect.y
        sprite.setSize(rect.width, rect.height)
        sprite.setFlip(false, true)
        sprite.color = color
    }

    open fun draw(batch: SpriteBatch) {
        sprite.draw(batch)
    }

}