package com.crawler.service;

import org.springframework.stereotype.Service;

import com.crawler.model.TableMeta;
import com.crawler.model.ColumnMeta;
import com.crawler.model.ForeignKeyMeta;

@Service
public class ModelGeneratorService {

    
    public String generateModelClass(TableMeta table) {

        String className = capitalize(table.getTableName());

       
        String result = "public class " + className + " {\n\n";

        
        for (ColumnMeta col : table.getColumns()) {
            String javaType = mapToJavaType(col.getType());
            result += "    private " + javaType + " " + col.getName() + ";\n";
        }

       
        for (ForeignKeyMeta fk : table.getForeignKeys()) {
            String relatedClass = capitalize(fk.getReferencedTable());
            result += "    private " + relatedClass + " " + fk.getColumnName() + ";\n";
        }

        result += "\n}";
        return result;
    }

    private String capitalize(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

   
    private String mapToJavaType(String mysqlType) {

        String type = mysqlType.toLowerCase();

        if (type.contains("varchar") || type.contains("text") || type.contains("char")) {
            return "String";
        }
        if (type.contains("int")) {
            return "int";
        }
        if (type.contains("bigint")) {
            return "long";
        }
        if (type.contains("double") || type.contains("float") || type.contains("decimal")) {
            return "double";
        }
        if (type.contains("date") || type.contains("time")) {
            return "java.util.Date";
        }

        return "String"; 
    }
}
