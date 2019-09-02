package com.web.auto.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
	public static String getMatchedString(String regex,String input){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if(matcher.find()){
			return matcher.group(0);
		}else{
			return "";
		}
	}
	
	public static void main(String[] args) {
		String regex = "共 \\d+ 条";
		String input = "显示 1 到 9 ，共 9 条";
		String matchedString = getMatchedString(regex,input);
		String regex2 = "\\d+";
		String matchedString2 = getMatchedString(regex2,matchedString);
		System.out.println(matchedString2);
	}
}
