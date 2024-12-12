package functionalClass;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class SearchTool {

    public static ArrayList<Object> search(ArrayList<Object> dataToSearch, Class<?> classToSearch, 
                                        ArrayList<Object> searchFields, ArrayList<SearchCondition> conditions) {
        ArrayList<Object> result = new ArrayList<>();
        
        if (dataToSearch.size() == 0) return result;

        Field[] targetClassFields = classToSearch.getDeclaredFields();

        if (targetClassFields.length != searchFields.size()) {
            return result;
        }

        for (Object dataObject : dataToSearch) {
            boolean match = true;

            if (!dataObject.getClass().equals(classToSearch)) {
                continue;
            }

            for (int i = 0; i < targetClassFields.length; i++) {
                Field field = targetClassFields[i];
                field.setAccessible(true);
                
                try {
                    Object fieldValue = field.get(dataObject);
                    Object searchField = searchFields.get(i);
                    SearchCondition condition = conditions.get(i);

                    if (!matchCondition(fieldValue, searchField, condition)) {
                        match = false;
                        break;
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    match = false;
                    break;
                }
            }

            if (match) {
                result.add(dataObject);
            }
        }
        
        return result;
    }

    private static boolean matchCondition(Object fieldValue, Object searchField, SearchCondition condition) {
        if (condition == SearchCondition.NONCONDITION) return true;

        else if (condition == SearchCondition.MATCH) {
            if (searchField instanceof Limit) {
                Limit<?> limit = (Limit<?>) searchField;
                Comparable<Object> value = (Comparable<Object>) fieldValue;
                return value.compareTo(limit.smallerValue) >= 0 && value.compareTo(limit.biggerValue) <= 0;
            }
            return fieldValue.equals(searchField);
        } else if (condition == SearchCondition.INCLUDE) {
            return fieldValue.toString().contains(searchField.toString());
        } else if (condition == SearchCondition.NONINCLUDE) {
            return !fieldValue.toString().contains(searchField.toString());
        }
        return false;
    }

    public enum SearchCondition {

        NONCONDITION, MATCH, INCLUDE, NONINCLUDE;

    }

    public static class Limit<T> {
        public final T smallerValue;
        public final T biggerValue;

        public Limit(T smallerValue, T biggerValue) {
            this.smallerValue = smallerValue;
            this.biggerValue = biggerValue;
        }
    }
}
