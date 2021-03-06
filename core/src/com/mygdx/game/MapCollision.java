package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.ArrayList;

public class MapCollision {
    private TiledMapTileLayer layer;

    public MapCollision (Map map, int toleranceUp, int toleranceDown) {
        layer = map.getLayer("Default");
    }

    public boolean willHitLeftBoundary (Character character) {
        Coordinate cordToHit = new Coordinate(character.getX(), character.getY());
        cordToHit.addToX(character.getSpeedLeft() * -1);
        if (isTileBlocked(cellAtPosition((int) cordToHit.getX(),(int) cordToHit.getY()))) {
            return true;
        } else {
            cordToHit.addToY(character.getHeight()/4f);
            if (isTileBlocked(cellAtPosition((int) cordToHit.getX(),(int) cordToHit.getY()))) {
                return true;
            } else {
                cordToHit.addToY(2 * character.getHeight()/4f * -1);
                return isTileBlocked(cellAtPosition((int) cordToHit.getX(),(int) cordToHit.getY()));
            }
        }
    }

    public boolean willHitRightBoundary (Character character) {
        Coordinate cordToHit = new Coordinate(character.getX(), character.getY());
        cordToHit.addToX(character.getSpeedRight() + character.getWidth() + 5);
        if (isTileBlocked(cellAtPosition((int) cordToHit.getX(),(int) cordToHit.getY()))) {
            return true;
        } else {
            cordToHit.addToY(character.getHeight()/4f);
            if (isTileBlocked(cellAtPosition((int) cordToHit.getX(),(int) cordToHit.getY()))) {
                return true;
            } else {
                cordToHit.addToY(2 * character.getHeight()/4f * -1);
                return isTileBlocked(cellAtPosition((int) cordToHit.getX(),(int) cordToHit.getY()));
            }
        }
    }

    public boolean willHitUpperBoundary (Character character) {
        Coordinate cordToHit = new Coordinate(character.getX(), character.getY());
        cordToHit.addToY(character.getSpeedUp() + character.getHeight()/4f);
        if (isTileBlocked(cellAtPosition((int) cordToHit.getX(),(int) cordToHit.getY()))) {
            return true;
        } else {
            cordToHit.addToX(character.getWidth());
            return isTileBlocked(cellAtPosition((int) cordToHit.getX(),(int) cordToHit.getY()));
        }
    }

    public boolean willHitBottomBoundary (Character character) {
        Coordinate cordToHit = new Coordinate(character.getX(), character.getY());
        cordToHit.addToY(character.getSpeedDown() * -1 - 5);
        if (isTileBlocked(cellAtPosition((int) cordToHit.getX(),(int) cordToHit.getY()))) {
            return true;
        } else {
            cordToHit.addToX(character.getWidth());
            return isTileBlocked(cellAtPosition((int) cordToHit.getX(),(int) cordToHit.getY()));
        }
    }

    private TiledMapTileLayer.Cell cellAtPosition(int x, int y) {
        x = x / layer.getTileWidth();
        y = y / layer.getTileHeight();
        return layer.getCell(x,y);
    }

    private boolean isTileBlocked (TiledMapTileLayer.Cell cell) {
        return (cell.getTile().getProperties().containsKey("blocked") && cell.getTile().getProperties().get("blocked",Boolean.class).equals(true));
    }

    private boolean isTileFree (TiledMapTileLayer.Cell cell) {
        return (cell.getTile().getProperties().containsKey("end") && cell.getTile().getProperties().get("end",Boolean.class).equals(true));
    }

    public void willHitFreeTile (Character character) {
            Coordinate cordToHit = new Coordinate(character.getHitbox().getX() + character.getHitbox().getWidth() / 2, character.getHitbox().getY());
            cordToHit.addToY(character.getSpeedDown() * -1 - 1);
            if (isTileFree(cellAtPosition((int) cordToHit.getX(), (int) cordToHit.getY()))) {
                character.setFree();
            }
    }


}
