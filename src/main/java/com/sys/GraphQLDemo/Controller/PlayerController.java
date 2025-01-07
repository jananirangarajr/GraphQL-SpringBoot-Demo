package com.sys.GraphQLDemo.Controller;

import com.sys.GraphQLDemo.model.Player;
import com.sys.GraphQLDemo.model.Team;
import com.sys.GraphQLDemo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @QueryMapping
    public List<Player> findAll(){
        return playerService.findAll();
    }

    @QueryMapping
    public Player findOne(@Argument Integer id) {
        return playerService.findPlayer(id);
    }
    @MutationMapping
    public Player create(@Argument String name, @Argument Team team) {
        return playerService.create(name,team);
    }
    @MutationMapping
    public Player update(@Argument Integer id, @Argument String name, @Argument Team team) {
        return playerService.update(id,name,team);
    }
    @MutationMapping
    public Player delete(@Argument Integer id) {
        return playerService.delete(id);
    }

}
