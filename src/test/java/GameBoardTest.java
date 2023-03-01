import com.minesweeper.models.Field;
import com.minesweeper.models.MineSweeperGameModel;
import org.junit.jupiter.api.Test;

public class GameBoardTest {

    @Test
    public void testGameBoard() throws Exception {
        MineSweeperGameModel model = new MineSweeperGameModel(8, 8);

        model.clickField(0,0);

        System.out.println("Amount of bombs: " + model.getAmountOfBombs());

        for(Field[] fields : model.getBoard()){
            for (Field field : fields) {
                System.out.print(field.getFieldType().toString() + " ");
            }
            System.out.println();
        }


        for(Field[] fields : model.getBoard()){
            for (Field field : fields) {
                System.out.print(field.getNumber() + " ");
            }
            System.out.println();
        }

        System.out.println();

        for(boolean[] fields : model.getTest()){
            for (boolean field : fields) {
                System.out.print(field ? "C " : "N" + " ");
            }
            System.out.println();
        }
    }
}
