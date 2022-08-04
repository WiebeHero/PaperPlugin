package me.WiebeHero.Abilities.Player;

import me.WiebeHero.Main.Main;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class BlazingTornado extends Ability{

    private Main main;
    private BukkitTask task;
    private float radius, height;
    private int particleLayers, maxParticles;
    private Location location;
    private int moveTimes, timer;

    public BlazingTornado(Main main, Location location, float radius, float height, int particleLayers){
        this.main = main;
        this.location = location;
        this.radius = radius;
        this.height = height;
        this.particleLayers = particleLayers;
        this.timer = 0;
        this.moveTimes = 30;
    }

    public void activate(){
        new BukkitRunnable(){
            public void run(){
                if(timer < moveTimes){
                    ArrayList<Location> locations = new ArrayList<Location>();
                    flames(true, locations,1);
                    spawnParticles(locations);
                    location.add(location.getDirection());
                    damage();
                    timer++;
                }
                else{
                    cancel();
                }
            }
        }.runTaskTimer(this.main, 0L, 3L);
    }

    public void flames(boolean first, ArrayList<Location> locations, int currentParticleLayer){
        if(first){
            this.location.getWorld().spawnParticle(Particle.FLAME, location, 1, 0, 0, 0, 0, null, false);
            this.flames(false, locations, currentParticleLayer);
        }
        else{
            if(currentParticleLayer <= particleLayers){
                float currentRadius = radius / (float)particleLayers * (float)currentParticleLayer;
                float currentHeight = height / (float)particleLayers * (float)currentParticleLayer;
                int maxParticles = currentParticleLayer * 2;
                double angle = 360.00 / ((double)currentParticleLayer * 2.0);
                for(int i = 0; i < maxParticles; i++){
                    double radians = (angle * (double)i) * Math.PI / 180.0F;
                    Vector vector = new Vector(currentRadius, 0, 0);
                    double x = Math.cos(radians) * vector.getX() - Math.sin(radians) * vector.getZ();
                    double z = Math.cos(radians) * vector.getZ() + Math.sin(radians) * vector.getX();
                    locations.add(location.clone().add(x, currentHeight, z));
                }
                this.flames(false, locations, currentParticleLayer + 1);
            }
        }
    }

    public void spawnParticles(ArrayList<Location> locations){
        new BukkitRunnable(){
            int index = 0;
            public void run(){
                for(int i = 0; i < locations.size(); i++){
                    Location loc = locations.get(i);
                    loc.getWorld().spawnParticle(Particle.FLAME, loc, 1, 0, 0, 0, 0, null, false);
                }
                cancel();
            }
        }.runTaskAsynchronously(this.main);
    }

    public void damage(){
        Collection entities = this.location.getNearbyEntities(this.radius, this.height, this.radius);
        Iterator<Entity> entitiesIterator = entities.iterator();
        while(entitiesIterator.hasNext()){
            Entity entity = entitiesIterator.next();
            if(entity instanceof LivingEntity){
                LivingEntity mob = (LivingEntity) entity;
                mob.damage(2.0);
                mob.setFireTicks(mob.getFireTicks() + 60);
            }
        }

    }
}
