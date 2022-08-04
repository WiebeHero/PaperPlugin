package me.WiebeHero.Abilities.Player;

import java.util.ArrayList;

public abstract class Ability {

    private String name;
    private ArrayList<String> description;

    public Ability(){

    }

    public abstract void activate();

}
