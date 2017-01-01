package com.example.dell.tetrisai.bot;

import com.example.dell.tetrisai.moves.MoveType;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by DELL on 19/12/2016.
 */

public class BotParser {
    final Scanner scan;
    final BotStarter bot;
    BotState currentState;

    public BotParser(BotStarter bot)
    {
        this.scan = new Scanner(System.in);
        this.bot = bot;
        this.currentState = new BotState();
    }

    public void run()
    {
        while(scan.hasNextLine())
        {
            String line = scan.nextLine().trim();
            if(line.length() == 0) { continue; }
            String[] parts = line.split(" ");
            switch(parts[0]) {
                case "settings":
                    this.currentState.updateSettings(parts[1], parts[2]);
                    break;
                case "update":
                    this.currentState.updateState(parts[1], parts[2], parts[3]);
                    break;
                case "action":
                    StringBuffer output = new StringBuffer();
                    String moveJoin = "";

                    ArrayList<MoveType> moves = bot.getMoves(currentState, Long.valueOf(parts[2]));

                    if(moves.size() > 0)
                        for(MoveType move : moves) {
                            output.append(moveJoin);
                            output.append(move.toString());
                            moveJoin = ",";
                        }
                    else
                        output.append("no_moves");

                    System.out.println(output);
                    break;
                default:
                    System.err.printf("Unable to parse line '%s'\n", line);
            }
        }
    }

}
