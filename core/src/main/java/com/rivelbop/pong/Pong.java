package com.rivelbop.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
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

    // Font rendering
    private SpriteBatch batch;
    private BitmapFont font;
    private GlyphLayout playerScoreGlyph;

    // Game elements
    private Player player;
    private Enemy enemy;
    private Ball ball;

    // Keep track of the scores
    private int playerScore = 0, enemyScore = 0;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();

        // Generate Size 36 Pong Bitmap Font
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("pong-score.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 36;
        font = generator.generateFont(parameter);
        generator.dispose(); // Generator is no longer necessary

        // Helps correctly center the player score font to the left of the center line
        playerScoreGlyph = new GlyphLayout();

        player = new Player();
        enemy = new Enemy();
        ball = new Ball();
    }

    public void reset() {
        playerScore = enemyScore = 0;
        player = new Player();
        enemy = new Enemy();
        ball = new Ball();
    }

    @Override
    public void render() {
        // Clear the screen to black
        ScreenUtils.clear(Color.BLACK);

        /* Updates */
        player.update(ball);
        enemy.update(ball);
        ball.update();

        if (ball.hasScored()) { // If either the player or enemy have scored
            if (ball.enemyHasScored) { // The enemy scored
                enemyScore++; // Increase the enemy's score
            } else { // The player scored
                playerScore++; // Increase the player's score
            }

            // Set player, enemy, and ball back to their center, starting positions
            player.BOUNDS.y = HEIGHT / 2f - player.HEIGHT / 2f;
            enemy.BOUNDS.y = HEIGHT / 2f - enemy.HEIGHT / 2f;
            ball.BOUNDS.setCenter(WIDTH / 2f, HEIGHT / 2f);

            // Reset the speedScale
            ball.speedScale = 1f;
        }

        // If the player or enemy get a score of 10, reset the game
        if (playerScore == 10 || enemyScore == 10) {
            reset();
        }

        // Calculate player score text size in the glyph
        playerScoreGlyph.setText(font, Integer.toString(playerScore));

        /* Drawing shapes to the screen */
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        // Draw center line
        shapeRenderer.rectLine(WIDTH / 2f, HEIGHT, WIDTH / 2f, 0f, 10f);
        // Draw ball
        shapeRenderer.rect(ball.BOUNDS.x, ball.BOUNDS.y, ball.WIDTH, ball.HEIGHT);
        // Draw player
        shapeRenderer.rect(player.BOUNDS.x, player.BOUNDS.y, player.WIDTH, player.HEIGHT);
        // Draw enemy
        shapeRenderer.rect(enemy.BOUNDS.x, enemy.BOUNDS.y, enemy.WIDTH, enemy.HEIGHT);
        shapeRenderer.end();

        /* Drawing font to the screen */
        batch.begin();
        // Draw player score
        font.draw(batch, Integer.toString(playerScore), WIDTH / 2f - playerScoreGlyph.width - 30f, HEIGHT);
        // Draw enemy score
        font.draw(batch, Integer.toString(enemyScore), WIDTH / 2f + 30f, HEIGHT);
        batch.end();
    }

    @Override
    public void dispose() {
        // Objects with C/C++ bindings must be disposed
        // These objects implement the Disposable interface
        shapeRenderer.dispose();
        batch.dispose();
        font.dispose();
    }
}
