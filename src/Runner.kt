import java.io.File

/**
 * Created by Aditya on January 29, 2018.
 */
fun main(args: Array<String>) {
    val data = ArrayList<Flower>()
    File("C:\\Users\\Aditya\\Desktop\\bezdekIris.txt").readLines().forEach {
        val line = it.split(",")
        val sepalLength: Double = line[0].toDouble()
        val sepalWidth: Double = line[1].toDouble()
        val petalLength: Double = line[2].toDouble()
        val petalWidth: Double = line[3].toDouble()
        val flowerType: String = line[4]
        data.add(Flower(sepalLength, sepalWidth, petalLength, petalWidth, flowerType))
    }


    val nn = NeuralNetwork(4, 2, 3)
    data.forEach { flower ->
        val inputMatrix = Matrix(4, 1)
        inputMatrix.data[0][0] = flower.sepalLength
        inputMatrix.data[1][0] = flower.sepalWidth
        inputMatrix.data[2][0] = flower.petalLength
        inputMatrix.data[3][0] = flower.petalWidth
        val target = flower.getFlowerType()
        nn.train(inputMatrix, target)
    }
}


