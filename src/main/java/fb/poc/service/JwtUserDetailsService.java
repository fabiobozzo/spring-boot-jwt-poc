package fb.poc.service;

import fb.poc.model.User;
import fb.poc.model.UserInput;
import fb.poc.repo.UserRepository;
import java.util.ArrayList;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

  private static final String HARDCODED_USERNAME = "poc";
  private static final String HARDCODED_PASSWORD = "$2a$10$jIvQQ9gE/drF11txMVGFoecNIGF3b1rFhR6ME2OzbHSP8CTyHhV0K";

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return Optional.ofNullable(userRepository.findByUsername(username)).map(
        u -> new org.springframework.security.core.userdetails.User(
            u.getUsername(),
            u.getPassword(),
            new ArrayList<>()
        )
    ).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
  }

  public User save(UserInput user) {
    return userRepository.save(new User(user.getUsername(), passwordEncoder.encode(user.getPassword())));
  }

}
