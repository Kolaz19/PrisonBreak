package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Map {
    TiledMap map;
    OrthogonalTiledMapRenderer renderer;

    public Map(String mapName) {
        map = MapManager.loadMap(mapName);
        renderer = new OrthogonalTiledMapRenderer(map);
    }


    public void render(String layerName) {
        renderer.getBatch().begin();
        renderer.renderTileLayer(getLayer(layerName));
        renderer.getBatch().end();
    }

    public TiledMapTileLayer getLayer(String layerName) {
        return (TiledMapTileLayer) map.getLayers().get(layerName);
    }

    public void setView(OrthographicCamera camera) {
        renderer.setView(camera);
    }

    public float getMapHeight (String layerName) {
        return getLayer(layerName).getHeight() * getLayer(layerName).getTileHeight();
    }

    public float getMapWidth (String layerName) {
        return getLayer(layerName).getWidth() * getLayer(layerName).getTileWidth();
    }



}
