package saqada.com.autocompletetriesearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by saqada on 23.02.2018.
 */

public class Trie
{
    private Vertex rootVertex;

    public Trie(List<Object> objectList)
    {
        this.rootVertex = new Vertex();

        for (int i = 0; i < objectList.size(); i++)
        {
            String word = objectList.get(i).toString().toLowerCase();
            addWord(this.rootVertex, word, i);
        }
    }

    public Vertex getRootVertex()
    {
        return this.rootVertex;
    }

    public void addWord(Vertex vertex, String word, int index)
    {
        if (word.isEmpty())
        {
            vertex.addIndexToWordsIndexList(index);
            vertex.increaseWordsNumber();
        }
        else
        {
            vertex.addIndexToPrefixesIndexList(index);
            vertex.increasePrefixesNumber();
            Character fChar = word.charAt(0);
            fChar = Character.toLowerCase(fChar);

            HashMap<Character, Vertex> vertexNodes = vertex.getVertexNodes();

            if (!vertexNodes.containsKey(fChar))
            {
                vertex.addVertexNode(fChar);
            }

            word = (word.length() == 1) ? "" : word.substring(1);
            addWord(vertexNodes.get(fChar), word, index);
        }
    }

    private List<Integer> getPrefixesIndexes(Vertex vertex, String prefix)
    {
        if (prefix.isEmpty())
        {

            List<Integer> prefixesIndexList = vertex.getPrefixesIndexList();
            return prefixesIndexList;
        }
        else
        {
            Character fChar = prefix.charAt(0);
            fChar = Character.toLowerCase(fChar);

            if (!(vertex.getVertexNodes().containsKey(fChar)))
            {
                return null;
            }
            else
            {
                prefix = (prefix.length() == 1) ? "" : prefix.substring(1);
                return getPrefixesIndexes(vertex.getVertexNodes().get(fChar), prefix);
            }
        }
    }

    private List<Integer> getWordsIndexes(Vertex vertex, String word)
    {
        if (word.isEmpty())
        {
            return vertex.getWordsIndexList();
        }
        else
        {
            Character fChar = word.charAt(0);
            fChar = Character.toLowerCase(fChar);

            if (!(vertex.getVertexNodes().containsKey(fChar)))
            {
                return null;
            }
            else
            {
                word = (word.length() == 1) ? "" : word.substring(1);
                return getWordsIndexes(vertex.getVertexNodes().get(fChar), word);
            }
        }
    }

    public List<Integer> getIndices(Vertex vertex, String text)
    {
        List<Integer> wordIndices =  getWordsIndexes(vertex, text);
        List<Integer> prefixIndices =  getPrefixesIndexes(vertex, text);

        List<Integer> indices = new ArrayList<>();
        indices.addAll(wordIndices);
        indices.addAll(prefixIndices);

        return indices;
    }

}
