package pwr.ite.bedrylo.lab03.client.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class TreeDto {
    private UUID id;
    private String name;
    private float diameter;
    private RegistrationDto registration;
}