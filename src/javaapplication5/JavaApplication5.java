/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author mscit9
 */
public class JavaApplication5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String ar1[];
        int i = 0, counttrans = 0;
        ArrayList<String> arr = new ArrayList<String>();
        ArrayList<String> comb2 = new ArrayList<String>();
        ArrayList<String> comb3 = new ArrayList<String>();
        HashMap<String, LinkedHashSet<String>> map1 = new HashMap<String, LinkedHashSet<String>>();
        LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
        try {
            BufferedReader in = new BufferedReader(
                    new FileReader("/home/mscit9/d.txt"));
            String str = in.readLine();

            while ((str = in.readLine()) != null) {
                String[] ar =str.split(",");
                //StringTokenizer st = new StringTokenizer(str);

               
              //  String ar = st.nextToken();
            //    st = new StringTokenizer(ar, ",");
//                while (st.hasMoreTokens()) {
//                    String key = st.nextToken();
//                    //System.out.println(item);
//                    if (map.containsKey(key)) {
//                        
//                        map1.get(key).add(key);
//                        //map1.put(item, );
//                    } else {
//                        List<String> friendList = new ArrayList();
//                        map.put(key, i + 1);
//                        map1.put(key, 1);
//                        arr.add(key);
//                        i++;
//                    }
//
//                }
            
            if(!map1.containsKey(ar[0])){
                LinkedHashSet<String> val = new LinkedHashSet<String>();
                map1.put(ar[0], val);
                val.add(ar[1]);
            }else{
                map1.get(ar[0]).add(ar[1]);
            }
            
            if(!map1.containsKey(ar[1])){
                LinkedHashSet<String> val = new LinkedHashSet<String>();
                map1.put(ar[1], val);
                val.add(ar[0]);
            }else{
                map1.get(ar[1]).add(ar[0]);
            }

        }
        in.close();
              
        for(HashMap.Entry<String, LinkedHashSet<String>> entry : map1.entrySet())
        {
            String key = entry.getKey();
            System.out.print(key+" : ");
            //LinkedHashSet<String>> friendList = entry.getValue();
            //for(int i = 0; i < friendList.)
            Iterator it = entry.getValue().iterator();
            while(it.hasNext())
                System.out.print(it.next() + " ");
            System.out.println();
        }

    } //try block
    catch (IOException e) {
        System.out.println("File error");

    }//catch close

    }//Closing bracket for main class
}
