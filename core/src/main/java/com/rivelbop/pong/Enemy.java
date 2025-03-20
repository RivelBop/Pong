package com.rivelbop.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Enemy {
    // Stores the center position of the ball (used for AI)
    private final Vector2 BALL_POS = new Vector2();

    public final float
        WIDTH = 16f, HEIGHT = 80f,
        // The base speed of the paddle
        SPEED = 280f;

    public final Rectangle BOUNDS;

    public Enemy() {
        // Choose a horizontal position for the paddle (1/8 of the screen width from the right)
        // Center the y-position of the paddle
        BOUNDS = new Rectangle(Pong.WIDTH - Pong.WIDTH / 8f - WIDTH, Pong.HEIGHT / 2f - HEIGHT / 2f, WIDTH, HEIGHT);
    }

    public void update(Ball ball) {
        ball.BOUNDS.getCenter(BALL_POS); // Get and store the center position of the ball
        if (BALL_POS.x < Pong.WIDTH / 2f) { // Ball is to the left of the screen
            return; // Don't apply AI movement
        }

        float centerY = BOUNDS.y + HEIGHT / 2f, // The center y-position of the paddle
              delta = Gdx.graphics.getDeltaTime();

        if (centerY < BALL_POS.y) { // The paddle is below the ball
            BOUNDS.y += SPEED * delta; // Move the paddle up
        } else if (centerY > BALL_POS.y) { // The paddle is above the ball
            BOUNDS.y -= SPEED * delta; // Move the paddle down
        }

        if (BOUNDS.y + HEIGHT > Pong.HEIGHT) { // Enemy leaves the top of the screen
            BOUNDS.y = Pong.HEIGHT - HEIGHT; // Place the enemy directly under the top of the screen
        } else if (BOUNDS.y < 0f) { // Enemy leaves the bottom of the screen
            BOUNDS.y = 0f; // Place the enemy directly above the bottom of the screen
        }

        // Checks if the ball is to the left of the paddle (compares center x-positions)
        boolean ballOnLeft = ball.BOUNDS.x + ball.WIDTH / 2f < BOUNDS.x + WIDTH / 2f;
        if (ballOnLeft && BOUNDS.overlaps(ball.BOUNDS)) { // The ball is on the left and overlaps the paddle
            ball.hDir = -1; // Move the ball to the left (since the enemy is to the right of the screen)
            ball.speedScale += 0.05f; // Increase the speed of the ball by 5% of the base speed
        }
    }
}
