package fr.callidos.account.controllers;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import fr.callidos.account.models.User;
import fr.callidos.account.payload.request.LoginRequest;
import fr.callidos.account.payload.request.SignupRequest;
import fr.callidos.account.payload.response.JwtResponse;
import fr.callidos.account.payload.response.MessageResponse;
import fr.callidos.account.repository.UserRepository;
import fr.callidos.account.security.jwt.JwtUtils;
import fr.callidos.account.security.services.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/account")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        String clientIp = request.getRemoteAddr();
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        user.setLogin_ip(clientIp);
        user.setLast_login(new Date());
        userRepository.save(user);

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Username is already use."));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Email is already use."));
        }

        if (!signUpRequest.getSex().equals("M") && !signUpRequest.getSex().equals("F")) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Invalid value, use M or F."));
        }

        if (signUpRequest.getPassword().length() < 8 || signUpRequest.getPassword().length() > 50) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Password must be at least 8 characters long or 50 max."));
        }

        if (signUpRequest.getPhone().length() > 10) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Invalid phone number."));
        }

        if (signUpRequest.getLastName().length() > 30 || signUpRequest.getLastName().length() < 3) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Min of 3 chars and 30 max for lastName."));
        }

        if (signUpRequest.getName().length() > 20 || signUpRequest.getName().length() < 3) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Min of 3 chars and 20 max for the name."));
        }

        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()), signUpRequest.getName(), signUpRequest.getLastName(), signUpRequest.getPhone(), signUpRequest.getLocation(), signUpRequest.getSex());
        String clientIp = request.getRemoteAddr();
        user.setRegister_ip(clientIp);
        user.setName(signUpRequest.getName());
        user.setLastName(signUpRequest.getLastName());
        user.setRole("ROLE_USER");

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully, welcome to Helix !"));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> getAllusers(){
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> putUserById(@PathVariable Long id, @RequestBody User userDetails){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            if (userDetails.getUsername() != null){
                user.setUsername(userDetails.getUsername());
            }
            if (userDetails.getEmail() != null){
                user.setEmail(userDetails.getEmail());
            }
            if (userDetails.getPassword() != null){
                user.setPassword(userDetails.getPassword());
            }
            if (userDetails.getName() != null){
                user.setName(userDetails.getName());
            }
            if (userDetails.getPhone() != null){
                user.setPhone(userDetails.getPhone());
            }
            if (userDetails.getLastName() != null){
                user.setLastName(userDetails.getLastName());
            }
            if (userDetails.getRole() != null){
                user.setRole(userDetails.getRole());
            }
            final User putuser = userRepository.save(user);
            return ResponseEntity.ok(putuser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        if (!userRepository.existsById(id)){
            return ResponseEntity.badRequest().body(new MessageResponse("User not found."));
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("User number " + id + " deleted successfully."));
    }

}
