package com.dzytsiuk.userservice.dao.jdbc;

import com.dzytsiuk.userservice.dao.jdbc.annotation.Table;
import com.dzytsiuk.userservice.dao.jdbc.annotation.Column;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class UserQueryFormer {

    public String getAllQuery(Class clazz) {
        StringBuilder query = new StringBuilder("SELECT ");
        String tableName = getTableName(clazz);
        List<String> columnNames = getColumnNames(clazz, true);
        appendColumnNames(query, columnNames);
        query.append(" FROM ").append(tableName).append(";");

        return query.toString();
    }


    public String insertQuery(Object obj) {
        StringBuilder query = new StringBuilder("INSERT INTO ");

        Class clazz = obj.getClass();
        String tableName = getTableName(clazz);

        query.append(tableName);

        List<String> columnNames = getColumnNames(clazz, false);
        List<Object> valuesToInsert = getColumnValues(obj, clazz);

        query.append(" (");
        appendColumnNames(query, columnNames);
        query.append(")");
        appendColumnValues(query, valuesToInsert);
        query.append(");");
        return query.toString();
    }


    public String updateQuery(Object obj) {
        StringBuilder query = new StringBuilder("UPDATE ");
        Class clazz = obj.getClass();
        String tableName = getTableName(clazz);
        query.append(tableName).append(" SET ");
        List<String> columnNames = getColumnNames(clazz, false);
        List<Object> valuesToUpdate = getColumnValues(obj, clazz);
        String idName = getIdName(clazz);
        Object idValue = getIdValue(obj, obj.getClass());

        for (int i = 0; i < columnNames.size(); i++) {
            query.append(columnNames.get(i)).append(" = ").append(valuesToUpdate.get(i)).append(", ");
        }
        query.setLength(query.length() - 2);

        query.append(" WHERE ").append(idName).append(" = ").append(idValue).append(";");
        return query.toString();
    }

    public String deleteQuery(Object obj) {
        StringBuilder query = new StringBuilder("DELETE FROM ");
        String tableName = getTableName(obj.getClass());
        query.append(tableName).append(" WHERE ");
        query.append(getIdName(obj.getClass())).append(" = ").append(getIdValue(obj, obj.getClass())).append(";");
        return query.toString();
    }


    private String getTableName(Class clazz) {
        Table annotation = (Table) clazz.getAnnotation(Table.class);
        if (annotation == null) {
            throw new IllegalArgumentException("Annotation @Table does not exist");
        }
        return annotation.name().isEmpty() ? clazz.getName() : annotation.name();
    }

    private void appendColumnNames(StringBuilder query, List<String> columnNames) {
        for (String columnName : columnNames) {
            query.append(columnName).append(", ");
        }
        query.setLength(query.length() - 2);
    }

    private void appendColumnValues(StringBuilder query, List<?> columnValues) {
        query.append(" VALUES (");

        for (Object columnValue : columnValues) {
            query.append(columnValue).append(", ");
        }
        query.setLength(query.length() - 2);

    }

    private String getIdName(Class clazz) {
        String idFieldName = null;
        for (Field field : clazz.getDeclaredFields()) {
            Column columnAnnotation = field.getAnnotation(Column.class);
            if (columnAnnotation != null && columnAnnotation.pk()) {
                idFieldName = columnAnnotation.name().isEmpty() ? field.getName() : columnAnnotation.name();
            }
        }
        return idFieldName;
    }

    private Object getIdValue(Object obj, Class clazz) {
        Object columnValue = null;
        for (Field field : clazz.getDeclaredFields()) {
            Column columnAnnotation = field.getAnnotation(Column.class);
            if (columnAnnotation != null && columnAnnotation.pk()) {
                try {
                    field.setAccessible(true);
                    columnValue = field.get(obj);
                    field.setAccessible(false);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Error getting pk field value", e);
                }

            }
        }
        return columnValue;
    }


    private List<String> getColumnNames(Class clazz, boolean withID) {
        List<String> columnNames = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            Column columnAnnotation = field.getAnnotation(Column.class);
            if (columnAnnotation != null) {
                String columnName = columnAnnotation.name().isEmpty() ? field.getName() : columnAnnotation.name();
                if (columnAnnotation.pk() && withID) {
                    columnNames.add(columnName);
                } else if (!columnAnnotation.pk()) {
                    columnNames.add(columnName);
                }
            }
        }
        return columnNames;
    }

    private List<Object> getColumnValues(Object obj, Class clazz) {
        List<Object> columnValues = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            Column columnAnnotation = field.getAnnotation(Column.class);
            if (columnAnnotation != null && !columnAnnotation.pk()) {
                try {
                    field.setAccessible(true);
                    Object columnValue = field.get(obj);
                    if (field.getType().equals(String.class)) {
                        columnValues.add("'" + columnValue + "'");
                    } else {
                        columnValues.add(columnValue);
                    }
                    field.setAccessible(false);

                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Error getting field value", e);
                }

            }
        }
        return columnValues;
    }
}
