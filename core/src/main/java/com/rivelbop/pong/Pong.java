package com.rivelbop.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * A basic Pong clone.
 * Used to teach libGDX game development.
 *
 * @author David Jerzak
 * @see <a href="https://www.udemy.com/user/david-jerzak/">Udemy</a>
 */
public class Pong extends ApplicationAdapter {
    // Window size
    public static final int WIDTH = 640, HEIGHT = 480;

    // Draws shapes to the screen
    private ShapeRenderer shapeRenderer;

    // Game elements
    private Player player;
    private Ball ball;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        player = new Player();
        ball = new Ball();
    }

    @Override
    public void render() {
        // Clear the screen to black
        ScreenUtils.clear(Color.BLACK);

        /* Updates */
        player.update();
        ball.update();

        /* Drawing to the screen */
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        // Draw ball
        shapeRenderer.rect(ball.BOUNDS.x, ball.BOUNDS.y, ball.WIDTH, ball.HEIGHT);
        // Draw player
        shapeRenderer.rect(player.BOUNDS.x, player.BOUNDS.y, player.WIDTH, player.HEIGHT);
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        // Objects with C/C++ bindings must be disposed
        // These objects implement the Disposable interface
        shapeRenderer.dispose();
    }
}
