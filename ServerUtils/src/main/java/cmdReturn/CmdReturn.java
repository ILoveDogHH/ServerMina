package cmdReturn;

import com.alibaba.fastjson.JSONArray;

public class CmdReturn {
    private static final int SUCCESS = 1;

    private static final int ERROR = 0;


    public static JSONArray success(Object data){
        JSONArray array = new JSONArray();
        array.add(SUCCESS);
        array.add(data);
        return array;
    }

    public static JSONArray error(Object data){
        JSONArray array = new JSONArray();
        array.add(ERROR);
        array.add(data);
        return array;
    }
}
