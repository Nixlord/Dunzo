package com.nixlord.dunzo.util;

import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.nixlord.dunzo.ml.TextScanner;
import com.nixlord.dunzo.ml.TextScannerKt;
import com.phoenixoverlord.pravega.extensions.LoggerKt;
import kotlin.Pair;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;

import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataFusion {
    public static void createProduct(ArrayList<String> elements, Pair<HashMap<String, Integer>, HashMap<String, Integer>> parts) {
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
//
//        int headerCount = 1;
//        String name = "";
//        String address = "";
//        String phoneNo = "";
//        String resultText = visionText.getText();
//        //print(resultText);
//
//
//
//        for(FirebaseVisionText.TextBlock block: visionText.getTextBlocks()){
//            String blockText = block.getText();
//            //print(blockText);
//            if(headerCount-->0) {
//
//                List<FirebaseVisionText.Line> lineList = block.getLines();
//                name = lineList.get(0).getText();
//                address = lineList.get(1).getText() + " " + lineList.get(2).getText() + " " + lineList.get(3).getText();
//                phoneNo = lineList.get(4).getText().replaceAll("Ph No. ", "");
//            }
//            for(FirebaseVisionText.Line line:block.getLines()){
//                String lineText = line.getText();
//                print("Line: "+lineText);
//            }
//        }
//        print("Name: "+name+"\n Address: "+address+"\nPhone No.: "+phoneNo);
//        //LoggerKt.logDebug("DataFusion", "");
//
//

//        Pair<HashMap<String, Integer>, HashMap<String, Integer>> parts = TextScanner.INSTANCE.parts(visionText);

        ArrayList<String> firstMarkers = TextScanner.INSTANCE.getFirstMarkers();
        ArrayList<String> secondMarkers = TextScanner.INSTANCE.getSecondMarkers();

        HashMap<String, Integer> firstMap = parts.getFirst();
        HashMap<String, Integer> secondMap = parts.getSecond();

        int lowestIndex = getLowestIndex(firstMap);
        int highestIndex = getHighestIndex(secondMap);
        ArrayList<String> extractedElements = extract(lowestIndex, highestIndex, elements);
        ArrayList<String> nameElementList = new ArrayList<>();
        ArrayList<String> numberElementList = new ArrayList<>();
        cleanStringElementList(firstMarkers, extractedElements);
        cleanStringElementList(secondMarkers, extractedElements);
        separateElementList(extractedElements, nameElementList, numberElementList);

        print("Before Outliers: "+nameElementList);
        removeOutliers(nameElementList);


        print("Total Element List: ");
        printList(extractedElements);
        print("Name Element List: ");
        printList(nameElementList);
        print("Number Element List: ");
        printList(numberElementList);

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
        ArrayList<String> updatedElementList = new ArrayList<String>();
        for(int i=startIndex;i<=endIndex;i++){
            updatedElementList.add(elementList.get(i));
        }
        return updatedElementList;
    }
    public static void separateElementList(ArrayList<String> elementList, ArrayList<String> nameElementList, ArrayList<String> numberElementList){
        for(String s:elementList){
            try{
                Float.parseFloat(s);
                numberElementList.add(s);
            }
            catch (Exception e){
                nameElementList.add(s);
            }
        }
    }
    public static void cleanStringElementList(ArrayList<String> toClear, ArrayList<String> from){
        for(String s:toClear){
            ExtractedResult extractedResult = FuzzySearch.extractOne(s, from);
            print(s+" "+extractedResult.toString());
            if(extractedResult.getScore()>75)
                from.remove(extractedResult.getIndex());
        }
    }
    public static void removeOutliers(ArrayList<String> elementList){
//        String pattern = "(\\d*)(,)(\\d*)";
//        Pattern p = Pattern.compile(pattern);
//        for(int i=0;i<elementList.size();i++){
//            Matcher m = p.matcher(elementList.get(i));
//            if(((Matcher) m).find()){
//                //remove from list
//                print("Outlier: "+elementList.get(i));
//                elementList.remove(i);
//            }
//        }
//        String pattern = "^[a-z]*$";
//        Pattern p = Pattern.compile(pattern);
        print("Total elements: "+elementList.size());
        for(int i=0;i<elementList.size();i++){
            String s = elementList.get(i);
            print("Checking... "+s);
            char[] ch = s.toCharArray();
            int flag = 0;
            for(char c:ch){
                if(Character.isLetter(c)==false){
                    flag = 1;
                    break;
                }
            }
            if(flag==1){
                print("Outlier: "+elementList.get(i));
                elementList.remove(i);
                i--;
            }
        }
    }
    public static void print(String msg){
        LoggerKt.logDebug("DataFusion", msg);
    }
    public static void printList(ArrayList<String> list){
        LoggerKt.logDebug("DataFusion",list.toString());
    }
}
