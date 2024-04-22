package hcmute.leettruyen.config;

import hcmute.leettruyen.repository.AuthorRepository;
import hcmute.leettruyen.repository.GenreRepository;
import hcmute.leettruyen.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class ConfigTestData {
    @Bean
    CommandLineRunner commandLineRunner(
            AuthorRepository authorRepository,
            GenreRepository genreRepository,
            UserRepository userRepository){
        return args -> {
//            Genre adventure = Genre.builder()
//                    .name("adventure")
//                    .color("red")
//                    .build();
//            Genre horror = Genre.builder()
//                    .name("horror")
//                    .color("black")
//                    .build();
//            genreRepository.saveAll(List.of(adventure,horror));
//            Author Ti = Author.builder()
//                    .name("Ti' ne`")
//                    .avatarLink("avt1")
//                    .build();
//            Author Teo = Author.builder()
//                    .name("Teo` ne`")
//                    .avatarLink("avt2")
//                    .build();
//            authorRepository.saveAll(List.of(Ti,Teo));
//            User test1 = User.builder()
//                    .email("Teo@gmail.com")
//                    .username("Teo`")
//                    .password("123456")
//                    .coin(22)
//                    .introduction("Day la Teo")
//                    .active(true)
//                    .build();
//            userRepository.save(test1);
        };
    }
}
