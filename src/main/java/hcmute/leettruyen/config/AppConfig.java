package hcmute.leettruyen.config;

import hcmute.leettruyen.entity.Author;
import hcmute.leettruyen.entity.Genre;
import hcmute.leettruyen.repository.AuthorRepository;
import hcmute.leettruyen.repository.GenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
