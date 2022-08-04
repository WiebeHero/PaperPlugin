package me.WiebeHero.Blessing;

import java.util.UUID;

public abstract class Blessing {

    protected UUID uuid;

    public Blessing(UUID uuid){
        this.uuid = uuid;
    }

    public UUID getUuid(){
        return this.uuid;
    }
}
