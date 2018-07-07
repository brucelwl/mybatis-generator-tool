package org.mybatis.generator.internal.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bruce on 2018/7/7 15:49
 */
public class OracleCommentsIntrospector {

    private void close(PreparedStatement pre, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pre != null) {
            try {
                pre.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * get the comment of tableName
     */
    public String getTableComment(Connection connection, String tableName) throws SQLException {
        String remarks = "";
        PreparedStatement preparedTable = null;
        ResultSet tableCommentRes = null;
        try {
            String tableCommentSql = "SELECT COMMENTS FROM user_tab_comments where TABLE_NAME = ? ";
            preparedTable = connection.prepareStatement(tableCommentSql);
            preparedTable.setString(1, tableName);
            tableCommentRes = preparedTable.executeQuery();
            if (tableCommentRes.next()) {
                remarks = tableCommentRes.getString("COMMENTS");
            }
        } finally {
           this.close(preparedTable, tableCommentRes);
        }
        return remarks;
    }

    /**
     * get the comment of tableName's columns
     */
    public Map<String, String> getColComments(Connection connection, String tableName) throws SQLException {
        PreparedStatement preparedCol = null;
        ResultSet colCommentRes = null;
        Map<String, String> colComments = new HashMap<>();
        try {
            String colCommentSql = "SELECT COLUMN_NAME,COMMENTS FROM user_col_comments where table_name = ? ";
            preparedCol = connection.prepareStatement(colCommentSql);
            preparedCol.setString(1, tableName);
            colCommentRes = preparedCol.executeQuery();
            while (colCommentRes.next()) {
                String columnName = colCommentRes.getString("COLUMN_NAME");
                String comment = colCommentRes.getString("COMMENTS");
                colComments.put(columnName, comment);
            }
        } finally {
            this.close(preparedCol, colCommentRes);
        }
        return colComments;
    }


}
