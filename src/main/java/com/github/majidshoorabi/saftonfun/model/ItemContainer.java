package com.github.majidshoorabi.saftonfun.model;

import org.openqa.selenium.WebElement;

/**
 * @author majid.shoorabi
 * @created 2022-08-April
 * @project saftonfun
 */

public class ItemContainer {

    private WebElement webElement;
    private int i;
    private int j;
    private int wordIndex;

    public ItemContainer() {
    }

    public ItemContainer(WebElement webElement, int i, int j, int wordIndex) {
        this.webElement = webElement;
        this.i = i;
        this.j = j;
        this.wordIndex = wordIndex;
    }

    public WebElement getWebElement() {
        return webElement;
    }

    public void setWebElement(WebElement webElement) {
        this.webElement = webElement;
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

    public int getWordIndex() {
        return wordIndex;
    }

    public void setWordIndex(int wordIndex) {
        this.wordIndex = wordIndex;
    }
}
