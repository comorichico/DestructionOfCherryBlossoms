package com.comorichico.destructionofcherryblossoms;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class DestructionOfCherryBlossoms extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onBlockDamage(BlockDamageEvent event){
        WoodList woodList = new WoodList();
        Block block = event.getBlock();
        Location location;
        int range = 6;
        int bottom = 5;
        int top = 20;
        Block searchBlock;
        for(Material wood: woodList.getWoodList()){
            if(block.getType() == wood){
                if(event.getItemInHand().getType() == Material.WOODEN_AXE){
                    location = block.getLocation();
                    int x = location.getBlockX();
                    int y = location.getBlockY();
                    int z = location.getBlockZ();
                    block.breakNaturally(event.getItemInHand());
                    for(int yy = y-bottom; yy <= y+top; yy++){
                        for(int xx = x-range; xx <= x+range; xx++){
                            for(int zz = z-range; zz <= z+range; zz++){
                                searchBlock = event.getPlayer().getWorld().getBlockAt(xx,yy,zz);
                                for(Material wood2: woodList.getWoodList()){
                                    if(searchBlock.getType() == wood2){
                                        searchBlock.breakNaturally(event.getItemInHand());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}