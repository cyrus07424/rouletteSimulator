package predictor;

import java.util.ArrayList;
import java.util.List;

import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.LSTM;
import org.deeplearning4j.nn.conf.layers.RnnOutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.impl.indexaccum.IMax;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.RmsProp;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import application.RouletteContext;
import constants.Configurations;
import enums.Spot;
import model.BetTypePrediction;
import model.SpotPrediction;
import utils.LogHelper;

/**
 * Rnn予測器.
 * https://github.com/deeplearning4j/dl4j-examples/blob/master/dl4j-examples/src/main/java/org/deeplearning4j/examples/recurrent/basic/BasicRNNExample.java
 *
 * @author
 */
public class RnnPredictor extends BasePredictor {

	/**
	 * シングルトンのインスタンス.
	 */
	private static RnnPredictor instance;

	// RNN dimensions
	private static final int HIDDEN_LAYER_WIDTH = 50;

	private static final int HIDDEN_LAYER_CONT = 2;

	/**
	 * 使用するネットワーク.
	 */
	private MultiLayerNetwork useNet;

	/**
	 * インスタンスを取得.
	 *
	 * @return
	 */
	public static RnnPredictor getInstance() {
		if (instance == null) {
			instance = new RnnPredictor();
		}
		return instance;
	}

	@Override
	public List<SpotPrediction> getNextSpotPredictionList(RouletteContext rouletteContext) {
		List<SpotPrediction> spotPredictionList = new ArrayList<>();
		if (useNet == null) {
			// ネットワークを取得
			if (rouletteContext.spotHistoryList.size() == Configurations.SPOT_HISTORY_LIST_SIZE) {
				useNet = getNet(rouletteContext, rouletteContext.spotHistoryList.toArray(new Spot[0]));
			}
		} else {
			// 有効な出目一覧
			List<Spot> availableSpotList = Spot.getAvailableList(rouletteContext.rouletteType);

			INDArray testInit = Nd4j.zeros(1, availableSpotList.size(), 1);
			testInit.putScalar(availableSpotList.indexOf(rouletteContext.spotHistoryList.getFirst()), 1);

			// run one step -> IMPORTANT: rnnTimeStep() must be called, not
			// output()
			// the output shows what the net thinks what should come next
			INDArray output = useNet.rnnTimeStep(testInit);

			// now the net should guess rouletteContext.spotHistoryList.length
			// more characters
			for (int i = 1; i < rouletteContext.spotHistoryList.size(); i++) {

				// first process the last output of the network to a concrete
				// neuron, the neuron with the highest output has the highest
				// chance to get chosen
				int sampledCharacterIdx = Nd4j.getExecutioner().exec(new IMax(output), 1).getInt(0);

				// print the chosen output
				// System.out.print(availableSpotList.get(sampledCharacterIdx));

				// use the last output as input
				INDArray nextInput = Nd4j.zeros(1, availableSpotList.size(), 1);
				nextInput.putScalar(availableSpotList.indexOf(rouletteContext.spotHistoryList.get(i)), 1);
				output = useNet.rnnTimeStep(nextInput);
			}

			int sampledCharacterIdx = Nd4j.getExecutioner().exec(new IMax(output), 1).getInt(0);
			LogHelper.info("次の予想:" + availableSpotList.get(sampledCharacterIdx));

			// 予測を作成
			spotPredictionList.add(new SpotPrediction(availableSpotList.get(sampledCharacterIdx), 1));
		}
		return spotPredictionList;
	}

