package hcmute.leettruyen.response;

import hcmute.leettruyen.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreResponse {
    private String name;
    private String color;
}
