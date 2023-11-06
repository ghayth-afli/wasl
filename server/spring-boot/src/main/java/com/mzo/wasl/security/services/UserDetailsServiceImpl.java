package com.mzo.wasl.security.services;

<<<<<<< HEAD
<<<<<<< HEAD
=======
import com.mzo.wasl.models.Profile;
>>>>>>> parent of b170955 (Revert "Added switch functionality")
import com.mzo.wasl.models.User;
import com.mzo.wasl.repositories.ProfileRepository;
import com.mzo.wasl.repositories.UserRepository;
import jakarta.transaction.Transactional;
=======
import com.mzo.wasl.model.User;
import com.mzo.wasl.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
>>>>>>> parent of e3f7f19 (Merge branch 'main' of https://github.com/belhajManel/wasl)
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProfileRepository profileRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return UserDetailsImpl.build(user);
    }

}
