package med.voll.api.controller;

import med.voll.api.domain.user.AuthenticationInfoDTO;
import med.voll.api.domain.user.User;
import med.voll.api.infraestructure.security.JWTTokenDTO;
import med.voll.api.infraestructure.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<JWTTokenDTO> login(@RequestBody AuthenticationInfoDTO authenticationInfoDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authenticationInfoDTO.username(), authenticationInfoDTO.password());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        String JWTToken = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new JWTTokenDTO(JWTToken));
    }
}
