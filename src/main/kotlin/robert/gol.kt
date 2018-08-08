package robert

import java.util.Random

const val rows = 20
const val cols = 90
val random = Random()

var grid = List(rows, { _ -> List(cols, { _ -> random.nextInt(5) == 0 })})

class Console {
    private fun clear() {
        print(String.format("%c[0;0f",0x1B));
    }

    fun render(grid :List<List<Boolean>>) {
        clear()
        // for (int i = 0; i < rows-1; i++){
        //     row = rows[i]
        // }

        for(row in 0 until rows) {
            for(col in 0 until cols) {
                if (grid[row][col]) {
                    print("@")
                } else {
                    print(" ")
                }
            }
            println()
        }
    }
}

data class Point(val row:Int, val col:Int) {
    fun isAlive(point:Point = this) = grid[point.row][point.col]
    private fun isValid(point:Point) = point.row in 0 until rows && point.col in 0 until cols
    fun countNeightbours() = myNeighbours()
                                .filter{isValid(it)}
                                .map{isAlive(it)}
                                .count{it}
}

fun Point.myNeighbours() = arrayOf(Point(row-1, col-1), Point(row-1, col), Point(row-1, col +1),
                                   Point(row, col-1),                      Point(row, col +1),
                                   Point(row+1, col-1), Point(row+1, col), Point(row+1, col +1))

fun evolve() = (0 until rows).map{row -> 
    (0 until cols).map{col -> 
        val point = Point(row, col)
        val neighbours = point.countNeightbours()
        neighbours == 3 || (neighbours == 2 && point.isAlive())
    }
}

fun main(args: Array<String>) {  
    val console = Console()
    while (true) {
        console.render(grid) 
        grid = evolve()
        Thread.sleep(200)
    }
}   