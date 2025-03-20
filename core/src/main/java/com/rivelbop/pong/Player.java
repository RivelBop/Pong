package com.rivelbop.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;

public class Player {
    public final float
        // The size of the paddle
        WIDTH = 16f, HEIGHT = 80f,
        // The speed of the paddle
        SPEED = 275f;

    public final Rectangle BOUNDS;

    public Player() {
        // Choose a horizontal position for the paddle (1/8 of the screen width from the left)
        // Center the y-position of the paddle
        BOUNDS = new Rectangle(Pong.WIDTH / 8f, Pong.HEIGHT / 2f - HEIGHT / 2f, WIDTH, HEIGHT);
    }

    public void update(Ball ball) {
        float delta = Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) { // User is holding the UP ARROW key
            BOUNDS.y += SPEED * delta; // Move the player up
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) { // User is holding the DOWN ARROW key
            BOUNDS.y -= SPEED * delta; // Move the player down
        }

        if (BOUNDS.y + HEIGHT > Pong.HEIGHT) { // Player leaves the top of the screen
            BOUNDS.y = Pong.HEIGHT - HEIGHT; // Place the player directly under the top of the screen
        } else if (BOUNDS.y < 0f) { // Player leaves the bottom of the screen
            BOUNDS.y = 0f; // Place the player directly above the bottom of the screen
        }

        // Checks if the ball is to the right of the paddle (compares center x-positions)
        boolean ballOnRight = ball.BOUNDS.x + ball.WIDTH / 2f > BOUNDS.x + WIDTH / 2f;
        if (ballOnRight && BOUNDS.overlaps(ball.BOUNDS)) { // The ball is on the right and overlaps the paddle
            ball.hDir = 1; // Move the ball to the right (since the player is to the left of the screen)
            ball.speedScale += 0.05f; // Increase the speed of the ball by 5% of the base speed
        }
    }
}
