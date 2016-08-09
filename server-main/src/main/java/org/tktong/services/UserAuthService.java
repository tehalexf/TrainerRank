package org.tktong.services;

import java.util.concurrent.Future;

import com.pokegoapi.api.PokemonGo;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.tktong.Trainer;

@Service
public class UserAuthService {

    @Async
    public void findUser(PokemonGo go) throws InterruptedException {
     
    }
}