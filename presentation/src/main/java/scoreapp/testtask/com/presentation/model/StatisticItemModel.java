package scoreapp.testtask.com.presentation.model;

public interface StatisticItemModel {
    void incrementValue();
    void addToValue(int value);
    void setValue(int value);
    void setName(String name);
    int getValue();
    String getName();
}
