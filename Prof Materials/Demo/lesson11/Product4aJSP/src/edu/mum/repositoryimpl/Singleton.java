package edu.mum.repositoryimpl;

//Sample implementation...not used : FOR REFERENCE

public class Singleton {
    // Private constructor. Prevents instantiation from other classes.
    private Singleton() { }

    /**
     * Initializes singleton.
     *
     * SingletonHolder is loaded on the first execution of Singleton.getInstance()
     * or the first access to SingletonHolder.INSTANCE, not before.
     */
    private static class SingletonHolder {
            private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
            return SingletonHolder.INSTANCE;
    }
}