package com.gmail.rayanral

data class Dimensions(val rows: Int, val cols: Int) {

    companion object {
        private fun isDimensionValid(dim: Int?, name: String): Boolean {
            val isValid = dim != null && dim in 5..9
            if (!isValid) {
                println("Board $name should be from 5 to 9")
            }
            return isValid
        }

        fun readDimensions(): Dimensions {
            println("Set the board dimensions (Rows x Columns)")
            println("Press Enter for default (6 x 7)")
            var dimensions = Dimensions(6, 7)
            val dimensionsString = readln()
            if (dimensionsString.isNotBlank()) {
                val dimensionsList: List<Int?> =
                    dimensionsString.lowercase().split("x").map { x: String -> x.trim().toIntOrNull() }
                if (dimensionsList.contains(null)) {
                    println("Invalid input")
                    return readDimensions()
                } else {
                    val rowsValid = isDimensionValid(dimensionsList[0], "rows")
                    val colsValid = isDimensionValid(dimensionsList[1], "columns")
                    if (!(rowsValid && colsValid)) {
                        return readDimensions()
                    }
                }
                dimensions = Dimensions(dimensionsList[0]!!, dimensionsList[1]!!)
            }
            return dimensions
        }
    }
}