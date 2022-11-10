package lotto;

import java.util.Arrays;
import java.util.List;

public class Checker {
	private static final int MAX_PRICE = 100000;
	private static final int MAX_WINNING_NUMBER_LENGTH = 6;
	private static final int MAX_BONUS_NUMBER_LENGTH = 1;
	private static final int MAX_NUMBER = 45;
	private static final int MIN_NUMBER = 1;
	private static final String WINNING_NUMBER_DELIMITER = ",";
	private static final String JOIN_DELIMITER = "";
	private static int FIRST;
	private static int SECOND;
	private static int THIRD;
	private static int FOURTH;
	private static int FIFTH;
	private static List<String> winningNumber;

	public static void checkUserInput(String userInput) {
		//숫자, ','만 입력 받아야함
		//6자리 이상이면 안돼
		checkNumberOnlyException(userInput);
		checkMaxPrice(userInput);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	public static void checkWinningNumberInput(String userInput) {
		winningNumber = Arrays.asList(userInput.split(WINNING_NUMBER_DELIMITER));
		userInput = String.join(JOIN_DELIMITER, userInput.split(WINNING_NUMBER_DELIMITER));

		checkNumberOnlyException(userInput);
		checkLengthException(winningNumber, MAX_WINNING_NUMBER_LENGTH);
		checkSameNumberException(winningNumber);
		checkNumberRangeException(winningNumber);
	}

	public static void checkBonusNumberInput(String userInput) {
		List<String> bonusNumber = Arrays.asList(userInput.split(""));

		checkNumberOnlyException(userInput);
		checkLengthException(bonusNumber, MAX_BONUS_NUMBER_LENGTH);
		checkSameNumberInWinningNumberException(userInput);
		checkNumberRangeException(bonusNumber);
	}

	private static void checkSameNumberInWinningNumberException(String bonusNumber) {
		if(winningNumber.contains(bonusNumber)){
			Exception.sameNumberException();
		}
	}

	public static void checkNumberRangeException(List<String> numbers) {
		for (String number : numbers) {
			if (Integer.parseInt(number) > MAX_NUMBER || Integer.parseInt(number) < MIN_NUMBER) {
				Exception.numberRangeException();
			}
		}
	}

	private static boolean isSameNumber(List<String> winningNumber) {
		boolean isSameNumberExist = winningNumber.stream()
			.distinct()
			.count() != winningNumber.size();

		return isSameNumberExist;
	}

	private static void checkSameNumberException(List<String> winningNumber) {

		if (isSameNumber(winningNumber)) {
			Exception.sameNumberException();
		}

	}

	public static void checkLengthException(List<String> winningNumber, int maxLength) {
		if (winningNumber.size() > maxLength) {
			Exception.winningNumberLengthException();
		}
	}

	private static void checkMaxPrice(String userInput) {
		if (isExceedMaxPrice(userInput)) {
			Exception.maxPriceException();
		}
	}

	private static boolean isExceedMaxPrice(String userInput) {
		return Integer.parseInt(userInput) > MAX_PRICE;
	}

	private static void checkNumberOnlyException(String userInput) {
		try {
			Integer.parseInt(userInput);
		} catch (IllegalArgumentException e) {
			Exception.numberOnlyException();
		}
	}

	public void compareNumbers() {

	}
}
