/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcare;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author drodr19
 */
public class HEALTHCARE {
     private static final HashMap map = new HashMap(); 
     private static final HashMap ma = new HashMap(); 
     private static final HashMap m = new HashMap(); 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         
   

        String csvFile = "C:\\Users\\drodr19\\Desktop\\HC.csv";
        //String csvFile1 = "C:\\Users\\drodr19\\Desktop\\VT_cleaned.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            
            while ((line = br.readLine()) != null) {
                
                // use comma as separator
                System.out.println(line);
                String[] W = line.split(cvsSplitBy);
                if (W[0].equals("Cancer") && W[3].equals("Female Breast Cancer Mortality Rate"))
                {
                   
                    
                    ///////////////////////////////////////////////
                    if(map.containsKey(W[5])) 
                    
                    {
                    // if we have already seen this word before,
                    // increment its count by one
                        Integer count = (Integer)map.get(W[5]);
                        map.put(W[5], new Integer(count.intValue() + 1));
                    } 
                    else 
                    {
                        // we haven't seen this word, so add it with count of 1
                         map.put(W[5], new Integer(1));
                    }
                    /////////////////////////////////////////////
                    if(ma.containsKey(W[7])) 
                    
                    {
                    // if we have already seen this word before,
                    // increment its count by one
                        Integer count = (Integer)ma.get(W[7]);
                        ma.put(W[7], new Integer(count.intValue() + 1));
                    } 
                    else 
                    {
                        // we haven't seen this word, so add it with count of 1
                         ma.put(W[7], new Integer(1));
                    }
                    //////////////////////////////////////
                    if(m.containsKey(W[9])) 
                    
                    {
                    // if we have already seen this word before,
                    // increment its count by one
                        Integer count = (Integer)m.get(W[9]);
                        m.put(W[9], new Integer(count.intValue() + 1));
                    } 
                    else 
                    {
                        // we haven't seen this word, so add it with count of 1
                         m.put(W[9], new Integer(1));
                    }
                    
                }
                
            }
           System.out.println ("/////////////////////////////////////////////////////////////////////////////");   
            ArrayList arraylist = new ArrayList(map.keySet());
                    Collections.sort(arraylist);
                    for (int i = 0; i < arraylist.size(); i++) 
                    {
                        String key = (String)arraylist.get(i);
                        Integer count = (Integer)map.get(key);
                        System.out.println(key + " --> " + count);
                    }
                    
            System.out.println ("/////////////////////////////////////////////////////////////////////////////");       
            ArrayList arraylist1 = new ArrayList(ma.keySet());
                    Collections.sort(arraylist1);
                    for (int i = 0; i < arraylist1.size(); i++) 
                    {
                        String key = (String)arraylist1.get(i);
                        Integer count = (Integer)ma.get(key);
                        System.out.println(key + " --> " + count);
                    }
           System.out.println ("/////////////////////////////////////////////////////////////////////////////");   
           ArrayList arraylist2 = new ArrayList(m.keySet());
                    Collections.sort(arraylist2);
                    for (int i = 0; i < arraylist2.size(); i++) 
                    {
                        String key = (String)arraylist2.get(i);
                        Integer count = (Integer)m.get(key);
                        System.out.println(key + " --> " + count);
                    }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    
}
