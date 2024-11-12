<p align="center">
    <img src="https://raw.githubusercontent.com/PKief/vscode-material-icon-theme/ec559a9f6bfd399b82bb44393651661b08aaf7ba/icons/folder-markdown-open.svg" align="center" width="30%">
</p>
<p align="center"><h1 align="center">ROULETTE SIMULATOR</h1></p>
<p align="center">
	<em>Spin Success with rouletteSimulator: Your Key to Strategic Roulette Simulation!</em>
</p>
<p align="center">
	<img src="https://img.shields.io/github/license/cyrus07424/rouletteSimulator?style=default&logo=opensourceinitiative&logoColor=white&color=0080ff" alt="license">
	<img src="https://img.shields.io/github/last-commit/cyrus07424/rouletteSimulator?style=default&logo=git&logoColor=white&color=0080ff" alt="last-commit">
	<img src="https://img.shields.io/github/languages/top/cyrus07424/rouletteSimulator?style=default&color=0080ff" alt="repo-top-language">
	<img src="https://img.shields.io/github/languages/count/cyrus07424/rouletteSimulator?style=default&color=0080ff" alt="repo-language-count">
</p>
<p align="center"><!-- default option, no dependency badges. -->
</p>
<p align="center">
	<!-- default option, no dependency badges. -->
</p>
<br>

##  Table of Contents

