package com.github.majidshoorabi.saftonfun.model;

/**
 * @author majid.shoorabi
 * @created 2022-08-April
 * @project saftonfun
 */

public class Pair {

    private int i;
    private int j;

    public Pair(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public Pair() {
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
}
