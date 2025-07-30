package authservice.dataloader;

import authservice.model.*;
import authservice.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepo;
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    public DataLoader(RoleRepository roleRepo, UserRepository userRepo, PasswordEncoder encoder) {
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {
        var userRole = roleRepo.findByName("USER")
                .orElseGet(() -> roleRepo.save(Role.builder().name("USER").build()));

        if (userRepo.findByUsername("admin").isEmpty()) {
            var admin = User.builder()
                    .username("admin")
                    .password(encoder.encode("admin123"))
                    .roles(Set.of(userRole))
                    .build();
            userRepo.save(admin);
        }
    }
}