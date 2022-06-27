
/**
 * This class represents a square object in the Sudoku board game.
 *
 * @author Gilad Samuel
 * @version 5-12-2021
 */
public class Square3x3
{
    // class variables 
    private int[][] _square;

    private static final int DEF_VAL = -1;
    private static final int DEF_SIZE = 3;
    private static final int MIN_SIZE = 0;
    private static final int MIN_NUM = 1;
    private static final int MAX_NUM = 9;

    /**
     * Default constructor for objects of class Square3x3.
     * Constructs and initializes a 2-dimensional
     * array of the size 3X3, with the values of –1 in each cell.
     */
    public Square3x3()
    {   
        _square = new int[DEF_SIZE][DEF_SIZE];

        for (int i = MIN_SIZE; i < _square.length; i++)
        {
            for( int j = MIN_SIZE; j < _square.length; j++)
            {
                _square [i][j] = DEF_VAL;
            }
        }
    }

    /**
     * Constructs a 2-dimensional array of the size 3X3,
     * whose values are taken from the given array.
     * If the given array’s size is bigger than 3X3, only the first 3X3 cells are taken.
     * If the given array is smaller, the rest of the cells are initialized to –1.
     * 
     * @param array The array which the values are taken from.
     */

    Square3x3(int[][]array)
    {   
        this(); // use default constructor in case the given array is to small.

        for (int i = MIN_SIZE; i < array.length && i < _square.length ; i++)
        {   
            for( int j = MIN_SIZE; j < array[i].length && j < _square.length; j++)
            {
                _square [i][j] = array [i][j];
            }
        }
    }

    /**
     * Copy constructor.
     * Constructs a 2-dimensional array of the size 3X3,
     * whose values are taken from the array of the given Square3x3 object.
     * 
     * @param other The Square3x3 object to copy. 
     */
    Square3x3(Square3x3 other)
    {
        this(); // use default constructor in case the given Square3x3 is null.

        if(other != null)
        {
            for (int i = 0; i < _square.length; i++)
            {
                for( int j = 0; j < _square.length; j++)
                {
                    _square [i][j] = other._square [i][j] ;
                }
            }
        }
    }

    /**
     * Returns the value in the (row, col) cell.
     * If the row and/or col are out of the array bounds, returns –1.
     * Legal values for row/col are 0,1,2.
     * 
     * @param row The cell's row.
     * @param col The cell's column.
     */
    public int getCell(int row, int col)
    {
        if(row > DEF_VAL && row < DEF_SIZE && col > DEF_VAL && col < DEF_SIZE)
        {
            return _square [row][col];
        }

        return DEF_VAL;
    }

    /**
     * Sets the cell (row, col) in the array to the given value.
     * If the row and/or col are out of the array bounds – does nothing.
     * Legal values for row/col are 0,1,2.
     * 
     * @param row The row of the cell to be set.
     * @param col The column of the cell to be set.
     * @param value The value of the cell to be set to.
     */
    public void setXY(int row, int col, int value)
    {
        if(row > DEF_VAL && row < DEF_SIZE && col > DEF_VAL && col < DEF_SIZE)
        {
            _square[row][col] = value;
        }
    }

    /**
     * Returns a String representation of the array.
     * 
     * @return The square as a string
     */
    public String toString()
    {
        String stri = "";

        for(int i = MIN_SIZE ; i < _square.length; i++)
        {   
            stri = stri + _square[i][MIN_SIZE]; // solve fence post problem

            for(int j = MIN_NUM; j < _square.length; j++)
            {
                stri = stri + "\t" + _square[i][j] ;
            }

            stri = stri + "\n";
        }

        return stri;
    }

    /**
     * Check if all integers 1-9 are in the given square.
     * 
     * @return True - if all integers 1-9 are in the given square, false - otherwise. 
     */
    public boolean allThere()
    {
        for(int k = MIN_NUM; k <= MAX_NUM; k++)
        {
            boolean foundNum = false; //flag

            for(int i = 0; i < _square.length && !foundNum; i++)
            { 
                for(int j = 0; j < _square.length && !foundNum; j++)
                {
                    if(_square [i][j] == k)
                        foundNum = true;
                }    
            }

            if (!foundNum)
                return false;
        }

        return true;
    } 

    /**
     * Initialize true in the matching 'values' array cell
     * for each integer between 1-9 which appear in the given row. 
     * 
     * @param row The row to take the integers from.
     * @param values The boolean array to initialize true in it's cells if needed.
     */
    public void whosThereRow (int row, boolean[] values )
    {
        if(row > DEF_VAL && row < _square.length && values != null)
        {
            if(values.length == 10)
            {
                for(int i = MIN_SIZE; i < _square.length; i++)
                {
                    if( checkGoodNum(row, i) )
                    {
                        values[ _square[row][i] ] = true; 
                    }
                }
            }
        }
    } 

    /**
     * Initialize true in the matching 'values' array cell
     * for each integer between 1-9 which appear in the given column. 
     * 
     * @param col The column to take the integers from.
     * @param values The boolean array to initialize true in it's cells if needed.
     */
    public void whosThereCol (int col, boolean[] values )
    {
        if(col > DEF_VAL && col < _square.length && values != null)
        {
            if(values.length == 10)
            {
                for(int i = MIN_SIZE; i < _square.length; i++)
                {
                    if( checkGoodNum(i, col) )
                    {
                        values[ _square[i][col] ] = true; 
                    }
                }
            }
        }    
    }

    private boolean checkGoodNum(int row, int col)
    {
        //check if the integer in the given row and column is between 1-9
        return _square[row][col] >= MIN_NUM && _square[row][col] <= MAX_NUM;  
    }

}
