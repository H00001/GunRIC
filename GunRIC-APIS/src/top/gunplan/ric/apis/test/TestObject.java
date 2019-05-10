package top.gunplan.ric.apis.test;

import java.io.Serializable;

/**
 * @author dosdrtt
 * transfer object must implement Serializable
 */
public class TestObject implements Serializable {
    private static final long serialVersionUID = -7070111165165709488L;
    public int x = 0;
    public int y = 1;
}
