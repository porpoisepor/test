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
        assertEquals(cellularAutomaton.getCell(0,0), new Integer(1));
    }

    @Test
    public void sizeIsRowsTimesColumns() throws Exception {
        CellularAutomata cellularAutomaton = new CellularAutomata(5,5);
        assertEquals(cellularAutomaton.getSize(), 25);
    }

    @Test
    public void someValueIsOne() throws Exception {
        CellularAutomata cellularAutomaton = new CellularAutomata(5,5);
        assertEquals(cellularAutomaton.getCell(2,2), new Integer(1));
    }
    @Test
    public void someValueCanBeChanged() throws Exception {
        CellularAutomata cellularAutomaton = new CellularAutomata(5,5);
        cellularAutomaton.setCell(2,2,0);
        assertEquals(cellularAutomaton.getCell(2,2), new Integer(0));
    }

    @Test
    public void charOfSomeStringRepresentationValueIsOne() throws Exception {
        CellularAutomata cellularAutomaton = new CellularAutomata(5,5);
        char cellAsChar = cellularAutomaton.getCellAsChar(2,2);
        assertEquals(cellAsChar, '1');
    }
    @Test
    public void charOfSomeStringRepresentationValueDoesNotChangeBeforeUpdate() throws Exception {
        CellularAutomata cellularAutomaton = new CellularAutomata(5,5);
        cellularAutomaton.setCell(2,2,0);
//        cellularAutomaton.updateRepresentation();
        char cellAsChar = cellularAutomaton.getCellAsChar(2,2);
        assertEquals(cellAsChar, '1');
    }
    @Test
    public void charOfSomeStringRepresentationValueCanBeChanged() throws Exception {
        CellularAutomata cellularAutomaton = new CellularAutomata(5,5);
        cellularAutomaton.setCell(2,2,0);
        cellularAutomaton.updateRepresentation();
        char cellAsChar = cellularAutomaton.getCellAsChar(2,2);
        assertEquals(cellAsChar, '0');
    }
}