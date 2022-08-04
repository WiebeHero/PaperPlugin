package me.WiebeHero.Blessing;

import java.util.ArrayList;
import java.util.UUID;

public class BlessingManager {

    private ArrayList<Blessing> blessing;

    public BlessingManager(){
        this.blessing = new ArrayList<Blessing>();
    }

    public Blessing getBlessing(UUID uuid){
        if (blessing != null) {
            for (int i = 0; i < this.blessing.size(); i++) {
                Blessing blessing = this.blessing.get(i);
                if (blessing.getUuid().equals(uuid)) {
                    return blessing;
                }
            }
        }
        return null;
    }

    public PlayerBlessing getPlayerBlessing(UUID uuid){
        if (blessing != null) {
            for (int i = 0; i < this.blessing.size(); i++) {
                Blessing blessing = this.blessing.get(i);
                if (blessing.getUuid().equals(uuid) && blessing instanceof PlayerBlessing) {
                    return (PlayerBlessing) blessing;
                }
            }
        }
        return null;
    }

}
