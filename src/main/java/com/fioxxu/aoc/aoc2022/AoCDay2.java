package com.fioxxu.aoc.aoc2022;

import com.fioxxu.aoc.aoc2022.utils.BufferedReaderHelper;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;

public class AoCDay2 {
    private static BufferedReader bufferedReader;
    private static final String INPUT_FILE = "aoc_day2_input.txt";
    private static Integer pointsPartOne = 0;
    private static Integer pointsPartTwo = 0;

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
    private static final Move UNKNOWN = new Move(Moves.UNKNOWN, Moves.UNKNOWN, Moves.UNKNOWN, 0);

    public static void main(String[] args) {

        bufferedReader = BufferedReaderHelper.build(INPUT_FILE);
        try {
            String line = bufferedReader.readLine();
            while (null != line) {
                String[] playerMoves = line.split("\\s+");
                playPartOne(playerMoves[0], playerMoves[1]);
                playPartTwo(playerMoves[0], playerMoves[1]);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("You should end up with " + pointsPartOne + " points! If played with info of part 1.");
        System.out.println("You should end up with " + pointsPartTwo + " points! If played with info of part 2.");
    }

    private static void playPartOne(String letterOne, String letterTwo) {
        Move opponent = createMove(letterOne);
        Move player = createMove(letterTwo);
        pointsPartOne += player.play(opponent);
    }

    private static void playPartTwo(String letterOne, String letterTwo) {
        Move opponentMove = createMove(letterOne);
        Move player = createMoveByOutcome(opponentMove, letterTwo);
        pointsPartTwo += player.play(opponentMove);
    }

    private static Move createMove(String alias) {
        return switch (alias) {
            case "A", "X" -> ROCK;
            case "B", "Y" -> PAPER;
            case "C", "Z" -> SCISSORS;
            default -> UNKNOWN;
        };
    }

    private static Move createMoveByOutcome(Move opponentMove, String resultAlias) {
        return switch (resultAlias) {
            //LOSE
            case "X" -> findMoveThatMakesMeLose(opponentMove);
            //DRAW
            case "Y" -> findMoveThatMakesMeDraw(opponentMove);
            //WIN
            case "Z" -> findMoveThatMakesMeWin(opponentMove);
            default -> UNKNOWN;
        };
    }

    private static Move findMoveThatMakesMeLose(Move opponentMove) {
        if (ROCK.beatenBy(opponentMove.getAlias())) {
            return ROCK;
        } else if (PAPER.beatenBy(opponentMove.getAlias())) {
            return PAPER;
        } else {
            return SCISSORS;
        }
    }

    private static Move findMoveThatMakesMeDraw(Move opponentMove) {
        if (ROCK.drawsWith(opponentMove.getAlias())) {
            return ROCK;
        } else if (PAPER.drawsWith(opponentMove.getAlias())) {
            return PAPER;
        } else {
            return SCISSORS;
        }
    }

    private static Move findMoveThatMakesMeWin(Move opponentMove) {
        if (ROCK.beats(opponentMove.getAlias())) {
            return ROCK;
        } else if (PAPER.beats(opponentMove.getAlias())) {
            return PAPER;
        } else {
            return SCISSORS;
        }
    }

    @RequiredArgsConstructor
    @EqualsAndHashCode
    @Getter
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