- [ Overview](#-overview)
- [ Features](#-features)
- [ Project Structure](#-project-structure)
  - [ Project Index](#-project-index)
- [ Getting Started](#-getting-started)
  - [ Prerequisites](#-prerequisites)
  - [ Installation](#-installation)
  - [ Usage](#-usage)
  - [ Testing](#-testing)
- [ Project Roadmap](#-project-roadmap)
- [ Contributing](#-contributing)
- [ License](#-license)
- [ Acknowledgments](#-acknowledgments)

---

##  Overview

The rouletteSimulator project is a sophisticated open-source tool that simulates roulette game strategies. It allows users to customize game parameters, select strategies, and view key statistics in a user-friendly interface. Ideal for gambling enthusiasts, researchers, and developers looking to analyze and optimize roulette betting strategies.

---

##  Features

|      | Feature         | Summary       |
| :--- | :---:           | :---          |
| ⚙️  | **Architecture**  | <ul><li>Java-based architecture</li><li>Utilizes JavaFX for UI components</li><li>Follows a modular design pattern</li></ul> |
| 🔩 | **Code Quality**  | <ul><li>Consistent coding style</li><li>Well-structured classes and methods</li><li>Proper error handling</li></ul> |
| 📄 | **Documentation** | <ul><li>Extensive documentation in Java and FXML files</li><li>Clear explanations of UI layouts and model classes</li><li>Missing information on package managers and containers</li></ul> |
| 🔌 | **Integrations**  | <ul><li>Integration with GitHub Actions for CI/CD</li><li>Dependency management with Maven and Dependabot</li><li>Uses Java and FXML for UI components</li></ul> |
| 🧩 | **Modularity**    | <ul><li>Separation of concerns with distinct UI and model components</li><li>Encapsulation of prediction and betting logic</li><li>Potential for further modularization based on codebase structure</li></ul> |
| 🧪 | **Testing**       | <ul><li>Testing details not provided</li><li>Assumed presence of unit tests for critical components</li><li>Opportunity for enhancing testing coverage and strategies</li></ul> |
| ⚡️  | **Performance**   | <ul><li>No specific performance details mentioned</li><li>Performance likely dependent on algorithm efficiency</li><li>Potential for optimization based on simulation complexity</li></ul> |
| 🛡️ | **Security**      | <ul><li>Security aspects not highlighted</li><li>Considerations for data validation and secure coding practices</li><li>Opportunity for security audits and enhancements</li></ul> |
| 📦 | **Dependencies**  | <ul><li>Dependencies include Java, JavaFX, and related FXML files</li><li>Utilizes GitHub Actions, Maven, and Dependabot for automation</li><li>Dependency details not fully specified</li></ul> |

---

##  Project Structure

```sh
└── rouletteSimulator/
    ├── .github
    │   ├── dependabot.yml
    │   └── workflows
    ├── LICENSE
    ├── README.md
    ├── pom.xml
    └── src
        └── main
```


###  Project Index
<details open>
	<summary><b><code>ROULETTESIMULATOR/</code></b></summary>
	<details> <!-- __root__ Submodule -->
		<summary><b>__root__</b></summary>
		<blockquote>
			<table>
			</table>
		</blockquote>
	</details>
	<details> <!-- src Submodule -->
		<summary><b>src</b></summary>
		<blockquote>
			<details>
				<summary><b>main</b></summary>
				<blockquote>
					<details>
						<summary><b>resources</b></summary>
						<blockquote>
							<details>
								<summary><b>fxml</b></summary>
								<blockquote>
									<table>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/resources/fxml/InitialSetting.fxml'>InitialSetting.fxml</a></b></td>
										<td>Defines the initial settings interface layout for the application, including menu options and user input fields for configuring the roulette game parameters.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/resources/fxml/SimulationMode.fxml'>SimulationMode.fxml</a></b></td>
										<td>- Defines the layout for the Simulation Mode interface, showcasing strategy options and key statistics<br>- The file structures the UI elements using JavaFX components, facilitating user interaction with the simulation.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/resources/fxml/SelectStrategyList.fxml'>SelectStrategyList.fxml</a></b></td>
										<td>- Defines the user interface layout for selecting strategies in the JavaFX application<br>- Displays a list of strategies in a ListView component and provides an 'OK' button for confirmation<br>- The SelectStrategyList.fxml file contributes to the visual representation and user interaction of the application's strategy selection feature.</td>
									</tr>
									</table>
									<details>
										<summary><b>cell</b></summary>
										<blockquote>
											<table>
											<tr>
												<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/resources/fxml/cell/SimulationModeStrategyCell.fxml'>SimulationModeStrategyCell.fxml</a></b></td>
												<td>Displays a structured layout for visualizing simulation mode strategy details, including key financial metrics and performance indicators.</td>
											</tr>
											<tr>
												<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/resources/fxml/cell/SelectStrategyListStrategyCell.fxml'>SelectStrategyListStrategyCell.fxml</a></b></td>
												<td>Defines the layout for displaying a strategy selection cell in the JavaFX application.</td>
											</tr>
											</table>
										</blockquote>
									</details>
								</blockquote>
							</details>
						</blockquote>
					</details>
					<details>
						<summary><b>java</b></summary>
						<blockquote>
							<details>
								<summary><b>model</b></summary>
								<blockquote>
									<table>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/model/SpotPrediction.java'>SpotPrediction.java</a></b></td>
										<td>Defines a SpotPrediction class in the model package to represent a predicted outcome with its associated probability.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/model/Bet.java'>Bet.java</a></b></td>
										<td>Defines a Bet class with properties for bet type and value, facilitating the representation of bets within the project's domain model.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/model/BetTypePrediction.java'>BetTypePrediction.java</a></b></td>
										<td>Define and encapsulate bet type predictions with associated probabilities in the project's model package.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/model/ColorPrediction.java'>ColorPrediction.java</a></b></td>
										<td>- Defines color prediction probabilities for red, black, and green outcomes<br>- This class encapsulates the likelihood of each color occurring based on a specific input<br>- It plays a crucial role in predicting color outcomes within the project's architecture.</td>
									</tr>
									</table>
								</blockquote>
							</details>
							<details>
								<summary><b>constants</b></summary>
								<blockquote>
									<table>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/constants/Configurations.java'>Configurations.java</a></b></td>
										<td>Define environment settings and configurations for the project, including random number generation method, spot history size, balance history size, initial balance, minimum and maximum bet amounts, setting file directory, and strategy list file name.</td>
									</tr>
									</table>
								</blockquote>
							</details>
							<details>
								<summary><b>enums</b></summary>
								<blockquote>
									<table>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/enums/BetType.java'>BetType.java</a></b></td>
										<td>- Defines various types of bets in a roulette game and provides methods to retrieve a list of valid bet types based on the game type, as well as the corresponding odds for each bet type<br>- The code file plays a crucial role in managing and organizing the different betting options available in the roulette game within the codebase architecture.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/enums/Spot.java'>Spot.java</a></b></td>
										<td>- Defines various characteristics and behaviors of roulette spots, such as color, number range, and column placement<br>- Enables retrieval of spots based on specific criteria and generation of random spots according to configurable rules<br>- Facilitates categorization and selection of spots for roulette simulations.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/enums/SpotGenerateType.java'>SpotGenerateType.java</a></b></td>
										<td>- Define various methods for generating spots in the project, such as random selection, sequential numbers, and specific color choices<br>- The SpotGenerateType enum encapsulates different strategies for generating spots on the board, contributing to the project's versatility and gameplay dynamics.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/enums/RouletteType.java'>RouletteType.java</a></b></td>
										<td>Defines different types of roulette for the project's architecture.</td>
									</tr>
									</table>
								</blockquote>
							</details>
							<details>
								<summary><b>predictor</b></summary>
								<blockquote>
									<table>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/predictor/MarkovPredictor.java'>MarkovPredictor.java</a></b></td>
										<td>- MarkovPredictor class utilizes Markov tables to predict the next spot and bet type based on historical data<br>- It updates probabilities for spot and bet transitions, aiding in making informed predictions for roulette outcomes<br>- The class contributes to enhancing the predictive capabilities of the overall system.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/predictor/CountPredictor2.java'>CountPredictor2.java</a></b></td>
										<td>- Generates spot and bet type predictions based on historical data<br>- Calculates probabilities for each spot and bet type, considering their occurrence frequency<br>- Additionally, computes color predictions by analyzing the frequency of red, black, and green spots<br>- The code contributes to predicting outcomes in a roulette game based on past results.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/predictor/DifferencePredictor.java'>DifferencePredictor.java</a></b></td>
										<td>- Generates spot predictions based on historical data, calculating the average difference to predict future spots in a roulette game<br>- The code file contributes to the project's architecture by providing a method to generate a list of spot predictions using a difference prediction approach.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/predictor/BasePredictor.java'>BasePredictor.java</a></b></td>
										<td>- Defines a base class for predictors in the project, providing methods to retrieve predictions for the next spot, bet type, and color based on the given context<br>- The class structure allows for easy extension and customization of prediction logic throughout the codebase architecture.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/predictor/MarkovPredictor2.java'>MarkovPredictor2.java</a></b></td>
										<td>- Generates spot and bet type predictions based on the Markov table using the recent spot history<br>- Calculates probabilities for the next spot and bet type occurrences, aiding in predicting outcomes for the roulette game.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/predictor/RandomPredictor.java'>RandomPredictor.java</a></b></td>
										<td>Generates random predictions for spots and bet types based on the current roulette context.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/predictor/RnnPredictor.java'>RnnPredictor.java</a></b></td>
										<td>- Generates spot predictions based on historical data using a recurrent neural network (RNN)<br>- The code initializes the RNN network, trains it with historical data, and predicts the next spot based on the input sequence<br>- The RNN model iterates through the historical data to make accurate spot predictions for future outcomes.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/predictor/CountPredictor.java'>CountPredictor.java</a></b></td>
										<td>- Generates predictions based on occurrence counts of spots and bet types in a roulette game<br>- Updates counts and probabilities for each prediction type.</td>
									</tr>
									</table>
								</blockquote>
							</details>
							<details>
								<summary><b>application</b></summary>
								<blockquote>
									<table>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/application/RouletteContext.java'>RouletteContext.java</a></b></td>
										<td>- The RouletteContext class encapsulates the state of a roulette game, tracking key parameters like balance and bet limits<br>- It also maintains a history of outcomes and calculates the percentage of red, black, and green spots in the history<br>- This class plays a crucial role in managing the game's context within the codebase architecture.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/application/Main.java'>Main.java</a></b></td>
										<td>- Initiates the application by displaying the initial settings screen through the BaseController<br>- The Main class in the provided file serves as the entry point for the application, launching the program and handling any exceptions that may occur during startup.</td>
									</tr>
									</table>
								</blockquote>
							</details>
							<details>
								<summary><b>controller</b></summary>
								<blockquote>
									<table>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/controller/InitialSettingController.java'>InitialSettingController.java</a></b></td>
										<td>- Manages initial settings for a roulette simulator app, allowing users to configure game parameters like balance, bet amounts, and game mode<br>- Provides options to view app information, select strategies, and start simulations<br>- Creates a context for the selected roulette type and user-defined settings.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/controller/SimulationModeController.java'>SimulationModeController.java</a></b></td>
										<td>- Manages simulation mode functionality by updating strategies, calculating outcomes, and displaying results<br>- Initializes a scheduled service for continuous processing, updating strategy parameters, and refreshing the UI<br>- Displays current loop count and color occurrence rates<br>- Logs information and adds outcomes to the context.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/controller/SelectStrategyListController.java'>SelectStrategyListController.java</a></b></td>
										<td>- Manages strategy selection for a roulette game by initializing strategy options, allowing users to enable/disable them, and saving selections<br>- Enables users to choose strategies and closes the selection window upon confirmation.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/controller/BaseController.java'>BaseController.java</a></b></td>
										<td>- Facilitates opening different screens in the application by providing methods to display initial settings, select strategies, and enter simulation mode<br>- Handles setting up stages, loading FXML layouts, and initializing controllers for each screen<br>- Supports seamless navigation and interaction within the application.</td>
									</tr>
									</table>
								</blockquote>
							</details>
							<details>
								<summary><b>utils</b></summary>
								<blockquote>
									<table>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/utils/LogHelper.java'>LogHelper.java</a></b></td>
										<td>- LogHelper class formats and outputs log messages to the console with timestamps and caller information<br>- It provides methods for logging at different levels (INFO, DEBUG, ERROR) and includes details like class and method names<br>- This utility enhances the project's logging capabilities, improving debugging and monitoring across the codebase.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/utils/StrategyHelper.java'>StrategyHelper.java</a></b></td>
										<td>- Facilitates management of strategy classes within the application, including creating, saving, and retrieving strategy class lists<br>- Enables dynamic loading and instantiation of strategy classes based on configuration settings.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/utils/FileHelper.java'>FileHelper.java</a></b></td>
										<td>- Create a FileHelper class to manage setting files<br>- The class provides a method to retrieve a setting file based on the given file name<br>- If the parent directory of the file does not exist, it creates the necessary directories.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/utils/PredictorHelper.java'>PredictorHelper.java</a></b></td>
										<td>Manages instances of predictors by storing them in a map for easy retrieval.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/utils/BetHelper.java'>BetHelper.java</a></b></td>
										<td>- The `BetHelper.java` file in the `utils` package provides essential functions for calculating the total bet value and total payout within the project<br>- It encapsulates logic to determine the total value of bets placed and the total payout based on specific criteria<br>- This utility class plays a crucial role in managing and processing betting-related data, contributing to the overall functionality of the codebase architecture.</td>
									</tr>
									</table>
								</blockquote>
							</details>
							<details>
								<summary><b>cell</b></summary>
								<blockquote>
									<table>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/cell/SimulationModeStrategyCell.java'>SimulationModeStrategyCell.java</a></b></td>
										<td>- Displays simulation mode strategy details in a cell format for the roulette game interface, including strategy name, balance, bet values, and winning statistics<br>- Updates cell visuals based on live status.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/cell/SelectStrategyListStrategyCell.java'>SelectStrategyListStrategyCell.java</a></b></td>
										<td>- Enables customization of strategy selection cells in the strategy selection screen by binding a callback to the checkbox displayed for each strategy<br>- This allows for dynamic updating of the checkbox state based on the selected strategy.</td>
									</tr>
									</table>
								</blockquote>
							</details>
							<details>
								<summary><b>strategy</b></summary>
								<blockquote>
									<table>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/strategy/MartingaleStrategy2.java'>MartingaleStrategy2.java</a></b></td>
										<td>- Implements a Martingale strategy based on higher probability between red and black in roulette<br>- Determines next bet based on previous outcome: minimum bet if won, double last bet if lost.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/strategy/TwoInOneStrategy.java'>TwoInOneStrategy.java</a></b></td>
										<td>- Implements a betting strategy based on the 2in1 method for red bets in roulette<br>- Adjusts bet amounts based on previous outcomes to optimize potential wins.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/strategy/TenUnitStrategy.java'>TenUnitStrategy.java</a></b></td>
										<td>- Implements a betting strategy based on the 10-unit method for red bets only<br>- Adjusts bet amounts dynamically based on previous outcomes to optimize balance utilization<br>- Automatically resets the betting sequence when reaching the limit.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/strategy/MultiBetStrategy.java'>MultiBetStrategy.java</a></b></td>
										<td>- Implements a multi-bet strategy using a predictor to determine bet amounts based on probabilities<br>- Determines bet amounts based on balance and prediction probabilities, ensuring bets are within set limits.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/strategy/ThirtyOneSystemStrategy.java'>ThirtyOneSystemStrategy.java</a></b></td>
										<td>- Implements a betting strategy based on the 31 System for red bets in roulette<br>- Resets after two consecutive wins or a loss on the 8th attempt<br>- Adjusts bet amounts based on the set count<br>- The strategy aims to optimize red bet outcomes in the game.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/strategy/MonteCarloStrategy.java'>MonteCarloStrategy.java</a></b></td>
										<td>- Implements a Monte Carlo strategy for betting on the first dozen in a roulette game<br>- Adjusts bet amounts based on previous outcomes to optimize winning potential.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/strategy/Rate_99_46_PercentStrategy.java'>Rate_99_46_PercentStrategy.java</a></b></td>
										<td>- Implements a betting strategy based on historical outcomes, targeting specific patterns to place bets on the second and third dozens<br>- The strategy aims to optimize betting decisions by leveraging past data to increase the chances of winning.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/strategy/StraightUpStrategy.java'>StraightUpStrategy.java</a></b></td>
										<td>- Implements a strategy for single-point bets using a predictor<br>- Determines the most probable outcome based on predictions and places a bet accordingly<br>- The strategy leverages a specific predictor to make informed betting decisions, enhancing the overall betting approach within the project architecture.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/strategy/RandomStrategy2.java'>RandomStrategy2.java</a></b></td>
										<td>- Generates a list of bets based on a random strategy, considering the previous winning bet<br>- The strategy creates a list of bets by copying the previous winning bet and adding random bets until reaching a specified size<br>- The file contributes to the project's architecture by providing a flexible and dynamic betting strategy for the roulette game.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/strategy/MartingaleStrategy3.java'>MartingaleStrategy3.java</a></b></td>
										<td>- Implements Martingale betting strategy using a predictor<br>- Determines next bet based on color prediction and previous outcomes<br>- Adjusts bet amount based on win history<br>- Designed to work within the broader roulette betting application architecture.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/strategy/GrandMartingaleStrategy.java'>GrandMartingaleStrategy.java</a></b></td>
										<td>- Implements Grand Martingale strategy for red bets in a roulette game<br>- Determines next bet based on previous outcomes: places minimum bet if last bet won, otherwise doubles last bet amount plus minimum bet<br>- Enhances gameplay strategy by dynamically adjusting betting amounts.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/strategy/EastCoastProgressionStrategy.java'>EastCoastProgressionStrategy.java</a></b></td>
										<td>- Implements East Coast Progression Strategy for red bets in the roulette game<br>- Adjusts bet amount based on previous outcomes to optimize balance.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/strategy/ReverseMartingaleRedStrategy.java'>ReverseMartingaleRedStrategy.java</a></b></td>
										<td>- Implements a reverse Martingale strategy for betting exclusively on red in a roulette game<br>- Determines the next bet amount based on previous outcomes, aiming to maximize winnings<br>- Inherits from a base strategy class and utilizes a specific bet type<br>- Designed to enhance the project's betting strategies within the overall architecture.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/strategy/NeighborStrategy.java'>NeighborStrategy.java</a></b></td>
										<td>- Implements a Neighbor Betting Strategy using a predictor for European-style roulette<br>- Calculates probabilities for different areas based on predictions and places bets on spots with the highest probability.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/strategy/DalembertStrategy.java'>DalembertStrategy.java</a></b></td>
										<td>- Implements a betting strategy based on the Dalembert method for red bets in roulette<br>- Adjusts bet size based on previous outcomes to manage risk and potential gains.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/strategy/FlowerBetStrategy.java'>FlowerBetStrategy.java</a></b></td>
										<td>- Implements Flower Bet Strategy using a predictor to determine high-probability outcomes<br>- Selects bet types based on predictions for the next spin.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/strategy/MartingaleStrategy4.java'>MartingaleStrategy4.java</a></b></td>
										<td>- Implements Martingale betting strategy for 1st and 2nd dozens in a roulette game<br>- Determines bet amounts based on previous outcomes, aiming to recover losses and make a profit<br>- The strategy is encapsulated within the MartingaleStrategy4 class, providing a structured approach to betting decisions within the project's architecture.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/strategy/Rate_98_48_PercentStrategy.java'>Rate_98_48_PercentStrategy.java</a></b></td>
										<td>- Implements a betting strategy based on historical outcomes, aiming to increase bets on specific conditions<br>- Utilizes past spot history to determine bet amounts and types for future rounds.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/strategy/CocomoStrategy.java'>CocomoStrategy.java</a></b></td>
										<td>- Implements a betting strategy based on the Cocomo method for the first dozen in roulette<br>- Determines the next bet amount based on previous outcomes, aiming to optimize winnings<br>- The strategy is encapsulated within the CocomoStrategy class, providing a structured approach to betting decisions in the application.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/strategy/MartingaleStrategy5.java'>MartingaleStrategy5.java</a></b></td>
										<td>- Implements a Martingale betting strategy for specific bet types in a roulette game<br>- Determines bet amounts based on previous outcomes to optimize potential wins.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/strategy/BaseStrategy.java'>BaseStrategy.java</a></b></td>
										<td>- Defines a base class for strategies in the project, managing key metrics like balance, bets, and wins<br>- It calculates and updates betting information, tracks performance metrics, and provides methods for sorting strategies based on balance and name.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/strategy/TripleSixStrategy.java'>TripleSixStrategy.java</a></b></td>
										<td>- Implements a betting strategy based on the '666法' system, this code file defines the TripleSixStrategy class<br>- It calculates and returns a list of bets for various bet types in a roulette game, each with specific bet amounts based on the provided context<br>- The strategy aims to optimize betting choices following the '666法' approach.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/strategy/StraightUpStrategy2.java'>StraightUpStrategy2.java</a></b></td>
										<td>- Implements a strategy for multiple straight bets using a predictor<br>- Determines bet amounts based on predictions and current balance<br>- Selects bet types and values based on spot predictions' probabilities.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/strategy/RandomStrategy.java'>RandomStrategy.java</a></b></td>
										<td>- Generates random betting strategies based on available bet types and multipliers<br>- Utilizes a random number generator to create a list of bets with varying amounts<br>- The strategy is designed to be unpredictable and diverse in its betting choices within the given context.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/strategy/MansuriansStrategy.java'>MansuriansStrategy.java</a></b></td>
										<td>- Implements a strategy based on the Mansurians method for the roulette game<br>- Extends the BaseStrategy class and provides a method to retrieve the strategy name<br>- The code file defines the logic for generating the next bet list within the roulette context.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/strategy/GoodmanStrategy.java'>GoodmanStrategy.java</a></b></td>
										<td>- Implements Goodman's Red-Only strategy for roulette<br>- Determines bet amounts based on previous outcomes, escalating after losses<br>- Resets after a win<br>- Designed to optimize betting strategy for red outcomes.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/strategy/MartingaleStrategy.java'>MartingaleStrategy.java</a></b></td>
										<td>- Implements Martingale betting strategy for red bets in a roulette game<br>- Determines next bet based on previous outcomes: minimum bet if last bet won, double last bet if lost<br>- Enhances gameplay dynamics by adjusting betting strategy dynamically.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/strategy/StraightUpStrategy3.java'>StraightUpStrategy3.java</a></b></td>
										<td>- Implements a strategy for multiple straight bets based on recent outcomes<br>- Determines bets on spots not recently appearing, adjusting based on consecutive wins<br>- Returns a list of bets for each available spot.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/strategy/TenPercentStrategy.java'>TenPercentStrategy.java</a></b></td>
										<td>- Implements a betting strategy based on the 10% rule for red bets in roulette<br>- Determines the bet amount as 10% of the current balance, ensuring a minimum bet of 1 unit<br>- The strategy aims to optimize betting decisions for red outcomes, enhancing the overall gameplay experience.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/strategy/BarnetStrategy.java'>BarnetStrategy.java</a></b></td>
										<td>- Implements a betting strategy based on the Barnet method for red bets only<br>- Adjusts bet amounts based on a set pattern, resetting after losses<br>- Provides a structured approach to managing bets in the roulette game.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/src/main/java/strategy/SevenKaimeStrategy.java'>SevenKaimeStrategy.java</a></b></td>
										<td>- Implements a strategy based on the "7th time law" for betting on red in roulette<br>- Determines if the last 7 spins resulted in black, then places a red bet if true<br>- This class extends the BaseStrategy and is part of the strategy package in the project structure.</td>
									</tr>
									</table>
								</blockquote>
							</details>
						</blockquote>
					</details>
				</blockquote>
			</details>
		</blockquote>
	</details>
	<details> <!-- .github Submodule -->
		<summary><b>.github</b></summary>
		<blockquote>
			<table>
			<tr>
				<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/.github/dependabot.yml'>dependabot.yml</a></b></td>
				<td>- Automates daily dependency updates for Maven and GitHub Actions, limiting open pull requests to 10<br>- Scheduled to run at midnight in Asia/Tokyo timezone<br>- This configuration file ensures timely updates and maintains a streamlined development process by managing dependencies efficiently.</td>
			</tr>
			</table>
			<details>
				<summary><b>workflows</b></summary>
				<blockquote>
					<table>
					<tr>
						<td><b><a href='https://github.com/cyrus07424/rouletteSimulator/blob/master/.github/workflows/maven.yml'>maven.yml</a></b></td>
						<td>- Automates Java project builds using Maven with JDK 8 setup, caching, and GitHub Actions<br>- Implements CI/CD workflows for building and testing Java projects on GitHub, ensuring code quality and automating label addition for pull requests by dependabot.</td>
					</tr>
					</table>
				</blockquote>
			</details>
		</blockquote>
	</details>
</details>

---
##  Getting Started

###  Prerequisites

Before getting started with rouletteSimulator, ensure your runtime environment meets the following requirements:

- **Programming Language:** Java


###  Installation

Install rouletteSimulator using one of the following methods:

**Build from source:**

1. Clone the rouletteSimulator repository:
```sh
❯ git clone https://github.com/cyrus07424/rouletteSimulator
```

2. Navigate to the project directory:
```sh
❯ cd rouletteSimulator
```

3. Install the project dependencies:

echo 'INSERT-INSTALL-COMMAND-HERE'



###  Usage
Run rouletteSimulator using the following command:
echo 'INSERT-RUN-COMMAND-HERE'

###  Testing
Run the test suite using the following command:
echo 'INSERT-TEST-COMMAND-HERE'

---
##  Project Roadmap

- [X] **`Task 1`**: <strike>Implement feature one.</strike>
- [ ] **`Task 2`**: Implement feature two.
- [ ] **`Task 3`**: Implement feature three.

---

##  Contributing

- **💬 [Join the Discussions](https://github.com/cyrus07424/rouletteSimulator/discussions)**: Share your insights, provide feedback, or ask questions.
- **🐛 [Report Issues](https://github.com/cyrus07424/rouletteSimulator/issues)**: Submit bugs found or log feature requests for the `rouletteSimulator` project.
- **💡 [Submit Pull Requests](https://github.com/cyrus07424/rouletteSimulator/blob/main/CONTRIBUTING.md)**: Review open PRs, and submit your own PRs.

<details closed>
<summary>Contributing Guidelines</summary>

1. **Fork the Repository**: Start by forking the project repository to your github account.
2. **Clone Locally**: Clone the forked repository to your local machine using a git client.
   ```sh
   git clone https://github.com/cyrus07424/rouletteSimulator
   ```
3. **Create a New Branch**: Always work on a new branch, giving it a descriptive name.
   ```sh
   git checkout -b new-feature-x
   ```
4. **Make Your Changes**: Develop and test your changes locally.
5. **Commit Your Changes**: Commit with a clear message describing your updates.
   ```sh
   git commit -m 'Implemented new feature x.'
   ```
6. **Push to github**: Push the changes to your forked repository.
   ```sh
   git push origin new-feature-x
   ```
7. **Submit a Pull Request**: Create a PR against the original project repository. Clearly describe the changes and their motivations.
8. **Review**: Once your PR is reviewed and approved, it will be merged into the main branch. Congratulations on your contribution!
</details>

<details closed>
<summary>Contributor Graph</summary>
<br>
<p align="left">
   <a href="https://github.com{/cyrus07424/rouletteSimulator/}graphs/contributors">
      <img src="https://contrib.rocks/image?repo=cyrus07424/rouletteSimulator">
   </a>
</p>
</details>

---

##  License

This project is protected under the [MIT License](https://choosealicense.com/licenses/mit/) License. For more details, refer to the [LICENSE](https://github.com/cyrus07424/fileUtils/blob/master/LICENSE) file.

---

##  Acknowledgments

- List any resources, contributors, inspiration, etc. here.

---
