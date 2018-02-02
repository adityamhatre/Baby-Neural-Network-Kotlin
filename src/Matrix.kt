import java.util.*

/**
 * Created by Aditya on January 31, 2018.
 */
class Matrix(private val rows: Int, val columns: Int) {
    val data = Array(rows) { DoubleArray(columns) { 0.0 } }

    companion object {
        fun <T> fromArrayList(inputArray: ArrayList<T>): Matrix {
            val matrix = Matrix(inputArray.size, columns = 1)
            for (i in 0 until inputArray.size)
                matrix.data[i][0] = inputArray[i] as Double
            return matrix
        }
    }

    fun randomize() {
        for (i in 0 until rows)
            for (j in 0 until columns)
                data[i][j] = random()
    }

    fun add(otherMatrix: Matrix): Matrix {
        val resultMatrix = Matrix(rows, columns)
        for (i in 0 until rows)
            for (j in 0 until columns)
                resultMatrix.data[i][j] = data[i][j] + otherMatrix.data[i][j]
        return resultMatrix
    }

    fun multiply(otherMatrix: Matrix): Matrix {
        val resultMatrix = Matrix(rows, otherMatrix.columns)
        for (i in 0 until resultMatrix.rows)
            for (j in 0 until resultMatrix.columns) {
                val sum = (0 until columns).sumByDouble { data[i][it] * otherMatrix.data[it][j] }
                resultMatrix.data[i][j] = sum
            }
        return resultMatrix
    }


    private fun random(): Double {
        return Random(System.nanoTime()).nextDouble() * 2 - 1
    }

    fun print() {
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                print("${data[i][j]}\t")
            }
            println()
        }
    }

    fun transpose(): Matrix {
        val matrix = Matrix(columns, rows)
        for (i in 0 until rows)
            for (j in 0 until columns)
                matrix.data[j][i] = data[i][j]
        return matrix
    }

    private fun multiply(value: Double): Matrix {
        val matrix = Matrix(rows, columns)
        for (i in 0 until rows)
            for (j in 0 until columns)
                matrix.data[i][j] = data[i][j] * value
        return matrix
    }


    override fun toString(): String {
        var showThis = ""
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                showThis += ("${data[i][j]}\t")
            }
            showThis += "\n"
        }
        return showThis
    }

    operator fun times(otherMatrix: Matrix): Matrix {
        return this.multiply(otherMatrix)
    }

    fun map(mapFunction: (Double) -> Double) {
        for (i in 0 until rows)
            for (j in 0 until columns)
                data[i][j] = mapFunction(data[i][j])
    }

    fun forEach(mapFunction: (Double) -> Double) {
        map(mapFunction)
    }

    operator fun plus(other: Matrix): Matrix {
        return this.add(other)
    }

    operator fun minus(other: Matrix): Matrix {
        return this + other.multiply(-1.0)
    }


}