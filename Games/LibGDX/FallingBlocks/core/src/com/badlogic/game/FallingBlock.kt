package com.badlogic.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import kotlin.random.Random

class FallingBlock(texture: Texture, x: Float = 0f, y: Float = 0f,
                   width: Float = 16f, height: Float = 16f):
        SpriteObject(texture, x, y, width, height) {

    var active = true
    var falling = true
    var fallSpeed = 1f + Random.nextFloat()
    var gravity = .1f

    init {
        blocks.add(this)
        vect.y = fallSpeed
        color = Color(Random.nextFloat() * .5f + .5f, Random.nextFloat() * .5f + .5f,
                Random.nextFloat() * .5f + .5f, 1f)
    }

    // store all active instances of the object
    companion object Manager {

        var minSize = 64
        var maxSize = 128
        var fallHeight = 0f

        var texture = Texture(Gdx.files.internal("graphics/sprites/block.png"))
        var blocks: MutableList<FallingBlock> = mutableListOf()

        fun update() {

            if (Random.nextDouble() < .03f) {
                var viable = true
                val nextRect = Rectangle()
                val size = Random.nextFloat() * (maxSize - minSize) + minSize
                nextRect.x = Random.nextFloat() * (Gdx.graphics.width + size) - size
                nextRect.y = fallHeight - size - 400f
                nextRect.width = size
                nextRect.height = size
                blocks.map {
                    if (nextRect.overlaps(it.rect)) {
                        viable = false
                        return
                    }
                }
                if (viable) FallingBlock(texture, nextRect.x, nextRect.y, nextRect.width, nextRect.height)
            }

            for (block in blocks) {
                block.update()
                if (block.rect.y > Gdx.graphics.height) {
                    block.active = false
                }
                if (block.rect.y + block.vect.y + block.rect.height > Gdx.graphics.height) {
                    block.falling = false
                    block.rect.y = Gdx.graphics.height - block.rect.height
                    block.sprite.y = block.rect.y
                }
                val nextRect = Rectangle(block.rect)
                nextRect.x += block.vect.x
                nextRect.y += block.vect.y
                for (other in blocks) {
                    if (block.falling && block.hashCode() != other.hashCode() && nextRect.overlaps(other.rect)) {
                        block.falling = false
                        block.sprite.y = block.rect.y
                        block.rect.y = other.rect.y - block.rect.height
                    }
                }
            }

            blocks.removeAll { !it.active }

            println(blocks.size)

        }

        fun draw(batch: SpriteBatch) {
            for (block in blocks) {
                block.draw(batch)
            }
        }

    }

    override fun update() {
        vect.y += gravity
        if (!falling) {
            vect.y = 0f
        }
        super.update()
    }

}