package com.fioxxu.aoc.aoc2022;

import com.fioxxu.aoc.aoc2022.utils.BufferedReaderHelper;

import java.io.BufferedReader;
import java.io.IOException;

public class AoCDay2 {
    private static BufferedReader bufferedReader;
    private static final String INPUT_FILE = "aoc_day2_input.txt";
    private static Integer points = 0;

    enum Move {
        ROCK("A", "X", 1),
        PAPER("B", "Y", 2),
        SCISSORS("C", "Z", 3),
        UNKNOWN("", "", 0);
        private final String alias1;
        private final String alias2;
        private final int basePoints;

        Move(String alias1, String alias2, int basePoints) {
            this.alias1 = alias1;
            this.alias2 = alias2;
            this.basePoints = basePoints;
        }

        public int getBasePoints() {
            return this.basePoints;
        }

        public static Move fromAlias(String alias) {
            for (var move : Move.values()) {
                if (move.alias1.equals(alias) || move.alias2.equals(alias))
                    return move;
            }
            return UNKNOWN;
        }
    }

    enum MatchResult {
        WIN(6),
        DRAW(3),
        LOSE(0),
        UNKNOWN(-1);

        private final int points;

        MatchResult(int points) {
            this.points = points;
        }

        public Integer getResultPoints() {
            return this.points;
        }

        public static MatchResult matchResult(Move playerMove, Move opponentMove) {
            if (playerMove.equals(opponentMove))
                return DRAW;
            switch (playerMove) {
                case PAPER -> {
                    if (opponentMove == Move.ROCK)
                        return WIN;
                    else
                        return LOSE;
                }
                case ROCK -> {
                    if (opponentMove == Move.PAPER)
                        return LOSE;
                    else
                        return WIN;
                }
                case SCISSORS -> {
                    if (opponentMove == Move.ROCK)
                        return LOSE;
                    else
                        return WIN;
                }
                case UNKNOWN -> {
                    return UNKNOWN;
                }
            }
            return UNKNOWN;
        }
    }

    public static void main(String[] args) {
        bufferedReader = BufferedReaderHelper.build(INPUT_FILE);
        try {
            String line = bufferedReader.readLine();
            while (null != line) {
                String[] playerMoves = line.split("\\s+");
                Move opponent = Move.fromAlias(playerMoves[0]);
                Move player = Move.fromAlias(playerMoves[1]);
                points += player.getBasePoints();
                points += MatchResult.matchResult(player, opponent).getResultPoints();
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("You should end up with " + points + " points!");
    }
}
