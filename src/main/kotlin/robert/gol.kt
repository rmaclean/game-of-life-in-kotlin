package robert

import java.util.Random

const val rows = 20
const val cols = 90

//var grid

class Console {
    private fun clear() {
        print(String.format("%c[0;0f",0x1B));
    }

    fun render(grid :List<List<Boolean>) {
        clear()
        
    }
}

fun main(args: Array<String>) {
    val console = Console()
    while (true) {
        //console.render(grid) 
        Thread.sleep(200)
    }
}