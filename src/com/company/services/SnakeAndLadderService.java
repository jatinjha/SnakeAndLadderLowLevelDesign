package com.company.services;

import com.company.exceptions.PlayerNotFoundException;
import com.company.model.*;

import java.util.*;

public class SnakeAndLadderService {
    private SnakeAndLadderBoard snakeAndLadderBoard;
    private List<Player> playerList;
    private int numberOfPlayers;
    private Deque<Player> playersQueue;
    private Boolean isGameFinished;

    public SnakeAndLadderService(int size){
        snakeAndLadderBoard = new SnakeAndLadderBoard(size);
        this.playerList = new ArrayList<Player>();
        this.playersQueue = new LinkedList<>();
        this.isGameFinished = false;
    }

    public void setPlayerList(List<Player> playerList){
        this.playerList = playerList;
        Map<String,Integer> playerPieces = new HashMap<String,Integer>();
        for(Player player : playerList){
            playersQueue.addFirst(player);
            playerPieces.put(player.getPlayerId(),0);
        }
        snakeAndLadderBoard.setPlayerPieces(playerPieces);
    }

    public void setSnakes(List<Snake> snakes){
        snakeAndLadderBoard.setSnakes(snakes);
    }

    public void setLadders(List<Ladder> ladders){
        snakeAndLadderBoard.setLadders(ladders);
    }

    public Boolean getIsGameFinished(){
        return isGameFinished;
    }

    public void setIsGameFinished(Boolean isGameFinished){
        this.isGameFinished = isGameFinished;
    }

    public Integer nextPosition(Integer diceValue , Integer currPosition){
        Integer nextPos = diceValue+currPosition;


        List<Snake> snakes = snakeAndLadderBoard.getSnakes();
        for(Snake snake : snakes){
            if(snake.getCoordinates().getEnd()==nextPos){
                nextPos = snake.getCoordinates().getEnd();
                return nextPos;
            }
        }

        List<Ladder> ladders = snakeAndLadderBoard.getLadders();
        for(Ladder ladder : ladders){
            if(ladder.getCoordinates().getEnd()==nextPos){
                nextPos = ladder.getCoordinates().getStart();
                return nextPos;
            }
        }

        return nextPos;

    }


    public void playerNextMove(Player player)throws Exception{
        Integer currentPosition = snakeAndLadderBoard.getPlayerPieces().getOrDefault(player.getPlayerId(),-1);
        System.out.println(player.getPlayerName()+"------------current Position-------------->"+currentPosition);
        if(currentPosition==-1){
            throw new PlayerNotFoundException("invalid player ");
        }

        Integer rollDice = DiceService.rollDice();
        Integer nextPosition = nextPosition(rollDice,currentPosition);

        if(nextPosition>snakeAndLadderBoard.getSize()){
            return;
        }

        if(nextPosition == 99){
            player.setWinner(true);
            System.out.println(player.getPlayerName()+"        "+"win the game");
            this.isGameFinished = true;
            return;
        }

        snakeAndLadderBoard.setPlayers(player.getPlayerId(),nextPosition);

    }

    public void startGame() throws Exception {
        while(true){
            Player currentPlayer = playersQueue.getFirst();
            playersQueue.removeFirst();
            playerNextMove(currentPlayer);
            System.out.println(currentPlayer.getPlayerName()+"-------next Position-------------->"+snakeAndLadderBoard.getPlayerPieces().get(currentPlayer.getPlayerId()));
            if(this.isGameFinished){
                break;
            }
            playersQueue.addLast(currentPlayer);
        }
    }

}
