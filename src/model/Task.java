package model;

/*
* Entity Task
* */
public class Task {

    /*
    * id
    * */
    private Long id;

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
    public Task(long id, String value) {
        this.id = id;
        this.value = value;
    }

    /*
    * Get id
    *
    * @return id
    * */
    public Long getId() {
        return id;
    }

    /*
    * Set id
    *
    * @param id Id
    * */
    public void setId(long id) {
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
