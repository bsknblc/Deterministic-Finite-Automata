class State implements States {
    String name;

    public State (String name){
        this.name=name;
    }

    String getName(){
        return this.name;
    }

    void setName(String name){
        this.name=name;
    }

}
