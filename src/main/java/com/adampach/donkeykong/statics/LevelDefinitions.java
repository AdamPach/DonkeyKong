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
                .addConstruction(new Rectangle2D(0, 580, 40, 20))
                .addConstruction(new Rectangle2D(40, 580, 40, 20))
                .addConstruction(new Rectangle2D(80, 575, 40, 20))
                .addConstruction(new Rectangle2D(120, 575, 40, 20))
                .addConstruction(new Rectangle2D(160, 570, 40, 20))
                .addConstruction(new Rectangle2D(200, 570, 40, 20))
                .addConstruction(new Rectangle2D(240, 565, 40, 20))
                .addConstruction(new Rectangle2D(280, 565, 40, 20))
                .addConstruction(new Rectangle2D(320, 560, 40, 20))
                .addConstruction(new Rectangle2D(360, 560, 40, 20))
                .addConstruction(new Rectangle2D(400, 555, 40, 20))
                .addConstruction(new Rectangle2D(440, 555, 40, 20))
                .addConstruction(new Rectangle2D(480, 550, 40, 20))
                .addConstruction(new Rectangle2D(520, 550, 40, 20))
                .addConstruction(new Rectangle2D(560, 550, 40, 20))
                .addConstruction(new Rectangle2D(520, 460, 40, 20))
                .addConstruction(new Rectangle2D(480, 460, 40, 20))
                .addConstruction(new Rectangle2D(440, 460, 40, 20))
                .addConstruction(new Rectangle2D(400, 455, 40, 20))
                .addConstruction(new Rectangle2D(360, 455, 40, 20))
                .addConstruction(new Rectangle2D(320, 450, 40, 20))
                .addConstruction(new Rectangle2D(280, 450, 40, 20))
                .addConstruction(new Rectangle2D(240, 445, 40, 20))
                .addConstruction(new Rectangle2D(200, 445, 40, 20))
                .addConstruction(new Rectangle2D(160, 440, 40, 20))
                .addConstruction(new Rectangle2D(120, 440, 40, 20))
                .addConstruction(new Rectangle2D(80, 435, 40, 20))
                .addConstruction(new Rectangle2D(40, 430, 40, 20))
                .addConstruction(new Rectangle2D(0, 430, 40, 20))
                .addConstruction(new Rectangle2D(40, 340,40, 20))
                .addConstruction(new Rectangle2D(80, 340,40, 20))
                .addConstruction(new Rectangle2D(120, 335, 40, 20))
                .addConstruction(new Rectangle2D(160, 335, 40, 20))
                .addConstruction(new Rectangle2D(200, 330, 40, 20))
                .addConstruction(new Rectangle2D(240, 330, 40, 20))
                .addConstruction(new Rectangle2D(280, 325, 40, 20))
                .addConstruction(new Rectangle2D(320, 325, 40, 20))
                .addConstruction(new Rectangle2D(360, 320, 40, 20))
                .addConstruction(new Rectangle2D(400, 320, 40, 20))
                .addConstruction(new Rectangle2D(440, 315, 40, 20))
                .addConstruction(new Rectangle2D(480, 315, 40, 20))
                .addConstruction(new Rectangle2D(520, 310, 40, 20))
                .addConstruction(new Rectangle2D(560, 310, 40, 20))
                .addConstruction(new Rectangle2D(560, 220, 40, 20))
                .addConstruction(new Rectangle2D(520, 220, 40, 20))
                .addConstruction(new Rectangle2D(480, 215, 40, 20))
                .addConstruction(new Rectangle2D(440, 215, 40, 20))
                .addConstruction(new Rectangle2D(400, 215, 40, 20))
                .addConstruction(new Rectangle2D(360, 210, 40, 20))
                .addConstruction(new Rectangle2D(320, 210, 40, 20))
                .addConstruction(new Rectangle2D(240, 205, 40, 20))
                .addConstruction(new Rectangle2D(280, 205, 40, 20))
                .addConstruction(new Rectangle2D(0, 200, 40, 20))
                .addConstruction(new Rectangle2D(40, 200, 40, 20))
                .addConstruction(new Rectangle2D(80, 200, 40, 20))
                .addConstruction(new Rectangle2D(120, 200, 40, 20))
                .addConstruction(new Rectangle2D(160, 200, 40, 20))
                .addConstruction(new Rectangle2D(200, 200, 40, 20))
                .addConstruction(new Rectangle2D(140, 80, 40, 20))
                .addConstruction(new Rectangle2D(180, 80, 40, 20))
                .addConstruction(new Rectangle2D(220, 80, 40, 20))
                .addConstruction(new Rectangle2D(260, 80, 40, 20))
                .addConstruction(new Rectangle2D(300, 80, 40, 20))
                .addConstruction(new Rectangle2D(340, 80, 40, 20))
                .addBarrelGenerator(
                        new Rectangle2D(0,100, 100, 100),
                        DirectionEnums.HorizontalDirection.Right,
                        new RandomIntervalGeneratorProvider(1, 12000))
                .addVerticalMovementZone(new Rectangle2D(539, 219, 1, 1), DirectionEnums.VerticalDirection.Down)
                .addVerticalMovementZone(new Rectangle2D(540, 310, 1, 1), DirectionEnums.VerticalDirection.None)
                .addHorizontalMovementZone(new Rectangle2D(540, 310, 1, 1), DirectionEnums.HorizontalDirection.Left)
                .addPlayerSpawnPoint(new Point2D(10, 550))
                .addDestroyBarrelZone(new Rectangle2D(0, 579, 1,1))
                .addPeach(new Rectangle2D(160, 40, 40, 40), false);
    }

    public static LevelBuilder getLevelTwoBuilder(LevelSettings settings)
    {
        return LevelBuilder.CreateBuilder(settings)
                .addConstruction(new Rectangle2D(0, 580, 40, 20))
                .addConstruction(new Rectangle2D(40, 580, 40, 20))
                .addConstruction(new Rectangle2D(80, 580, 40, 20))
                .addConstruction(new Rectangle2D(120, 580, 40, 20))
                .addConstruction(new Rectangle2D(160, 580, 40, 20))
                .addConstruction(new Rectangle2D(200, 580, 40, 20))
                .addConstruction(new Rectangle2D(240, 580, 40, 20))
                .addConstruction(new Rectangle2D(280, 580, 40, 20))
                .addConstruction(new Rectangle2D(320, 580, 40, 20))
                .addConstruction(new Rectangle2D(360, 580, 40, 20))
                .addConstruction(new Rectangle2D(400, 580, 40, 20))
                .addConstruction(new Rectangle2D(440, 580, 40, 20))
                .addConstruction(new Rectangle2D(480, 580, 40, 20))
                .addConstruction(new Rectangle2D(520, 580, 40, 20))
                .addConstruction(new Rectangle2D(560, 580, 40, 20))
                .addConstruction(new Rectangle2D(200, 490, 40, 20))
                .addConstruction(new Rectangle2D(240, 490, 40, 20))
                .addConstruction(new Rectangle2D(280, 490, 40, 20))
                .addConstruction(new Rectangle2D(320, 490, 40, 20))
                .addConstruction(new Rectangle2D(360, 490, 40, 20))
                .addConstruction(new Rectangle2D(0, 490, 40, 20))
                .addConstruction(new Rectangle2D(40, 490, 40, 20))
                .addConstruction(new Rectangle2D(80, 490, 40, 20))
                .addConstruction(new Rectangle2D(480, 490, 40, 20))
                .addConstruction(new Rectangle2D(520, 490, 40, 20))
                .addConstruction(new Rectangle2D(560, 490, 40, 20))
                .addConstruction(new Rectangle2D(0, 400, 40, 20))
                .addConstruction(new Rectangle2D(40, 400, 40, 20))
                .addConstruction(new Rectangle2D(80, 400, 40, 20))
                .addConstruction(new Rectangle2D(120, 400, 40, 20))
                .addConstruction(new Rectangle2D(160, 400, 40, 20))
                .addConstruction(new Rectangle2D(200, 400, 40, 20))
                .addConstruction(new Rectangle2D(360, 400, 40, 20))
                .addConstruction(new Rectangle2D(400, 400, 40, 20))
                .addConstruction(new Rectangle2D(440, 400, 40, 20))
                .addConstruction(new Rectangle2D(480, 400, 40, 20))
                .addConstruction(new Rectangle2D(520, 400, 40, 20))
                .addConstruction(new Rectangle2D(560, 400, 40, 20))
                .addConstruction(new Rectangle2D(260, 350, 40, 20))
                .addConstruction(new Rectangle2D(300, 350, 40, 20))
                .addConstruction(new Rectangle2D(140, 270, 40, 20))
                .addConstruction(new Rectangle2D(180, 270, 40, 20))
                .addConstruction(new Rectangle2D(220, 270, 40, 20))
                .addConstruction(new Rectangle2D(260, 270, 40, 20))
                .addConstruction(new Rectangle2D(300, 270, 40, 20))
                .addConstruction(new Rectangle2D(340, 270, 40, 20))
                .addConstruction(new Rectangle2D(380, 270, 40, 20))
                .addConstruction(new Rectangle2D(420, 270, 40, 20))
                .addConstruction(new Rectangle2D(0, 180, 40, 20))
                .addConstruction(new Rectangle2D(40, 180, 40, 20))
                .addConstruction(new Rectangle2D(80, 180, 40, 20))
                .addConstruction(new Rectangle2D(120, 180, 40, 20))
                .addConstruction(new Rectangle2D(160, 180, 40, 20))
                .addConstruction(new Rectangle2D(200, 180, 40, 20))
                .addConstruction(new Rectangle2D( 240, 180, 40, 20))
                .addConstruction(new Rectangle2D(280, 180, 40, 20))
                .addConstruction(new Rectangle2D(320, 180, 40, 20))
                .addConstruction(new Rectangle2D(360, 180, 40, 20))
                .addConstruction(new Rectangle2D(400, 180, 40, 20))
                .addConstruction(new Rectangle2D(440, 180, 40, 20))
                .addConstruction(new Rectangle2D(480, 180, 40, 20))
                .addConstruction(new Rectangle2D(520, 180, 40, 20))
                .addConstruction(new Rectangle2D(560, 180, 40, 20))
                .addConstruction(new Rectangle2D(200, 50, 40, 20))
                .addConstruction(new Rectangle2D(240, 50, 40, 20))
                .addConstruction(new Rectangle2D(280, 50, 40, 20))
                .addConstruction(new Rectangle2D(320, 50, 40, 20))
                .addConstruction(new Rectangle2D(360, 50, 40, 20))
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
                        new RandomIntervalGeneratorProvider(1, 9000))
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
                .addPeach(new Rectangle2D(280, 10, 40, 40), true)
                .arrangeLaddersOnTheTopOfList();
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
