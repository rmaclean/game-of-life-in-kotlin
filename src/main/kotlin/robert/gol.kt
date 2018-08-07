package robert

import java.util.Random

const val rows = 20
const val cols = 90

val random = Random()
var grid : Array<BooleanArray> = Array(rows, { _ -> BooleanArray(cols, { _ -> random.nextInt(5) == 0 })})

class Console {
    private fun clear() {
        print(String.format("%c[0;0f",0x1B));
    }

    fun render(grid : Array<BooleanArray>) {
        clear()
        for (row in grid) {
            for (col in row) {
               print(if (!col) ' ' else '*') 
            }
            println()
        }
    }
}

fun evolve():Array<BooleanArray> {
    val newGrid = Array<BooleanArray>(rows, {_ -> BooleanArray(cols) })
    val rowLastIndex = rows -1;
    val colsLastIndex = cols -1;
    for (row in 0..rowLastIndex) {
        for (col in 0..colsLastIndex) {
            val alive = grid[row][col]
            var neighbours = 0

            if (row > 0) {
                if (col > 0) {
                    neighbours += if (grid[row-1][col-1]) 1 else 0 
                }

                neighbours += if (grid[row-1][col]) 1 else 0

                if (col < colsLastIndex) {
                    neighbours += if (grid[row-1][col+1]) 1 else 0 
                }
            }

            if (col > 0) {
                neighbours += if (grid[row][col-1]) 1 else 0 
            }

            if (col < colsLastIndex) {
                neighbours += if (grid[row][col+1]) 1 else 0 
            }

            if (row < rowLastIndex) {
                if (col > 0) {
                    neighbours += if (grid[row+1][col-1]) 1 else 0 
                }

                neighbours += if (grid[row+1][col]) 1 else 0

                if (col < colsLastIndex) {
                    neighbours += if (grid[row+1][col+1]) 1 else 0 
                }
            }

            if (alive) {
                newGrid[row][col] = neighbours == 2 || neighbours == 3
            } else {
                newGrid[row][col] = neighbours == 3
            }
        }
    }

    return newGrid        
}

fun main(args: Array<String>) {
    val console = Console()
    while (true) {
        console.render(grid) 
        grid = evolve() 
        Thread.sleep(200)
    }
}


