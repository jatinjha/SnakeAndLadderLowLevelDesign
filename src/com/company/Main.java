package com.company;

import com.company.model.Coordinates;
import com.company.model.Ladder;
import com.company.model.Player;
import com.company.model.Snake;
import com.company.services.SnakeAndLadderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main{

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int noOfSnakes = scanner.nextInt();
        List<Snake> snakes = new ArrayList<Snake>();
        for (int i = 0; i < noOfSnakes; i++) {
            Coordinates coordinates = new Coordinates(scanner.nextInt(),scanner.nextInt());
            snakes.add(new Snake(coordinates));
        }

        int noOfLadders = scanner.nextInt();
        List<Ladder> ladders = new ArrayList<Ladder>();
        for (int i = 0; i < noOfLadders; i++) {
            ladders.add(new Ladder(new Coordinates(scanner.nextInt(),scanner.nextInt())));
        }

        int noOfPlayers = scanner.nextInt();
        List<Player> players = new ArrayList<Player>();
        for (int i = 0; i < noOfPlayers; i++) {
            players.add(new Player(scanner.next()));
        }

        SnakeAndLadderService snakeAndLadderService = new SnakeAndLadderService(100);
        snakeAndLadderService.setPlayerList(players);
        snakeAndLadderService.setSnakes(snakes);
        snakeAndLadderService.setLadders(ladders);
        try{
            snakeAndLadderService.startGame();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

