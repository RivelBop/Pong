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
    // Temporary shape movement (to demonstrate necessity of clearing the screen)
    private float shapeX = 150f;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render() {
        // Clear the screen to black
        ScreenUtils.clear(Color.BLACK);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Changes the color of all shapes that follow to yellow
        shapeRenderer.setColor(Color.YELLOW);

        // Draw rectangle
        shapeRenderer.rect(50f, 50f, 15f, 85f);

        shapeRenderer.setColor(Color.RED);

        // Draw circle
        shapeRenderer.circle(WIDTH / 2f, HEIGHT / 2f, 15f);

        shapeRenderer.setColor(Color.WHITE);

        // Draw triangle (Point 1, Point 2, Point 3, Color 1, Color 2, Color 3)
        shapeRenderer.triangle(90f, 90f, 110f, 110f, 110f, 90f, Color.RED, Color.GREEN, Color.BLUE);

        // Draw line
        shapeRenderer.line(0f, HEIGHT / 2f, WIDTH, HEIGHT / 2f);
        shapeRenderer.end();

        // If the screen is not cleared, this shape movement will cover the screen
        shapeX += 0.5f;

        // Change the shape type to just a line (instead of filled)
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(shapeX, 100f, 15f, 85f);
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        // Objects with C/C++ bindings must be disposed
        // These objects implement the Disposable interface
        shapeRenderer.dispose();
    }
}
