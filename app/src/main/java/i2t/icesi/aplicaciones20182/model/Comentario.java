package i2t.icesi.aplicaciones20182.model;

import java.util.HashMap;

public class Comentario {

    private String id;
    private String texto;
    private HashMap<String, String> likes;

    public Comentario(){
        likes = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public HashMap<String, String> getLikes() {
        return likes;
    }

    public void setLikes(HashMap<String, String> likes) {
        this.likes = likes;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Comentario){
            Comentario c = (Comentario) obj;
            return this.getId().equals(c.getId());
        }
        return false;
    }
}
