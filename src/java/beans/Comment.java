package beans;

public class Comment {
    private String firstname;
    private String lastname;
    private String text;
    private String date;

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }
}
