package scoreapp.testtask.com.presentation.model;

import com.evrencoskun.tableview.sort.ISortableModel;

public class Cell implements ISortableModel {

    private String id;
    private Object data;

    public Cell(String id) {
        this.id = id;
    }

    public Cell(String id, Object data) {
        this.id = id;
        this.data = data;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Object getContent() {
        return data;
    }

    public Object getData() {
        return data;
    }

    public void setData(String data) { this.data = data; }

}
