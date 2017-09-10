import org.junit.Before;
import org.junit.Test;
import tictactoe.Board;
import tictactoe.BoardBuilder;

import static junit.framework.TestCase.assertEquals;

public class TicTacToeTests {

    /*
    *
    * Think-> Red -> Green -> Refactor
    *
    * Guideline 1: Always start with outputs when doing an analysis
    *
    * Tic Tac Toe Game:
    *   * 3x3 board
    *   * X plays first
    *   * 0 plays second
    *   * Results:
    *       * X won
    *       * 0 won
    *       * Draw
    *   * Win conditions:
    *       * On column
    *       * On line
    *       * On diagonal
    *
    * Guideline 2: Behavior Slicing
    * Inputs                Outputs
    * X finished line       X won
    * X finished column     X won
    * X finished diagonal   X won
    *
    *
    * Guideline 3: SIMPLIFY!
    *
    * Guideline 4: Introduce only one notion (domain concept) at a time, one per test
    *
    * Guideline 5: The rule of three "only extract duplication when spotted at least three times"
    *
    * Guideline 6: Triangulation
    *
    * */

    private GameResult gameResult;
    @Before
    public void setup(){
        gameResult = new GameResult();
    }

    private String buildBoardStructure(String tokenVecinityPosition, String direction) {
        String column = "column"; String space = "";
        return space + tokenVecinityPosition + space + direction + space + column;
    }

    private String buildBoardTemplate(String typeOfBoard, String boardStructure) {
        return typeOfBoard + boardStructure;
    }

    private Board buildBoard(String boardOneByOne, String tokenVecinityPosition, String direction) {
        String boardSize = boardOneByOne;
        String boardStructure = buildBoardStructure(tokenVecinityPosition, direction);
        String boardTemplate = buildBoardTemplate(boardSize, boardStructure);
        return new BoardBuilder().withStructure(boardStructure).withSize(boardSize).withTemplate(boardTemplate).build();
    }

    @Test
    public void forOneByOneBoardXAlwaysWins(){
        // Settings
        String boardOneByOne = "one by one";
        String tokenVecinityPosition = "";
        String direction = "";

        // Arrange
        Board board = buildBoard(boardOneByOne, tokenVecinityPosition, direction);

        // Act
        String actual = gameResult.getGameResult(board);

        // Assert
        assertEquals(gameResult.getGameMessageXWon(), actual);
    }

    // Introduced the notion of winning
    /*
    * Intotroduced the notion of column
    * X 0
    * X
    * */

    @Test
    public void forTwoByTwoBoardXWinsOnLeftColumn(){
        // Settings
        String boardTwoByTwo = "two by two";
        String tokenVecinityPosition = "with X on";
        String direction = "left";


        // Arrange
        Board board = buildBoard(boardTwoByTwo, tokenVecinityPosition, direction);

        // Act
        String actual = gameResult.getGameResult(board);

        // Assert
        assertEquals(gameResult.getGameMessageXWon(), actual);
    }
    /*
    * Introduced no new notions
    * 0  X
    *    X
    * */

    @Test
    public void forTwoByTwoBoardXWinsOnRightColumn(){
        // Settings
        String boardTwoByTwo = "two by two";
        String tokenVecinityPosition = "with X on";
        String direction = "right";

        // Arrange
        Board board = buildBoard(boardTwoByTwo, tokenVecinityPosition, direction);

        // Act
        String actual = gameResult.getGameResult(board);

        // Assert
        assertEquals(gameResult.getGameMessageXWon(), actual);
    }
}