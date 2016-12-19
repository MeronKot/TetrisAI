package com.example.dell.tetrisai.player;

import com.example.dell.tetrisai.field.Field;

/**
 * Created by DELL on 19/12/2016.
 */

public class Player
{
    private Field field;
    private String name;
    private int points;
    private int combo;
    private int skips;

    public Player(String name) {
        this.name = name;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Field getField() {
        return this.field;
    }

    public String getName() {
        return this.name;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return this.points;
    }

    public void setCombo(int combo) {
        this.combo = combo;
    }

    public int getCombo() {
        return this.combo;
    }

    public void setSkips(int skips) {
        this.skips = skips;
    }

    public int getSkips() {
        return this.skips;
    }
}
