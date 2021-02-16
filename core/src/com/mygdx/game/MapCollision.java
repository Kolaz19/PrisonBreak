package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class MapCollision {
    private TiledMapTileLayer layer;
    private int toleranceUp;
    private int toleranceDown;

    public MapCollision (Map map, int toleranceUp, int toleranceDown) {
        layer = map.getLayer("Default");
        this.toleranceUp = toleranceUp;
        this.toleranceDown = toleranceDown;
    }

    //TODO adjust boundaries with character height/width
    public boolean willHitLeftBoundary (Character character, int speedInPixels) {
        Coordinate cordToHit = new Coordinate(character.getX(), character.getY());
        cordToHit.addToX(speedInPixels * -1);
        return isTileBlocked(cellAtPosition((int) cordToHit.getX(),(int) cordToHit.getY()));
    }

    public boolean willHitRightBoundary (Character character, int speedInPixels) {
        Coordinate cordToHit = new Coordinate(character.getX(), character.getY());
        cordToHit.addToX(speedInPixels);
        return isTileBlocked(cellAtPosition((int) cordToHit.getX(),(int) cordToHit.getY()));
    }

    public boolean willHitUpperBoundary (Character character, int speedInPixels) {
        Coordinate cordToHit = new Coordinate(character.getX(), character.getY());
        cordToHit.addToY(speedInPixels);
        return isTileBlocked(cellAtPosition((int) cordToHit.getX(),(int) cordToHit.getY()));
    }

    public boolean willHitBottomBoundary (Character character, int speedInPixels) {
        Coordinate cordToHit = new Coordinate(character.getX(), character.getY());
        cordToHit.addToY(speedInPixels * -1);
        return isTileBlocked(cellAtPosition((int) cordToHit.getX(),(int) cordToHit.getY()));
    }

    private TiledMapTileLayer.Cell cellAtPosition(int x, int y) {
        x = x / layer.getTileWidth();
        y = y / layer.getTileHeight();
        return layer.getCell(x,y);
    }

    private boolean isTileBlocked (TiledMapTileLayer.Cell cell) {
        return (cell.getTile().getProperties().containsKey("blocked") && cell.getTile().getProperties().get("blocked",Boolean.class).equals(true));
    }

}
