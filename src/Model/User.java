package Model;

import java.util.UUID;

public class User {
    private String uuid;
    private String name;
    private String password;
    private String mail;

    private Boolean uuidLocked = false;

    public User(Boolean genUUID){
        if(genUUID){
            uuid = UUID.randomUUID().toString().replaceAll("-", "");
            uuidLocked = true;
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
        if (!uuidLocked)
            this.uuid = UUID;
        else
            System.out.println("这是个新生成的用户，无法更改uuid");
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
