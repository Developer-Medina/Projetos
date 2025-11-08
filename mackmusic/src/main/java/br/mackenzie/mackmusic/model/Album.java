package br.mackenzie.mackmusic.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "album") //Aqui colocamos o nome da tabela
@Setter //Cria automaticamente os getters/setters
@Getter //Cria automaticamente os getters/setters
@NoArgsConstructor //Cria o construtor
@AllArgsConstructor //Cria o construtor

public class Album { //Essa classe é uma tabela, logo, se torna uma entidade


    @Id
    @GeneratedValue(strategy = GenerationType.UUID) //Gera uma chave única para o ID baseada em timestamp
    private UUID id;

    private String name;

    private int releaseYear;

    @ManyToMany(mappedBy = "albums", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST) //Muitos para muitos
    private List<Song> songs = new ArrayList<>();

    public void addSong(Song song) {
        this.songs.add(song);
        song.getAlbums().add(this);
    }

}
