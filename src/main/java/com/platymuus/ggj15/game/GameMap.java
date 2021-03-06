package com.platymuus.ggj15.game;

import org.jsfml.system.Vector2f;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * info on the entities contained on the game map.
 */
public class GameMap {

    public static Collection<? extends Entity> entities() {
        LinkedList<Entity> L = new LinkedList<>();

        // Q1
        for (int j = 1; j <= 5; ++j) {
            add(L, new Path(), 550 * j, -300 * j);
        }
        add(L, new Dunes(), 550 * 8, -300 * 13);
        add(L, new Oasis(), 550 * 8 - 20, -300 * 13 - 50 + 1200);
        add(L, new StaticFollower("Joe"), 550 * 8, -1300);

        add(L, new ObeliskRune("feather"), 450, -200);
        add(L, new Well(1), 1000, -200);
        add(L, new ObeliskRune("scarab"), 550 * 8 + 100, -300 * 12);

        // Q2
        for (int j = 1; j <= 3; ++j) {
            add(L, new Path(), -750 * j, -750 * j);
        }
        add(L, new ObeliskRune("eye"), -750 * 4, -750 * 4);
        add(L, new Well(0), -750 * 4 - 100, -750 * 4 + 300);

        // Q3
        add(L, new Path(), -500, 300);
        add(L, new ObeliskRune("tad"), -1000, 600);
        for (int j = 1; j <= 3; ++j) {
            add(L, new Path(), -1000 + 100 * j, 600 + 600 * j);
        }
        add(L, new ObeliskRune("ankh"), -1000 + 100 * 4, 600 + 600 * 4);
        add(L, new ObeliskRune("bird"), -1000 + 100 * 7, 600 + 600 * 4 + 100);
        add(L, new Well(0), -1000 + 100, 600 + 600 * 4 - 100);

        // Q4
        add(L, new Path(), 600, 600);
        add(L, new Path(), 1200, 1100);
        add(L, new StaticFollower("Prof"), 1200, 1200);

        add(L, new Bunker(), 1200, 4500);
        add(L, new Key(), 3400, 3400);
        L.add(new Boat(new Vector2f(2000, 3500), new Vector2f(2000, 4000)));

        for (int j = -10; j <= 10; ++j) {
            add(L, new River(), j * 1347, 3450);
        }

        return L;
    }

    private static void add(List<Entity> L, Entity e, float x, float y) {
        e.location = new Vector2f(x, y);
        L.add(e);
    }
}
