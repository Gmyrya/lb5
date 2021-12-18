package main.com.company.lab_rmi;

import java.util.*;

public class Magazine {
    public static Map<String, List<String>> grammatical = new HashMap<>();
    public static StringBuilder stack = new StringBuilder();
    public static String inputLine = "pagg";

    public static void main(String[] args) {
        initializeGrammatical();
        while (inputLine.length() > 0) {
            String symbol = String.valueOf(inputLine.charAt(0));
            if (inputLine.length() == 1) {
                checkGrammaticalByKey(symbol);
                inputLine = inputLine.substring(1);
                System.out.println("Input line - " + inputLine + ", magazine - " + stack.toString());
            } else {
                inputLine = inputLine.substring(1);
                stack.append(symbol);
                System.out.println("Input line - " + inputLine + ", magazine - " + stack.toString());
                checkGrammaticalByValue();
            }
        }
    }

    private static void checkGrammaticalByKey(String lastSymbol) {
        for (Map.Entry<String, List<String>> stringListEntry : grammatical.entrySet()) {
            for (String g : stringListEntry.getValue()) {
                if (g.equals(lastSymbol)) {
                    stack = new StringBuilder("");
                    System.out.println("Input line - " + inputLine + ", magazine - " + stringListEntry.getKey());
                    System.out.println("Input line - " + inputLine + ", magazine - " + g);
                }
            }
        }
    }

    private static void checkGrammaticalByValue() {
        boolean isChanged = false;
        isChanged = checkStack(isChanged);
        if (!isChanged) {
            String lastSymbol = stack.substring(stack.length() - 1);
            for (Map.Entry<String, List<String>> stringListEntry : grammatical.entrySet()) {
                for (String g : stringListEntry.getValue()) {
                    if (g.equals(lastSymbol)) {
                        stack = new StringBuilder(stack.substring(0, stack.length() - 1) + stringListEntry.getKey());
                        System.out.println("Input line - " + inputLine + ", magazine - " + stack.toString());
                        isChanged = true;
                    }
                }
            }
        }
        if (isChanged) {
            checkGrammaticalByValue();
        }
    }

    private static boolean checkStack(boolean isChanged) {
        for (Map.Entry<String, List<String>> stringListEntry : grammatical.entrySet()) {
            for (String g : stringListEntry.getValue()) {
                if (g.equals(stack.toString())) {
                    stack = new StringBuilder(stringListEntry.getKey());
                    System.out.println("Input line - " + inputLine + ", magazine - " + stack.toString());
                    isChanged = true;
                }
            }
        }
        return isChanged;
    }

    private static void initializeGrammatical() {

        grammatical.put("S", Arrays.asList("R", "T"));
        grammatical.put("R", Arrays.asList("pX", "paR", "paT", "E"));
        grammatical.put("T", Arrays.asList("Tg", "g"));
        grammatical.put("X", List.of("aXb"));
        grammatical.put("Y", Arrays.asList("aYa", "y"));

    }
}
