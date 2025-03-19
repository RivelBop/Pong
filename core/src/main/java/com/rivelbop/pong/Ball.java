package com.rivelbop.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class Ball {
    public final float
        // The size of the ball
        WIDTH = 16f, HEIGHT = 16f,
        // The base speed of the ball
        SPEED = 300f;

    // Used for rendering and collisions
    public final Rectangle BOUNDS;

    // Keeps track of the horizontal direction (hDir) and vertical direction (vDir) of the ball
    public int hDir = -1, vDir = 1;

    public Ball() {
        // The position of the ball is based in the bottom left corner
        // To position from the center, we have to subtract half the width and height from the x and y coordinates
        // This will shift the ball accordingly
        BOUNDS = new Rectangle(Pong.WIDTH / 2f - WIDTH / 2f, Pong.HEIGHT / 2f - HEIGHT / 2f, WIDTH, HEIGHT);
    }

    public void update() {
        if (BOUNDS.x <= 0f) { // Ball leaves left side of the screen
            hDir = 1; // Move right
        } else if (BOUNDS.x + WIDTH >= Pong.WIDTH) { // Ball leaves right side of the screen
            hDir = -1; // Move left
        }

        if (BOUNDS.y <= 0f) { // Ball leaves the bottom of the screen
            vDir = 1; // Move up
        } else if (BOUNDS.y + HEIGHT >= Pong.HEIGHT) { // Ball leaves the top of the screen
            vDir = -1; // Move down
        }

        // Stores the time span between the current frame and the last frame in seconds
        // Used to keep consistent movement no matter the speed/FPS of the system
        float delta = Gdx.graphics.getDeltaTime();
        // Move the ball horizontally based on the speed, horizontal direction, and delta time
        BOUNDS.x += SPEED * hDir * delta;
        // Move the ball vertically based on the speed, vertical direction, and delta time
        BOUNDS.y += SPEED * vDir * delta;
    }
}
