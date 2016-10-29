package com.zlw.mymodel.service;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟数据
 * Created by zlw on 2016/6/28 0028.
 */
public class DataService {
    private static DataService service;
    private List<Item> lists;

    private DataService() {
        lists = new ArrayList<Item>();
        initData();
    }

    public static DataService getInstance() {
        if (service == null) {
            service = new DataService();
        }
        return service;
    }

    private void initData() {
        for (int i = 0; i < 60; i++) {
            lists.add(new Item(0, "name" + i, "content" + i));
        }
    }


    public List<Item> getDataByStart(int start, int count) {
        int end = start + count;
        List<Item> data = new ArrayList<>();
        if (end > lists.size()) {
            end = lists.size();
        }
        data = lists.subList(start, end);
        return data;
    }

}
