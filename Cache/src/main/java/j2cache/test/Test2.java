package j2cache.test;

import j2cache.spring.annotation.Cache;

public class Test2 {


    @Cache(key = "test5", expire = 100)
    public int test5() {
        System.out.println("test5");
        return 1;
    }




    public static void main(String[] args){



    }

}
