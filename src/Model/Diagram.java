package Model;

import java.util.UUID;

public class Diagram {
    private String uuid;
    private String name;
    private String path;

    private Boolean uuidLocked = false;

    public Diagram(Boolean genUUID){
        if(genUUID){
            uuid = UUID.randomUUID().toString().replaceAll("-", "");
        }
        else {
            uuid = null;
        }
        name = null;
    }

    public String getUUID() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public void setUUID(String UUID) {
        if (!uuidLocked)
            this.uuid = UUID;
        else
            System.out.println("这是个新生成的图表，无法更改uuid");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
