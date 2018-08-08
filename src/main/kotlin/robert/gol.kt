package robert

import java.util.Random

const val rows = 20
const val cols = 90

var random = Random()
var grid = List(rows, { _ -> List(cols, {_ -> random.nextInt(5) == 0})})

class Console {
    private fun clear() {
        print(String.format("%c[0;0f",0x1B));
    }

    fun render(grid :List<List<Boolean>>) {
        clear()
        for (row in 0 until rows) {
            for (col in 0 until cols) {
                if (grid[row][col]) print('*') else print(' ')
            }
            println()
        }    
    }
}

data class Point(val x : Int, val y: Int) {
    fun isAlive() = grid[x][y]
    fun isValid() = (x in 1 until rows) && (y in 1 until cols)
}

fun neighbours(point: Point):Array<Point> {
    val (x, y) = point;
    return arrayOf(Point(x-1, y-1), Point(x-1, y), Point(x-1, y+1),
                   Point(x, y-1),                  Point(x, y+1),  
                   Point(x+1, y-1), Point(x+1, y), Point(x+1, y+1))  
} 

fun Point.countNeighbours() = neighbours(this)
                                .filter{it.isValid()}
                                .map{it.isAlive()}
                                .count{it}

fun evolve() = (0 until rows).map{row -> 
    (0 until cols).map{col -> 
        val point = Point(row, col)
        val isAlive = point.isAlive()
        val neighbours = point.countNeighbours()
        neighbours == 3 || (isAlive && neighbours == 2) 
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


