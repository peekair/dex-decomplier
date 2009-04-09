package com.wendal.java.dex.decomplier.javafile.model;

import java.util.ArrayList;

public class PrototypeStatement {

    String dex_offset;
    String opcodes;
    String line_index;

    String info;

    String note;
    
    boolean isString;

    /**
     * ��֪��ʽ dex_offset: opcodes |line_index info note
     * 
     * @param src_statement
     * @return
     */
    public static ArrayList<PrototypeStatement> newInstanceList(
            ArrayList<String> opcode_src) {
        ArrayList<PrototypeStatement> ps_list = new ArrayList<PrototypeStatement>();
        for (int i = 0; i < opcode_src.size(); i++) {
            String src_statement = opcode_src.get(i);
            PrototypeStatement ps = new PrototypeStatement();

            // �������ڵ����,ǰ6λΪƫ��
            ps.dex_offset = src_statement.substring(0, 6);

            int index_1 = src_statement.indexOf("|");

            ps.opcodes = src_statement.substring(7, index_1).trim();
            
            //String��opcodeΪ1a03
            //����String������ܰ�������,����Ҫ���⴦��
            if(ps.opcodes.startsWith("1a03")){
                ps.isString = true;
                int len = src_statement.indexOf("\"");
                while(true){
                    String src_tmp = src_statement.substring(len);
                    if(src_tmp.matches("\\\".+ // string@[0-9a-z]{4}$")){
                        System.out.println("Here---------------------------------------------------");
                        break;
                    }else{
                        i++;
                        src_statement+=opcode_src.get(i);
                    }
                }
            }

            ps.line_index = src_statement.substring(index_1 + 1, index_1 + 5);

            if (src_statement.lastIndexOf("//") > -1) {
                ps.info = src_statement.substring(index_1 + 6, src_statement
                        .lastIndexOf("//"));
                ps.note = src_statement.substring(src_statement
                        .lastIndexOf("//") + 3);
            }
//            System.out.println(ps.line_index);
        }
        return ps_list;
    }
}
