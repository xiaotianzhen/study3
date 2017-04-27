package com.qianwang.eventbus.bean;

import java.util.List;

/**
 * Created by sky on 2017/4/26.
 */

public class Event {

    /** 列表加载事件 */
    public static class ItemListEvent
    {
        private List<Item> items;

        public ItemListEvent(List<Item> items)
        {
            this.items = items;
        }

        public List<Item> getItems()
        {
            return items;
        }
    }
}
