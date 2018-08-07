package robert

import java.util.Random

const val rows = 20
const val cols = 90

fun ClosedRange<Int>.random() = Random().nextInt(endInclusive + 1 - start) + start
var grid : Array<BooleanArray> = Array(rows, { _ -> BooleanArray(cols, { _ -> (0..5).random() == 0 })})

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

data class Point(val row:Int, val col:Int)

fun validPositions(position: Point):Array<Point> {
    val ( row, col ) = position
    return arrayOf(
        Point(row-1, col-1), Point(row-1, col), Point(row-1, col+1),
        Point(row  , col-1),                    Point(row  , col+1),
        Point(row+1, col-1), Point(row+1, col), Point(row+1, col+1)
    )
}

fun isValid(position: Point) = position.row in 1 until rows && position.col in 1 until cols

fun readCell(position: Point) = grid[position.row][position.col]

fun countNeighbours(position: Point) = validPositions(position)
                                        .filter{ isValid(it) }
                                        //.filter({prv -> isValid(prv)})
                                        .map{ readCell(it) }
                                        .count{ it }

fun evolve():Array<BooleanArray> {
    val newGrid = Array<BooleanArray>(rows, {_ -> BooleanArray(cols) })

    (0 until rows).map{ row -> 
        (0 until cols).map{ col -> 
            val center = Point(row, col)
            val isAlive = readCell(center)
            val neighbours = countNeighbours(center)
            newGrid[row][col] = (isAlive && !(neighbours < 2 || neighbours > 3)) || (neighbours == 3)
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


