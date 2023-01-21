package com.gmail.rayanral

object Display {

    fun board(board: Board) {
        printHeader(board.board)
        printMainBoard(board.board)
        printLastRow(board.board)
    }

    private fun printHeader(board: Array<Array<String>>) {
        val numCols = board[0].size
        val headerNumbers = (1..numCols).toList().joinToString(" ")
        val headerRow = " $headerNumbers "
        println(headerRow)
    }

    private fun printMainBoard(board: Array<Array<String>>) {
        val numRows = board.size - 1
        for (row in numRows downTo 0) {
            val rowString = board[row].joinToString("|")
            println("|${rowString}|")
        }
    }

    private fun printLastRow(board: Array<Array<String>>) {
        val numCols = board[0].size
        val lastRow = (0..numCols).toList().joinToString("=") { _ -> "=" }
        println(lastRow)
    }
}