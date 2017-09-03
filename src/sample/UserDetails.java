package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author admin
 */
public class UserDetails {

    private final StringProperty textv;
    private final StringProperty text;

    //Default constructor

    public UserDetails(String textv, String text) {

        this.textv = new SimpleStringProperty(textv);
        this.text = new SimpleStringProperty(text);

    }

    //Getters

    public String gettextv() {
        return textv.get();
    }

    public String gettext() {
        return text.get();
    }


    //Setters

    public void settextv(String value) {
        textv.set(value);
    }
    public void settext(String value) {
        text.set(value);
    }



    //Property values
    public StringProperty textvProperty() {
        return textv;
    }
    public StringProperty textProperty() {
        return text;
    }



}
