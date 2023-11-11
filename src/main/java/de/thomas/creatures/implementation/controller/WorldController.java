package de.thomas.creatures.implementation.controller;

import de.thomas.creatures.implementation.model.Creature;
import de.thomas.creatures.implementation.model.WorldModel;
import de.thomas.creatures.implementation.view.MainWindow;
import de.thomas.creatures.implementation.view.WorldView;

public class WorldController {
    private WorldModel worldModel;
    private WorldView worldView;
    private WorldUpdater worldUpdater;
    private MainWindow mainWindow;

    public WorldController(WorldModel worldModel, WorldView worldView) {
        this.worldModel = worldModel;
        this.worldView = worldView;
        worldUpdater = new WorldUpdater(worldModel, this);
    }

    public void updateWorld(double delta) {
        worldUpdater.updateWorld(delta);
    }

    public void changeZoomFactor(int zoomChange, boolean relative) {
        int zoomFactor = worldView.getZoomFactor();

        if (zoomFactor + zoomChange > 0) {
            worldView.setZoomFactor(zoomFactor + zoomChange);

            if (relative) {
                if (zoomChange > 0) {
                    worldView.setOffsetX(worldView.getOffsetX() + (int) ((worldView.getSize().width) * 0.8) / 2);
                    worldView.setOffsetY(worldView.getOffsetY() + worldView.getSize().height / 2);
                } else {
                    worldView.setOffsetX(worldView.getOffsetX() - (int) ((worldView.getSize().width) * 0.8) / 2);
                    worldView.setOffsetY(worldView.getOffsetY() - worldView.getSize().height / 2);
                }
            }
        }
    }

    public void changeOffsetX(int offsetChange) {
        int offsetX = worldView.getOffsetX();
        worldView.setOffsetX(offsetX + offsetChange);
    }

    public void changeOffsetY(int offsetChange) {
        int offsetY = worldView.getOffsetY();
        worldView.setOffsetY(offsetY + offsetChange);
    }

    public void addCreature(Creature creature) {
        worldModel.addCreature(creature);
        creature.getAi().setCreature(creature);
        creature.getAi().setWorldModel(worldModel);
        creature.getAi().init();
    }

    public void changeSpeed(double speedChange) {
        double change = WorldModel.speedFactor + speedChange;
        setSpeed(change);
    }

    public void setSpeed(double speed) {
        if (speed >= 0 && speed <= 15) {
            WorldModel.speedFactor = speed;
            mainWindow.setSpeedSlider(speed);
        }
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void setViewInputFocus() {
        mainWindow.setViewInputFocus();
    }
}
