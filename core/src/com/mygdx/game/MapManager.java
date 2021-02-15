package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class MapManager {

    public static TiledMap loadMap(String mapName) {
        TmxMapLoader mapLoader = new TmxMapLoader();
        return mapLoader.load(mapName);
    }
}
