package YourTicket.controller;

import YourTicket.model.User;
import YourTicket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public @ResponseBody List<User> findAll() {
        return userRepository.findAll();
    }

    @PostMapping("/add")
    public @ResponseBody User register(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/update/{idUser}")
    public @ResponseBody User updateUser(@PathVariable(value = "idUser") Integer idUser, @RequestBody User user) {

        User newUser = userRepository.getOne(idUser);
        newUser.setName(user.getName());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        return userRepository.save(newUser);
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody boolean delete(@PathVariable("id") Integer id){
        List<User> users = this.findAll();
        for(User u : users)
            if(u.getIdUser().equals(id)) {
                userRepository.deleteById(id);
                return true;
            }
        return false;
    }

    @GetMapping("/login/{username}/{password}")
    public @ResponseBody boolean login(@PathVariable("username") String username,@PathVariable("password") String password) {
        List<User> users = this.findAll();
        for(User u : users)
            if(u.getUsername().equals(username) && u.getPassword().equals(password))
                return true;
        return false;
    }

}
