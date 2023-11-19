package com.adampach.donkeykong.statics;

import com.adampach.donkeykong.builders.LevelBuilder;
import com.adampach.donkeykong.data.LevelSettings;
import com.adampach.donkeykong.enums.DirectionEnums;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public class LevelDefinitions
{
    public static LevelBuilder getLevelOneBuilder(LevelSettings levelSettings)
    {
        return LevelBuilder.CreateBuilder(levelSettings)
                .addConstruction(new Rectangle2D(0, 580, 80, 20))
                .addConstruction(new Rectangle2D(80, 575, 80, 20))
                .addConstruction(new Rectangle2D(160, 570, 80, 20))
                .addConstruction(new Rectangle2D(240, 565, 80, 20))
                .addConstruction(new Rectangle2D(320, 560, 80, 20))
                .addConstruction(new Rectangle2D(400, 555, 80, 20))
                .addConstruction(new Rectangle2D(480, 550, 120, 20))
                .addConstruction(new Rectangle2D(480, 460, 80, 20))
                .addConstruction(new Rectangle2D(400, 455, 80, 20))
                .addConstruction(new Rectangle2D(320, 450, 80, 20))
                .addConstruction(new Rectangle2D(240, 445, 80, 20))
                .addConstruction(new Rectangle2D(160, 440, 80, 20))
                .addConstruction(new Rectangle2D(80, 435, 80, 20))
                .addConstruction(new Rectangle2D(0, 430, 80, 20))
                .addConstruction(new Rectangle2D(40, 340, 80, 20))
                .addConstruction(new Rectangle2D(120, 335, 80, 20))
                .addConstruction(new Rectangle2D(200, 330, 80, 20))
                .addConstruction(new Rectangle2D(280, 325, 80, 20))
                .addConstruction(new Rectangle2D(360, 320, 80, 20))
                .addConstruction(new Rectangle2D(440, 315, 80, 20))
                .addConstruction(new Rectangle2D(520, 310, 80, 20))
                .addConstruction(new Rectangle2D(520, 220, 80, 20))
                .addConstruction(new Rectangle2D(440, 215, 80, 20))
                .addConstruction(new Rectangle2D(360, 210, 80, 20))
                .addConstruction(new Rectangle2D(280, 205, 80, 20))
                .addConstruction(new Rectangle2D(0, 200, 280, 20))
                .addConstruction(new Rectangle2D(150, 80, 230, 20))
                .addLadder(new Rectangle2D(540, 460, 20, 90))
                .addLadder(new Rectangle2D(40, 340, 20, 90))
                .addLadder(new Rectangle2D(540, 220, 20, 90))
                .addLadder(new Rectangle2D(360, 80, 20, 130))
                .addBarrelGenerator(new Rectangle2D(0,100, 100, 100), DirectionEnums.HorizontalDirection.Right)
                .addVerticalMovementZone(new Rectangle2D(539, 219, 1, 1), DirectionEnums.VerticalDirection.Down)
                .addVerticalMovementZone(new Rectangle2D(540, 310, 1, 1), DirectionEnums.VerticalDirection.None)
                .addHorizontalMovementZone(new Rectangle2D(540, 310, 1, 1), DirectionEnums.HorizontalDirection.Left)
                .addDestroyBarrelZone(new Rectangle2D(0, 579, 1,1))
                .addPlayerSpawnPoint(new Point2D(10, 550));
    }
}
