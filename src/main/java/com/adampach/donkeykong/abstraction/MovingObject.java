package com.adampach.donkeykong.abstraction;

import com.adampach.donkeykong.enums.MovingObjectsEnum;

import java.util.ArrayList;
import java.util.stream.Stream;

public abstract class MovingObject extends TextureObject implements Simulable
{
    private MovingObjectsEnum.ConstructionStatus constructionStatus;
    private MovingObjectsEnum.LadderStatus ladderStatus;
    private ArrayList<MovingObjectsEnum.LevelBorderStatus> levelBorderStatus;

    public MovingObject(int positionX, int positionY, int width, int height) {
        super(positionX, positionY, width, height);
        levelBorderStatus = new ArrayList<>();
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

    public abstract void handleCollision(Collisionable collisionable);
}
