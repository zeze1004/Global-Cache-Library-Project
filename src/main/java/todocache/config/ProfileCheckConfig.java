package todocache.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class ProfileCheckConfig {

    @Autowired
    private Environment environment;

    @PostConstruct
    public void checkActiveProfiles() {
        String[] profiles = environment.getActiveProfiles();
        for (String profile : profiles) {
            System.out.println("Active profile: " + profile);
        }
    }
}
