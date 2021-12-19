class FinalState implements States {
    String name;

    public FinalState (String name){
        this.name=name;
    }

    String getName(){
        return this.name;
    }

    void setName(String name){
        this.name=name;
    }
}
