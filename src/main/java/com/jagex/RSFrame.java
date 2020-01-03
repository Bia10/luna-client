package com.jagex;

import io.luna.Constants;

import java.awt.Frame;
import java.awt.Graphics;

@SuppressWarnings("serial")
public final class RSFrame extends Frame {

    private final RSApplet rsApplet;


    public RSFrame(RSApplet rsApplet, int sizeX, int sizeY) {
        this.rsApplet = rsApplet;
        setTitle(Constants.CLIENT_NAME);
        setResizable(false);
        setVisible(true);
        toFront();
        setSize(sizeX + 8, sizeY + 28);
        setResizable(true);
        setLocationRelativeTo(null);
    }

    @Override
    public Graphics getGraphics() {
        Graphics g = super.getGraphics();
        g.translate(4, 24);
        return g;
    }

    @Override
    public void update(Graphics g) {
        rsApplet.update(g);
    }

    @Override
    public void paint(Graphics g) {
        rsApplet.paint(g);
    }
}
