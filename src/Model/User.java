package Model;

import java.util.UUID;

public class User {
    private String uuid;
    private String name;
    private String password;
    private String mail;

    public User(Boolean genUUID){
        if(genUUID){
            uuid = UUID.randomUUID().toString().replaceAll("-", "");
        }
        else {
            uuid = null;
        }
        name = null;
        password = null;
        mail = null;
    }

    public String getUUID() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    public void setUUID(String UUID) {
        this.uuid = UUID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
