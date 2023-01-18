package de.thomas.creatures.implementation.view;

import de.thomas.creatures.implementation.controller.WorldController;

import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class WorldInputListener implements KeyListener, MouseListener, MouseWheelListener {
    private final Set<Integer> pressed = new HashSet<Integer>();
    private WorldController controller;
    private boolean rightButtonPressed = false;
    private boolean leftButtonPressed = false;
    private Point lastPosition = MouseInfo.getPointerInfo().getLocation();

    public WorldInputListener(WorldController controller) {
        this.controller = controller;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Nothing is done here
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressed.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressed.remove(e.getKeyCode());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //Nothing is done here
    }

    @Override
    public void mousePressed(MouseEvent e) {
        controller.setViewInputFocus();

        if (e.getButton() == MouseEvent.BUTTON1) {
            leftButtonPressed = true;
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            rightButtonPressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftButtonPressed = false;
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            rightButtonPressed = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //Nothing is done here
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //Nothing is done here
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        controller.changeZoomFactor(e.getWheelRotation(), true);
    }

    public void handlePressedKeys() {
        Iterator<Integer> iterator = pressed.iterator();

        while (iterator.hasNext()) {
            int keyCode = iterator.next();

            if (keyCode == KeyEvent.VK_LEFT) {
                controller.changeOffsetX(5);
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                controller.changeOffsetX(-5);
            } else if (keyCode == KeyEvent.VK_UP) {
                controller.changeOffsetY(5);
            } else if (keyCode == KeyEvent.VK_DOWN) {
                controller.changeOffsetY(-5);
            } else if (keyCode == KeyEvent.VK_ADD) {
                controller.changeSpeed(1);
                iterator.remove();
            } else if (keyCode == KeyEvent.VK_SUBTRACT) {
                controller.changeSpeed(-1);
                iterator.remove();
            }
        }
    }

    public void handlePressedMouseButtons(WorldView view) {
        double deltaX = (double) MouseInfo.getPointerInfo().getLocation().x - lastPosition.x;
        double deltaY = (double) MouseInfo.getPointerInfo().getLocation().y - lastPosition.y;

        if (rightButtonPressed || leftButtonPressed) {
            if (Math.abs(deltaX) > 0) {
                controller.changeOffsetX(((int) deltaX) * view.getZoomFactor());
            }

            if (Math.abs(deltaY) > 0) {
                controller.changeOffsetY(((int) deltaY) * view.getZoomFactor());
            }
        }

        lastPosition = MouseInfo.getPointerInfo().getLocation();
    }
}
