package com.zd.aoding.outsourcing.weChat.api.utils.file;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dikaerj {
	@SuppressWarnings("rawtypes")
	    public static List<String> descartes( List<List> strs) {
//	        String[] list = str.split("==");
//	        List<List> strs = new ArrayList<List>();
//	        for (int i = 0; i < list.length; i++) {
//	            strs.add(Arrays.asList(list[i].split(",")));
//	        }
	        System.out.println(strs);
	        int total = 1;
	        for (int i = 0; i < strs.size(); i++) {
	            total *= strs.get(i).size();
	        }
	        String[] mysesult = new String[total];
	        int now = 1;
	        //每个元素每次循环打印个数
	        int itemLoopNum = 1;
	        //每个元素循环的总次数
	        int loopPerItem = 1;
	        for (int i = 0; i < strs.size(); i++) {
	            List temp = strs.get(i);
	            now = now * temp.size();
	            //目标数组的索引值
	            int index = 0;
	            int currentSize = temp.size();
	            itemLoopNum = total / now;
	            loopPerItem = total / (itemLoopNum * currentSize);
	            int myindex = 0;
	            for (int j = 0; j < temp.size(); j++) {
	
	                //每个元素循环的总次数
	                for (int k = 0; k < loopPerItem; k++) {
	                    if (myindex == temp.size())
	                        myindex = 0;
	                    //每个元素每次循环打印个数
	                    for (int m = 0; m < itemLoopNum; m++) {
	                        mysesult[index] = (mysesult[index] == null ? "" : mysesult[index] + ",") + ((String) temp.get(myindex));
	                        index++;
	                    }
	                    myindex++;
	
	                }
	            }
	        }
	        return Arrays.asList(mysesult);
	    }
	
}

