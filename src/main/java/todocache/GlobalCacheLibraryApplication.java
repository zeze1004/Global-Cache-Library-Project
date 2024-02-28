package todocache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

import todocache.config.CacheResourceConfig;

@Import(CacheResourceConfig.class)
@SpringBootApplication
public class GlobalCacheLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlobalCacheLibraryApplication.class, args);
	}

}
