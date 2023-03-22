package com.fioxxu.aoc.aoc2022;

import com.fioxxu.aoc.aoc2022.utils.BufferedReaderHelper;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;

public class AoCDay2 {
    private static BufferedReader bufferedReader;
    private static final String INPUT_FILE = "aoc_day2_input.txt";
    private static Integer points = 0;

    enum Moves {
        ROCK,
        PAPER,
        SCISSORS,
        UNKNOWN
    }

    /* Three possible moves */
    private static final Move ROCK = new Move(Moves.ROCK, Moves.PAPER, Moves.SCISSORS, 1);
    private static final Move PAPER = new Move(Moves.PAPER, Moves.SCISSORS, Moves.ROCK, 2);
    private static final Move SCISSORS = new Move(Moves.SCISSORS, Moves.ROCK, Moves.PAPER, 3);

    public static void main(String[] args) {

        bufferedReader = BufferedReaderHelper.build(INPUT_FILE);
        try {
            String line = bufferedReader.readLine();
            while (null != line) {
                String[] playerMoves = line.split("\\s+");
                Move opponent = createMove(playerMoves[0]);
                Move player = createMove(playerMoves[1]);
                points += player.play(opponent);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("You should end up with " + points + " points!");
    }

    private static Move createMove(String alias) {
        switch (alias) {
            case "A", "X" -> {
                return ROCK;
            }
            case "B", "Y" -> {
                return PAPER;
            }
            case "C", "Z" -> {
                return SCISSORS;
            }
            default -> {
                return new Move(Moves.UNKNOWN, Moves.UNKNOWN, Moves.UNKNOWN, 0);
            }
        }
    }

    @RequiredArgsConstructor
    @EqualsAndHashCode
    static class Move {
        private final Moves alias;
        private final Moves beatenBy;
        private final Moves beats;
        private final Integer basePoints;

        private final Integer winPoints = 6;
        private final Integer drawPoints = 3;
        private final Integer losePoints = 0;

        private Boolean beats(Moves opponentMove) {
            return beats.equals(opponentMove);
        }

        private Boolean beatenBy(Moves opponentMove) {
            return beatenBy.equals(opponentMove);
        }

        private Boolean drawsWith(Moves opponentMove) {
            return alias.equals(opponentMove);
        }

        final Integer play(Move opponentMove) {
            if (Boolean.TRUE.equals(opponentMove.beats(this.alias))) {
                return losePoints + basePoints;
            } else if (Boolean.TRUE.equals(opponentMove.beatenBy(this.alias))) {
                return winPoints + basePoints;
            } else {
                return drawPoints + basePoints;
            }
        }
    }
}
