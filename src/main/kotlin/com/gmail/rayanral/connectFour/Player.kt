package com.gmail.rayanral.connectFour

data class Player(val name: String, val symbol: String) {
    companion object {

        private val players = mutableListOf<Player>()
        private var curIdx = 0

        fun addPlayer(name: String): Player {
            when (players.size) {
                1 -> players.add(Player(name, "*"))
                0 -> players.add(Player(name, "o"))
            }
            return players.last()
        }

        fun getCurrentPlayer(): Player {
            return players[curIdx]
        }

        fun getPlayerFromSymbol(symbol: String): Player? {
            return when (symbol) {
                "*" -> players[1]
                "o" -> players[0]
                else -> null
            }
        }

        fun changePlayer() {
            curIdx = (curIdx + 1) % 2
        }


    }
}
