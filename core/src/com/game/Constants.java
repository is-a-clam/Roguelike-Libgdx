package com.game;

public final class Constants {
    public final static double AnimationDelta = (20/60.0);
    public final static double FrameDelta = (1/60.0);
    public final static int VelocityIterations = 6;
    public final static int PositionIterations = 2;

    public final static double damping = 0.7;
    public final static int speed = 100;

    public final static int FLOOR_LAYER = 0;
    public final static int DARKNESS_LAYER = 1;
    public final static int EXIT_COLLISION = 2;
    public final static int WALL_LAYER = 3;
    public final static int WALL_COLLISION = 4;
    public final static int BOX_BOTTOM = 5;
    public final static int LEDGE = 6;
    public final static int BOX_TOP = 7;
    public final static int ENEMY_SPAWN_LOCATION = 8;

    public final static int WIDTH = 16*16;
    public final static int HEIGHT = 10*16;

    public final static String ROOM_TEMPLATE = "TemplateRoom.tmx";
    public final static String DUNGEON_TILES = "DungeonTiles.png";
    public final static String BLUE_FOUNTAIN = "Animated_Tiles/Blue_Fountain.png";
    public final static String RED_FOUNTAIN = "Animated_Tiles/Red_Fountain.png";
    public final static String CANDLES = "Animated_Tiles/Candles.png";
    public final static String FLAGS = "Animated_Tiles/Flags.png";
    public final static String SPIKES = "Animated_Tiles/Spikes.png";

    public final static String PLAYER_SPRITES = "Player_Sprite.png";
    public final static String PLAYER_ATLAS = "Player_Sprite.atlas";

    // animations
    public final static String PLAYER_START_TEXTURE = "player_idle_anim_f0";
    public final static String PLAYER_IDlE_ANIM = "player_idle_anim";
    public final static int PLAYER_IDLE_ANIM_NO = 4;
    public final static String PLAYER_RUN_ANIM = "player_run_anim";
    public final static int PLAYER_RUN_ANIM_NO = 4;
}