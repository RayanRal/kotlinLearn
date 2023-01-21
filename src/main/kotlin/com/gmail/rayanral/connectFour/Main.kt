package com.gmail.rayanral.connectFour

fun main() {
    println("Connect Four")
    print("First player's name: ")
    val firstPlayer = Player.addPlayer(readln())

    print("Second player's name: ")
    val secondPlayer = Player.addPlayer(readln())

    val dimensions = Dimensions.readDimensions()
    println("${firstPlayer.name} VS ${secondPlayer.name}")
    println("${dimensions.rows} X ${dimensions.cols} board")

    val board = Board.createBoard(dimensions)
    Display.board(board)
    var isEnd = false

    while (!isEnd) {
        isEnd = playTurn(board)
        if (!isEnd) {
            Display.board(board)
        }
        Player.changePlayer()
    }
    print("Game over!")
}

fun validateInput(input: String, board: Board): Boolean {
    if (input == "end") {
        return true
    }
    if (input.toIntOrNull() == null) {
        println("Incorrect column number")
        return false
    }
    val inputCol = input.toInt() - 1
    if (!board.isColumnInBounds(inputCol)) {
        println("The column number is out of range (1 - ${board.getMaxCols()})")
        return false
    }
    if (board.isColumnFull(inputCol)) {
        println("Column ${inputCol + 1} is full")
        return false
    }
    return true
}

fun processInput(input: String, board: Board): Position {
    val inputCol = input.toInt() - 1
    return board.addDisc(Player.getCurrentPlayer(), inputCol)
}

fun playTurn(board: Board): Boolean {
    var isValidInput = false
    var gameInput: String? = null
    while (!isValidInput) {
        println("${Player.getCurrentPlayer().name}'s turn:")
        gameInput = readln()
        isValidInput = validateInput(gameInput, board)
    }
    if (gameInput == "end") {
        return true
    } else {
        val lastTurnPosition = processInput(gameInput!!, board)
        val maybeWinner = board.getWinner(lastTurnPosition)
        if (maybeWinner != null) {
            Display.board(board)
            println("Player ${maybeWinner.name} won")
            return true
        }
        if (board.isFull()) {
            Display.board(board)
            println("It is a draw")
            return true
        }
        return false
    }
}

