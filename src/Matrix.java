public class Matrix {
    State[][] Matrix;
    int numberOfStates;
    int numberOfVariables;

    public Matrix (int numberOfStates, int numberOfVariables){
        this.numberOfStates=numberOfStates;
        this.numberOfVariables=numberOfVariables;
        Matrix = new State[numberOfStates][numberOfVariables];
    }

    public State[][] getMatrix() {
        return Matrix;
    }

    public int getNumberOfStates() {
        return numberOfStates;
    }

    public int getNumberOfVariables() {
        return numberOfVariables;
    }

    public void setMatrix(State[][] matrix) {
        Matrix = matrix;
    }

    public void setNumberOfStates(int numberOfStates) {
        this.numberOfStates = numberOfStates;
    }

    public void setNumberOfVariables(int numberOfVariables) {
        this.numberOfVariables = numberOfVariables;
    }

    public void setMatrixValues(int first, int second, State value){
        this.Matrix[first][second]=value;
    }

}
