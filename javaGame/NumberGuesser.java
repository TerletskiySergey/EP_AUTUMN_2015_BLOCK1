package block1.javaGame;

import block1.javaGame.verifyers.GameVerifier;
import block1.javaGame.verifyers.Verifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NumberGuesser {
    private static final int MIN_BORDER = 0;
    private static final int MAX_BORDER = 100;

    private int falseCounter;
    private int minVal;
    private int maxVal;
    private Scanner sc;
    private int toGuess;
    private List<Integer> userAttempts;
    private Verifier ver;

    public NumberGuesser(int minVal, int maxVal) {
        this.falseCounter = 0;
        this.minVal = (minVal > maxVal) ? MIN_BORDER : minVal;
        this.maxVal = (minVal > maxVal) ? MAX_BORDER : maxVal;
        this.sc = new Scanner(System.in);
        this.toGuess = thinkNumber(minVal, maxVal);
        this.userAttempts = new ArrayList<>();
        this.ver = new GameVerifier();
    }

    public void start() {
        clearScreen();
        do {
            askUserNumber();
            clearScreen();
            respondUser();
        } while (userAttempts.get(userAttempts.size() - 1) != toGuess);
        showSummary();
    }

    private void askUserNumber() {
        String prompt = String.format("Enter integer value in the range [%s..%s]:", minVal, maxVal);
        System.out.print(prompt);
        String userInput = sc.nextLine();
        while (!ver.check(userInput, minVal, maxVal)) {
            falseCounter++;
            System.out.println("Incorrect input.");
            System.out.print(prompt);
            userInput = sc.nextLine();
        }
        this.userAttempts.add(Integer.parseInt(userInput));
    }

    private void clearScreen() {
        for (int i = 0; i < 25; i++) {
            System.out.println("\n");
        }
    }

    private void respondUser() {
        showUserAttempts();
        int lastTry = userAttempts.get(userAttempts.size() - 1);
        if (toGuess > lastTry) {
            System.out.printf("Recently entered number %d is too small\n", lastTry);
            minVal = lastTry + 1;
            return;
        }
        if (toGuess < lastTry) {
            System.out.printf("Recently entered number %d is too big\n", lastTry);
            maxVal = lastTry - 1;
            return;
        }
        System.out.println("You've guessed!");
    }

    private void showSummary() {
        System.out.println("\nSUMMARY:");
        System.out.printf("GUESSED NUMBER: %d\n", toGuess);
        System.out.printf("TOTAL ATTEMPTS QUANTITY: %d\n", userAttempts.size() + falseCounter);
        System.out.printf("INCORRECT ENTRIES QUANTITY: %d\n", falseCounter);
    }

    private void showUserAttempts() {
        if (userAttempts.isEmpty()) {
            return;
        }
        System.out.print("Previous attempts: ");
        for (int i = 0; i < userAttempts.size(); i++) {
            System.out.print(userAttempts.get(i));
            if (i != userAttempts.size() - 1) {
                System.out.print(", ");
            } else {
                System.out.println(".");
            }
        }
    }

    private int thinkNumber(int min, int max) {
        return MathUtils.rand(min, max);
    }

    public static void main(String[] args) {
        new NumberGuesser(0, 100).start();
    }
}