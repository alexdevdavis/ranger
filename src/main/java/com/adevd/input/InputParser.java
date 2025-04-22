package com.adevd.input;

import java.util.HashMap;

public class InputParser {
    HashMap<String, ValidInput> inputMap = new HashMap<>();

    public Integer getMaxInputLength() {
        return MAX_INPUT_LENGTH;
    }

    final Integer MAX_INPUT_LENGTH = 7;

    public InputParser() {
        inputMap.put("w", ValidInput.FORWARD);
        inputMap.put("a", ValidInput.LEFT);
        inputMap.put("d", ValidInput.RIGHT);
        inputMap.put("e", ValidInput.INTERACT);

        inputMap.put("k", ValidInput.FORWARD);
        inputMap.put("h", ValidInput.LEFT);
        inputMap.put("l", ValidInput.RIGHT);
        inputMap.put("spacebar", ValidInput.INTERACT);

        inputMap.put("up", ValidInput.FORWARD);
        inputMap.put("left", ValidInput.LEFT);
        inputMap.put("right", ValidInput.RIGHT);
        inputMap.put("enter", ValidInput.INTERACT);
    }
    public enum ValidInput {
        FORWARD,
        LEFT,
        RIGHT,
        INTERACT,
        NONE
    }
    public ValidInput[] parse(String[] input) {
        if (input == null) {
            return new ValidInput[0];
        }
        int inputLength = Math.min(input.length, MAX_INPUT_LENGTH);
        ValidInput[] validatedInstructions = new ValidInput[inputLength];
        for (int i = 0; i < inputLength; i++) {
           var instruction = inputMap.getOrDefault(input[i].toLowerCase(), ValidInput.NONE);
           validatedInstructions[i] = instruction;
        }
        return validatedInstructions;
    }
}
