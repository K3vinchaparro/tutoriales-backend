package ud.prueba.tecnica.controladores;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ud.prueba.tecnica.modelos.Usuario;
import ud.prueba.tecnica.seguridad.JwtRequest;
import ud.prueba.tecnica.seguridad.JwtResponse;
import ud.prueba.tecnica.seguridad.JwtUtils;
import ud.prueba.tecnica.seguridad.UserDetailsServiceImpl;

@RestController
@RequestMapping("/token")
@CrossOrigin("*")
public class AuthenticationControlador {

	@Autowired
    private AuthenticationManager authenticationManager;
	
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;
    
    @PostMapping("/generar/")
    public ResponseEntity<?> generarToken(@RequestBody JwtRequest jwtRequest) throws Exception {
    	
    	
    	autenticar(jwtRequest.getUsername(),jwtRequest.getPassword());

        UserDetails userDetails =  this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
    
    @GetMapping("/actual-usuario/")
    public Usuario obtenerUsuarioActual(Principal principal){
    	System.out.println(principal.getName());
        return (Usuario) this.userDetailsService.loadUserByUsername(principal.getName());
    }
    
    @GetMapping("/")
    public void mensaje(){
        System.out.println("bienvenido");
    }
    
    public void autenticar(String username,String password) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException exception){
            throw  new Exception("USUARIO DESHABILITADO " + exception.getMessage());
        }catch (BadCredentialsException e){
        	throw  new Exception("Credenciales invalidas " + e.getMessage());
        }
    }
    

}