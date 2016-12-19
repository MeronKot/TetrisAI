package com.example.dell.tetrisai.moves;

/**
 * Created by DELL on 19/12/2016.
 */

public enum MoveType
{
    DOWN, LEFT, RIGHT, TURNLEFT, TURNRIGHT;
    // , DROP, SKIP;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
