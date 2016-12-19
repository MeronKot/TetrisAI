package com.example.dell.tetrisai.bot;

import com.example.dell.tetrisai.moves.MoveType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by DELL on 19/12/2016.
 */

public class BotStarter
{
    public ArrayList<MoveType> getMoves(BotState state, long timeout) {
        ArrayList<MoveType> moves = new ArrayList<MoveType>();
        Random rnd = new Random();

        int nrOfMoves = rnd.nextInt(41);
        List<MoveType> allMoves = Collections.unmodifiableList(Arrays.asList(MoveType.values()));
        for(int n=0; n<=nrOfMoves; n++) {
            moves.add(allMoves.get(rnd.nextInt(allMoves.size())));
        }

        return moves;
    }

    public static void main(String[] args)
    {
        BotParser parser = new BotParser(new BotStarter());
        parser.run();
    }
}
