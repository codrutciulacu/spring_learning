package com.codrut.dependency_injection.xml_config;

public class BookworkOracle implements Oracle {
    private Encyclopedia encyclopedia;

    @Override
    public String defineMeaningOfLife() {
        return "Encyclopedias are a waste of money - go see the world instead";
    }
}
