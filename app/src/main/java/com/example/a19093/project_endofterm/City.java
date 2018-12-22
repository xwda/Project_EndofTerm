package com.example.a19093.project_endofterm;

public class City {
    private String name;
    private String isSelect;
    City(){
    }

    City(String name){
       this.name = name;
       this.isSelect= "否";
    }

    City(String name, String isSelect){
        this.name = name;
        this.isSelect= isSelect;
    }

    public String getName() {
        return name;
    }

    public String getIsSelect() {
        return isSelect;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setIsSelect(String isSelect){
        this.isSelect = isSelect;
    }

    public String toString2(){
        String[] s;
        s = name.split(" ");
        return s[0] + "," + s[1];
    }

}
