package com.mateusferro.todolist.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/* Modificadores de acesso de classe
   1 - public
   2 - private
   3 - protected
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;
    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel) {
        var user = this.userRepository.findByUsername(userModel.getUsername());

        if(user != null){
            System.out.println("User already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists!");
        }
        var passwordHashred = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
        userModel.setPassword(passwordHashred);
        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.OK).body(userCreated);
    }
}