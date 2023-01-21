package com.gmail.rayanral

data class Position(val row: Int, val col: Int)

class Board(val board: Array<Array<String>>) {

    fun isColumnInBounds(col: Int): Boolean {
        return col >= 0 && col < board.size
    }

    fun getMaxCols(): Int {
        return board[0].size
    }

    fun addDisc(player: Player, col: Int): Position {
        val freeRow = findFreeRow(col)
        board[freeRow!!][col] = player.symbol
        return Position(freeRow, col)
    }

    fun isColumnFull(col: Int): Boolean {
        return findFreeRow(col) == null
    }

    fun getWinner(position: Position): Player? {
        return getWinnerFromPosition(position)?.let { s -> Player.getPlayerFromSymbol(s) }
    }

    fun isFull(): Boolean {
        return board.filter { row -> row.none { e -> e == " " } }.size == board.size
    }

    private fun getWinnerFromPosition(position: Position): String? {
        val idxGroups = createChecks(position)
        return idxGroups.firstNotNullOfOrNull { idxs -> foldElements(idxs) }
    }

    private fun createChecks(position: Position): List<List<Position>> {
        val (row, col) = position
        val idxs = mutableListOf<List<Position>>()
        for (offset in 0 downTo -3) {
            // 4 consecutive in column
            idxs.add(
                listOf(
                    Position(row + offset, col),
                    Position(row + 1 + offset, col),
                    Position(row + 2 + offset, col),
                    Position(row + 3 + offset, col)
                )
            )
            // 4 consecutive in row
            idxs.add(
                listOf(
                    Position(row, col + offset),
                    Position(row, col + 1 + offset),
                    Position(row, col + 2 + offset),
                    Position(row, col + 3 + offset)
                )
            )
            // 4 consecutive in diagonal
            idxs.add(
                listOf(
                    Position(row + offset, col + offset),
                    Position(row + 1 + offset, col + 1 + offset),
                    Position(row + 2 + offset, col + 2 + offset),
                    Position(row + 3 + offset, col + 3 + offset)
                )
            )
            idxs.add(
                listOf(
                    Position(row + offset, col + offset),
                    Position(row - 1 + offset, col - 1 + offset),
                    Position(row - 2 + offset, col - 2 + offset),
                    Position(row - 3 + offset, col - 3 + offset)
                )
            )
            idxs.add(
                listOf(
                    Position(row + offset, col + offset),
                    Position(row - 1 + offset, col + 1 + offset),
                    Position(row - 2 + offset, col + 2 + offset),
                    Position(row - 3 + offset, col + 3 + offset)
                )
            )
            idxs.add(
                listOf(
                    Position(row + offset, col + offset),
                    Position(row + 1 + offset, col - 1 + offset),
                    Position(row + 2 + offset, col - 2 + offset),
                    Position(row + 3 + offset, col - 3 + offset)
                )
            )
        }
        return idxs
    }


    private fun foldElements(idxs: List<Position>): String? {
        val validIdxs = idxs.filter { p -> isInBounds(p) }.size
        if (validIdxs < 4) return null
        val elements = idxs.map { idx -> board[idx.row][idx.col] }
        val element = elements[0]
        val isAllEqual = elements.all { el -> el == element }
        return if (isAllEqual) {
            element
        } else {
            null
        }
    }

    private fun isInBounds(position: Position): Boolean {
        return position.row >= 0 && position.row < board.size && position.col >= 0 && position.col < board[position.row].size
    }

    private fun findFreeRow(col: Int): Int? {
        val column = getColumn(col)
        for (row in column.indices) {
            if (column[row] == " ") {
                return row
            }
        }
        return null
    }

    private fun getColumn(col: Int): List<String> {
        return board.indices.map { r -> board[r][col] }
    }

    companion object {

        fun createBoard(dimensions: Dimensions): Board {
            val board = Array(dimensions.rows) { Array(dimensions.cols) { " " } }
            return Board(board)
        }

    }
}