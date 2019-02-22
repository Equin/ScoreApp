package scoreapp.testtask.com.presentation.model;

public class StatisticItemModelImpl implements StatisticItemModel {
    private String name;
    private int value;

    public StatisticItemModelImpl(String name) {
        this.name = name;
        this.value = 0;
    }

    @Override
    public void incrementValue() {
        this.value++;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addToValue(int value) {
        this.value += value;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}