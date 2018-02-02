/**
 * Created by Aditya on January 29, 2018.
 */
class Flower(val sepalLength: Double, val sepalWidth: Double, val petalLength: Double, val petalWidth: Double, val flowerType: String) {

    fun getFlowerType(): Matrix {
        val matrix = Matrix(3, 1)
        when (flowerType) {
            "Iris-setosa" -> matrix.data[0][0] = 1.0
            "Iris-versicolor" -> matrix.data[1][0] = 2.0
            "Iris-virginica" -> matrix.data[2][0] = 3.0
            else -> Unit
        }
        return matrix
    }
}