package com.cms.util;

import java.io.*; 
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest; 
  
public class ReadTest { 
  
  public static List<String> first_list; 
  
  public ReadTest() { 
	  first_list = new LinkedList<>(); 
  } 
  
  public static void ReadFile() { 
	  first_list = new LinkedList<>(); 

    final String filename = "d://a.txt"; 
    String str = null; 
    int i = 0; 
    try { 
      LineNumberReader reader = null; 
      reader = new LineNumberReader(new FileReader(filename)); 
      while ((str = reader.readLine()) != null) { 
        if (!str.isEmpty()) { 
          String values[] = str.split("  "); 
          first_list.add(values[0]); 
        } 
      } 
    } catch (IOException e) { 
      e.printStackTrace(); 
    } 
  } 
  
  public static void main(String args[]) { 
  
    ReadTest reader = new ReadTest(); 
    reader.ReadFile(); 
  
    for (int i = 0; i < first_list.size(); i++) { 
      System.out.println(first_list.get(i) ); 
    } 
  } 
} 