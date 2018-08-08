package robert

import java.util.Random

const val rows = 20
const val cols = 90

fun ClosedRange<Int>.random() = Random().nextInt(endInclusive + 1 - start) + start
var grid = List(rows, { _ -> List(cols, { _ -> (0..5).random() == 0 }) })

class Console {
    private fun clear() {
        print(String.format("%c[0;0f", 0x1B))
    }

    fun render(grid: List<List<Boolean>>) {
        clear()
        for (row in grid) {
            for (col in row) {
                print(if (!col) ' ' else '*')
            }
            println()
        }
    }
}

data class Point(val row: Int, val col: Int) {

    private fun myNeighbours() = arrayOf(
        Point(row - 1, col - 1), Point(row - 1, col), Point(row - 1, col + 1),
        Point(row, col - 1),                          Point(row, col + 1),
        Point(row + 1, col - 1), Point(row + 1, col), Point(row + 1, col + 1))

    private fun isValidPosition(point:Point) = point.row in 0 until rows && point.col in 0 until cols

    fun positionState(point:Point = this) = grid[point.row][point.col]

    fun countNeighbours() = myNeighbours()
        .filter { isValidPosition(it) }
        .map { positionState(it) }
        .count { it }
}

fun evolve() = (0 until rows).map { row ->
        (0 until cols).map { col ->
            val point = Point(row, col)
            val neighbours = point.countNeighbours()
            neighbours == 3 || (neighbours == 2 && point.positionState())
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
