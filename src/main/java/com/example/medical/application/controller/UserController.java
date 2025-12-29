package com.example.medical.application.controller;

import com.example.medical.application.user.dtos.LoginDTO;
import com.example.medical.application.user.dtos.UserRequestDTO;
import com.example.medical.application.user.dtos.UserResponseDTO;
import com.example.medical.domain.model.Users;
import com.example.medical.domain.repository.UserRepository;
import com.example.medical.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    // -------------------------
    // Constants
    // -------------------------
    private static final String ROLE_USER = "USER";
    private static final String AUTH_HEADER = "Authorization";
    private static final String USER_EMAIL_NOT_FOUND = "User with email %s not found";
    private static final String USER_ID_NOT_FOUND = "User with id %d not found";
    private static final String PASSWORD_MISMATCH = "Password did not match";
    private static  final String USER_NOT_FOUND="User not found";

    // Nested custom exceptions
    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) { super(message); }
    }

    public static class InvalidPasswordException extends RuntimeException {
        public InvalidPasswordException(String message) { super(message); }
    }

    // Signup
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> signup(@RequestBody UserRequestDTO dto){
        if (userRepository.findByEmail(dto.getEmail()).isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        Users user = Users.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(ROLE_USER)
                .age(dto.getAge())
                .gender(dto.getGender())
                .createdAt(Instant.now())
                .build();
        Users saveUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponseDTO(saveUser));
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginDTO logindto){
        Users user = userRepository.findByEmail(logindto.getEmail())
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_EMAIL_NOT_FOUND, logindto.getEmail())));

        if (!passwordEncoder.matches(logindto.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException(PASSWORD_MISMATCH);
        }

        UserResponseDTO response = new UserResponseDTO(user);
        String accesstoken = jwtUtil.generateAccessToken(user.getEmail(), user.getRole());
        String refreshtoken=jwtUtil.generateRefreshToken(user.getEmail());

        Map<String, String> tokens=new HashMap<>();
        tokens.put("accesstoken",accesstoken);
        tokens.put("refreshtoken",refreshtoken);
       response.setAccessToken(accesstoken);
       response.setRefreshToken(refreshtoken);
       // System.out.println();
        return ResponseEntity.ok(response);
    }

    // new endpoint for refresh token
    @PostMapping("/refresh")
    public ResponseEntity<Map<String,String>>refreshAccessToken(@RequestBody Map<String,String> request){
        String refreshToken=request.get("refreshToken");
        if(!jwtUtil.validateToken(refreshToken)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        }
        String email= jwtUtil.ExtractEmail(refreshToken);

        Users users=userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException(String.format(USER_NOT_FOUND)));
        String newAccessToken=jwtUtil.generateAccessToken(users.getEmail(), users.getRole());
        Map<String,String> response=new HashMap<>();
        response.put("accessToken",newAccessToken);
        response.put("refreshToken",refreshToken);

        return  ResponseEntity.ok(response);

    }

    // for logout
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader(AUTH_HEADER) String authHeader,@RequestBody Map<String,String> body){
        String accessToken=authHeader.replace("Bearer","").trim();
        String refreshToken=body.get("refreshToken");

        jwtUtil.blackListToken(accessToken);
        jwtUtil.blackListToken(refreshToken);
        boolean isBlackListed= jwtUtil.isTokenBlackListed(accessToken);
        System.out.println("Access token "+isBlackListed);


       boolean isRefreshTokenBlackListed= jwtUtil.isTokenBlackListed(refreshToken);
        System.out.println("Refresh token "+isRefreshTokenBlackListed);

        return ResponseEntity.ok().build();
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_ID_NOT_FOUND, id)));
        return ResponseEntity.ok(user);
    }

    // Get current user
    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getCurrentUser(@Valid @RequestHeader(AUTH_HEADER) String authHeader){


        String token = authHeader.replace("Bearer","").trim();
        if(jwtUtil.validateToken(token)){
            System.out.println("token is valid ");
        }
        if(!jwtUtil.validateToken(token)){
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }



        String email = jwtUtil.ExtractEmail(token);

        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_EMAIL_NOT_FOUND, email)));

        UserResponseDTO responseDTO=new UserResponseDTO(user);
        //responseDTO.setAccessToken(token);
      //  responseDTO.setRefreshToken(token);
        return ResponseEntity.ok(responseDTO);
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    // Global Exception Handler inside same controller
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<String> handleInvalidPassword(InvalidPasswordException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

}
