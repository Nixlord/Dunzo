package com.nixlord.dunzo.util;

import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.phoenixoverlord.pravega.extensions.LoggerKt;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;

import java.util.*;

public class DataFusion {
    public static void createProduct(FirebaseVisionText visionText) {
//        food/bill10
        /*

        Transform image text into Product
        Study Firebase docs to know how much library will help. Then medium articles. Then regex.

        Print the Bill in an orderly fashion.
        Once it is printed well, distinctly,

        Then test it on some other bill, and fix formatting, etc.

        Once that is done, create a Product class and a Store class which can optimally store the Product/Store

        Replace print calls with Object creation and return the object.

         */

        int headerCount = 1;
        String name = "";
        String address = "";
        String phoneNo = "";
        String resultText = visionText.getText();
        //print(resultText);

        for(FirebaseVisionText.TextBlock block: visionText.getTextBlocks()){
            String blockText = block.getText();
            print(blockText);
            if(headerCount-->0) {

                List<FirebaseVisionText.Line> lineList = block.getLines();
                name = lineList.get(0).getText();
                address = lineList.get(1).getText() + " " + lineList.get(2).getText() + " " + lineList.get(3).getText();
                phoneNo = lineList.get(4).getText().replaceAll("Ph No. ", "");
            }
        }
        print("Name: "+name+"\n Address: "+address+"\nPhone No.: "+phoneNo);
        //LoggerKt.logDebug("DataFusion", "");

    }
    public static int getLowestIndex(HashMap<String, Integer> map){
        Integer lowest = Integer.MAX_VALUE;
        for(Map.Entry<String, Integer> entry: map.entrySet()){
            if(entry.getValue()!=-1 && entry.getValue()<lowest){
                lowest = entry.getValue();
            }
        }
        return lowest;
    }
    public static int getHighestIndex(HashMap<String,Integer> map){
        Integer highest = -1;
        for(Map.Entry<String, Integer> entry: map.entrySet()){
            if(entry.getValue()!=-1 &&entry.getValue()>highest){
                highest = entry.getValue();
            }
        }
        return highest;
    }
    public static ArrayList<String> extract(int startIndex, int endIndex, ArrayList<String> elementList){
        ArrayList<String> updatedElementList = (ArrayList<String>) elementList.subList(startIndex, endIndex+1);
        return updatedElementList;
    }
    public static void separateElementList(ArrayList<String> numberElementList, ArrayList<String> nameElementList){
        int index = 0;
        for(String s:numberElementList){
            try{
                Float.parseFloat(s);
            }
            catch (Exception e){
                nameElementList.add(numberElementList.remove(index));
            }
            index++;
        }
    }
    public static void cleanStringElementList(ArrayList<String> toClear, ArrayList<String> from){
        for(String s:toClear){
            ExtractedResult extractedResult = FuzzySearch.extractOne(s, from);
            from.remove(extractedResult.getIndex());
        }
    }
    public static void print(String msg){
        LoggerKt.logDebug("DataFusion", msg);
    }
}
