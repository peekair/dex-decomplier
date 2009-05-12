//:~
/**
 * $Id$
 * 
 * $LastChangedBy$
 * @version $Revision$
 * 
 */
/*
 * This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.wendal.java.dex.decomplier.javafile.model.statement;

import com.wendal.java.dex.decomplier.javafile.model.PrototypeStatement;
import com.wendal.java.dex.decomplier.toolkit.String_Toolkit;

public class PrototypeStatement_Invoke_Static extends PrototypeStatement {

    public String class_name;
    
    public String method_name;
    
    public String [] parameters;
    
    public boolean hasRetrun = false;
    
    public PrototypeStatement_Invoke_Static() {
        
    }
    
    @Override
    public void parse() {
        super.parse();
        String tmp = info.substring(info.indexOf("{") + 1, info.indexOf("}"));
        parameters = tmp.split(",");
        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = parameters[i].trim();
        }
        
        String tmp_str = info.substring(info.indexOf(", L")+1, info.indexOf(";"));
        class_name = String_Toolkit.parseSingleClassName(tmp_str).replaceAll(";", "");
        
        method_name = info.substring(info.indexOf(".")+1, info.indexOf(":"));
        
        if(info.trim().endsWith(")V")){
            hasRetrun = false;
        }else{
            hasRetrun = true;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
//        sb.append(super.toString()).append("\n");
        
        sb.append(class_name).append(".").append(method_name);
        sb.append("(");
        for (int i = 0; i < parameters.length; i++) {
            sb.append(parameters[i]);
            if(i < parameters.length -1){
                sb.append(" ,");
            }
        }
        sb.append(")");
//        sb.append("\n/*hasRetrun -->").append(hasRetrun).append("*/");
        return sb.toString();
    }
}