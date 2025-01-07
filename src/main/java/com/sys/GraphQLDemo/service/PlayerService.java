package com.sys.GraphQLDemo.service;

import com.sys.GraphQLDemo.model.Player;
import com.sys.GraphQLDemo.model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {

    List<Player> players = new ArrayList<>();
    AtomicInteger id = new AtomicInteger(0);

    public List<Player> findAll(){
        return players;
    }

    public Player findPlayer(Integer id){
        return players.stream().filter(x -> id == x.id()).findFirst().get();
    }

    public Player create(String name, Team team){

        Player player = new Player(id.incrementAndGet(),name,team);
        players.add(player);
        return player;
    }
    public Player update(Integer id, String name, Team team){

        Optional<Player> player = players.stream().filter(x -> x.id() == id).findFirst();
        if (player.isPresent()) {
            Player foundPlayer = player.get();
            Player newPlayer = new Player(id,name,team);
            players.set(players.indexOf(foundPlayer), newPlayer);
            return newPlayer;
        }
        else
            throw new IllegalArgumentException("Invalid Player");
    }

    public Player delete(Integer id) {
        Optional<Player> player = players.stream().filter(x -> x.id() == id).findFirst();
        if (player.isPresent()) {
            players.remove(player.get());
            return player.get();
        }
        else
            throw new IllegalArgumentException("Invalid Player");
    }

    @PostConstruct
    public void init() {
        players.add(new Player(id.incrementAndGet(), "Sachin", Team.MI));
        players.add(new Player(id.incrementAndGet(), "MS Dhoni", Team.CSK));
        players.add(new Player(id.incrementAndGet(), "Virat", Team.RR));
        players.add(new Player(id.incrementAndGet(), "Hirthik", Team.DC));
    }
}
