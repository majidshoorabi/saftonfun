package com.github.majidshoorabi.saftonfun;

import com.github.majidshoorabi.saftonfun.model.ItemContainer;
import com.github.majidshoorabi.saftonfun.model.Pair;
import org.openqa.selenium.WebElement;

import java.util.*;

/**
 * @author majid.shoorabi
 * @created 2022-07-April
 * @project saftonfun
 */

public class Finder {

    public List<List<ItemContainer>>  find(String word, WebElement[][] table) {

        List<List<ItemContainer>> containersWords = new LinkedList<>();
        List<String> alphabetList = Arrays.asList(word.split("(?!^)"));

        for (int wordIndex = 0; wordIndex < alphabetList.size(); wordIndex++) {
            String alp = alphabetList.get(wordIndex);
            List<ItemContainer> containerList = new LinkedList<>();

            for (int i = 0; i < table.length; i++) {
                WebElement[] row = table[i];

                for (int j = 0; j < row.length; j++) {
                    WebElement webElement = row[j];

                    if (alp.equals(webElement.getText())) {
                        if (wordIndex == 0) {
                            // this is a first match
                            containerList.add(new ItemContainer(webElement, i, j, wordIndex));
                            matchOccurred(i, j, containerList, table, alphabetList, wordIndex, containersWords);
                            containerList.clear();
                        }
                    }
                }
            }
        }
        return containersWords;
    }


    private void matchOccurred(int currentI, int currentJ, List<ItemContainer> containerList, WebElement[][] table,
                               List<String> alphabetList, int currentWordIndex, List<List<ItemContainer>> containersWords) {

        if (containerList.size() >= alphabetList.size()) {
            containersWords.add(new LinkedList<>(containerList));
            containerList.remove(containerList.size() - 1);
            return;
        }

        List<Pair> pairList = new LinkedList<>();

        if (currentI - 1 >= 0 && currentJ - 1 >= 0)
            pairList.add(new Pair(currentI - 1, currentJ - 1));
        if (currentI - 1 >= 0)
            pairList.add(new Pair(currentI - 1, currentJ));
        if (currentI - 1 >= 0 && currentJ + 1 <= 9)
            pairList.add(new Pair(currentI - 1, currentJ + 1));

        if (currentJ - 1 >= 0)
            pairList.add(new Pair(currentI, currentJ - 1));
        if (currentJ + 1 <= 9)
            pairList.add(new Pair(currentI, currentJ + 1));

        if (currentI + 1 <= 9 && currentJ - 1 >= 0)
            pairList.add(new Pair(currentI + 1, currentJ - 1));
        if (currentI + 1 <= 9)
            pairList.add(new Pair(currentI + 1, currentJ));
        if (currentI + 1 <= 9 && currentJ + 1 <= 9)
            pairList.add(new Pair(currentI + 1, currentJ + 1));

        for (int index = currentWordIndex + 1; index < alphabetList.size(); index++) {

            for (Pair pair : pairList) {

                if (notExistPairInContainerList(containerList, pair)) {

                    WebElement element = table[pair.getI()][pair.getJ()];
                    String text = element.getText();

                    if (alphabetList.get(index).equals(text)) {
                        containerList.add(new ItemContainer(element, pair.getI(), pair.getJ(), index));
                        matchOccurred(pair.getI(), pair.getJ(), containerList, table, alphabetList, index, containersWords);
                    }
                }
            }
            if (containerList.size() == index)
                break;
        }
        containerList.remove(containerList.size() - 1);
    }

    private boolean notExistPairInContainerList(List<ItemContainer> containerList, Pair pair) {
        return containerList.stream().noneMatch(c -> c.getI() == pair.getI() && c.getJ() == pair.getJ());
    }


}
