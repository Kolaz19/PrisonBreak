package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class MapManager {

    public static TiledMap loadMap(String mapName) {
        TmxMapLoader mapLoader = new TmxMapLoader();
        TmxMapLoader.Parameters parameters = new TmxMapLoader.Parameters();
        return mapLoader.load(mapName, parameters);
    }
}
