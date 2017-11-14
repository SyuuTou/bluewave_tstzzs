package com.lhjl.tzzs.proxy.utils;

import com.lhjl.tzzs.proxy.dto.HistogramList;

import java.util.Comparator;

public class ComparatorHistogramList implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {

        HistogramList user0=(HistogramList)o1;
        HistogramList user1=(HistogramList)o2;

        //首先比较年龄，如果年龄相同，则比较名字

        int flag=user0.getMoney().compareTo(user1.getMoney());


        return flag;
    }
}
