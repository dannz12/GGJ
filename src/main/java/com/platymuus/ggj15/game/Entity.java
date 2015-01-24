package com.platymuus.ggj15.game;

import com.platymuus.jsc.Hacks;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Vector2f;

/**
 * Todo: Javadoc for Entity.
 */
public class Entity implements Drawable {

    public Vector2f location = Vector2f.ZERO;
    protected World world;
    protected Drawable drawable;

    public void update() {

    }

    @Override
    public void draw(RenderTarget renderTarget, RenderStates renderStates) {
        if (drawable != null) {
            drawable.draw(renderTarget, Hacks.offset(renderStates, location));
        }
    }
}