package br.mackenzie.mackmusic;

import br.mackenzie.mackmusic.model.*;
import br.mackenzie.mackmusic.repository.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MackmusicApplicationTests {

    //Quem faz a operacao sao os repositorios, entao vamos implementar eles

    @Autowired private ArtistRepository artistRepository; //AutoWired gerencia ciclo de vida

    @Autowired private AlbumRepository albumRepository;

    @Autowired private RatingRepository ratingRepository;

    @Autowired private UserRepository userRepository;

    @Autowired private SongRepository songRepository;

	@Test
	void contextLoads() {
	}

    @Test
    @Transactional //Garante as propriedades em questao de atomicidade, consistencia, isolamento e durabilidade, incluindo commit
    void testUserRating() { //Quem faz teste é o J unity

        //Criando user
        User user = new User(null, "Fulano", "fulano@mackenzie.com.br", "Pass123");
        userRepository.save(user); //Salva o usuario no BD, isso é derivado da interface UserRepository, gerenciados pelo JpaRepository

        Artist artist = new Artist(null, "Lana del Rey", "US", new ArrayList<>());
        artistRepository.save(artist); //Persistimos o artista

        Song song = new Song(null, "Religion", "Indie", 2015, artist, new ArrayList<>());
        songRepository.save(song);

        Rating rating = new Rating(null, user, song, 5, "Amazing song!", LocalDateTime.now()); //Agora mesmo
        ratingRepository.save(rating);

        List<Rating> userRatings = ratingRepository.findByUserId(user.getId());
        assertFalse(userRatings.isEmpty()); //Não pode estar vazia
        assertEquals(5, userRatings.getFirst().getScore()); //pega o primeiro score, até porque só vai terum

    }

    //Testando relacionamento N para N
    @Test
    @Transactional
    void testSongAndAlbum() {

        Artist artist = new Artist(null, "Lana del Rey", "US", new ArrayList<>());
        artistRepository.save(artist); //Persistimos o artista

        Album album1 = new Album(null, "Lust for Life", 2017, new ArrayList<>());
        Album album2 = new Album(null, "Chemtrails Over The Country Club", 2021, new ArrayList<>());
        albumRepository.saveAll(List.of(album1, album2)); //salvamos todos e colocamos em uma lista


        Song song1 = new Song(null, "Heroin", "Indie", 2017, artist, new ArrayList<>());
        Song song2 = new Song(null, "Wild at Heart", "Indie", 2021, artist, new ArrayList<>());
        Song song3 = new Song(null, "Love", "Indie", 2017, artist, new ArrayList<>());

        album1.addSong(song1); //LFL
        album1.addSong(song3); //LFL
        album2.addSong(song2); //Chemtrails

        albumRepository.saveAll(List.of(album1, album2));

        Album foundAlbum1 = albumRepository.findById(album1.getId()).orElse(null);
        Album foundAlbum2 = albumRepository.findById(album2.getId()).orElse(null);

        assertNotNull(foundAlbum1);
        assertNotNull(foundAlbum2);
        assertNotNull(foundAlbum1.getSongs());
        assertNotNull(foundAlbum2.getSongs());
        assertEquals(2, foundAlbum1.getSongs().size());
        assertEquals(1, foundAlbum2.getSongs().size());

    }

}
