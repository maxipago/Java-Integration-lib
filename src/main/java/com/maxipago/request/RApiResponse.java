package com.maxipago.request;

import com.maxipago.Record;
import com.maxipago.ResultSetInfo;

import java.util.List;

public class RApiResponse {
    public String errorCode;
    public String errorMsg;
    public String command;
    public String time;
    public ResultSetInfo resultSetInfo;
    public List<Record> records;
}
