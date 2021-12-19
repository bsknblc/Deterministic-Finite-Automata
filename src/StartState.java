class StartState implements States {
    String name;

    public StartState (String name){
        this.name=name;
    }

    String getName(){
        return this.name;
    }

    void setName(String name){
        this.name=name;
    }
}
