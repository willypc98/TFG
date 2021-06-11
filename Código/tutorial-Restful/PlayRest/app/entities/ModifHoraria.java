package entities;

import java.time.LocalDateTime;

public class ModifHoraria {

    private ModifType type;
    private LocalDateTime franja;

    public ModifHoraria(){
        super();

    }

    public ModifHoraria(ModifType type,LocalDateTime franja ){
        super();
        this.type=type;
        this.franja=franja;

    }

    public ModifType getType() {
        return type;
    }

    public void setType(ModifType type) {
        this.type = type;
    }

    public LocalDateTime getFranja() {
        return franja;
    }

    public void setFranja(LocalDateTime franja) {
        this.franja = franja;
    }
}
