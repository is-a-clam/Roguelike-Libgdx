package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.Array;

public class Room {

    private static AnimatedTiledMapTile blueFountainWallAnimation;
    private static AnimatedTiledMapTile blueFountainFloorAnimation;
    private static AnimatedTiledMapTile redFountainWallAnimation;
    private static AnimatedTiledMapTile redFountainFloorAnimation;
    private static AnimatedTiledMapTile frontCandleAnimation;
    private static AnimatedTiledMapTile rightCandleAnimation;
    private static AnimatedTiledMapTile leftCandleAnimation;
    private static AnimatedTiledMapTile flagAnimation;
    private static AnimatedTiledMapTile spikeAnimation;

    private static StaticTiledMapTile wall_front;
    private static StaticTiledMapTile wall_front_noDetail;
    private static StaticTiledMapTile wall_topRight;
    private static StaticTiledMapTile wall_topLeft;
    private static StaticTiledMapTile wall_bottomRight;
    private static StaticTiledMapTile wall_bottomLeft;

    private static StaticTiledMapTile ledge_front;
    private static StaticTiledMapTile ledge_topRight;
    private static StaticTiledMapTile ledge_topLeft;
    private static StaticTiledMapTile ledge_bottomRight;
    private static StaticTiledMapTile ledge_bottomLeft;
    private static StaticTiledMapTile ledge_cap;

    private static StaticTiledMapTile wall_side_right;
    private static StaticTiledMapTile wall_side_left;
    private static StaticTiledMapTile wall_corner_topRight;
    private static StaticTiledMapTile wall_corner_topLeft;
    private static StaticTiledMapTile wall_corner_bottomRight;
    private static StaticTiledMapTile wall_corner_bottomLeft;

    private static StaticTiledMapTile floor;
    private static StaticTiledMapTile floor_hole_topRight;
    private static StaticTiledMapTile floor_hole_topLeft;
    private static StaticTiledMapTile floor_hole_bottomRight;
    private static StaticTiledMapTile floor_hole_bottomLeft;
    private static StaticTiledMapTile floor_crack_1;
    private static StaticTiledMapTile floor_crack_2;

    private static StaticTiledMapTile darkness;
    private static StaticTiledMapTile darkness_faceUP_topRight;
    private static StaticTiledMapTile darkness_faceUP_topLeft;
    private static StaticTiledMapTile darkness_faceUP_bottomRight;
    private static StaticTiledMapTile darkness_faceUP_bottomLeft;
    private static StaticTiledMapTile darkness_faceDOWN_topRight;
    private static StaticTiledMapTile darkness_faceDOWN_topLeft;
    private static StaticTiledMapTile darkness_faceDOWN_bottomRight;
    private static StaticTiledMapTile darkness_faceDOWN_bottomLeft;
    private static StaticTiledMapTile darkness_faceRIGHT_topRight;
    private static StaticTiledMapTile darkness_faceRIGHT_topLeft;
    private static StaticTiledMapTile darkness_faceRIGHT_bottomRight;
    private static StaticTiledMapTile darkness_faceRIGHT_bottomLeft;
    private static StaticTiledMapTile darkness_faceLEFT_topRight;
    private static StaticTiledMapTile darkness_faceLEFT_topLeft;
    private static StaticTiledMapTile darkness_faceLEFT_bottomRight;
    private static StaticTiledMapTile darkness_faceLEFT_bottomLeft;

    private static StaticTiledMapTile box_low_bottom;
    private static StaticTiledMapTile box_low_top;
    private static StaticTiledMapTile box_high_bottom;
    private static StaticTiledMapTile box_high_top;

    private static StaticTiledMapTile wall_variation_1;
    private static StaticTiledMapTile wall_variation_2;
    private static StaticTiledMapTile wall_variation_3;
    private static StaticTiledMapTile wall_variation_floor;

    private static StaticTiledMapTile entrance_bottom;
    private static StaticTiledMapTile entrance_top;
    private static StaticTiledMapTile exit;

    private static StaticTiledMapTile empty;

    private static TiledMap mapTemplate;

    private TiledMap map;
    private RoomType roomType;

    private boolean topExit;
    private boolean bottomExit;
    private boolean leftExit;
    private boolean rightExit;

    private TiledMapTileLayer floorLayer;
    private TiledMapTileLayer darknessLayer;
    private MapLayer          exitCollision;
    private TiledMapTileLayer wallLayer;
    private MapLayer          wallCollision;
    private TiledMapTileLayer boxBottomLayer;
    private TiledMapTileLayer ledgeLayer;
    private TiledMapTileLayer boxTopLayer;

