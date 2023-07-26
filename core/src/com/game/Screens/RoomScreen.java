package com.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.utils.Box2DBuild;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.game.Constants;
import com.game.MyGame;
import com.game.OrthogonalTiledMapRendererWithSprites;
import com.game.Room;
import com.game.Sprites.Player;

public class RoomScreen implements Screen{

    MyGame myGame;

    private Box2DDebugRenderer box2DDebugRenderer;
    private World world;
    private TiledMap map;
    private OrthogonalTiledMapRendererWithSprites renderer;

    private TextureAtlas playerAtlas;

    private Player player;

    private OrthographicCamera camera;
    private Viewport viewport;

    public RoomScreen(MyGame game, Room room) {
        this.myGame = game;
        this.world = new World(new Vector2(0, 0), true);

        this.box2DDebugRenderer = new Box2DDebugRenderer();

        this.playerAtlas = new TextureAtlas(Constants.PLAYER_ATLAS);

        this.player = new Player(world, this);

        this.map = room.getMap();
        this.renderer = new OrthogonalTiledMapRendererWithSprites(this.map);
        this.renderer.addSprite(this.player);

        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(Constants.WIDTH, Constants.HEIGHT, camera);

        this.camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);

        for (MapObject mapObject : map.getLayers().get(Constants.WALL_COLLISION).getObjects().getByType(RectangleMapObject.class)) {
            collisionFromMapObject(mapObject);
        }
        for (MapObject mapObject : map.getLayers().get(Constants.EXIT_COLLISION).getObjects().getByType(RectangleMapObject.class)) {
            collisionFromMapObject(mapObject);
        }
    }

    public TextureAtlas getPlayerAtlas(){
        return this.playerAtlas;
    }

    @Override
    public void show() {

    }

    private void collisionFromMapObject(MapObject mapObject) {
        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(rectangle.getX() + rectangle.getWidth()/2,
                rectangle.getY() + rectangle.getHeight()/2);
        body = world.createBody(bodyDef);
        shape.setAsBox(rectangle.getWidth()/2, rectangle.getHeight()/2);
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
    }

    private void update(float deltaTime) {
        world.step((float) Constants.FrameDelta, Constants.VelocityIterations, Constants.PositionIterations);
        player.update(deltaTime);

        this.camera.update();
        this.renderer.setView(camera);
        this.camera.position.x = player.getBox2DBody().getPosition().x;
        this.camera.position.y = player.getBox2DBody().getPosition().y;
    }

    private void handleInput(float dt) {
        float horizontalVelocity = player.getBox2DBody().getLinearVelocity().x;
        float verticalVelocity = player.getBox2DBody().getLinearVelocity().y;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            horizontalVelocity += Constants.speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            horizontalVelocity -= Constants.speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            verticalVelocity += Constants.speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            verticalVelocity -= Constants.speed;
        }
        horizontalVelocity *= Math.pow(1f - Constants.damping, dt * 10f);
        verticalVelocity *= Math.pow(1f - Constants.damping, dt * 10f);

        player.getBox2DBody().setLinearVelocity(horizontalVelocity, verticalVelocity);
    }

    @Override
    public void render(float delta) {
        update(delta);
        handleInput(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.renderer.render();

        //box2DDebugRenderer.render(world, camera.combined);

        this.myGame.getBatch().begin();
        this.myGame.getBatch().end();
        this.myGame.getBatch().setProjectionMatrix(camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        this.map.dispose();
        this.renderer.dispose();
    }
}
