package com.crawler.service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crawler.model.ColumnMeta;
import com.crawler.model.TableMeta;
import com.crawler.model.ForeignKeyMeta;


@Service
public class DatabaseCrawlerService {

    private DatabaseConnectionService connectionService = new DatabaseConnectionService();

    public List<TableMeta> getAllTables() throws Exception {

        Connection conn = connectionService.getConnection();
        DatabaseMetaData metaData = conn.getMetaData();

        List<TableMeta> tables = new ArrayList<>();

        ResultSet rs = metaData.getTables(null, null, "%", new String[]{"TABLE"});

        while (rs.next()) {

            String tableName = rs.getString("TABLE_NAME");
            TableMeta tableMeta = new TableMeta(tableName);

          

            ResultSet pkRs = metaData.getPrimaryKeys(null, null, tableName);
            while (pkRs.next()) {
                String pkColumn = pkRs.getString("COLUMN_NAME");
                tableMeta.addPrimaryKey(pkColumn);
          
            }

            ResultSet fkRs = metaData.getImportedKeys(null, null, tableName);
            while (fkRs.next()) {
                String fkColumn = fkRs.getString("FKCOLUMN_NAME");
                String pkTable = fkRs.getString("PKTABLE_NAME");
                String pkColumn = fkRs.getString("PKCOLUMN_NAME");

                tableMeta.addForeignKey(new ForeignKeyMeta(fkColumn, pkTable, pkColumn));

            }

           
            ResultSet colRs = metaData.getColumns(null, null, tableName, "%");
            while (colRs.next()) {
                String columnName = colRs.getString("COLUMN_NAME");
                String type = colRs.getString("TYPE_NAME");
                int size = colRs.getInt("COLUMN_SIZE");
                boolean nullable = colRs.getInt("NULLABLE") == DatabaseMetaData.columnNullable;

                tableMeta.addColumn(new ColumnMeta(columnName, type, size, nullable));

              
            }

            tables.add(tableMeta);
        }

        conn.close();
        return tables;
    }

    public static void main(String[] args) throws Exception {
        DatabaseCrawlerService service = new DatabaseCrawlerService();
        service.getAllTables();
    }
}
