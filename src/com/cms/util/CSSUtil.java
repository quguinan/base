package com.cms.util;

import java.io.IOException;  
import java.io.InputStream;  
import java.io.InputStreamReader;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
  
import org.w3c.css.sac.InputSource;  
import org.w3c.dom.css.CSSRule;  
import org.w3c.dom.css.CSSRuleList;  
import org.w3c.dom.css.CSSStyleSheet;

import com.steadystate.css.parser.CSSOMParser;
  
  
public class CSSUtil {  
  
    private CSSStyleSheet sheet = null;  
      
    public CSSStyleSheet getSheet() {  
        return sheet;  
    }  
  
    public void setSheet(CSSStyleSheet sheet) {  
        this.sheet = sheet;  
    }  
  
    /** 
     * 指定文件流 
     * @param stream 
     */  
    public CSSUtil(InputStream stream){  
        InputSource source = new InputSource(new InputStreamReader(stream));  
          
        CSSOMParser parser = new CSSOMParser();  
        try {  
             sheet = parser.parseStyleSheet(source);  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
    /** 
     * 获取样式信息 
     * @param className 样式名 
     * @return 返回该样式的信息 
     */  
    public String getClass(String className){  
        if(sheet == null){  
            return null;  
        }  
        String res = "";  
        CSSRuleList rules = sheet.getCssRules();  
        for (int i = 0; i < rules.getLength(); i++) {  
            CSSRule rule = rules.item(i);  
            Pattern pattern = Pattern.compile("\\."+className + ".*([^\\{]\\{.*[^\\}]\\})");  
            Matcher matcher = pattern.matcher(rule.getCssText());  
            while(matcher.find()){  
                res = res + matcher.group(1) + "\n";  
            }  
        }  
        return res;  
    }  
    /** 
     * 获取某个样式类下的某个属性信息，没有返回空 
     * @param className 样式类名称 
     * @param attrName 属性名称 
     * @return 
     */  
    public String getCssAttribute(String className, String attrName){  
        String classContent = this.getClass(className);  
        Pattern pattern = Pattern.compile("[^a-zA-Z\\-]" + attrName + ":\\s*([^;\\}]{1,100})\\s*[;\\}]{1}");  
        Matcher matcher = pattern.matcher(classContent);  
        String res = "";  
        while(matcher.find()){  
            res = matcher.group(1);  
        }  
        return res;  
    }  
    
    
    
}  
