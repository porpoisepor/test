package com.example.fonorio.gameoflife;
import java.util.ArrayList;

public class CellularAutomata {
    private ArrayList<Integer> cells_;
    private ArrayList<Integer> nextGenBufferedCells_;
    private StringBuilder representation_;
    private int height_;
    private int width_;

    CellularAutomata(int height, int width){
        setHeight(height);
        setWidth(width);
        setRepresentation(new StringBuilder(getSize() + getHeight()));
        setCells( new ArrayList<Integer>(height * width));
        setNextGenBufferedCells( new ArrayList<Integer>(height * width));
        for(int elementNumber = 0; elementNumber < getSize(); ++elementNumber){
            cells_.add(elementNumber, 1);
            nextGenBufferedCells_.add(elementNumber, 1);
        }
        setupRepresentation();
        setNewlines();
        updateRepresentation();
    }
    public ArrayList<Integer> getCells() {
        return cells_;
    }
    private ArrayList<Integer> getNextGenCells() {
        return nextGenBufferedCells_;
    }
    public void setCells(ArrayList<Integer> cells) {
        this.cells_ = cells;
    }
    public void setCells(int[] primitiveArray) {
        for(int elementNumber = 0; elementNumber < getSize(); ++elementNumber){
            setCell(elementNumber, primitiveArray[elementNumber]);
        }
    }
    public void setNextGenBufferedCells(ArrayList<Integer> cells) {
        nextGenBufferedCells_ = cells;
    }
    public StringBuilder getRepresentation() {
        return representation_;
    }
    public String getStringRepresentation() {
        return representation_.toString();
    }
    public int[] getPrimitiveArrayRepresentation() {
        int[] intRepresentation = new int[getSize()];
        for(int elementNumber = 0; elementNumber < getSize(); ++elementNumber){
            intRepresentation[elementNumber] = getCell(elementNumber);
        }
        return intRepresentation;
    }
    public void setRepresentation(StringBuilder representation) {
        this.representation_ = representation;
    }
    // TODO: abstract the cell into a class instead of just an int value
    public Integer getCell(int row, int column){
        return getCells().get(row * getWidth() + column);
    }
    private Integer getNextGenCell(int row, int column){
        return getNextGenCells().get(row * getWidth() + column);
    }
    public Integer getCell(int elementNumber){
        return getCells().get(elementNumber);
    }
    private Integer getNextGenCell(int elementNumber){
        return getNextGenCells().get(elementNumber);
    }
    public char getCellAsChar(int row, int column){
        return representation_.charAt(row*(getWidth()+1) + column);
    }
    public void setCell(int row, int column, int value) {
        cells_.set(row * getWidth() + column, value);
    }
    public void setCell(int row, int column, int value, ArrayList<Integer> cellArray) {
        cellArray.set(row * getWidth() + column, value);
    }
    public void setCell(int elementNumber, int value) {
        cells_.set(elementNumber, value);
    }
    public void setAllCells(int value) {
        for(Integer cell: cells_){
            cell = value;
        }
    }
    public int getHeight() {
        return height_;
    }
    public void setHeight(int height) {
        this.height_ = height;
    }
    public int getWidth() {
        return width_;
    }
    public void setWidth(int width) {
        this.width_ = width;
    }
    public int getSize() {
        return getHeight()*getWidth();
    }
    private void setupRepresentation() {
        for(int rowIndex = 0; rowIndex < getHeight(); ++rowIndex){
            for(int columnIndex = 0; columnIndex < getWidth(); ++columnIndex){
                representation_.append(getCell(rowIndex, columnIndex).toString().charAt(0));
            }
            representation_.append('X');
        }
    }
    public void updateRepresentation() {
        for(int rowIndex = 0; rowIndex < getHeight(); ++rowIndex){
            for(int columnIndex = 0; columnIndex < getWidth(); ++columnIndex){
                representation_.setCharAt(rowIndex*(getWidth()+1) + columnIndex, getCell(rowIndex,columnIndex).toString().charAt(0));
            }
        }
    }
    private void setNewlines() {
        for(int rowIndex = 0; rowIndex < getHeight(); ++rowIndex){
            representation_.setCharAt((rowIndex)* (getWidth()+1)+getWidth(), '\n');
        }
    }
    public void updateCurrentGen(){
        calculateNextGen();
        for(int elementNumber = 0; elementNumber < getSize(); ++elementNumber){
            setCell(elementNumber, getNextGenCell(elementNumber));
        }
    }
    public void calculateNextGen(){
        int neighborSum = 0;
        for(int rowIndex = 0; rowIndex < getHeight(); ++rowIndex){
            for(int columnIndex = 0; columnIndex < getWidth(); ++columnIndex){
                neighborSum = sumOfNeighbors(rowIndex, columnIndex);

                if(( neighborSum < 2 || neighborSum > 3 ) || (neighborSum == 2 && getCell(rowIndex, columnIndex) == 0)){
                    setCell(rowIndex,columnIndex,0, nextGenBufferedCells_);
                } else {
                    setCell(rowIndex,columnIndex,1, nextGenBufferedCells_);
                }
            }
        }
    }
    public int sumOfNeighbors(int row, int column){
        int leftNeighborIndex = 0;
        int rightNeighborIndex = 0;
        int upperNeighborIndex = 0;
        int lowerNeighborIndex = 0;
        if(row == 0){
            upperNeighborIndex = getHeight() - 1;
        } else if(row == getWidth() - 1){
            lowerNeighborIndex = 0;
        }
        if(column == 0){
            leftNeighborIndex = getWidth() - 1;
        } else if(column == getWidth() - 1){
            rightNeighborIndex = 0;
        }
        int result = 0;
        result += getCell(upperNeighborIndex, leftNeighborIndex);
        result += getCell(upperNeighborIndex, column);
        result += getCell(upperNeighborIndex, rightNeighborIndex);
        result += getCell(row, leftNeighborIndex);
        result += getCell(row, column);
        result += getCell(row, rightNeighborIndex);
        result += getCell(lowerNeighborIndex, leftNeighborIndex);
        result += getCell(lowerNeighborIndex, column);
        result += getCell(lowerNeighborIndex, rightNeighborIndex);
        return result;
    }
}

/*
neighbors	cell	next	cell	next
0	0	0	1	0
1	0	0	1	0
2	0	0	1	1
3	0	1	1	1
4	0	0	1	0
if	( n < 2 || n >3 ) || (n == 2 && c == 0)				0
	else				1
Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
Any live cell with two or three live neighbours lives on to the next generation.
Any live cell with more than three live neighbours dies, as if by overpopulation.
Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

*/
