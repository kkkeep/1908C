package com.m.k.seetaoism.data.entity;

import java.util.ArrayList;
import java.util.List;

/*
 * created by Cherry on 2020-01-08
 **/
public class ColumnData {

    private ColumnList list;


    public ColumnList getList() {
        return list;
    }

    public void setList(ColumnList list) {
        this.list = list;
    }

    public class ColumnList{

        private ArrayList<Column> my_column;
        private ArrayList<Column> more_column;


        public ArrayList<Column> getMyColumn() {
            return my_column;
        }

        public void setMyColumn(ArrayList<Column> myColumn) {
            this.my_column = myColumn;
        }

        public ArrayList<Column> getMore_column() {
            return more_column;
        }

        public void setMore_column(ArrayList<Column> more_column) {
            this.more_column = more_column;
        }
    }


    public class Column{

        private String id;
        private String name;
        private String back_color;
        private int type;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBack_color() {
            return back_color;
        }

        public void setBack_color(String back_color) {
            this.back_color = back_color;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
