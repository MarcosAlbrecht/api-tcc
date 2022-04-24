package com.example.apitcc.controllers;
import java.util.List;
import com.example.apitcc.models.Endereco;
import com.example.apitcc.models.Usuario;
import com.example.apitcc.repository.EnderecoRespository;
import com.example.apitcc.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@EnableMongoRepositories
public class UsuarioController{

    private Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private PasswordEncoder encoder;
 
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private EnderecoRespository enderecoRepository;

    @ResponseBody
    public ResponseEntity<?> all() {
        return new ResponseEntity<>(usuarioRepository.findAll(), HttpStatus.FOUND);
    }

    
    @GetMapping(value = "/users")
    public List<Usuario> getAllUsers(){
        logger.info("Getting all users.");
        return usuarioRepository.findAll();
    }
    @GetMapping(value = "/usersid/{userId}")
    public Usuario getUserById(@PathVariable String userId) {
        logger.info("Getting users with ID: {}", userId);
        return usuarioRepository.findUsuarioById(userId);
    }

    @GetMapping(value = "/usersname/{username}")
    public List<Usuario> getUserByName(@PathVariable String username) {
        logger.info("Getting users with Name: {}", username);
        return usuarioRepository.findUsuarioByNome(username);
    } 

    @GetMapping(value = "/login")
    public Usuario getLogin(@RequestParam String email, @RequestParam String password) {
        logger.info("Getting login: {}", email);

        // Optional<Usuario> o = usuarioRepository.findUsuarioByEmail(email);
        
        // if (!o.isPresent())        
        //     return new ResponseEntity<>("{\"message\":\"incorrect email\"}", HttpStatus.NOT_FOUND);
        // else{
        //     boolean valid = encoder.matches(password, o.get().getPassword());
        //     if (valid) {
        //         return new ResponseEntity<>(usuarioRepository.findUsuarioByEmail(email), HttpStatus.FOUND);
        //     }else{
        //         return new ResponseEntity<>("{\"message\":\"incorrect password\"}", HttpStatus.NOT_FOUND);
        //     }    
        // }

        //return new ResponseEntity<>(usuarioRepository.findUsuarioByEmail(email), HttpStatus.FOUND);

        Usuario u = usuarioRepository.findUsuarioByEmail(email);

        if (u.getEmail() == null) {
            throw new IllegalStateException("not found users with email" + email);   
        }else{

            boolean valid = encoder.matches(password, u.getPassword());
            if (valid) {
                return usuarioRepository.findUsuarioByEmail(email); 
            }else{
                throw new IllegalStateException("bab password");
            }
        }           
    } 

    @GetMapping(value = "/loginId")
    public Usuario getLogin(@RequestParam String idUser) {
        logger.info("Getting login with ID: {}", idUser);


        Usuario u = usuarioRepository.findUsuarioById(idUser);

        if (u != null) {
            
            return u;   
        }else{
            throw new IllegalStateException("not found users with ID");
            
        }           
    } 

    @PutMapping(value = "/usersupdate/{userId}")
    public Usuario updateUser(@PathVariable String userId, @RequestBody Usuario usuario) {
        logger.info("Updating user with ID: {}", userId);
       

        Usuario pa = usuarioRepository.findUsuarioById(usuario.getId());
        //return postAdocaoRepository.save(postAdocao);
        if (pa != null) {       

            //buscar Ousuario conforme ID e seta o Usuario do PostAdocao
            Endereco end = enderecoRepository.findEnderecoById(usuario.getEndereco().getId());


            pa.setApelido(usuario.getApelido());
            pa.setCep(usuario.getCep());
            pa.setCpf(usuario.getCpf());
            pa.setDdd(usuario.getDdd());
            pa.setEmail(usuario.getEmail());
            pa.setEndereco(end);
            pa.setFoto(usuario.getFoto());
            pa.setIdade(usuario.getIdade());
            pa.setNome(usuario.getNome());

            //verifica se a senha foi alterada, se for gera uma nova hash,
            //se nao continua a mesma
            boolean valid = encoder.matches(usuario.getPassword(), pa.getPassword());
            if (valid) {
                pa.setPassword(usuario.getPassword()); 
            }else{
                pa.setPassword(encoder.encode(usuario.getPassword()));
            }
            pa.setTelefone(usuario.getTelefone());      

            return usuarioRepository.save(pa);   
        }else{
            throw new IllegalStateException("erro"+":"+"Ocorreu um erro inesperado");   
        }
    }

    @PostMapping (value = "/users/create") 
    public Usuario addUsuario(@RequestBody Usuario user) { 
        logger.info ("Saving user.");
        // String teste;
        // teste = user.getChefe().getId();
        //Estado estado = estadoRepository.findEstadoById(user.getEstado().getId());
        //user.setEstado(estado);
        user.setPassword(encoder.encode(user.getPassword()));

        Endereco endereco = enderecoRepository.findEnderecoById(user.getEndereco().getId());
        user.setEndereco(endereco);

        return usuarioRepository.save(user); 
        //return usuarioRepository.createUser(user);
    }
}
