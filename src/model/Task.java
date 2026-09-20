package model;

/*
* Entity Task
* */
public class Task {

    /*
    * id
    * */
    private int id;

    /*
    * value
    * */
    private String value;


    /*
    * Default Constructor
    * */
    public Task() {}

    /*
    * Constructor
    *
    * @param id Id
    * @param value Value
    * */
    public Task(int id, String value) {
        this.id = id;
        this.value = value;
    }

    /*
    * Get id
    *
    * @return id
    * */
    public int getId() {
        return id;
    }

    /*
    * Set id
    *
    * @param id Id
    * */
    public void setId(int id) {
        this.id = id;
    }

    /*
    * Get value
    *
    * @return value
    * */
    public String getValue() {
        return value;
    }

    /*
    * Set value
    *
    * @param value Value
    * */
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
