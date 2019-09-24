/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 *
 * @author mscit9
 */
public class JavaApplication5 {

    static HashMap<String, LinkedHashSet<String>> map1 = new HashMap<String, LinkedHashSet<String>>();
    static String currentUserName = "";
    static LinkedHashSet<String> friendSuggestion = new LinkedHashSet<String>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(System.in);
            BufferedReader in = new BufferedReader(new FileReader("/home/mscit9/d.txt"));
            String str = in.readLine();

            while ((str = in.readLine()) != null) {//reading values & inserting them in the map1
                String[] ar =str.split(",");
                //inserting the values into the hashmap
                if(!map1.containsKey(ar[0])){
                    LinkedHashSet<String> val = new LinkedHashSet<String>();//we use linkedhashset to store it in a array form
                    map1.put(ar[0], val);//insert the value at index 0 and map it to a hashset
                    val.add(ar[1]);//adds value to the hashset
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

            for(HashMap.Entry<String, LinkedHashSet<String>> entry : map1.entrySet())//Prints the hashmap
            {
                String key = entry.getKey();
                System.out.print(key+" : ");
                
                Iterator it = entry.getValue().iterator();//used to iterator to go through the hashset
                while(it.hasNext())
                    System.out.print(it.next() + " ");//prints eachh value of the hashset
                System.out.println();
            }

            System.out.println("Enter the yourUsername");//for login purposes
            currentUserName = input.nextLine();// this name is constant throughout as it is declared as global variable

            FriendSuggestion();//this function suggests the names of friends
            
            System.out.println("Enter the nameOfPerson to stalk");
            String FriendName = input.nextLine();//used to take the input name of you want to check the friends list of
            String chainFriendsString = ChainFriends(FriendName);//stores the friends names that are returned from the function
            
            if(chainFriendsString == null){//check if the string is null
                System.out.println("No Mutual Friends found");
            } else{
                String[] splitString = chainFriendsString.split(",");//splits the string based on the comma
                System.out.println("Mutual Friends:");
                for(int i = 0; i < splitString.length; i++){
                    System.out.print(splitString[i] + " ");//prints the list
                }
            }
            
            System.out.println("Enter the person you hate");
            String unfriend = input.nextLine();//input for unfriending
            UnFriend(unfriend);//function to unfriend
            
        } //try block
        catch (IOException e) {
            System.out.println("File error");
        }//catch close
        
    }//Closing bracket for main class
    
    public static String ChainFriends(String friendName) {

        LinkedHashSet<String> myFriends = null;
        LinkedHashSet<String> personFriends = null;

        if (map1.containsKey(currentUserName)) {
            myFriends = map1.get(currentUserName);//gets the usernames friends
        }

        if (map1.containsKey(friendName)) {
            personFriends = map1.get(friendName);//gets the friends friends list
        }

        if (myFriends != null && personFriends != null) { //checks if the any of the list value is empty
            String s = "";
            Iterator<String> i = myFriends.iterator();

            while (i.hasNext()) {
                String currentElement = i.next();
                Iterator<String> j = personFriends.iterator();//goes through each value 
                while (j.hasNext()) {
                    if (currentElement.equals(j.next())) {
                        s += currentElement + ",";
                    }
                }
            }

            if (!s.equals("")) {
                return s.substring(0, s.length() - 1);
            }
        }

        return null;
    }
    
    public static void FriendSuggestion(){
        friendSuggestion.clear();
        
        if(map1.containsKey(currentUserName)){
            LinkedHashSet<String> friendList = map1.get(currentUserName);
            Iterator<String> friendListit = friendList.iterator();
            while(friendListit.hasNext())
            {
                String friendName = friendListit.next();
                
                Iterator<String> friendListit2 = map1.get(friendName).iterator();
                while(friendListit2.hasNext())
                {
                    String friendFriendName = friendListit2.next(); 
                    if(!friendFriendName.equals(currentUserName) && !friendName.equals(friendFriendName))
                    {
                        //remove entry where my name pops up in my friend's friend list
                        //if friendName is present in both list ie my friend list and my friend's friend list has same person dont include them in final string
                        friendSuggestion.add(friendFriendName);
                    }  
                }
            }
        }
        
        
        Iterator it = friendSuggestion.iterator();
        while(it.hasNext())
            System.out.print(it.next() + " ");
        System.out.println();
    }
    
    public static void UnFriend(String unfriend){
        LinkedHashSet<String> friendListCurrentUser = map1.get(currentUserName);
        LinkedHashSet<String> friendListUnFriend = map1.get(unfriend);
        friendListCurrentUser.remove(unfriend);
        friendListUnFriend.remove(currentUserName);
        
        
        for(HashMap.Entry<String, LinkedHashSet<String>> entry : map1.entrySet())
        {
            String key = entry.getKey();
            System.out.print(key+" : ");

            Iterator it = entry.getValue().iterator();
            while(it.hasNext())
                System.out.print(it.next() + " ");
            System.out.println();
        }
    }
}
