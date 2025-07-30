package com.workintech.s19d1.controller;

import com.workintech.s19d1.dto.ActorRequest;
import com.workintech.s19d1.dto.ActorResponse;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.ActorService;
import com.workintech.s19d1.util.Converter;
import com.workintech.s19d1.util.HollywoodValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/actor")
public class ActorController {

    private ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public List<ActorResponse> findAll(){
        List<Actor> allActors = actorService.findAll();
        return Converter.actorResponseConvert(allActors);
    }



    @GetMapping("/{id}")
    public ActorResponse findById(@PathVariable long id){
        HollywoodValidation.checkId(id);
        Actor actor=actorService.findById(id);
        return Converter.actorResponseConvert(actor);
    }


    @PostMapping
    public ActorResponse save(@Validated @RequestBody ActorRequest actorRequest){
        Actor actor=actorRequest.getActor();
        List<Movie> movies=actorRequest.getMovies();

        for(Movie movie:movies){
            actor.addMovie(movie);
        }
        Actor savedActor = actorService.save(actor);
        return Converter.actorCreateResponseConvert(savedActor);
    }

    @PutMapping("/{id}")
    public ActorResponse update(@RequestBody Actor actor,@PathVariable long id){
        Actor foundActor=actorService.findById(id);
        actor.setMovies(foundActor.getMovies());
        actor.setId(id);
        actorService.save(actor);
        return Converter.actorResponseConvert(actor);
    }

    @DeleteMapping("/{id}")
    public ActorResponse delete(@PathVariable long id){
        Actor foundActor=actorService.findById(id);

        actorService.delete(foundActor);

        return Converter.actorResponseConvert(foundActor);
    }
}