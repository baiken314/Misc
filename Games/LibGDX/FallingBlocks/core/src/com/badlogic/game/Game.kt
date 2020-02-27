package com.badlogic.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Vector3
import kotlin.math.max
import kotlin.math.min

class Game : ApplicationAdapter() {

    lateinit var batch: SpriteBatch
    lateinit var camera: OrthographicCamera
    lateinit var player: Player

    override fun create() {

        Gdx.graphics.setWindowedMode(1200, 800)

        batch = SpriteBatch()
        camera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        camera.setToOrtho(true)
        player = Player(Player.texture, 20f, 20f, 64f, 64f)
        FallingBlock(FallingBlock.texture, 0f, Gdx.graphics.height - 64f, Gdx.graphics.width.toFloat(), 64f)

    }

    override fun render() {

        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        camera.position.y = min(player.rect.y, 400f)
        camera.update()

        player.update()

        FallingBlock.fallHeight = camera.position.y
        FallingBlock.update()

        batch.begin()
        FallingBlock.draw(batch)
        player.draw(batch)
        batch.end()

        batch.projectionMatrix = camera.combined

    }

    override fun dispose() {
        batch.dispose()
    }

}