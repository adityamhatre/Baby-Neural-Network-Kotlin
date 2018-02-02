import java.util.*

/**
 * Created by Aditya on January 31, 2018.
 */
class NeuralNetwork(inputNodes: Int, val hiddenNodes: Int, outputNodes: Int) {
    private val weightsInputToHidden = Matrix(hiddenNodes, inputNodes)
    private val weightsHiddenToOutput = Matrix(outputNodes, hiddenNodes)
    private val biasHidden = Matrix(hiddenNodes, columns = 1)
    private val biasOutput = Matrix(outputNodes, columns = 1)

    init {
        weightsInputToHidden.randomize()
        weightsHiddenToOutput.randomize()
        biasHidden.randomize()
        biasOutput.randomize()
    }


    private fun sigmoid(x: Double): Double {
        return 1 / (1 + Math.pow(Math.E, -x))
    }



    private fun feedForward(input: Matrix): Matrix {
        val hiddenOutput = (weightsInputToHidden * input) + biasHidden
        hiddenOutput.map { sigmoid(it) }
        val output = (weightsHiddenToOutput * hiddenOutput) + biasOutput
        output.map { sigmoid(it) }
        return output
    }

    fun train(input: Matrix, target: Matrix) {
        val output = feedForward(input)
        val outputError = target - output
        val hiddenError = target.transpose() * outputError
    }
}