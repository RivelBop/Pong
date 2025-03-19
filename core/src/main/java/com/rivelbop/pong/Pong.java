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

    private Ball ball;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        ball = new Ball();
    }

    @Override
    public void render() {
        // Clear the screen to black
        ScreenUtils.clear(Color.BLACK);

        /* Updates */
        ball.update();

        /* Drawing to the screen */
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        // BONUS: shapeRenderer.setColor(new Color(0.3f, 0.7f, 0.1f, 1f));
        shapeRenderer.rect(ball.BOUNDS.x, ball.BOUNDS.y, ball.WIDTH, ball.HEIGHT);
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        // Objects with C/C++ bindings must be disposed
        // These objects implement the Disposable interface
        shapeRenderer.dispose();
    }
}
