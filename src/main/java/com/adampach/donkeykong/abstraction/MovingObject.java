package com.adampach.donkeykong.abstraction;

import com.adampach.donkeykong.enums.MovingObjectsEnum;
import com.adampach.donkeykong.objects.Construction;
import com.adampach.donkeykong.objects.Ladder;
import com.adampach.donkeykong.objects.LevelBorders;

import java.util.ArrayList;
import java.util.stream.Stream;

public abstract class MovingObject extends TextureObject implements Simulable
{
    private MovingObjectsEnum.ConstructionStatus constructionStatus;
    private MovingObjectsEnum.LadderStatus ladderStatus;
    private final ArrayList<MovingObjectsEnum.LevelBorderStatus> levelBorderStatus;
    private final ArrayList<GameObject> collidedObjects;

    public MovingObject(int positionX, int positionY, int width, int height) {
        super(positionX, positionY, width, height);
        levelBorderStatus = new ArrayList<>();
        collidedObjects = new ArrayList<>();
    }

    @Override
    public void resetSimulationCycle()
    {
        levelBorderStatus.clear();
        collidedObjects.clear();
        setConstructionStatus(MovingObjectsEnum.ConstructionStatus.None);
        setLadderStatus(MovingObjectsEnum.LadderStatus.None);
    }

    public MovingObjectsEnum.ConstructionStatus getConstructionStatus() {
        return constructionStatus;
    }

    public void setConstructionStatus(MovingObjectsEnum.ConstructionStatus constructionStatus) {
        this.constructionStatus = constructionStatus;
    }

    public MovingObjectsEnum.LadderStatus getLadderStatus() {
        return ladderStatus;
    }

    public void setLadderStatus(MovingObjectsEnum.LadderStatus ladderStatus) {
        this.ladderStatus = ladderStatus;
    }

    public void addLevelBorderStatus(MovingObjectsEnum.LevelBorderStatus status)
    {
        levelBorderStatus.add(status);
    }

    public void clearLevelBorderStatus()
    {
        levelBorderStatus.clear();
    }

    public void removeLevelBorderStatus(MovingObjectsEnum.LevelBorderStatus status)
    {
        levelBorderStatus.remove(status);
    }

    public Stream<MovingObjectsEnum.LevelBorderStatus> getLevelBorderStatusStream()
    {
        return levelBorderStatus.stream();
    }

    public int getCollisionObjectsSize()
    {
        return collidedObjects.size();
    }

    public GameObject getCollidedObjectAt(int index)
    {
        return collidedObjects.get(index);
    }

    public void handleCollision(Collisionable collisionable)
    {
        if(!collisionable.intersect(this.getRectangle()))
            return;
        if(collisionable instanceof Construction)
            handleConstructionCollision((Construction) collisionable);
        else if(collisionable instanceof Ladder)
            handleLadderCollision((Ladder) collisionable);
        else if(collisionable instanceof LevelBorders)
            handleLevelCollision((LevelBorders)collisionable);
    }

    private void handleConstructionCollision(Construction construction)
    {
        if(construction.getPositionY() < this.getMaxPositionY()
                && construction.getPositionY() + construction.getHeight() > getMaxPositionY())
            setConstructionStatus(MovingObjectsEnum.ConstructionStatus.Step);
        else if(getConstructionStatus() != MovingObjectsEnum.ConstructionStatus.Step)
        {
            if(construction.getPositionY() == this.getMaxPositionY())
                setConstructionStatus(MovingObjectsEnum.ConstructionStatus.On);
            else if (getConstructionStatus() != MovingObjectsEnum.ConstructionStatus.On)
                setConstructionStatus(MovingObjectsEnum.ConstructionStatus.In);
        }
        collidedObjects.add(construction);
    }

    private void handleLadderCollision(Ladder ladder)
    {
        if(ladder.getMaxPositionY() == this.getMaxPositionY())
            setLadderStatus(MovingObjectsEnum.LadderStatus.Bottom);
        else if(ladder.getMaxPositionY() > this.getMaxPositionY() &&
                this.getMaxPositionY() > ladder.getPositionY())
            setLadderStatus(MovingObjectsEnum.LadderStatus.In);
        else setLadderStatus(MovingObjectsEnum.LadderStatus.On);
    }

    private void handleLevelCollision(LevelBorders levelBorders)
    {
        if(getMaxPositionX() >= levelBorders.getWidth())
            addLevelBorderStatus(MovingObjectsEnum.LevelBorderStatus.Right);
        else if(getPositionX() <= 0)
            addLevelBorderStatus(MovingObjectsEnum.LevelBorderStatus.Left);
    }
}
