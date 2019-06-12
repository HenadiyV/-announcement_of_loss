package adver.example.adver.models;

import org.springframework.security.core.GrantedAuthority;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *09.06.2019
 */
public enum Rl implements GrantedAuthority {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
