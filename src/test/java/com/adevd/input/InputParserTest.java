package com.adevd.input;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputParserTest {

    InputParser ip;
    @BeforeEach
    void setUp() {
         ip = new InputParser();
    }

    @Test
    void parse_ValidInputsWKUP_ShouldReturnForward() {
        String[] input = {"w", "k", "up"};
        InputParser.ValidInput[] instructions = ip.parse(input);
        for (var instruction: instructions) {
            assertEquals(InputParser.ValidInput.FORWARD, instruction);
        }
    }

    @Test
    void parse_ValidInputsCaseInsensitive_ShouldReturnForward() {
        String[] input = {"W", "K", "UP"};
        InputParser.ValidInput[] instructions = ip.parse(input);
        for (var instruction: instructions) {
            assertEquals(InputParser.ValidInput.FORWARD, instruction);
        }
    }

    @Test
    void parse_ValidInputsAHLEFT_ShouldReturnLeft() {
        String[] input = {"a", "h", "left"};
        InputParser.ValidInput[] instructions = ip.parse(input);
        for (var instruction: instructions) {
            assertEquals(InputParser.ValidInput.LEFT, instruction);
        }
    }

    @Test
    void parse_ValidInputsDLRIGHT_ShouldReturnRight() {
        String[] input = {"d", "l", "right"};
        InputParser.ValidInput[] instructions = ip.parse(input);
        for (var instruction: instructions) {
            assertEquals(InputParser.ValidInput.RIGHT, instruction);
        }
    }

    @Test
    void parse_ValidInputsESPACEBARENTER_ShouldReturnInteract() {
        String[] input = {"e", "spacebar", "enter"};
        InputParser.ValidInput[] instructions = ip.parse(input);
        for (var instruction : instructions) {
            assertEquals(InputParser.ValidInput.INTERACT, instruction);
        }
    }

    @Test
    void parse_InvalidInputs_ShouldReturnNone() {
        String[] input = {"v", "turkey", "unrestricted", "rotten input"};
        InputParser.ValidInput[] instructions = ip.parse(input);
        for (var instruction : instructions) {
            assertEquals(InputParser.ValidInput.NONE, instruction);
        }
    }

    @Test
    void parse_ValidAndInvalidInputs_ShouldMaintainInputOrder() {
        InputParser.ValidInput[] expected = {InputParser.ValidInput.FORWARD, InputParser.ValidInput.RIGHT, InputParser.ValidInput.NONE, InputParser.ValidInput.LEFT, InputParser.ValidInput.INTERACT};
        String[] input = {"w", "l", "rotten borough", "left","sPaCeBAR"};
        InputParser.ValidInput[] instructions = ip.parse(input);
        assertArrayEquals(expected, instructions);
    }

    @Test
    void parse_ExcessiveInputInstructions_ShouldIgnoreSurplus(){
        String[] input = {"w","w","w","w","w","w","w","d","d","pointless instruction"};
        InputParser.ValidInput[] instructions = ip.parse(input);
        assertEquals(ip.getMaxInputLength(), instructions.length);
        for (var instruction: instructions) {
            assertEquals(InputParser.ValidInput.FORWARD, instruction);
        }
    }

    @Test
    void parse_EmptyInput_ShouldReturnEmptyArray() {
        String[] input = {};
        InputParser.ValidInput[] instructions = ip.parse(input);
        assertEquals(0, instructions.length);
    }

    @Test
    void parse_NullInput_ShouldReturnEmptyArray() {
        String[] input = null;
        InputParser.ValidInput[] instructions = ip.parse(input);
        assertEquals(0, instructions.length);
    }
}