package com.wendal.java.dex.decomplier.toolkit;

public final class String_Toolkit {

    public static String parseType(String src) {
        // �ο�
        // /Dex_decomplier/dex/com/wendal/dex/simple/easy/SimpleClass_fields.dump.txt
        String tmp = "";

        // ���src��[��ͷ,��ʾ��Ϊһ������
        String srcA = src.replaceAll("\\[", "");
        int tt = countC(src, 0);

        if ("I".equals(srcA))
            tmp = "int";
        if ("S".equals(srcA))
            tmp = "short";
        if ("J".equals(srcA))
            tmp = "long";
        if ("Z".equals(srcA))
            tmp = "boolean";
        if ("C".equals(srcA))
            tmp = "char";
        if ("F".equals(srcA))
            tmp = "float";
        if ("D".equals(srcA))
            tmp = "double";
        if ("B".equals(srcA))
            tmp = "byte";

        // �����������û��ƥ��,��src�����������
        if (tmp.equals("")) {
            tmp = srcA;
            if(srcA.startsWith("L")){
                tmp = srcA.substring(1);
            }
        }

        for (int i = 0; i < tt; i++) {
            tmp += "[]";
        }

        // System.out.println(tmp);
        return tmp;
    }

    private static int countC(String src, int count) {
        // String tmp = "[]";
        if (src.startsWith("[")) {
            count++;
            return countC(src.substring(1), count);
        } else {
            return count;
        }
    }

    public static String parseSingleClassName(String src_srt) {
            String value_temp = src_srt.trim().replaceAll("'", "");
    //        String value_temp2 = value_temp.replaceAll(";", "");
    //        value_temp2 = value_temp2.replaceAll("\\(", "");
    //        value_temp2 = value_temp2.replaceAll("\\)", "");
    //        if(value_temp2.startsWith("IL")){
    //            value_temp2 = value_temp2.substring(2);
    //        }
            if(value_temp.startsWith("L")){
                value_temp = value_temp.substring(1);
            }
            return value_temp.replaceAll("/", ".");
        }

    
    public static String join(Object [] obj_list , String sp) {
        return "";
    }
}
