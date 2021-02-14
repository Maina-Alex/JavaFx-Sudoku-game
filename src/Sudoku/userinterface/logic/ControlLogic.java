package Sudoku.userinterface.logic;

import Sudoku.Computationlogic.GameLogic;
import Sudoku.ProblemDomain.Constants.GameState;
import Sudoku.ProblemDomain.Constants.Messages;
import Sudoku.ProblemDomain.Istorage;
import Sudoku.ProblemDomain.SudokuGame;
import Sudoku.userinterface.IUserInterfaceContract;
import com.sun.javafx.iio.ios.IosDescriptor;

import java.io.IOException;

public class ControlLogic  implements IUserInterfaceContract.EventListener {
   private Istorage storage;
   private IUserInterfaceContract.View view;

    public ControlLogic(Istorage storage, IUserInterfaceContract.View view) {
        this.storage = storage;
        this.view = view;
    }

    @Override
    public void onSudokuInput(int x, int y, int input) {
        try{
            SudokuGame gameData= storage.getGameData();
            int[][] newGridState = gameData.getCopyOfGridState();
            newGridState[x][y]= input;

            gameData= new SudokuGame(
                    GameLogic.checkForCompletion(newGridState),
                    newGridState
            );
            storage.updateGameData(gameData);

            view.updateSquare(x,y,input);

            if(gameData.getGameState()== GameState.COMPLETE){
                view.showDialog(Messages.GAME_COMPLETE);
            }

        }catch (IOException e){
            e.printStackTrace();
            view.showError(Messages.Error);
        }

    }

    @Override
    public void onDialogClick() {
        try{
            storage.updateGameData(
                    GameLogic.getNewGame()
            );
            view.updateBord(storage.getGameData());

        }catch(IOException e){
            view.showError(Messages.Error);

        }

    }
}
