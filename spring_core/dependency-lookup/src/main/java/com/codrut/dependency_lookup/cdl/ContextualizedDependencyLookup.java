package com.codrut.dependency_lookup.cdl;

public class ContextualizedDependencyLookup implements ManagedComponent{

    private Object dependency;

    @Override
    public void performLookUp(Container container) {
        this.dependency = container.getDependency("myDependency");
    }

    @Override
    public String toString() {
        return dependency.toString();
    }
}

interface ManagedComponent {
    void performLookUp(Container container);
}

interface Container {
    Object getDependency(String name);
}
