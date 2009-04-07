package com.wendal.java.dex.decomplier.dexfile.model;

import java.util.ArrayList;

public class Dex_Method {
    
    private String name;
    private String type; //����ֵ
    private String access_flag;
    
//    private ArrayList<String> code_list;

//    private int registers;
//    private int ins;
//    private int outs;
//    private String insns_size;
    
    private ArrayList<String> opcodes_list = new ArrayList<String>();

    private ArrayList<String> catches_list = new ArrayList<String>();
//    private ArrayList<String> positions_list = new ArrayList<String>();
    private ArrayList<String> locals_list = new ArrayList<String>();
    
    private ArrayList<String> method_data;
    
    public void parse() {
        if(method_data != null){
            {
                String[] data_array = method_data.get(0).split(":");
                this.name = data_array[1].replaceAll("'", "").trim();
            }
            {
                //����������!
                String[] data_array = method_data.get(1).split(":");
                this.type = Dex_AbstractClass.parseSingleClassName(data_array[1]);
            }
            {
                String[] data_array = method_data.get(2).split(":");
                this.access_flag = data_array[1];
            }
                
            {
                //�ӵ�9�п�ʼΪopcodes
                for (int i = 8; i < method_data.size(); i++) {
                    if(method_data.get(i).startsWith(StringTaken_Dex.catches)) break;
                    opcodes_list.add(method_data.get(i));
                }
            }
            
            {
                catches_list.clear();
                boolean flag = false;
                for (int i = 8; i < method_data.size(); i++) {
                    if(method_data.get(i).startsWith(StringTaken_Dex.catches)) {
                        flag = true;
                        continue;
                    }
                    if (method_data.get(i).startsWith(StringTaken_Dex.locals)) {
                        break;
                    }
                    if(flag){
                        locals_list.add(method_data.get(i));
                    }
                } 
            }
            
            //����locals
            {
                locals_list.clear();
                boolean flag = false;
                for (int i = 8; i < method_data.size(); i++) {
                    if(method_data.get(i).startsWith(StringTaken_Dex.locals)) {
                        flag = true;
                        continue;
                    }
                    if(flag){
                        locals_list.add(method_data.get(i));
                    }
                } 
            }
        }
    }
    
    public ArrayList<String> getMethod_data() {
        return method_data;
    }

    public void setMethod_data(ArrayList<String> method_data) {
        this.method_data = method_data;
    }

    @Override
    public String toString() {
        return "<"+access_flag+"> #"+type + "# "+name + " <-- "+opcodes_list.size();
    }
}
