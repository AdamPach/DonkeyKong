package com.adampach.donkeykong.statics;

import com.adampach.donkeykong.builders.LevelBuilder;
import com.adampach.donkeykong.builders.LevelSettingsBuilder;
import com.adampach.donkeykong.data.LevelSettings;
import com.adampach.donkeykong.enums.DirectionEnums;
import com.adampach.donkeykong.geometry.Rectangle;
import com.adampach.donkeykong.providers.RandomIntervalGeneratorProvider;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public class LevelDefinitions
{
    public static LevelBuilder getLevelOneBuilder(LevelSettings levelSettings)
    {
        return LevelBuilder.CreateBuilder(levelSettings)
                .addLadder(new Rectangle2D(540, 460, 20, 90))
                .addLadder(new Rectangle2D(40, 340, 20, 90))
                .addLadder(new Rectangle2D(540, 220, 20, 90))
                .addLadder(new Rectangle2D(360, 80, 20, 130))
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
                .addBarrelGenerator(
                        new Rectangle2D(0,100, 100, 100),
                        DirectionEnums.HorizontalDirection.Right,
                        new RandomIntervalGeneratorProvider(1, 15000))
                .addVerticalMovementZone(new Rectangle2D(539, 219, 1, 1), DirectionEnums.VerticalDirection.Down)
                .addVerticalMovementZone(new Rectangle2D(540, 310, 1, 1), DirectionEnums.VerticalDirection.None)
                .addHorizontalMovementZone(new Rectangle2D(540, 310, 1, 1), DirectionEnums.HorizontalDirection.Left)
                .addPlayerSpawnPoint(new Point2D(10, 550))
                .addPeach(new Rectangle2D(160, 40, 40, 40), false);
    }

    public static LevelBuilder getLevelTwoBuilder(LevelSettings settings)
    {
        return LevelBuilder.CreateBuilder(settings)
                .addConstruction(new Rectangle2D(0, 580, 600, 20))
                .addConstruction(new Rectangle2D(200, 490, 200, 20))
                .addConstruction(new Rectangle2D(0, 490, 140, 20))
                .addConstruction(new Rectangle2D(460, 490, 140, 20))
                .addConstruction(new Rectangle2D(0, 400, 260, 20))
                .addConstruction(new Rectangle2D(340, 400, 260, 20))
                .addConstruction(new Rectangle2D(260, 350, 80, 20))
                .addConstruction(new Rectangle2D(150, 270, 300, 20))
                .addConstruction(new Rectangle2D(0, 180, 600, 20))
                .addConstruction(new Rectangle2D(200, 50, 200, 20))
                .addLadder(new Rectangle2D(200, 490, 20, 90))
                .addLadder(new Rectangle2D(380, 490, 20, 90))
                .addLadder(new Rectangle2D(60, 400, 20, 90))
                .addLadder(new Rectangle2D(520, 400, 20, 90))
                .addLadder(new Rectangle2D(290, 270, 20, 80))
                .addLadder(new Rectangle2D(150, 180, 20, 90))
                .addLadder(new Rectangle2D(430, 180, 20, 90))
                .addLadder(new Rectangle2D(200, 50, 20, 130))
                .addLadder(new Rectangle2D(380, 50, 20, 130))
                .addBarrelGenerator(
                        new Rectangle2D(250, 80, 100, 100),
                        DirectionEnums.HorizontalDirection.Both,
                        new RandomIntervalGeneratorProvider(1, 12000))
                .addVerticalMovementZone(
                        new Rectangle2D(149, 179, 1, 1),
                        DirectionEnums.VerticalDirection.Down)
                .addVerticalMovementZone(
                        new Rectangle2D(150, 269, 1,1),
                        DirectionEnums.VerticalDirection.None)
                .addHorizontalMovementZone(
                        new Rectangle2D(150, 200, 1, 1)
                        ,DirectionEnums.HorizontalDirection.Right)
                .addVerticalMovementZone(
                        new Rectangle2D(451, 179, 1, 1),
                        DirectionEnums.VerticalDirection.Down
                )
                .addVerticalMovementZone(
                        new Rectangle2D(449, 269, 1, 1),
                        DirectionEnums.VerticalDirection.None
                )
                .addHorizontalMovementZone(
                        new Rectangle2D(449, 200, 1, 1),
                        DirectionEnums.HorizontalDirection.Left)
                .addVerticalMovementZone(
                        new Rectangle2D(199, 489, 1, 1),
                        DirectionEnums.VerticalDirection.Down
                )
                .addVerticalMovementZone(
                        new Rectangle2D(200, 580, 1, 1),
                        DirectionEnums.VerticalDirection.None
                )
                .addHorizontalMovementZone(
                        new Rectangle2D(200, 500, 1, 1),
                        DirectionEnums.HorizontalDirection.Right
                )
                .addVerticalMovementZone(
                        new Rectangle2D(401, 489, 1, 1),
                        DirectionEnums.VerticalDirection.Down
                )
                .addVerticalMovementZone(
                        new Rectangle2D(399, 580, 1, 1),
                        DirectionEnums.VerticalDirection.None
                )
                .addHorizontalMovementZone(
                        new Rectangle2D(399, 500, 1, 1),
                        DirectionEnums.HorizontalDirection.Left
                )
                .addDestroyBarrelZone(new Rectangle2D(0, 579, 1,1))
                .addDestroyBarrelZone(new Rectangle2D(600, 579, 1,1))

                .addPlayerSpawnPoint(new Point2D(280, 550))
                .addPeach(new Rectangle2D(280, 10, 40, 40), true);
    }

    public static LevelSettingsBuilder getDefaultSetting(int playgroundWidth, int playgroundHeight)
    {
        return LevelSettingsBuilder
                .CreateBuilder(playgroundWidth, playgroundHeight)
                .addClimbingSpeed(2)
                .addJumpGravity(-11)
                .addMaxGravityIndex(4)
                .addMovementSpeed(3)
                .addDefaultPlayerSize(new Rectangle(35,35))
                .addDefaultBarrelSize(new Rectangle(20, 20))
                .addPlayerLives(3)
                .addMaxAvailableScore(5000)
                .addCyclesToDecrease(100)
                .addDecreaseAtOnce(200);
    }
}
