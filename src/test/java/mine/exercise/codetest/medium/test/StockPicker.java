package mine.exercise.codetest.medium.test;
public class StockPicker {

	public static int stockPicker(int[] prices) {
		int numberOfTxnPerDay = 1;
		int numberOfStockPrices = prices.length;
		int[][] profits = new int[numberOfTxnPerDay + 1][numberOfStockPrices + 1];

		for (int i = 0; i <= numberOfTxnPerDay; i++)
			profits[i][0] = 0;

		for (int j = 0; j <= numberOfStockPrices; j++)
			profits[0][j] = 0;

		for (int i = 1; i <= numberOfTxnPerDay; i++) { // Iterate over txns per day, start from second txn
			for (int j = 1; j < numberOfStockPrices; j++) { // Iterate over the prices, start from second day
				int maxProfit = -1;

				for (int m = 0; m < j; m++)
					maxProfit = Math.max(maxProfit, prices[j] - prices[m] + profits[i - 1][m]);

				profits[i][j] = Math.max(profits[i][j - 1], maxProfit);
			}
		}
		return profits[numberOfTxnPerDay][numberOfStockPrices - 1] == 0 ? -1
				: profits[numberOfTxnPerDay][numberOfStockPrices - 1];
	}

	public static void main(String[] args) {
		// keep this function call here
		// Scanner s = new Scanner(System.in);
		System.out.print(stockPicker(new int[] { 44, 30, 24, 32, 35, 30, 40, 38, 15 }));
	}

}
