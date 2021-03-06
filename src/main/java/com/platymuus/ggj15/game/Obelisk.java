package com.platymuus.ggj15.game;

import com.platymuus.ggj15.Resources;
import com.platymuus.jsc.BoundsHandler;
import com.platymuus.jsc.gui.Group;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

/**
 * Obelisk o' murder.
 */
public class Obelisk extends Interactable {

    private Sprite sprite;
    private Sprite horror;
    private Sprite glow;

    public Obelisk() {
        location = new Vector2f(0, -50);

        sprite = Resources.getSprite("game/obelisk.png");
        horror = Resources.getSprite("game/obelisk-horror.png");
        glow = Resources.getSprite("game/obelisk-glow.png");
        BoundsHandler.of(sprite).position(0.5f, 1f);
        BoundsHandler.of(horror).position(0.5f, 1f);
        glow.setOrigin(Vector2f.add(horror.getOrigin(), new Vector2f(50, 50)));
        drawable = new Group(glow, sprite, horror);

        collision = new FloatRect(-20, -20, 40, 40);
    }

    @Override
    public boolean isInteractable() {
        return world.runes == world.totalRunes;
    }

    @Override
    public String getInteractText() {
        return "Activate Obelisk";
    }

    @Override
    public String getUninteractableText() {
        return "Activate " + (world.totalRunes - world.runes) + " more runes...";
    }

    @Override
    public void interact() {
        world.fate = "a demonic";
    }

    @Override
    public void draw(RenderTarget renderTarget, RenderStates renderStates) {
        horror.setColor(new Color(Color.WHITE, 255 * world.runes / world.totalRunes));
        glow.setColor(new Color(Color.WHITE, world.runes == world.totalRunes ? (int) ((216) + 64 * Math.sin(System.currentTimeMillis() / 700.)) : 0));
        super.draw(renderTarget, renderStates);
    }
}