	/**
	 * ネットワークを取得.
	 *
	 * @param rouletteContext
	 * @param spotHistoryArray
	 * @return
	 */
	private static MultiLayerNetwork getNet(RouletteContext rouletteContext, Spot[] spotHistoryArray) {
		// 有効な出目一覧
		List<Spot> availableSpotList = Spot.getAvailableList(rouletteContext.rouletteType);

		// パラメータ設定
		NeuralNetConfiguration.Builder builder = new NeuralNetConfiguration.Builder();
		builder.seed(123);
		builder.biasInit(0);
		builder.miniBatch(false);
		builder.updater(new RmsProp(0.001));
		builder.weightInit(WeightInit.XAVIER);

		NeuralNetConfiguration.ListBuilder listBuilder = builder.list();

		// first difference, for rnns we need to use LSTM.Builder
		for (int i = 0; i < HIDDEN_LAYER_CONT; i++) {
			LSTM.Builder hiddenLayerBuilder = new LSTM.Builder();
			hiddenLayerBuilder.nIn(i == 0 ? availableSpotList.size() : HIDDEN_LAYER_WIDTH);
			hiddenLayerBuilder.nOut(HIDDEN_LAYER_WIDTH);
			// adopted activation function from LSTMCharModellingExample
			// seems to work well with RNNs
			hiddenLayerBuilder.activation(Activation.TANH);
			listBuilder.layer(i, hiddenLayerBuilder.build());
		}

		// we need to use RnnOutputLayer for our RNN
		RnnOutputLayer.Builder outputLayerBuilder = new RnnOutputLayer.Builder(LossFunctions.LossFunction.MCXENT);
		// softmax normalizes the output neurons, the sum of all outputs is 1
		// this is required for our sampleFromDistribution-function
		outputLayerBuilder.activation(Activation.SOFTMAX);
		outputLayerBuilder.nIn(HIDDEN_LAYER_WIDTH);
		outputLayerBuilder.nOut(availableSpotList.size());
		listBuilder.layer(HIDDEN_LAYER_CONT, outputLayerBuilder.build());

		// finish builder
		listBuilder.pretrain(false);
		listBuilder.backprop(true);

		// create network
		MultiLayerConfiguration conf = listBuilder.build();
		MultiLayerNetwork net = new MultiLayerNetwork(conf);
		net.init();
		net.setListeners(new ScoreIterationListener(1));

		// 学習データを作成
		// create input and output arrays: SAMPLE_INDEX, INPUT_NEURON,
		// SEQUENCE_POSITION
		INDArray input = Nd4j.zeros(1, availableSpotList.size(), spotHistoryArray.length);
		INDArray labels = Nd4j.zeros(1, availableSpotList.size(), spotHistoryArray.length);
		// loop through our sample-sentence
		int samplePos = 0;
		for (Spot currentChar : spotHistoryArray) {
			// small hack: when currentChar is the last, take the first char as
			// nextChar - not really required. Added to this hack by adding a
			// starter first character.
			Spot nextChar = spotHistoryArray[(samplePos + 1) % (spotHistoryArray.length)];
			// input neuron for current-char is 1 at "samplePos"
			input.putScalar(new int[] { 0, availableSpotList.indexOf(currentChar), samplePos }, 1);
			// output neuron for next-char is 1 at "samplePos"
			labels.putScalar(new int[] { 0, availableSpotList.indexOf(nextChar), samplePos }, 1);
			samplePos++;
		}
		DataSet trainingData = new DataSet(input, labels);

		// some epochs
		for (int epoch = 0; epoch < 100; epoch++) {

			LogHelper.debug("Epoch " + epoch);

			// train the data
			net.fit(trainingData);

			// clear current stance from the last example
			net.rnnClearPreviousState();

			// put the first character into the rrn as an initialisation
			INDArray testInit = Nd4j.zeros(1, availableSpotList.size(), 1);
			testInit.putScalar(availableSpotList.indexOf(spotHistoryArray[0]), 1);

			// run one step -> IMPORTANT: rnnTimeStep() must be called, not
			// output()
			// the output shows what the net thinks what should come next
			INDArray output = net.rnnTimeStep(testInit);

			// now the net should guess spotHistoryArray.length more characters
			for (Spot dummy : spotHistoryArray) {

				// first process the last output of the network to a concrete
				// neuron, the neuron with the highest output has the highest
				// chance to get chosen
				int sampledCharacterIdx = Nd4j.getExecutioner().exec(new IMax(output), 1).getInt(0);

				// print the chosen output
				System.out.print(availableSpotList.get(sampledCharacterIdx));
				System.out.print(",");

				// use the last output as input
				INDArray nextInput = Nd4j.zeros(1, availableSpotList.size(), 1);
				nextInput.putScalar(sampledCharacterIdx, 1);
				output = net.rnnTimeStep(nextInput);

			}
			System.out.print("\n");
		}

		return net;
	}

	@Override
	public List<BetTypePrediction> getNextBetTypePredictionList(RouletteContext rouletteContext) {
		return null;
	}
}