    private int[][] floorLayout;

    private boolean completed;

    public static void initialize() {
        Texture DungeonTileTexture  = new Texture(Gdx.files.internal(Constants.DUNGEON_TILES));
        Texture blueFountainTexture = new Texture(Gdx.files.internal(Constants.BLUE_FOUNTAIN));
        Texture redFountainTexture  = new Texture(Gdx.files.internal(Constants.RED_FOUNTAIN));
        Texture candlesTexture      = new Texture(Gdx.files.internal(Constants.CANDLES));
        Texture flagTexture         = new Texture(Gdx.files.internal(Constants.FLAGS));
        Texture spikeTexture        = new Texture(Gdx.files.internal(Constants.SPIKES));

        blueFountainWallAnimation  = createAnimatedTile(blueFountainTexture, 0);
        blueFountainFloorAnimation = createAnimatedTile(blueFountainTexture, 1);
        redFountainWallAnimation   = createAnimatedTile(redFountainTexture , 0);
        redFountainFloorAnimation  = createAnimatedTile(redFountainTexture , 1);
        frontCandleAnimation       = createAnimatedTile(candlesTexture     , 0);
        rightCandleAnimation       = createAnimatedTile(candlesTexture     , 1);
        leftCandleAnimation        = createAnimatedTile(candlesTexture     , 2);
        flagAnimation              = createAnimatedTile(flagTexture        , 0);
        spikeAnimation             = createAnimatedTile(spikeTexture       , 0);

        TextureRegion[][] DungeonTiles = TextureRegion.split(DungeonTileTexture, 16, 16);

        wall_front              = new StaticTiledMapTile(DungeonTiles[1][1]);
        wall_front_noDetail     = new StaticTiledMapTile(DungeonTiles[2][1]);
        wall_topRight           = new StaticTiledMapTile(DungeonTiles[1][4]);
        wall_topLeft            = new StaticTiledMapTile(DungeonTiles[1][3]);
        wall_bottomRight        = new StaticTiledMapTile(DungeonTiles[3][4]);
        wall_bottomLeft         = new StaticTiledMapTile(DungeonTiles[3][3]);

        ledge_front             = new StaticTiledMapTile(DungeonTiles[0][1]);
        ledge_topRight          = new StaticTiledMapTile(DungeonTiles[0][4]);
        ledge_topLeft           = new StaticTiledMapTile(DungeonTiles[0][3]);
        ledge_bottomRight       = new StaticTiledMapTile(DungeonTiles[2][4]);
        ledge_bottomLeft        = new StaticTiledMapTile(DungeonTiles[2][3]);
        ledge_cap               = new StaticTiledMapTile(DungeonTiles[3][0]);

        wall_side_right         = new StaticTiledMapTile(DungeonTiles[1][2]);
        wall_side_left          = new StaticTiledMapTile(DungeonTiles[1][0]);
        wall_corner_topRight    = new StaticTiledMapTile(DungeonTiles[0][2]);
        wall_corner_topLeft     = new StaticTiledMapTile(DungeonTiles[0][0]);
        wall_corner_bottomRight = new StaticTiledMapTile(DungeonTiles[2][2]);
        wall_corner_bottomLeft  = new StaticTiledMapTile(DungeonTiles[2][0]);

        floor                   = new StaticTiledMapTile(DungeonTiles[0][5]);
        floor_hole_topRight     = new StaticTiledMapTile(DungeonTiles[1][5]);
        floor_hole_topLeft      = new StaticTiledMapTile(DungeonTiles[1][7]);
        floor_hole_bottomRight  = new StaticTiledMapTile(DungeonTiles[2][6]);
        floor_hole_bottomLeft   = new StaticTiledMapTile(DungeonTiles[2][5]);
        floor_crack_1           = new StaticTiledMapTile(DungeonTiles[0][6]);
        floor_crack_2           = new StaticTiledMapTile(DungeonTiles[0][7]);

        darkness                       = new StaticTiledMapTile(DungeonTiles[3][1]);
        darkness_faceUP_topRight       = new StaticTiledMapTile(DungeonTiles[4][7]);
        darkness_faceUP_topLeft        = new StaticTiledMapTile(DungeonTiles[4][6]);
        darkness_faceUP_bottomRight    = new StaticTiledMapTile(DungeonTiles[5][7]);
        darkness_faceUP_bottomLeft     = new StaticTiledMapTile(DungeonTiles[5][6]);
        darkness_faceDOWN_topRight     = new StaticTiledMapTile(DungeonTiles[5][0]);
        darkness_faceDOWN_topLeft      = new StaticTiledMapTile(DungeonTiles[5][1]);
        darkness_faceDOWN_bottomRight  = new StaticTiledMapTile(DungeonTiles[4][0]);
        darkness_faceDOWN_bottomLeft   = new StaticTiledMapTile(DungeonTiles[4][1]);
        darkness_faceRIGHT_topRight    = new StaticTiledMapTile(DungeonTiles[5][3]);
        darkness_faceRIGHT_topLeft     = new StaticTiledMapTile(DungeonTiles[4][3]);
        darkness_faceRIGHT_bottomRight = new StaticTiledMapTile(DungeonTiles[5][2]);
        darkness_faceRIGHT_bottomLeft  = new StaticTiledMapTile(DungeonTiles[4][2]);
        darkness_faceLEFT_topRight     = new StaticTiledMapTile(DungeonTiles[4][4]);
        darkness_faceLEFT_topLeft      = new StaticTiledMapTile(DungeonTiles[5][4]);
        darkness_faceLEFT_bottomRight  = new StaticTiledMapTile(DungeonTiles[4][5]);
        darkness_faceLEFT_bottomLeft   = new StaticTiledMapTile(DungeonTiles[5][5]);

        box_low_bottom       = new StaticTiledMapTile(DungeonTiles[7][0]);
        box_low_top          = new StaticTiledMapTile(DungeonTiles[6][0]);
        box_high_bottom      = new StaticTiledMapTile(DungeonTiles[7][1]);
        box_high_top         = new StaticTiledMapTile(DungeonTiles[6][1]);

        wall_variation_1     = new StaticTiledMapTile(DungeonTiles[4][8]);
        wall_variation_2     = new StaticTiledMapTile(DungeonTiles[5][8]);
        wall_variation_3     = new StaticTiledMapTile(DungeonTiles[4][9]);
        wall_variation_floor = new StaticTiledMapTile(DungeonTiles[5][9]);

        entrance_bottom      = new StaticTiledMapTile(DungeonTiles[3][9]);
        entrance_top         = new StaticTiledMapTile(DungeonTiles[2][9]);
        exit                 = new StaticTiledMapTile(DungeonTiles[2][7]);

        empty                = new StaticTiledMapTile(DungeonTiles[3][5]);

        mapTemplate = new TmxMapLoader().load(Constants.ROOM_TEMPLATE);
    }

