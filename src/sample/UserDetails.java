package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author admin
 */
public class UserDetails {

    private final StringProperty name;

    //Default constructor

    public UserDetails(String name) {

        this.name = new SimpleStringProperty(name);

    }

    //Getters

    public String getname() {
        return name.get();
    }


    //Setters

    public void setname(String value) {
        name.set(value);
    }



    //Property values

    public StringProperty nameProperty() {
        return name;
    }



}
