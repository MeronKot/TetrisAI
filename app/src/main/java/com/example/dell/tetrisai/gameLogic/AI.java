package com.example.dell.tetrisai.gameLogic;

import com.example.dell.tetrisai.field.Shape;

/**
 * Created by DELL on 01/01/2017.
 */

public class AI {
    private static final int MAXROTAT = 4;
    private int heightWeight;
    private int linesWeight;
    private int holesWeight;
    private int bumpinessWeight;

    public AI(int heightWeight, int linesWeight, int holesWeight, int bumpinessWeight) {
        this.heightWeight = heightWeight;
        this.linesWeight = linesWeight;
        this.holesWeight = holesWeight;
        this.bumpinessWeight = bumpinessWeight;
    }

    public Object[] best(GameLogic grid, Shape[] existShapes, int shInd) {
        //results[0] = best (shape) results[1] = bestScore
        Object[] results = new Object[2];
        Shape bestMatch = null;
        float bestScore = 0;
        Shape shape = existShapes[shInd];

        for (int rotation = 0; rotation < MAXROTAT; rotation++) {
            Shape currentShape = shape.clone();
            currentShape.rotateShape(rotation);

            while (grid.isMoveLeft(currentShape))
                currentShape.setLocation(currentShape.getLocation().x - 1, currentShape.getLocation().y);
            while (grid.isValid(currentShape)) {
                Shape sh = currentShape.clone();
                while (grid.isMoveDown(sh))
                    sh.setLocation(sh.getLocation().x, sh.getLocation().y + 1);
                GameLogic newGrid = grid.cloneGrid();
                newGrid.addShape(sh);

                float score = 0;
                if (shInd == (existShapes.length - 1)) {
                    score = -this.heightWeight * newGrid.aggregateHeight() +
                            this.linesWeight * newGrid.countLines() -
                            this.holesWeight * newGrid.getHole() -
                            this.bumpinessWeight * newGrid.bumpiness();
                } else {
                    score = (float) best(newGrid, existShapes, shInd + 1)[1];
                }

                if (score > bestScore || bestScore == 0) {
                    bestScore = score;
                    bestMatch = currentShape.clone();
                }

                currentShape.setLocation(currentShape.getLocation().x + 1, currentShape.getLocation().y);
            }
        }
        results[0] = bestMatch;
        results[1] = bestScore;

        return results;
    }
}
