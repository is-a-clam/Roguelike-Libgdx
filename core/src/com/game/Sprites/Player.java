package com.game.Sprites;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.game.Constants;
import com.game.Room;
import com.game.Screens.RoomScreen;

import javax.xml.soap.Text;

public class Player extends Sprite {
    private World world;
    private Body box2DBody;

    private Animation<TextureRegion> playerIdle;
    private Animation<TextureRegion> playerRun;

    private enum State {IDLE, RUNNING};
    public State previousState;
    public State currentState;
    private boolean facingRight;
    private float stateTimer;

    public Player(World world, RoomScreen screen) {
        super(screen.getPlayerAtlas().findRegion(Constants.PLAYER_START_TEXTURE));

        currentState = State.IDLE;
        previousState = State.IDLE;
        facingRight = true;
        stateTimer = 0;

        this.world = world;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 0; i < Constants.PLAYER_IDLE_ANIM_NO; i++) {
            String regionString = Constants.PLAYER_IDlE_ANIM + "_f" + i;
            frames.add(new TextureRegion(screen.getPlayerAtlas().findRegion(regionString)));
        }
        playerIdle = new Animation<>((float) Constants.AnimationDelta, frames);
        frames.clear();

        for(int i = 0; i < Constants.PLAYER_RUN_ANIM_NO; i++) {
            String regionString = Constants.PLAYER_RUN_ANIM + "_f" + i;
            frames.add(new TextureRegion(screen.getPlayerAtlas().findRegion(regionString)));
        }
        playerRun = new Animation<>((float) Constants.AnimationDelta, frames);
        frames.clear();


        definePlayerBox2D();

        setBounds(0, 0, 16, 32);
        setRegion(screen.getPlayerAtlas().findRegion(Constants.PLAYER_START_TEXTURE),
                0, 0, 16, 32);
    }

    public Body getBox2DBody() {
        return box2DBody;
    }

    public void definePlayerBox2D() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(50, 50);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        box2DBody = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape polygonShape= new PolygonShape();
        polygonShape.setAsBox(8, 8);

        fixtureDef.shape = polygonShape;
        box2DBody.createFixture(fixtureDef);
    }

    public State getState() {
        if(box2DBody.getLinearVelocity().x != 0 || box2DBody.getLinearVelocity().y != 0) {
            return State.RUNNING;
        }
        return State.IDLE;
    }

    private TextureRegion getFrame(float deltaTime)
    {
        currentState = getState();
        TextureRegion region;

        switch(currentState)
        {
            case RUNNING:
                region = playerRun.getKeyFrame(stateTimer, true);
                break;
            case IDLE:
            default:
                region = playerIdle.getKeyFrame(stateTimer, true);
                break;
        }

        if((box2DBody.getLinearVelocity().x < 0 || !facingRight) && !region.isFlipX())
        {
            region.flip(true, false);
            facingRight = false;
        }
        else if((box2DBody.getLinearVelocity().x > 0 || facingRight) && region.isFlipX())
        {
            region.flip(true, false);
            facingRight = true;
        }

        if (currentState == previousState) {
            stateTimer += deltaTime;
        }
        else {
            stateTimer = 0;
        }
        previousState = currentState;

        return region;
    }

    public void update(float deltaTime) {
        setPosition(box2DBody.getPosition().x - getWidth()/2, box2DBody.getPosition().y - getHeight()/4);
        setRegion(getFrame(deltaTime));
    }
}
