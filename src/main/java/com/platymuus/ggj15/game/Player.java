package com.platymuus.ggj15.game;

import com.platymuus.jsc.Hacks;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Joystick;

/**
 * Todo: Javadoc for Player.
 */
public class Player extends Entity {

    private boolean aHeldLast;

    private Interactable prevInteractable;

    public Player() {
        location = new Vector2f(0, 40);

        RectangleShape shape = new RectangleShape(new Vector2f(20, 20));
        shape.setFillColor(Color.BLUE);
        shape.setOrigin(10, 10);

        collision = new FloatRect(-10, -10, 20, 20);

        drawable = shape;
    }

    @Override
    public void update() {
        // key-movement handling
        boolean left = Control.LEFT.held();
        boolean right = Control.RIGHT.held();
        boolean up = Control.UP.held();
        boolean down = Control.DOWN.held();
        float x = left && !right ? -1 : right && !left ? 1 : 0;
        float y = up && !down ? -1 : down && !up ? 1 : 0;

        // joy-movement handling
        for (int j = 0; j < Joystick.JOYSTICK_COUNT; ++j) {
            if (!Joystick.isConnected(j)) continue;

            x += adjust(Joystick.getAxisPosition(j, Joystick.Axis.X));
            y += adjust(Joystick.getAxisPosition(j, Joystick.Axis.Y));
        }

        // apply movement
        float spd = 2.f;
        if (Control.GO_FAST.held()) spd *= 5;
        world.collideTranslate(this, new Vector2f(spd * x, spd * y));

        // search for interactable thing
        Interactable interactable = null;
        for (Entity entity : world.entities) {
            if (entity instanceof Interactable) {
                Vector2f delta = Vector2f.sub(entity.location, location);
                Interactable inter = ((Interactable) entity);
                if (Hacks.dist(delta) < 50 && inter.isInteractable()) {
                    interactable = inter;
                    break;
                }
            }
        }
        // update its state
        if (interactable != prevInteractable) {
            if (prevInteractable != null) prevInteractable.setActive(false);
            if (interactable != null) interactable.setActive(true);
        }
        // interact with it if needed
        boolean action = Control.ACTION.held();
        if (action && !aHeldLast && interactable != null) {
            interactable.interact();
        }

        // bookkeeping
        aHeldLast = action;
        prevInteractable = interactable;
    }

    private float adjust(float v) {
        v /= 100;
        if (Math.abs(v) < .3) {
            return 0;
        } else {
            return Math.signum(v) * (1 / .7f) * (Math.abs(v) - 0.3f);
        }
    }
}
