// package org.tktong;

// import java.util.concurrent.Future;

// import org.springframework.scheduling.annotation.Async;
// import org.springframework.scheduling.annotation.AsyncResult;
// import org.springframework.stereotype.Service;

// @Service
// public class UserInitialAuthService {

//     RestTemplate restTemplate = new RestTemplate();

//     @Async
//     public Future<Trainer> findUser(String token) throws InterruptedException {
//         System.out.println("Looking Token " + token);
//         // User results = restTemplate.getForObject("https://api.github.com/users/" + user, User.class);


//         return new AsyncResult<User>(results);
//     }

//     @Async
//     public Future<User> findUser(String user, String password) throws InterruptedException {
//         System.out.println("Looking up " + user);
//         // User results = restTemplate.getForObject("https://api.github.com/users/" + user, User.class);


//         return new AsyncResult<User>(results);
//     }

// }