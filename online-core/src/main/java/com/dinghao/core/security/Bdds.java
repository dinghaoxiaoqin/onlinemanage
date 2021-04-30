//package com.dinghao.core.security;
//
//import lombok.Data;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Data
//public class Bdds {
//
//    private String name;
//
//    private String nickName;
//
//
//
//    public Bdds() {
//    }
//
//    public Bdds(AddS addS){
//        this.name = addS.getName();
//        this.nickName = addS.getNickName();
//    }
//    public static void main(String[] args) {
//        List<AddS> list = new ArrayList<>();
//        AddS addS1 = new AddS("1","张三","张");
//        AddS addS2 = new AddS("2","李四","李");
//        AddS addS3 = new AddS("3","王五","王");
//        list.add(addS1);
//        list.add(addS2);
//        list.add(addS3);
//        List<Bdds> bdds = list.stream().map(Bdds::new).collect(Collectors.toList());
//        for (Bdds bdd : bdds) {
//            System.out.println("获取的数据："+bdd);
//        }
//
//    }
//
//
//}
