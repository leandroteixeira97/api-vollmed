package med.voll.api.infraestructure.security;

public record JWTTokenDTO(String jwtToken) {

    public JWTTokenDTO(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
