package saqada.com.autocompletetriesearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by saqada on 23.02.2018.
 */

public class Vertex
{
    private HashMap<Character, Vertex> vertexNodes;
    private List<Integer>              prefixesIndexList;
    private int                        prefixesNumber;
    private List<Integer>              wordsIndexList;
    private int                        wordsNumber;

    public Vertex()
    {
        this.vertexNodes = new HashMap<>();
        this.prefixesIndexList = new ArrayList<>();
        this.wordsIndexList = new ArrayList<>();
        this.prefixesNumber = 0;
        this.wordsNumber = 0;
    }

    public boolean hasWords()
    {
        if (wordsNumber > 0)
        {
            return true;
        }
        return false;
    }

    public boolean hasPrefixes()
    {
        if (this.prefixesNumber > 0)
        {
            return true;
        }
        return false;
    }

    public void addVertexNode(Character character)
    {
        this.vertexNodes.put(character, new Vertex());
    }

    public void addIndexToPrefixesIndexList(int index)
    {
        this.prefixesIndexList.add(index);
    }

    public void addIndexToWordsIndexList(int index)
    {
        wordsIndexList.add(index);
    }

    public HashMap<Character, Vertex> getVertexNodes()
    {
        return this.vertexNodes;
    }

    public List<Integer> getPrefixesIndexList()
    {
        return this.prefixesIndexList;
    }

    public List<Integer> getWordsIndexList()
    {
        return wordsIndexList;
    }

    public int getPrefixesNumber()
    {
        return this.prefixesNumber;
    }

    public int getWordsNumber()
    {
        return wordsNumber;
    }

    public void increasePrefixesNumber()
    {
        this.prefixesNumber++;
    }

    public void increaseWordsNumber()
    {
        wordsNumber++;
    }
}
