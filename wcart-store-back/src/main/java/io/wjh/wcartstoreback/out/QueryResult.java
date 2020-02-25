package io.wjh.wcartstoreback.out;

import java.util.List;

public class QueryResult<T> {
    //数据列表
    private List<T> list;
    //数据总数
    private long total;
}