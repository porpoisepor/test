package com.example.fonorio.gameoflife;

import org.junit.Test;

import static org.junit.Assert.*;

public class CellularAutomataTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void firstCellIsOne() throws Exception{
        CellularAutomata cellularAutomaton = new CellularAutomata(5,5);
        assertEquals(new Integer(1), cellularAutomaton.getCell(0,0));
    }

    @Test
    public void sizeIsRowsTimesColumns() throws Exception {
        CellularAutomata cellularAutomaton = new CellularAutomata(5,5);
        assertEquals(25, cellularAutomaton.getSize());
    }

    @Test
    public void someValueIsOne() throws Exception {
        CellularAutomata cellularAutomaton = new CellularAutomata(5,5);
        assertEquals(new Integer(1), cellularAutomaton.getCell(2,2));
    }
    @Test
    public void someValueCanBeChanged() throws Exception {
        CellularAutomata cellularAutomaton = new CellularAutomata(5,5);
        cellularAutomaton.setCell(2,2,0);
        assertEquals(new Integer(0), cellularAutomaton.getCell(2,2));
    }

    @Test
    public void charOfSomeStringRepresentationValueIsOne() throws Exception {
        CellularAutomata cellularAutomaton = new CellularAutomata(5,5);
        char cellAsChar = cellularAutomaton.getCellAsChar(2,2);
        assertEquals('O', cellAsChar);
    }
    @Test
    public void charOfSomeStringRepresentationValueDoesNotChangeBeforeUpdate() throws Exception {
        CellularAutomata cellularAutomaton = new CellularAutomata(5,5);
        cellularAutomaton.setCell(2,2,0);
//        cellularAutomaton.updateRepresentation();
        char cellAsChar = cellularAutomaton.getCellAsChar(2,2);
        assertEquals('O', cellAsChar);
    }
    @Test
    public void charOfSomeStringRepresentationValueCanBeChanged() throws Exception {
        CellularAutomata cellularAutomaton = new CellularAutomata(5,5);
        cellularAutomaton.setCell(2,2,0);
        cellularAutomaton.updateRepresentation();
        char cellAsChar = cellularAutomaton.getCellAsChar(2,2);
        assertEquals('.', cellAsChar);
    }
    @Test
    public void primitiveArrayRepresentationWorks() throws Exception {
        CellularAutomata cellularAutomaton = new CellularAutomata(5,5);
        int[] representation = cellularAutomaton.getPrimitiveArrayRepresentation();
        int[] expected = {
                1,1,1,1,1,
                1,1,1,1,1,
                1,1,1,1,1,
                1,1,1,1,1,
                1,1,1,1,1,
        };
        assertArrayEquals(representation, expected);
    }
    @Test
    public void setPrimitiveArrayWorks() throws Exception {
        CellularAutomata cellularAutomaton = new CellularAutomata(5,5);
        int[] newPattern = {
                1,1,1,1,1,
                1,0,1,0,1,
                1,1,1,1,1,
                1,0,1,0,1,
                1,1,1,1,1,
        };
        cellularAutomaton.setCells(newPattern);
        int[] representation = cellularAutomaton.getPrimitiveArrayRepresentation();
        assertArrayEquals(newPattern, representation);
    }

    @Test
    public void firstUpdateWorks() throws Exception {
        CellularAutomata cellularAutomaton = new CellularAutomata(5,5);
        int[] firstGen = {
                1,1,1,1,1,
                1,1,1,1,1,
                1,1,1,1,1,
                1,1,1,1,1,
                1,1,1,1,1,
        };
        int[] secondGen = {
                0,0,0,0,0,
                0,0,0,0,0,
                0,0,0,0,0,
                0,0,0,0,0,
                0,0,0,0,0,
        };
        cellularAutomaton.setCells(firstGen);
        cellularAutomaton.updateCurrentGen();
        assertArrayEquals(secondGen, cellularAutomaton.getPrimitiveArrayRepresentation());
    }
    @Test
    public void randomPatternWorks1() throws Exception {
        CellularAutomata cellularAutomaton = new CellularAutomata(5,5);
        int[] firstGen = {
                0,0,0,0,0,
                0,1,1,0,0,
                0,0,1,1,0,
                0,0,1,0,0,
                0,0,0,0,0,
        };
        int[] secondGen = {
                0,0,0,0,0,
                0,1,1,1,0,
                0,0,0,1,0,
                0,0,1,1,0,
                0,0,0,0,0,
        };
        cellularAutomaton.setCells(firstGen);
        cellularAutomaton.updateCurrentGen();
        int[] currentGen = cellularAutomaton.getPrimitiveArrayRepresentation();
//        String stringRepresentation = cellularAutomaton.getStringRepresentation();
        assertArrayEquals(secondGen, currentGen);
    }
    @Test
    public void randomPatternWorks2() throws Exception {
        CellularAutomata cellularAutomaton = new CellularAutomata(5,5);
        int[] firstGen = {
                0,0,0,0,0,
                0,0,0,0,0,
                0,1,1,1,0,
                0,0,0,0,0,
                0,0,0,0,0,
        };
        int[] secondGen = {
                0,0,0,0,0,
                0,0,1,0,0,
                0,0,1,0,0,
                0,0,1,0,0,
                0,0,0,0,0,
        };
        cellularAutomaton.setCells(firstGen);
        cellularAutomaton.updateCurrentGen();
//        String stringRepresentation = cellularAutomaton.getStringRepresentation();
        int[] currentGen = cellularAutomaton.getPrimitiveArrayRepresentation();
        assertArrayEquals(secondGen, currentGen);
    }

    @Test
    public void stringRepresentationLengthIsCorrect() throws Exception {
        CellularAutomata cellularAutomaton = new CellularAutomata(5,5);
        int length = cellularAutomaton.getStringRepresentation().length();
        assertEquals((5)*(5+1), length);
    }
    @Test//
    public void stringRepresentationLengthStaysCorrectAfterUpdate() throws Exception {
        CellularAutomata cellularAutomaton = new CellularAutomata(5,5);
        cellularAutomaton.updateCurrentGen();
        int length = cellularAutomaton.getStringRepresentation().length();

        assertEquals((5)*(5+1), length);
    }
}