package com.schizhande.websocketbackend;

import lombok.Getter;
import lombok.Setter;

import java.security.Principal;

@Setter
@Getter
public class WSPrincipal implements Principal {
    String name;

    public WSPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