    private static AnimatedTiledMapTile createAnimatedTile(Texture texture, int row) {
        TextureRegion[] textureRegions = TextureRegion.split(texture, 16, 16)[row];
        Array<StaticTiledMapTile> frameTiles = new Array<StaticTiledMapTile>(textureRegions.length);
        for (TextureRegion textureRegion : textureRegions) {
            frameTiles.add(new StaticTiledMapTile(textureRegion));
        }
        return new AnimatedTiledMapTile((float) Constants.AnimationDelta, frameTiles);
    }

    private static int[][] RandomFloorLayout() {
        int noOfLayout = 4;
        int[][] layout = new int[28][28];

        switch((int)(Math.random()*noOfLayout)) {
            case 0:
                layout = new int[][]
                        {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,6,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,7,1,1,1,1,1},
                         {1,1,1,1,1,11,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,10,1,1,1,1,1},
                         {1,1,1,1,1,11,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,10,1,1,1,1,1},
                         {1,1,1,1,1,11,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,10,1,1,1,1,1},
                         {1,1,1,1,1,11,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,10,1,1,1,1,1},
                         {1,1,1,1,1,11,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,10,1,1,1,1,1},
                         {1,1,1,1,1,11,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,10,1,1,1,1,1},
                         {1,1,1,1,1,11,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,10,1,1,1,1,1},
                         {1,1,1,1,1,11,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,10,1,1,1,1,1},
                         {1,1,1,1,1,11,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,10,1,1,1,1,1},
                         {1,1,1,1,1,11,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,10,1,1,1,1,1},
                         {1,1,1,1,1,11,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,10,1,1,1,1,1},
                         {1,1,1,1,1,11,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,10,1,1,1,1,1},
                         {1,1,1,1,1,11,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,10,1,1,1,1,1},
                         {1,1,1,1,1,11,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,10,1,1,1,1,1},
                         {1,1,1,1,1,11,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,10,1,1,1,1,1},
                         {1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1},
                         {1,1,1,1,1,8,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,9,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
                break;
            case 1:
                layout = new int[][]
                        {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,3,3,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,2,3,3,3,3,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,2,3,3,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,3,3,3,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,3,3,3,2,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,3,3,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,3,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,3,3,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,2,3,3,3,3,3,3,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,3,3,2,3,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,2,3,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
                break;
            case 2:
                layout = new int[][]
                        {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1},
                         {1,1,1,3,3,3,3,3,3,1,1,1,1,1,1,1,1,1,1,3,3,3,3,3,3,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,3,3,3,3,3,3,1,1,1,1,1,1,1,1,1,1,3,3,3,3,3,3,1,1,1},
                         {1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
                break;
            case 3:
                layout = new int[][]
                        {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1},
                         {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,1,1,1,1,3,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1},
                         {1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1},
                         {1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1},
                         {1,1,1,1,3,1,1,1,1,1,1,1,1,3,3,1,1,1,1,1,1,1,1,3,1,1,1,1},
                         {1,1,1,1,3,1,1,1,1,1,1,1,2,3,3,3,3,1,1,1,1,1,1,3,1,1,1,1},
                         {1,1,1,1,3,1,1,1,1,1,1,1,1,3,3,2,1,1,1,1,1,1,1,3,1,1,1,1},
                         {1,1,1,1,3,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,3,1,1,1,1},
                         {1,1,1,1,3,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,3,1,1,1,1},
                         {1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1},
                         {1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1},
                         {1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1},
                         {1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,3,1,1,1,1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
                         {1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
                break;
        }
        return layout;
    }

    private static int[] getTileCoordFromLayout(int row, int col) {
        int[] coord = new int[2];
        coord[0] = col + 2;
        coord[1] = 29 - row;
        return coord;
    }

    public Room(RoomType roomType, boolean topExit, boolean bottomExit, boolean leftExit, boolean rightExit) {
        this.roomType   = roomType;
        this.topExit    = topExit;
        this.bottomExit = bottomExit;
        this.leftExit   = leftExit;
        this.rightExit  = rightExit;

        this.map = mapTemplate;

        MapLayers layers = this.map.getLayers();

        this.floorLayer     = (TiledMapTileLayer) layers.get(Constants.FLOOR_LAYER);
        this.darknessLayer  = (TiledMapTileLayer) layers.get(Constants.DARKNESS_LAYER);
        this.exitCollision  =                     layers.get(Constants.EXIT_COLLISION);
        this.wallLayer      = (TiledMapTileLayer) layers.get(Constants.WALL_LAYER);
        this.wallCollision  =                     layers.get(Constants.WALL_COLLISION);
        this.boxBottomLayer = (TiledMapTileLayer) layers.get(Constants.BOX_BOTTOM);
        this.ledgeLayer     = (TiledMapTileLayer) layers.get(Constants.LEDGE);
        this.boxTopLayer    = (TiledMapTileLayer) layers.get(Constants.BOX_TOP);

        this.floorLayout = Room.RandomFloorLayout();

        this.addExits();
        this.applyLayout();
        this.addWallDetail();
        this.addFloorDetail();
    }

    private void addExits() {
        if (topExit) {
            this.floorLayer.setCell(15, 31, new Cell().setTile(Room.floor));
            this.floorLayer.setCell(16, 31, new Cell().setTile(Room.floor));
            this.floorLayer.setCell(15, 30, new Cell().setTile(Room.floor));
            this.floorLayer.setCell(16, 30, new Cell().setTile(Room.floor));
            this.darknessLayer.setCell(15, 31, new Cell().setTile(Room.darkness));
            this.darknessLayer.setCell(16, 31, new Cell().setTile(Room.darkness));
            this.darknessLayer.setCell(15, 30, new Cell().setTile(Room.darkness_faceDOWN_bottomRight));
            this.darknessLayer.setCell(16, 30, new Cell().setTile(Room.darkness_faceDOWN_bottomLeft));
            this.darknessLayer.setCell(15, 29, new Cell().setTile(Room.darkness_faceDOWN_topRight));
            this.darknessLayer.setCell(16, 29, new Cell().setTile(Room.darkness_faceDOWN_topLeft));
            this.exitCollision.getObjects().add(new RectangleMapObject(240,496,32,16));
            this.wallLayer.setCell(14, 30, new Cell().setTile(Room.wall_bottomRight));
            this.ledgeLayer.setCell(14, 31, new Cell().setTile(Room.ledge_bottomRight));
            this.wallCollision.getObjects().add(new RectangleMapObject(235, 496, 5, 16));
            this.wallLayer.setCell(17, 30, new Cell().setTile(Room.wall_bottomLeft));
            this.ledgeLayer.setCell(17, 31, new Cell().setTile(Room.ledge_bottomLeft));
            this.wallCollision.getObjects().add(new RectangleMapObject(272, 496, 5, 16));
        }
        else {
            this.wallLayer.setCell(15, 30, new Cell().setTile(Room.wall_front));
            this.wallLayer.getCell(15, 30).getTile().getProperties().put("details", Boolean.TRUE);
            this.ledgeLayer.setCell(15, 31, new Cell().setTile(Room.ledge_front));
            this.wallLayer.setCell(16, 30, new Cell().setTile(Room.wall_front));
            this.wallLayer.getCell(16, 30).getTile().getProperties().put("details", Boolean.TRUE);
            this.ledgeLayer.setCell(16, 31, new Cell().setTile(Room.ledge_front));
            this.wallCollision.getObjects().add(new RectangleMapObject(240,480,32,16));
        }
        if (bottomExit) {
            this.floorLayer.setCell(15, 0, new Cell().setTile(Room.floor));
            this.floorLayer.setCell(16, 0, new Cell().setTile(Room.floor));
            this.floorLayer.setCell(15, 1, new Cell().setTile(Room.floor));
            this.floorLayer.setCell(16, 1, new Cell().setTile(Room.floor));
            this.darknessLayer.setCell(15, 0, new Cell().setTile(Room.darkness));
            this.darknessLayer.setCell(16, 0, new Cell().setTile(Room.darkness));
            this.darknessLayer.setCell(15, 1, new Cell().setTile(Room.darkness_faceUP_bottomLeft));
            this.darknessLayer.setCell(16, 1, new Cell().setTile(Room.darkness_faceUP_bottomRight));
            this.darknessLayer.setCell(15, 2, new Cell().setTile(Room.darkness_faceUP_topLeft));
            this.darknessLayer.setCell(16, 2, new Cell().setTile(Room.darkness_faceUP_topRight));
            this.exitCollision.getObjects().add(new RectangleMapObject(240,0,32,16));
            this.wallLayer.setCell(14, 1, new Cell().setTile(Room.wall_topRight));
            this.ledgeLayer.setCell(14, 2, new Cell().setTile(Room.ledge_topRight));
            this.wallLayer.setCell(14, 0, new Cell().setTile(Room.wall_side_left));
            this.wallCollision.getObjects().add(new RectangleMapObject(235, 0, 5, 16));
            this.wallLayer.setCell(17, 1, new Cell().setTile(Room.wall_topLeft));
            this.ledgeLayer.setCell(17, 2, new Cell().setTile(Room.ledge_topLeft));
            this.wallLayer.setCell(17, 0, new Cell().setTile(Room.wall_side_right));
            this.wallCollision.getObjects().add(new RectangleMapObject(272, 0, 5, 16));
        }
        else {
            this.wallLayer.setCell(15, 1, new Cell().setTile(Room.wall_front_noDetail));
            this.wallLayer.getCell(15, 1).getTile().getProperties().put("details", Boolean.FALSE);
            this.ledgeLayer.setCell(15, 2, new Cell().setTile(Room.ledge_front));
            this.wallLayer.setCell(16, 1, new Cell().setTile(Room.wall_front_noDetail));
            this.wallLayer.getCell(16, 1).getTile().getProperties().put("details", Boolean.FALSE);
            this.ledgeLayer.setCell(16, 2, new Cell().setTile(Room.ledge_front));
            this.wallCollision.getObjects().add(new RectangleMapObject(240,16,32,16));
        }
        if (leftExit) {
            this.floorLayer.setCell(0, 15, new Cell().setTile(Room.floor));
            this.floorLayer.setCell(0, 16, new Cell().setTile(Room.floor));
            this.floorLayer.setCell(1, 15, new Cell().setTile(Room.floor));
            this.floorLayer.setCell(1, 16, new Cell().setTile(Room.floor));
            this.darknessLayer.setCell(0, 15, new Cell().setTile(Room.darkness));
            this.darknessLayer.setCell(0, 16, new Cell().setTile(Room.darkness));
            this.darknessLayer.setCell(1, 15, new Cell().setTile(Room.darkness_faceRIGHT_bottomRight));
            this.darknessLayer.setCell(1, 16, new Cell().setTile(Room.darkness_faceRIGHT_bottomLeft));
            this.darknessLayer.setCell(2, 15, new Cell().setTile(Room.darkness_faceRIGHT_topRight));
            this.darknessLayer.setCell(2, 16, new Cell().setTile(Room.darkness_faceRIGHT_topLeft));
            this.exitCollision.getObjects().add(new RectangleMapObject(0,240,16,32));
            this.wallLayer.setCell(1, 14, new Cell().setTile(Room.wall_topRight));
            this.ledgeLayer.setCell(1, 15, new Cell().setTile(Room.ledge_topRight));
            this.wallLayer.setCell(0, 14, new Cell().setTile(Room.wall_front_noDetail));
            this.wallLayer.getCell(0, 14).getTile().getProperties().put("details", Boolean.FALSE);
            this.ledgeLayer.setCell(0, 15, new Cell().setTile(Room.ledge_front));
            this.wallCollision.getObjects().add(new RectangleMapObject(0,224,32,16));
            this.wallLayer.setCell(1, 17, new Cell().setTile(Room.wall_bottomRight));
            this.ledgeLayer.setCell(1, 18, new Cell().setTile(Room.ledge_bottomRight));
            this.wallLayer.setCell(0, 17, new Cell().setTile(Room.wall_front_noDetail));
            this.wallLayer.getCell(0, 17).getTile().getProperties().put("details", Boolean.FALSE);
            this.ledgeLayer.setCell(0, 18, new Cell().setTile(Room.ledge_front));
            this.wallCollision.getObjects().add(new RectangleMapObject(0,272,32,16));
        }
        else {
            this.wallLayer.setCell(1, 15, new Cell().setTile(Room.wall_side_left));
            this.wallLayer.setCell(1, 16, new Cell().setTile(Room.wall_side_left));
            this.wallCollision.getObjects().add(new RectangleMapObject(27,240,5,32));
        }
        if (rightExit) {
            this.floorLayer.setCell(30, 15, new Cell().setTile(Room.floor));
            this.floorLayer.setCell(30, 16, new Cell().setTile(Room.floor));
            this.floorLayer.setCell(31, 15, new Cell().setTile(Room.floor));
            this.floorLayer.setCell(31, 16, new Cell().setTile(Room.floor));
            this.darknessLayer.setCell(31, 15, new Cell().setTile(Room.darkness));
            this.darknessLayer.setCell(31, 16, new Cell().setTile(Room.darkness));
            this.darknessLayer.setCell(30, 15, new Cell().setTile(Room.darkness_faceLEFT_bottomLeft));
            this.darknessLayer.setCell(30, 16, new Cell().setTile(Room.darkness_faceLEFT_bottomRight));
            this.darknessLayer.setCell(29, 15, new Cell().setTile(Room.darkness_faceLEFT_topLeft));
            this.darknessLayer.setCell(29, 16, new Cell().setTile(Room.darkness_faceLEFT_topRight));
            this.exitCollision.getObjects().add(new RectangleMapObject(496,240,16,32));
            this.wallLayer.setCell(30, 14, new Cell().setTile(Room.wall_topLeft));
            this.ledgeLayer.setCell(30, 15, new Cell().setTile(Room.ledge_topLeft));
            this.wallLayer.setCell(31, 14, new Cell().setTile(Room.wall_front_noDetail));
            this.wallLayer.getCell(31, 14).getTile().getProperties().put("details", Boolean.FALSE);
            this.ledgeLayer.setCell(31, 15, new Cell().setTile(Room.ledge_front));
            this.wallCollision.getObjects().add(new RectangleMapObject(480,224,32,16));
            this.wallLayer.setCell(30, 17, new Cell().setTile(Room.wall_bottomLeft));
            this.ledgeLayer.setCell(30, 18, new Cell().setTile(Room.ledge_bottomLeft));
            this.wallLayer.setCell(31, 17, new Cell().setTile(Room.wall_front_noDetail));
            this.wallLayer.getCell(31, 17).getTile().getProperties().put("details", Boolean.FALSE);
            this.ledgeLayer.setCell(31, 18, new Cell().setTile(Room.ledge_front));
            this.wallCollision.getObjects().add(new RectangleMapObject(480,272,32,16));
        }
        else {
            this.wallLayer.setCell(30, 15, new Cell().setTile(Room.wall_side_right));
            this.wallLayer.setCell(30, 16, new Cell().setTile(Room.wall_side_right));
            this.wallCollision.getObjects().add(new RectangleMapObject(480,240,5,32));
        }
    }

    private void applyLayout() {
        for (int row = 0; row < this.floorLayout.length; row++) {
            for (int col = 0; col < this.floorLayout[row].length; col++) {
                int[] tileCoord = Room.getTileCoordFromLayout(row, col);
                switch(this.floorLayout[row][col]) {
                    // no floor
                    case 0:
                        this.floorLayer.setCell(tileCoord[0], tileCoord[1],
                                new Cell().setTile(Room.empty));
                        break;
                    // low box
                    case 2:
                        this.boxBottomLayer.setCell(tileCoord[0], tileCoord[1],
                                new Cell().setTile(Room.box_low_bottom));
                        this.boxTopLayer.setCell(tileCoord[0], tileCoord[1]+1,
                                new Cell().setTile(Room.box_low_top));
                        this.wallCollision.getObjects().add(new RectangleMapObject(
                                tileCoord[0]*16, tileCoord[1]*16,
                                16, 16));
                        break;
                    // high box
                    case 3:
                        this.boxBottomLayer.setCell(tileCoord[0], tileCoord[1],
                                new Cell().setTile(Room.box_high_bottom));
                        this.boxTopLayer.setCell(tileCoord[0], tileCoord[1]+1,
                                new Cell().setTile(Room.box_high_top));
                        this.wallCollision.getObjects().add(new RectangleMapObject(
                                tileCoord[0]*16, tileCoord[1]*16,
                                16, 16));
                        break;
                    // front wall
                    case 4:
                        this.wallLayer.setCell(tileCoord[0], tileCoord[1],
                                new Cell().setTile(Room.wall_front));
                        Boolean b = true;
                        this.wallLayer.getCell(tileCoord[0], tileCoord[1]).getTile().getProperties().put("details", Boolean.TRUE);
                        this.ledgeLayer.setCell(tileCoord[0], tileCoord[1]+1,
                                new Cell().setTile(Room.ledge_front));
                        this.wallCollision.getObjects().add(new RectangleMapObject(
                                tileCoord[0]*16, tileCoord[1]*16,
                                16, 16));
                        break;
                    // front wall no detail
                    case 5:
                        this.wallLayer.setCell(tileCoord[0], tileCoord[1],
                                new Cell().setTile(Room.wall_front_noDetail));
                        this.wallLayer.getCell(tileCoord[0], tileCoord[1]).getTile().getProperties().put("details", Boolean.FALSE);
                        this.ledgeLayer.setCell(tileCoord[0], tileCoord[1]+1,
                                new Cell().setTile(Room.ledge_front));
                        this.wallCollision.getObjects().add(new RectangleMapObject(
                                tileCoord[0]*16, tileCoord[1]*16,
                                16, 16));
                        break;
                    // top left wall
                    case 6:
                        this.wallLayer.setCell(tileCoord[0], tileCoord[1],
                                new Cell().setTile(Room.wall_topLeft));
                        this.ledgeLayer.setCell(tileCoord[0], tileCoord[1]+1,
                                new Cell().setTile(Room.ledge_topLeft));
                        this.wallCollision.getObjects().add(new RectangleMapObject(
                                tileCoord[0]*16, tileCoord[1]*16,
                                16, 16));
                        break;
                    // top right wall
                    case 7:
                        this.wallLayer.setCell(tileCoord[0], tileCoord[1],
                                new Cell().setTile(Room.wall_topRight));
                        this.ledgeLayer.setCell(tileCoord[0], tileCoord[1]+1,
                                new Cell().setTile(Room.ledge_topRight));
                        this.wallCollision.getObjects().add(new RectangleMapObject(
                                tileCoord[0]*16, tileCoord[1]*16,
                                16, 16));
                        break;
                    // bottom left wall
                    case 8:
                        this.wallLayer.setCell(tileCoord[0], tileCoord[1],
                                new Cell().setTile(Room.wall_bottomLeft));
                        this.ledgeLayer.setCell(tileCoord[0], tileCoord[1]+1,
                                new Cell().setTile(Room.ledge_bottomLeft));
                        this.wallCollision.getObjects().add(new RectangleMapObject(
                                tileCoord[0]*16, tileCoord[1]*16,
                                16, 16));
                        this.wallCollision.getObjects().add(new RectangleMapObject(
                                tileCoord[0]*16, (tileCoord[1]+1)*16,
                                5, 16));
                        break;
                    // bottom right wall
                    case 9:
                        this.wallLayer.setCell(tileCoord[0], tileCoord[1],
                                new Cell().setTile(Room.wall_bottomRight));
                        this.ledgeLayer.setCell(tileCoord[0], tileCoord[1]+1,
                                new Cell().setTile(Room.ledge_bottomRight));
                        this.wallCollision.getObjects().add(new RectangleMapObject(
                                tileCoord[0]*16, tileCoord[1]*16,
                                16, 16));
                        this.wallCollision.getObjects().add(new RectangleMapObject(
                                tileCoord[0]*16+11, (tileCoord[1]+1)*16,
                                5, 16));
                        break;
                    // side left wall
                    case 10:
                        this.wallLayer.setCell(tileCoord[0], tileCoord[1],
                                new Cell().setTile(Room.wall_side_left));
                        this.floorLayer.setCell(tileCoord[0], tileCoord[1],
                                new Cell().setTile(Room.empty));
                        this.wallCollision.getObjects().add(new RectangleMapObject(
                                tileCoord[0]*16+11, tileCoord[1]*16,
                                5, 16));
                        break;
                    // side right wall
                    case 11:
                        this.wallLayer.setCell(tileCoord[0], tileCoord[1],
                                new Cell().setTile(Room.wall_side_right));
                        this.floorLayer.setCell(tileCoord[0], tileCoord[1],
                                new Cell().setTile(Room.empty));
                        this.wallCollision.getObjects().add(new RectangleMapObject(
                                tileCoord[0]*16, tileCoord[1]*16,
                                5, 16));
                        break;
                }
            }
        }
    }

    private void addWallDetail() {
        for (int x = 0; x < this.wallLayer.getWidth(); x++) {
            for (int y = 0; y < this.wallLayer.getHeight(); y++) {
                Cell wallCell = this.wallLayer.getCell(x, y);

                if (wallCell != null) {
                    MapProperties properties = wallCell.getTile().getProperties();

                    if (properties.containsKey("details") && properties.get("details", Boolean.class)) {
                        switch ((int) (Math.random() * 15)) {
                            case 0:
                                this.ledgeLayer.getCell(x, y+1).setTile(Room.ledge_cap);
                                wallCell.setTile(Room.blueFountainWallAnimation);
                                this.floorLayer.getCell(x, y-1).setTile(Room.blueFountainFloorAnimation);
                                this.floorLayout[30-y][x-2] = 11;
                                break;
                            case 1:
                                this.ledgeLayer.getCell(x, y+1).setTile(Room.ledge_cap);
                                wallCell.setTile(Room.redFountainWallAnimation);
                                this.floorLayer.getCell(x, y-1).setTile(Room.redFountainFloorAnimation);
                                this.floorLayout[30-y][x-2] = 11;
                                break;
                            case 2:
                                wallCell.setTile(Room.wall_variation_3);
                                this.floorLayer.getCell(x, y - 1).setTile(Room.wall_variation_floor);
                                this.floorLayout[30-y][x-2] = 11;
                                break;
                            case 3:
                                wallCell.setTile(Room.wall_variation_1);
                                break;
                            case 4:
                                wallCell.setTile(Room.wall_variation_2);
                                break;
                        }
                    }
                }
            }
        }
    }

    private void addFloorDetail() {
        for (int row = 0; row < this.floorLayout.length; row++) {
            for (int col = 0; col < this.floorLayout[row].length; col++) {
                int[] tileCoord = Room.getTileCoordFromLayout(row, col);
                if (this.floorLayout[row][col] == 1) {
                    switch ((int) (Math.random() * 25)) {
                        case 0:
                            if (row != this.floorLayout.length-1 && col != this.floorLayout.length-1
                                    && this.floorLayout[row+1][col] == 1
                                    && this.floorLayout[row][col+1] == 1
                                    && this.floorLayout[row+1][col+1] == 1) {
                                this.floorLayer.getCell(tileCoord[0], tileCoord[1]).setTile(Room.floor_hole_topLeft);
                                this.floorLayout[row][col] = 11;
                                this.floorLayer.getCell(tileCoord[0]+1, tileCoord[1]).setTile(Room.floor_hole_topRight);
                                this.floorLayout[row][col+1] = 11;
                                this.floorLayer.getCell(tileCoord[0], tileCoord[1]-1).setTile(Room.floor_hole_bottomLeft);
                                this.floorLayout[row+1][col] = 11;
                                this.floorLayer.getCell(tileCoord[0]+1, tileCoord[1]-1).setTile(Room.floor_hole_bottomRight);
                                this.floorLayout[row+1][col+1] = 11;
                            }
                            break;
                        case 1:
                            this.floorLayer.getCell(tileCoord[0], tileCoord[1]).setTile(Room.floor_crack_1);
                            this.floorLayout[row][col] = 11;
                            break;
                        case 2:
                            this.floorLayer.getCell(tileCoord[0], tileCoord[1]).setTile(Room.floor_crack_2);
                            this.floorLayout[row][col] = 11;
                            break;
                    }
                }
            }
        }
    }

    public TiledMap getMap() {
        return this.map;
    }
}
