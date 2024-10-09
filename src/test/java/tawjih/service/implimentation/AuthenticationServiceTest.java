package tawjih.service.implimentation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import tawjih.auth.RegisterRequest;
import tawjih.config.JwtService;
import tawjih.enums.Role;
import tawjih.repository.PersonneRepository;



import static org.junit.jupiter.api.Assertions.*;

class AuthenticationServiceTest {


    @Mock
    private PersonneRepository personneRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void Register_RoleIsAdmin() {
        RegisterRequest request = new RegisterRequest();
        Role role = Role.ADMIN;

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authenticationService.register(request, role);
        });

        assertEquals("Cannot register new admin", exception.getMessage());
    }

    @Test
    public void testRegister_WithConfirmPasswordNotMatch() {
        RegisterRequest request = new RegisterRequest();
        request.setPassword("password");
        request.setConfirmPassword("differentPassword");

        Role role = Role.ETUDIANT;

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authenticationService.register(request, role);
        });

        assertEquals("password not equal to confirm password", exception.getMessage());
    }




}