package com.uni;

import com.uni.exception.ValidationException;
import com.uni.model.IntersectionResult;
import java.io.InputStream;
import java.util.Scanner;

@SuppressWarnings("java:S106")
public class App {
    private static final int MIN_VALUE = -115;
    private static final int MAX_VALUE = 115;
    private static final String INPUT_FILE = "/input.txt";

    private final InputValidator validator;
    private final LineIntersectionCalculator calculator;
    private final Scanner scanner;
    private final boolean isReadingFromFile;

    public App(InputValidator validator, LineIntersectionCalculator calculator, Scanner scanner, boolean isReadingFromFile) {
        this.validator = validator;
        this.calculator = calculator;
        this.scanner = scanner;
        this.isReadingFromFile = isReadingFromFile;
    }

    public static void main(String[] args) {
        InputStream inputStream = App.class.getResourceAsStream(INPUT_FILE);
        boolean fromFile = inputStream != null;
        Scanner sc = fromFile ? new Scanner(inputStream) : new Scanner(System.in);

        InputValidator val = new InputValidator(MIN_VALUE, MAX_VALUE);
        LineIntersectionCalculator calc = new LineIntersectionCalculator();

        if (fromFile) System.out.println("=== Читання з файлу " + INPUT_FILE + " ===\n");

        App app = new App(val, calc, sc, fromFile);
        app.run();
    }

    public void run() {
        try {
            System.out.println("=== Аналіз взаємного розміщення прямих ===\n");

            // Пряма 1 (Канонічна)
            if (!isReadingFromFile) System.out.println("Пряма 1 (канонічна)");
            double x01 = readValue("x01", false);
            double y01 = readValue("y01", false);
            double l1 = readValue("l1", true);
            double m1 = readValue("m1", true);

            // Пряма 2 (Канонічна)
            if (!isReadingFromFile) System.out.println("\nПряма 2 (канонічна)");
            double x02 = readValue("x02", false);
            double y02 = readValue("y02", false);
            double l2 = readValue("l2", true);
            double m2 = readValue("m2", true);

            // Пряма 3 (Кутовий коефіцієнт)
            if (!isReadingFromFile) System.out.println("\nПряма 3 (y = kx + b)");
            double k = readValue("k", false);
            double b = readValue("b", true);

            IntersectionResult result = calculator.calculate(x01, y01, l1, m1, x02, y02, l2, m2, k, b);

            System.out.println("\n=== Результат ===");
            System.out.println(result.getMessage());

        } catch (ValidationException e) {
            System.err.println("\n!!! ПОМИЛКА: " + e.getMessage());
            System.err.println("Рекомендація: " + e.getRecommendation());
        } finally {
            scanner.close();
        }
    }

    private double readValue(String paramName, boolean mustBeNonZero) throws ValidationException {
        if (!isReadingFromFile) System.out.print("Введіть " + paramName + ": ");

        while (scanner.hasNext() && !scanner.hasNextDouble()) {
            String line = scanner.next();
            if (line.startsWith("#")) { scanner.nextLine(); continue; }
            throw new ValidationException("Некоректне значення: " + line, "Введіть число");
        }

        if (!scanner.hasNextDouble()) throw new ValidationException("Відсутні дані", "Перевірте вхідний потік");

        double val = scanner.nextDouble();
        validator.validateRange(val, paramName);
        if (mustBeNonZero) validator.validateNonZero(val, paramName);
        return val;
    }
}
