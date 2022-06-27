
/**
 * This class represents a Sudoku board game object.
 *
 * @author Gilad Samuel
 * @version 5-12-2021
 */
public class Sudoku
{
    // class variables
    private Square3x3[][] _board ;

    private static final int DEF_VAL = -1;
    private static final int DEF_SIZE = 3;
    private static final int MIN_SIZE = 0;
    private static final int MIN_NUM = 1;
    private static final int MAX_NUM = 9;

    /**
     * Default constructor for objects of class Sudoku
     * Constructs and initializes a 2-dimensional
     * Square3x3 object array of the size 3X3,
     * whith The value of -1 for each Square3x3 object cell.
     */
    public Sudoku()
    {
        _board = new Square3x3[DEF_SIZE][DEF_SIZE];

        for (int i = MIN_SIZE; i < _board.length; i++)
        {
            for( int j = MIN_SIZE; j < _board.length; j++)
            {
                _board [i][j] = new Square3x3();
            }
        }
    }

    /**
     * Constructs and initializes a 2-dimensional
     * Square3x3 object array of the size 3X3,
     * which the values of each Square3x3 object are copied from the given square3x3Array.
     * 
     * @param square3x3Array The Square3x3 array which the values of each Square3x3 object are copied from
     */
    public Sudoku(Square3x3[][] square3x3Array)
    {
        this(); //use default constructor in case the given Square3x3 array is null.

        if(square3x3Array != null)
        {
            for (int i = MIN_SIZE; i < _board.length; i++)
            {
                for( int j = MIN_SIZE; j < _board.length; j++)
                {
                    _board [i][j] = new Square3x3(square3x3Array [i][j]);
                }
            }
        }
    }

    /**
     * Check if the board is valid by Sudoku rules:
     * Each square, row and column has all 1-9 integers (without returns).
     * 
     * @return True - if the board is valid, false - otherwise.
     */
    public boolean isValid()
    {
        for (int i = MIN_SIZE; i < _board.length; i++)
        {
            for( int k = MIN_SIZE; k < _board.length; k++)
            {
                boolean [] rowArry = new boolean [10];
                boolean [] colArry = new boolean [10];

                for( int j = MIN_SIZE; j < _board.length; j++)
                {
                    _board [i][j].whosThereRow(k, rowArry);  //check row values
                    _board [j][i].whosThereCol(k, colArry); //check column values
                }

                for( int t = MIN_NUM; t <= MAX_NUM; t++)
                {
                    if( !rowArry[t] || !colArry[t] ) //check if num missing
                    {
                        return false;   
                    }
                }

                if( !_board[i][k].allThere() ) //check if any square invalid 
                {
                    return false;    
                }
            }
        }

        return true; //there is nothing invalid
    }

}//end of Sudoku class
