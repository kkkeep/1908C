package com.m.k.seetaoism.data.entity;


import java.util.ArrayList;

/**
 * {
 *     "code": 1,
 *     "message": "操作成功",
 *     "data": {
 *         "list": {
 *             "my_column": [
 *                 {
 *                     "id": "recommend",
 *                     "name": "推荐",
 *                     "type": 1,
 *                     "back_color": "E04968"
 *                 },
 *                 {
 *                     "id": "6",
 *                     "name": "战略",
 *                     "type": 2,
 *                     "back_color": "003372"
 *                 },
 *                 {
 *                     "id": "14",
 *                     "name": "工程",
 *                     "type": 2,
 *                     "back_color": "4A8950"
 *                 },
 *                 {
 *                     "id": "10",
 *                     "name": "一带一路",
 *                     "type": 2,
 *                     "back_color": "2883B0"
 *                 },
 *                 {
 *                     "id": "29",
 *                     "name": "机械",
 *                     "type": 2,
 *                     "back_color": "A18A6D"
 *                 },
 *                 {
 *                     "id": "28",
 *                     "name": "特写",
 *                     "type": 2,
 *                     "back_color": "C85306"
 *                 },
 *                 {
 *                     "id": "27",
 *                     "name": "社评",
 *                     "type": 2,
 *                     "back_color": "F6B051"
 *                 },
 *                 {
 *                     "id": "42",
 *                     "name": "即时",
 *                     "type": 2,
 *                     "back_color": "E03A2E"
 *                 },
 *                 {
 *                     "id": "39",
 *                     "name": "传承",
 *                     "type": 2,
 *                     "back_color": "9149B4"
 *                 }
 *             ],
 *             "more_column": []
 *         }
 *     }
 * }
 */
public class ColumnData {

    private ColumnInfo list;


    public ColumnInfo getList() {
        return list;
    }

    public void setList(ColumnInfo list) {
        this.list = list;
    }

    public class ColumnInfo{

        private ArrayList<Column> my_column;
        private ArrayList<Column> more_column;

        public ArrayList<Column> getMyColumn() {
            return my_column;
        }

        public void setMyColumn(ArrayList<Column> myColumn) {
            this.my_column = myColumn;
        }

        public ArrayList<Column> getMoreColumn() {
            return more_column;
        }

        public void setMoreColumn(ArrayList<Column> moreColumn) {
            this.more_column = moreColumn;
        }
    }



    public class Column{
        /**
         * {
         *                     "id":"recommend",
         *                     "name":"推荐",
         *                     "type":1,
         *                     "back_color":"E04968"
         *                 },
         */



        private String id;
        private String back_color;
        private String name;
        private int type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBackColor() {
            return back_color;
        }

        public void setBackColor(String backColor) {
            this.back_color = backColor;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "Column{" +
                    "id='" + id + '\'' +
                    ", back_color='" + back_color + '\'' +
                    ", name='" + name + '\'' +
                    ", type=" + type +
                    '}';
        }
    }
}
