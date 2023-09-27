package com.crpreparacoes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="status")
public class Status{

    public enum StatusEnum {
        WAITING_APPROVAL(1L, "WAITING_APPROVAL", "Aguardando aprovação"),
        CANCELED(2L, "CANCELED", "Cancelado"),
        WAITING_PARTS(3L, "WAITING_PARTS", "Esperando peças"),
        IN_PRODUCTION(4L, "IN_PRODUCTION", "Em produção"),
        FINISHED(5L, "FINISHED", "Finalizado");

        public final Long id;
        public final String code;
        public final String description;
        StatusEnum(Long id, String code, String description) {
            this.id = id;
            this.code = code;
            this.description = description;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    public Status(StatusEnum statusEnum){
        this.id = statusEnum.id;
        this.code = statusEnum.code;
        this.description = statusEnum.description;
    }
}
